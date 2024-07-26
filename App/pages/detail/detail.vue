<template>
    <view class="u-margin-left-20 u-margin-right-20">
		<u-navbar :is-back="true" title="工程详情" :border-bottom="false"
		back-text="返回" :custom-back="goHome" ></u-navbar>
		<u-image width="100%" height="800rpx" :src="src"></u-image>
       <!-- <view>
            <u-image :list="swiperlist" :height="750" mode="number" effect3d effect3d-previous-margin="20" :interval="5000" @click="clickImg"></u-image>
        </view> -->
		<view class="item u-margin-top-5">
					<view class="item-price">工程名称：{{ProdectById.projectName}}</view>
		</view>
		
		<!-- 小程序通讯 -->
		
		<u-gap height="1" bg-color="#f8f8f8"></u-gap>
		<view style="position: relative;">
			<u-card title="工程介绍" :border="false" :head-border-bottom="false" padding="0" title-size="35">
				<view class="u-padding-top-45" slot="body">
					<view class="u-flex" style="justify-content: space-between;">
						<view class="item">
							<view>项目编号：{{ProdectById.id}}</view>
							<view>预计项目启动日期：{{ProdectById.startDate}}</view>
							<view>预计项目结束日期：{{ProdectById.endDate}}</view>
							<view>项目预算：{{ProdectById.budgetTotal}}</view>
							<view>项目简述：{{ProdectById.briefDesc}}</view>
							<!-- <view>项目简述：{{ProdectById.imageUrl}}</view> 
							<image src="'D:/ruoyi/uploadPath'+'ProdectById.imageUrl'"  mode="widthFix" style="width: 100%;"></image>
						-->
						</view>
					</view>
				</view>
			</u-card>
		</view>
		<u-gap height="1" bg-color="#f8f8f8"></u-gap>
		<view style="position: relative;">
		</view>

		
			
		<u-gap height="20" bg-color="#f8f8f8"></u-gap>
		<view style="position: relative;padding-bottom: 150rpx;">
			<u-card title="项目负责人" :border="false" :head-border-bottom="false" padding="0" title-size="36">
				<view class="u-padding-top-45" slot="body">
					<view class="u-flex">
					    <view class="u-m-r-20">
					        <u-avatar :src="room.agentAvatar" size="80"></u-avatar>
							
					    </view>
					    <view class="u-flex-1">
							<view class="u-font-16 u-p-b-40">{{ProdectById.managerId}}</view>
					        <!-- <view class="u-font-12 u-p-b-10">{{user.agentPhone}}</view> -->
					    </view>
					</view>
					<view class="arrow-right" @click="clickItem">
						<view class="phone">
							<u-icon name="phone" class="u-p-l-10" color="#d1d1d1"
							size="40"></u-icon>
						</view>
					</view>
				</view>
			</u-card>
			<view class="item">
				<u-divider>xx工程为您服务</u-divider>
			</view>
			
		</view>		
		<view class="navigation">
			<view class="left">
				<view class="item" @click="heartHouse">
					<u-icon name="heart" :size="40" v-if="!room.heart"></u-icon>
					<u-icon name="heart-fill" color="#ff9900" :size="40" v-if="room.heart"></u-icon>
					<view class="text">收藏</view>
				</view>
				<button type="default" open-type="share"  class="clearBtn"
				style="font-size: 14px;color: #6a6a6a;">
					<view class="item">
						<u-icon name="zhuanfa" :size="40"></u-icon>
						<view class="text">分享</view>
					</view> 
				</button>
			</view>
			<view class="right" @click="clickItem">
				<view class="btn">参与项目</view>
			</view>
		</view>
    </view>
</template>

<script>
	
	import config from "@/common/config.js" // 全局配置文件
	import { findProduct } from "../index/js/index.js"
    export default {
        data() {
            return {
				src: '/static/img/index/swiper/swiper1.jpg',
				actionSheetShow: false,
				room: {
					villageName:'',
					houseNum:'',
					houseHall:'',
					toiletNum:'',
					roomType:'',
					direction:'',
					price:'',
					introduce:'',
					heart:false,
				},
				village: {
					year:'2008-09-07',
					type:'天宇广场',
					day:'180',
					green:'34',
				},
                swiperlist: [],
				index:0,
				ProdectById:{}
				
            }
        },
		onLoad: function (option) {
			console.log(option);
			// let houseId = option.houseId //上个页面传递的参数。
			// this.houseId =houseId
			this.index=option.productId
			// 渲染当前房源信息
			this.findProdectById(this.index);
			
		},
        methods: {
			async findProdectById(index){
				const res = await findProduct();
				this.ProdectById=res.data.rows[index];
				
				console.log(res.data.rows);
				console.log("aaa");
				console.log(res.data.rows[index]);
				// console.log(this.$data.ProdectById);
			},
			heartHouse(){
				// 判断是否有userId
				let lifeData = uni.getStorageSync('lifeData');
				let vuex_user = lifeData.vuex_user
				if(!vuex_user){
					// 没有userId 则跳转到登录
					return uni.reLaunch({
						url:'../login/login'
					})
				}
				// 收藏
				let url = "api/houseApi/saveHeart";
				this.$u.post(url, {
					heart: this.room.heart,
					houseId: this.room.id,
					userId: vuex_user.user.userId,
				}).then(result => {
					this.room.heart = !this.room.heart
					this.$mytip.toast(result.msg)
				});
			},
			goHome(){
				uni.reLaunch({
					url:'../index/index'
				})
			},
            clickItem() {
                //拨打固定电话
              uni.reLaunch({
              	url:'../detail/preHouse'
                });
            },
			clickImg(index){
				let imgArr = this.swiperlist.map(val=>{
					return val.image
				})
				console.log(imgArr);
				 // 预览图片
				uni.previewImage({
					current: index,
					urls: imgArr
				});
			},
			clickMap(e){
				uni.openLocation({
					longitude: Number(this.longitude),
					latitude: Number(this.latitude),
					name: this.room.villageName,
					address: this.room.address
				})
			},
			sendProjectId() {
			      EventBus.$emit('projectId', this.ProdectById.id);
			    },
        }
    }
</script>

<style>
    page {
        background-color: rgb(255, 255, 255);
    }
</style>


<style lang="scss" scoped>
	map {
		width: 100%;
		height: 350rpx;
	}
	
	.item{
		padding: 25rpx;
		line-height: 80rpx;
		margin-top: -30rpx;
	}
	
    .item-title {
        font-size: 42rpx;
        color: $u-main-color;
        font-weight: bold;
    }

    .item-price {
        font-weight: normal;
        font-size: 45rpx;
        color: $u-type-warning;
    }

    .item-desc {
        font-weight: normal;
        font-size: 36rpx;
        color: $u-tips-color;
    }
	
	.arrow-right{
		position: absolute;
		top: 80rpx;
		right: 28rpx;
		font-weight: normal;
		font-size: 28rpx;
		color: $u-tips-color;
		.phone{
			width: 90rpx;height:90rpx;
			border-radius: 100%;
			box-shadow: 0px 1px 20px 0px rgba(0,0,0,0.1);
			display: flex;
			justify-content: center;
			align-items: center;
		}
	}
	
	.wayClass{
		color: #606266;
		padding: 30rpx;
		line-height: 50rpx;
	}

	.clearBtn{
		margin:0;
		padding: 0;
		line-height: 1;
		background-color: #FFFFFF;
	}
	
	.clearBtn::after{
	    position: unset !important;
	    border: unset;
	}
	
	.navigation {
		display: flex;
		justify-content: center;
		align-items: center;
		position: fixed;
		left: 0;
		right: 0;
		bottom: 0;
		padding: 0rpx 15rpx;
		background-color: #ffffff;
		box-shadow:0 -1px 1px 0 rgba(0, 0, 0, 0.05);
		z-index: 9999;
		.left {
			display: flex;
			justify-content: center;
			align-items: center;
			margin-top: 38rpx;
			font-size: 14px;
			color: #6a6a6a;
			.item {
				display: flex;
				.text{
					margin-left: 4rpx;
				}
			}
		}
		.right {
			display: flex;
			font-size: 28rpx;
			align-items: center;
			.btn {
				line-height: 66rpx;
				margin-left: 30rpx;
				padding: 5rpx 50rpx;
				color: #ffffff;
				border-radius:  36rpx;
				background-color: #2979ff;
			}
		}
	}
</style>
