<template>
	<view class='app'>
		<!-- 当前选择地址 -->
		<view class="current-address row b-b">
			<view class="left">
				<view class="row">
					<text class="red">[当前]</text>
					<text class="title">{{currentAddress.title}}</text>
				</view>
				<text class="addr clamp">{{currentAddress.address}}</text>
			</view>
			<!-- 搜索图标 -->
			<image
				class="ser-icon" 
				src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAENElEQVRoQ+2a/1XcMAzHlQkKExQmaJmAMgHtBJQJqkzQMsGpExQm6HWCHhMUJihMUJjAfd97znuucc6Sklxf4PwXjzixPtYPS/I19MJG88J4aQf83DW+0/BOw89sByY3aWbea5rmDREdhBAO0v1rmmYVQrgXkbtt7eskwMz8lojOiOgdEeHv2nggolXTNMvFYnFVmzzk+ajAbduehhA4gnrlArwQ0VcRwd+jjlGAo0YXA0FzsDW4iFyMSTwYmJk/E9GXDULdEtGSiOCnqa/uRXOHX78nolc937ghog9j+bkbGMGIiKDVjwVB7+MmLLVmGa0E7gDfL2n7REQAP2i4gCPsz0JAWoOKyKVXKmaGxmExJfDzId+GTF5gwCICp+MHtK3VaG1DmBlmjo1LTR1+PUjTZmBmRgT9lAl8ISKb/LjGV3wezRz+/zqZAOhD78aagOOuf8+kaxFKXUSKl6L7INilml6JyIni9SdT1MBx4V/ImJKvXIlIKWh5ZOl9J2p6lUG7/NkCDJPFEdSNWxHRZFGjwDMzIjhOhW64TFsFHLX7m4hwFHUDwQO7vrXBzFjvOFnQrGUtMMz2W7LQtYjkUXpycGbGmjghunEnIoeWhbXAiJSnyYePxkgCLIJ2c5kZyQeqr26YZKkCR3P+kyyAcu6fMs8juPedgi+bjkQNcG5GW4nMfRsSMzHEk26Y3EsDnEdnJPIw8f82MrN+EJF9rTAaYKR3aV679eicw+TRWkSqHN03qhMLR8G+N63TaqE2j5ndSjADW3azJrj3OTPnbqa2urkC5wUMiglVI1ADnJ/B6t30arD23tQ+7DafmuDe51MD52nlpOVgbRMKidDo5zCyqvSgvxGRo5pgUz0v1ORo56KSUo2qD+MrzIyAkHYd1EFCJYVh0pAjCctogXM/NuWvBp6NU2NaiSZEV6Y+ikhaslaX0gLnZo3iG1WK6iioSqGcUNCuOa9XAUezzrMb82JKruK0QtHwiHaTNeuzAOdahmDmjoMHuqcP7nIrNXDUcu7L+LepAHcCo1OKPnU3XNpVB61UyELHYXBzfNMmMDNaS3ln1L3JJg1HLcO00WbJbwSQkLivWHLoDdc5gxIfM3CERns27xPjEYAh0KB73bZtz0IIKBDyI8fctMs30gVcgXZfaMeuJHrfmzqilyJy7okFLh/O/BnmjWoq7SKmU5bxdxzId59cdcbffxyHEBCQAKltDrqh3RrOwEvRu08JAAeYJkO6jgGrdH3qgh4FOAlmffe6Vgt8cs9cyLLWMcNq3qMBd0QxI0L1AjNNCw4NNO6YcRlZvMIZA3p04IKPd75ZCkTd7z5w/am6pxoKPSmwRqWeOUOgZwkcY0ZezODf1WbAbIH7oGtt5FkDF6Cr/a3ZA0fodWDU5PLPAtgS+HbAlt2a49ydhueoNYvMOw1bdmuOc/8C4xLETHORTG0AAAAASUVORK5CYII="
				@click="navTo('search?city='+curCity)"
			></image>
		</view>
		<!-- 补充详细地址 -->
		<view class="confirm-wrap row">
			<!-- <input class="input" placeholder="补充详细地址：楼号、门牌等(选填)" placeholder-class="placeholder" v-model="room" @confirm='submit'></input> -->
			<view class="btn" @click="submit">确定</view>
		</view>
		<map 
			id="map"
			class="map"
			:scale="15" 
			:show-location="true" 
			:longitude="map.longitude" 
			:latitude="map.latitude"
			@regionchange="onRegionchange"
		>
			<cover-image class="map-center-icon" src="https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/5bfbd58b18200d8e16f6b17a02e6ed9.png"></cover-image>
		</map>
		<!-- 结果集 -->
		<scroll-view scroll-y class="addr-list-scroll">
			<view class="addr-list">
				<view class="addr-item b-b row" v-for="(item, index) in list" :key="index" @click="chooseAddress(index)">
					<view class="left">
						<text class="title">{{item.title}}</text>
						<text class="addr">{{item.address}}</text>
					</view>
					<icon type="success" color="#2979ff" size="18" class="icon_circle" v-if='checked === index' />
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	const QQMapWX = require('./js/qqmap-wx-jssdk.min.js')
	const qqmapsdk = new QQMapWX({
		key: 'FALBZ-J2G3I-ZY5GX-5ATUZ-GHOOZ-YVFAR'
	})
	let _mapCtx = null;
	export default {
		data() {
			return {
				curCity: '',
				mapStatus: 1,
				map: {
					longitude: 116.39742,
					latitude: 39.909,
				},
				room: '', //补充地址 门牌号、房间号
				list: [], //地址列表
				checked: 0, //当前选择选择地址下标
				tempAddress: null, //编辑或者搜索到地址时，需要手动将结果添加到poi集
			}
		},
		computed: {
			currentAddress(){
				if(this.list.length === 0){
					return {};
				}
				return this.list[this.checked];
			}
		},
		created() {
			_mapCtx = uni.createMapContext('map');
		},
		onNavigationBarButtonTap() {
			this.submit();
		},
		async onLoad(options) {
			//编辑地址时参数
			let optionData = options.data;
			let lng, lat;
			if(optionData){
				//编辑地址时
				this.tempAddress = JSON.parse(optionData);
				this.room = this.tempAddress.room;
				lng = this.tempAddress.location.lng;
				lat = this.tempAddress.location.lat;
			}else{
				//没有传坐标时获取用户当前定位
				const userLocation = await this.getLocation();
				lng = +userLocation.longitude;
				lat = +userLocation.latitude;
			}
			this.map = {
				longitude: lng,
				latitude: lat
			}
			this.position = {
				longitude: lng,
				latitude: lat
			}
			this.getAddressList(1)
		},
		methods: {
			//确定选择
			submit() {
				const {currentAddress, room} = this;
				const {ad_info, address, location, title} = currentAddress;
				// this.$util.prePage().setAddress(Object.assign({}, {
				// 	ad_info, address, location, title,room
				// }));
				uni.$emit('changeAddressConfig', address,location.lng,location.lat);
				uni.navigateBack();
			},
			//获取poi列表  
			getAddressList(s = 0) {
				//在ios下防止搜索返回时多次加载地址列表的问题
				if(this.isSetTempAddress === 1){
					return;
				}
				qqmapsdk.reverseGeocoder({
					location: {
						latitude: this.position.latitude,
						longitude: this.position.longitude
					},
					get_poi: 1,
					poi_options: "page_size=30;page_index=1", 
					success: res=> {
						res.result.pois.forEach(poi=>{
							if(!poi.ad_info){
								poi.ad_info = {
									adcode: poi.adcode,
									city: poi.city,
									district: poi.district,
									province: poi.province
								}
							}
						})
						//有搜索结果时，手动追加到列表顶部
						if(this.tempAddress){
							if(this.tempAddress.title != res.result.pois[0].title){
								if(!this.tempAddress.ad_info){
									this.tempAddress.ad_info = {
										adcode: this.tempAddress.adcode,
										city: this.tempAddress.city,
										district: this.tempAddress.district,
										province: this.tempAddress.province
									}
								}
								res.result.pois.unshift(this.tempAddress);
							}
							this.tempAddress = null;
							this.isSetTempAddress = 1;
							setTimeout(()=>{
								this.isSetTempAddress = 0;
							}, 500)
						}
						if (s) {
							const ad_info = res.result.pois[0].ad_info;
							this.curCity = ad_info.city || '';
							res.result.pois[0].select = 1
							this.list = res.result.pois;
							this.checked = 0;
						} else {
							this.list = res.result.pois;
						}
					},
					fail: err=> {
						console.log(err)
					}
				})
			},
			//地图区域改变
			onRegionchange(e) {
				clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					//h5 end  安卓 regionchange
					if (e.type === 'end' || e.type === 'regionchange') {
						_mapCtx.getCenterLocation({
							success: res => {
								this.position = {
									latitude: res.latitude,
									longitude: res.longitude
								}
								if (this.mapStatus == 1) { // 防止地图点击时 进行多次加载
									this.getAddressList(1)
								}
							},
							fail: err=>{
								console.log(err);
							}
						})
					}
				}, 200)
			},
			//地址列表点击
			chooseAddress(index) {
				let list = this.list
				this.map = {
					longitude: list[index].location.lng,
					latitude: list[index].location.lat
				}
				this.checked = index;
				this.mapStatus = 0;
				//防止ios下触发多次加载列表的bug
				clearTimeout(this.mapStatusTimer);
				this.mapStatusTimer = setTimeout(()=>{
					this.mapStatus = 1;
				}, 1000)
			},
			//获取用户定位
			getLocation (){
				return new Promise(resolve=> {
					// #ifndef H5
					uni.getLocation({
						type: 'gcj02',
						success: res=> {
							resolve({
								longitude: res.longitude,
								latitude: res.latitude
							})
						},
						err: err=> {
							console.log(err)
							resolve({
								longitude: 116.39742,
								latitude: 39.909,
							})
						},
						complete(res) {
							console.log(res);
						}
					})
					// #endif
					// #ifdef H5
					//h5的定位没有写，这里直接默认天安门，可以根据项目需求使用对应jssdk获取定位
					resolve({
						longitude: 116.39742,
						latitude: 39.909,
					})
					// #endif
				})
			},
			navTo(url){
				uni.navigateTo({
					url
				})
			}
		}
	}
</script>

<style>
	page {
		height: 100%;
		background: #F6F6F6;
	}
</style>
<style scoped lang="scss">
	.app{
		display: flex;
		flex-direction: column;
		height: 100%;
		overflow: hidden;
	}
	.row{
		display: flex;
		align-items: center;
	}
	.b-b{
		position: relative;
		
		&:after{
			position: absolute;
			z-index: 3;
			left: 0;
			top: auto;
			bottom: 0;
			right: 0;
			height: 0;
			content: '';
			transform: scaleY(.5);
			border-bottom: 1px solid #e5e5e5;
		}
	}
	.clamp {
		/* #ifdef APP-PLUS-NVUE */
		lines: 1;
		/* #endif */
		/* #ifndef APP-PLUS-NVUE */
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		display: block;
		/* #endif */
	}
	.current-address{
		height: 120rpx;
		padding: 0 24rpx;
		background-color: #fff;
		
		.left{
			width: 634rpx;
		}
		.red{
			flex-shrink: 0;
			margin-right: 6rpx;
			font-size: 28rpx;
			color: #2979ff;
			line-height: 36rpx;
		}
		.title{
			font-size: 28rpx;
			color: #333;
			font-weight: bold;
			line-height: 36rpx;
		}
		.addr{
			width: 700rpx;
			margin-top: 12rpx;
			font-size: 24rpx;
			color: #909399;
			line-height: 1.4;
		}
		.ser-icon{
			flex-shrink: 0;
			width: 66rpx;
			height: 66rpx;
			padding: 12rpx 4rpx 12rpx 20rpx;
		}
	}	
	.confirm-wrap{
		height: 88rpx;
		padding: 0 24rpx;
		background-color: #fff;
		
		.input{
			flex: 1;
			font-size: 28rpx;
			color: #303133;
		}
		.btn{
			width: 700rpx;
			padding: 0 25rpx;
			font-size: 26rpx;
			color: #fff;
			height: 60rpx;
			line-height: 60rpx;
			background-color: #2979ff;
			border-radius: 100rpx;
			text-align: center;
		}
	}
	.map{
		width: 750rpx;
		height: 700rpx;
		
		.map-center-icon{
			position: absolute;
			left: 339rpx;
			top: 264rpx;
			width: 72rpx;
			height: 72rpx;
		}
	}
	.addr-list-scroll{
		flex: 1;
		overflow: hidden;
		
		.addr-list{
			background-color: #fff;
		}
		.addr-item{
			padding: 24rpx;
		}
		.left{
			flex: 1;
			display: flex;
			flex-direction: column;
			padding-right: 50rpx;
		}
		.title{
			font-size: 28rpx;
			color: #303133;
		}
		.addr{
			margin-top: 10rpx;
			font-size: 24rpx;
			color: #909399;
			line-height: 1.4;
		}
	}
</style>
