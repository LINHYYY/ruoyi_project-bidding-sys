<template>
	<view>
		<view class="list">
			<view class="keyList" v-for="(keyItem, keyIndex) in renderList" :key="keyIndex">
				<view :id="'keyword' + keyItem.key" class="keyword">
					<text>{{ keyItem.key === 'AAA' ? '热门地区' : keyItem.key }}</text>
				</view>
				<view class="item b-b" v-for="(item, index) in keyItem.list" :key="index" @click="chooseCity(item)">
					<text>{{ item.label }}</text>
				</view>
			</view>
		</view>

		<view class="key-list column" @click.stop.prevent="stopPrevent">
			<view 
				class="key-item" 
				:class="{active: index === keywordCurrent}"
				v-for="(item, index) in renderList" 
				:key="index"
				 @click="onKeywordClick(index)"
			>
				<text>{{ item.key === 'AAA' ? '#' : item.key }}</text>
			</view>
		</view>

		<view class="key-show center" :class="{show: keyChanged}">
			<text>{{ keywordCurrentName }}</text>
		</view>
	</view>
</template>

<script>
	import getFirstLetter from './js/getFirstLetter.js'
	import cityData from './js/city'
	const _anchorList = [];
	let _onKeywordClicking = false;
	let _timer = 0;
	export default {
		data() {
			return {
				keyChanged: false,
				keywordCurrentName: '',
				keywordCurrent: 0, //当前索引
				renderList: [],
				list: [],
			}
		},
		onPageScroll(e) {
			if (_onKeywordClicking) {
				return;
			}
			const top = e.scrollTop;
			for (let i = 0; i < _anchorList.length; i++) {
				if (top > _anchorList[i]) {
					this.keywordCurrent = i;
				}
			}
		},
		watch: {
			keywordCurrent(val) {
				this.keywordCurrentName = this.renderList[val].key;
				if (_timer) {
					clearTimeout(_timer);
				}
				this.keyChanged = true;
				_timer = setTimeout(() => {
					this.keyChanged = false;
				}, 500)
			}
		},
		onLoad(options) {
			this.initList();
		},
		methods: {
			chooseCity(item){
				const pages = getCurrentPages();
				const prePage = (pages[pages.length - 2]).$vm;
				prePage.city = item.label;
				prePage.searchList();
				uni.navigateBack();
			},
			//初始化列表
			initList() {
				const list = cityData;
				const tempData = {};
				list.forEach(item => {
					let key = getFirstLetter.getLetter(item.label).firstletter;
					if (!tempData[key]) {
						tempData[key] = [];
					}
					tempData[key].push(item);
				})
				//排序用
				const tempKeyList = [];
				for (let key in tempData) {
					tempKeyList.push(key);
				}
				tempKeyList.sort();
				const renderList = [];
				tempKeyList.forEach(keyword => {
					for (let key in tempData) {
						if (key == keyword) {
							renderList.push({
								key,
								list: tempData[key]
							})
						}
					}
				})
				this.renderList = renderList;
				//生成右侧索引
				setTimeout(() => {
					this.calcAnchor();
				}, 500)
			},
			//计算锚点高度
			calcAnchor() {
				const list = this.renderList;
				let size = {};
				const placeFillHeight = this.systemInfo.navigationBarHeight + this.systemInfo.statusBarHeight;

				list.forEach(async item => {
					let size = await this.getElSize('keyword' + item.key);
					item.top = size.top;
					_anchorList.push(size.top);
				})
			},
			//右侧索引点击
			onKeywordClick(index) {
				_onKeywordClicking = true;
				setTimeout(() => {
					_onKeywordClicking = false;
				}, 500)
				this.keywordCurrent = index;
				uni.pageScrollTo({
					scrollTop: this.renderList[index].top
				})
			},
			//获得元素的size
			getElSize(id) {
				return new Promise(res => {
					let el = uni.createSelectorQuery().select('#' + id);
					el.boundingClientRect(data => {
						res(data);
					}).exec();
				});
			}
			
		}
	}
</script>

<style>
	page {
		background-color: #f7f7f7;
	}
</style>
<style scoped lang="scss">
	.key-show {
		position: fixed;
		left: 50%;
		top: 50%;
		z-index: 95;
		transform: translate(-50%, -50%);
		width: 80rpx;
		height: 80rpx;
		background-color: #2979ff;
		border-radius: 100rpx;
		font-size: 40rpx;
		font-weight: 600;
		color: #fff;
		opacity: 0;

		&.show {
			opacity: 1;
		}
	}

	.keyList {
		position: relative;

		.keyword {
			position: sticky;
			left: 0;
			top: var(--window-top);
			z-index: 95;
			padding-top: 6rpx;
			padding-left: 30rpx;
			width: 100%;
			height: 66rpx;
			line-height: 60rpx;
			font-size: 28rpx;
			color: #333;
			font-weight: 700;
			background-color: #f7f7f7;
		}

		.item {
			padding-left: 30rpx;
			line-height: 80rpx;
			font-size: 28rpx;
			color: #333;
			background-color: #fff;

			&:after {
				left: 30rpx;
			}

			.cn-name {
				margin-left: 12rpx;
				color: #666;
			}
		}
	}

	/* 右侧索引 */
	.key-list {
		position: fixed;
		right: 0;
		top: 50%;
		z-index: 96;
		padding-right: 20rpx;
		padding-left: 40rpx;
		transform: translateY(-50%);

		.key-item {
			width: 40rpx;
			height: 40rpx;
			margin-bottom: 2rpx;
			text-align: center;
			line-height: 40rpx;
			font-size: 26rpx;
			color: #333;
			border-radius: 100rpx;
		}

		.active {
			background-color: #2979ff;
			color: #fff;
		}
	}
</style>
