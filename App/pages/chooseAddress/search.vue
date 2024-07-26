<template>
	<view class='app'>
		<view class="search-wrap">
			<view class="city" @click="navTo('cityList?city='+city)">
				<image class="icon" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAC70lEQVRIS8VW7VXcMBDctQsAKshRQYRcAJcKIBUAFQAVhFSQowKggpAKuBTgRVcBRwWQAiTljZ/Ek235436hf3e2NTuzO7vL9EmHPwmXZgMrpRZlWZ4Q0dJ7v2TmfQTtvX9n5jURra21D8aY9zlkJoGVUvtFUVwS0VUEG7oYQRDRyjl3OxXAKLBSSpVleUdEKrDbENG9c84Q0TYEsCiKAs/Pmflr+M9Yay+MMXgvewaBAVoUxVNg+c9ae2qMgaSDp6qqUwRGRHtg75w7MsbEAFvfZYEhb1mWT2Dqvd8455ZT0sVbQ2rWgT2Yf8t9mwXWWt8w8w8iAtNF90NcHmVFYLnnZVmCKZj/FJGbrkw94BDxCyT23l+ICKRrTnj2i5nP04u898j7dRqA1ho5vwuSH3aD6wEnH2xEpCmqCBrlhxKwT3i0BDMi6smqtTZQpksA3/WAq6p6JKIT7/2tiFxFYK31ipkvvfd/nXMotMavQYVHZj4e+oaI/tR1jcL7ODnGKIzjUBQfVVxVFYD2rLUHAzl9g6wicpCotIRKCFZEoMwo8JaZv6TAoWu95C5IFIkBI5+NhZRSEbiVtqzUWuse42CvN+SxruujnJFjPlNFEuBZjBvgrg201j0lMpK+isgiUaGx5Vypo4cf6rr+sE3HHt9jFwOroih+5+xXVRWseJbzcs7HMS+tQgGLeFFOaiJqBRrejwWJ7tVqt0Odq5E15z8wD5OqGQjoXJhIaaPB/4lCLflj0KMt03u/FZHDAYajf8eamN0yky4Ve+21iKx2AZ/q9Vk7JRXZ9Fr8ttZivA3O1jSoMMOfQxpavX60gaQPk/a5DbN1dK0J7fOZmWGpXpucDTx3tibpmT3D5+xcWH9ghTiBeitNZ0XCDFdDm8doVXcLKVzcgIf5OtRAXsPkmqyHScZJW8S2GVca+LepdGZuRueuK9Js4CSPADxLVcEcds7dzN3LRu005ltskynjuq6xPOx0dmK8080TL38a8H+fVUE94KTW6AAAAABJRU5ErkJggg=="></image>
				<text>{{ city }}</text>
				<image class="c-icon" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAvElEQVQ4T+3TwQ3CMAwF0G9lAQbgwgRlhEgegBEQm3UEFrCUEWACLgzAAlaQpQa1EJxK9MChvaVKXp3vmrDwQwt7WMHfE31lGGPchBBuAM4icppDM3MP4KCqu5TSw85MmsLMFyLqcs59CzWMiI4556uI7EsBE3CoMrXQMaaqsVT3UaG9aKEeVgU9tIV9BWuo5V0ye7/muIHupJTrA+iICNYAD3MrLF8d0LutVXU7bkDt11pnec7A+Xv+P8Mn98h9FeV7S88AAAAASUVORK5CYII="></image>
			</view>
			<input class="input" maxlength="30" @input="bindConfirm" focus auto-focus placeholder="请输入地址关键字搜索" @confirm='bindConfirm'></input>
		</view>
		
		<view class="addr-list">
			<view class="addr-item b-b" v-for="(item, index) in list" :key="index" @click="confirm(item)">
				<text class="title">{{item.title}}</text>
				<text class="addr">{{item.address}}</text>
			</view>
		</view>
	</view>
</template>

<script>
	const QQMapWX = require('./js/qqmap-wx-jssdk.min.js')
	var qqmapsdk = new QQMapWX({
		key: 'FALBZ-J2G3I-ZY5GX-5ATUZ-GHOOZ-YVFAR'
	})
	export default {
		data() {
			return {
				city: '',
				keyword: '',
				list: []
			}
		},
		onLoad(options) {
			uni.setNavigationBarTitle({
				title: '搜索地址'
			})
			this.city = options.city || '';
		},
		methods: {
			//选择地址
			confirm(item) {
				const pages = getCurrentPages()
				const prePages = pages[pages.length - 2].$vm
				
				prePages.tempAddress = item;
				prePages.position = {
					longitude: item.location.lng,
					latitude: item.location.lat,
				}
				prePages.map = {
					longitude: item.location.lng,
					latitude: item.location.lat,
				}
				// #ifdef H5 || MP-WEIXIN
				prePages.getAddressList(1); //h5没触发地图regionchange事件，需要手动调用获取新地址列表。微信小程序开发工具自动触发，真机不触发，同样需要调用一下。
				// #endif
				uni.navigateBack({
					delta: 1
				})
			},
			//搜索地址
			searchList() {
				qqmapsdk.getSuggestion({
					keyword: this.keyword,
					policy: 1, //默认0，常规策略  policy=1：本策略主要用于收货地址、上门服务地址的填写，
					page_size: 20, //每页条目数，最大限制为20条，默认值10
					page_index: 1,
					region: this.city || '全国',
					success: res=> {
						this.list = res.data;
					},
					fail: err => {
						this.list = [];
					}
				})
			},
			bindConfirm(e) {
				this.keyword = e.detail.value;
				this.searchList()
			},
			navTo(url){
				uni.navigateTo({
					url
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	view{
		box-sizing: border-box;
	}
	.app{
		padding-top: 100rpx;
	}
	.search-wrap{
		position: fixed;
		left: 0;
		top: var(--window-top);
		z-index: 90;
		display: flex;
		background-color: #fff;
		align-items: center;
		width: 100%;
		height: 100rpx;
		padding: 0 30rpx;
		background-color: #fff;
		
		.city{
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: 30rpx;
			color: #333;
		}
		.icon{
			width: 34rpx;
			height: 34rpx;
			margin-right: 4rpx;
		}
		.c-icon{
			width: 22rpx;
			height: 22rpx;
			margin-left: 4rpx;
		}
		.input{
			flex: 1;
			margin-left: 16rpx;
			padding: 0 28rpx;
			height: 70rpx;
			line-height: 70rpx;
			font-size: 30rpx;
			color: #333;
			background-color: #f5f5f5;
			border-radius: 100rpx;
		}
	}
	.addr-list{
		background-color: #fff;
		
		.addr-item{
			display: flex;
			flex-direction: column;
			padding: 24rpx 30rpx;
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
