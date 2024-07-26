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
			
			<view class="right" @click="clickPlan">
				<view class="btn">上传日志</view>
			</view>
			<view class="right" @click="clickAcceptance">
				<view class="btn">验收文件</view>
			</view>
			<view class="right" @click="clickItem">
				<view class="btn">上传进度</view>
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
				index:-40,
				ProdectById:{},
				
            }
        },
		onLoad: function (option) {
			console.log(option);
			// let houseId = option.houseId //上个页面传递的参数。
			// this.houseId =houseId
			// // 渲染当前房源信息
			this.index=option.productId
			// 渲染当前房源信息
			this.findProdectById(this.index);
			// this.findHouseById(houseId);
			// this.findProdectById(houseId);
			
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
              uni.reLaunch({
              	url:'/pages/join/revise'
                });
            },
			clickPlan() {
			  uni.reLaunch({
			  	url:'/pages/join/plan'
			    });
			},
			clickAcceptance() {                
              uni.reLaunch({
              	url:'/pages/join/acceptance'
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
			findHouseById(houseId){
				// let url = "api/houseApi/findHouseById";
				this.$u.get(url, {
					id: houseId
				}).then(result => {
					let room = result.data
					if(room.type == 0){
						room.type = '整租'
					}else if(room.type == 1){
						room.type = '合租'
					}
					if(room.roomType == 1){
						room.roomType = '主卧'
					}else if(room.roomType == 2){
						room.roomType = '次卧'
					}else{
						room.roomType = '未知'
					}
					
					if(this.$u.test.isEmpty(room.houseNum)){
						room.houseNum = ''
					}
					if(this.$u.test.isEmpty(room.houseHall)){
						room.houseHall = ''
					}
					if(this.$u.test.isEmpty(room.toiletNum)){
						room.toiletNum = ''
					}
					if(this.$u.test.isEmpty(room.floor)){
						room.floor = ''
					}else{
						room.floor = room.floor + '层'
					}
					this.swiperlist = room.imageList.map(val=>{
						let imgUrl = val.imgUrl
						if(!imgUrl.includes(config.staticUrl)){
							imgUrl = config.staticUrl+val.imgUrl
						}else{
							imgUrl = val.imgUrl
						}
						return {
							title: val.imageName,
							image: imgUrl
						}
					})
					
					if(!room.agentAvatar.includes(config.staticUrl)){
						room.agentAvatar = config.staticUrl+room.agentAvatar
					}
					this.user = room.user
					this.village = room.village
					this.room = room
					// 判断是否收藏
					this.selectHouseHeart(houseId);
					
					
					// 分享自定义标题与图片
					let shareTitle = ''
					if(room.type == '整租'){
						shareTitle = this.village.name + " " + this.room.houseNum + this.room.houseHall + this.room.toiletNum + " " + this.room.decoration+ " ¥" + this.room.price+"/月"
					}else{
						shareTitle = this.village.name + " " + room.roomType + " " + this.room.decoration+  " ¥" + this.room.price+"/月"
					}
					this.$u.mpShare = {
						title:  shareTitle, // 默认为小程序名称，可自定义
						// 支持PNG及JPG，默认为当前页面的截图
						imageUrl: this.room.faceUrl, 
					}
					
					// 添加到浏览历史
					let houseHistory = uni.getStorageSync('houseHistory')
					if(!houseHistory){
						houseHistory = []
					}
					// 如果超过20个了，则删除最后一个
					if(houseHistory.length >= 20){
						houseHistory.pop()
					}
					houseHistory.push(this.room)
					// 数据去重
					const keyArr = [];
					houseHistory.forEach((element,index) => {
						keyArr.push(element.code);  // 通过code来判断
					});
					const newArr = [];
					const newKey = new Set(keyArr);  // key去重
					newKey.forEach(item =>{
						const index = keyArr.findIndex(item2 => item2 === item);
						newArr.push(houseHistory[index]);
					})
					uni.setStorageSync('houseHistory',newArr)
					
					// 经纬度
					this.longitude = room.longitude
					this.latitude = room.latitude
					// 计算距离
					let lat1 = 39.909
					let lng1 = 116.39742
					let lat2 = this.latitude
					let lng2 = this.longitude
					uni.getLocation({
						type: 'gcj02',
						success: res=> {
							lat1 = res.latitude
							lng1 = res.longitude
							this.distance = this.getDistance(lat1,lng1 ,lat2 , lng2);
						}
					})
				});
			},
			selectHouseHeart(houseId){
				// 判断是否有userId
				let lifeData = uni.getStorageSync('lifeData');
				let vuex_user = lifeData.vuex_user
				if(vuex_user){
					let url = "api/houseApi/selectHouseHeart";
					this.$u.get(url, {
						houseId: houseId,
						userId: vuex_user.user.userId,
					}).then(result => {
						this.room.heart = result.data
					});
				}
			},			
			selectHouseEvals(houseId){
				let url = "api/houseApi/selectHouseEvals";
				this.$u.get(url, {
					houseId: houseId,
					pageNum: 1,
					pageSize: 1
				}).then(result => {	
					if(result.total >0){
						this.evaluate = result.rows[0].evaluate;
						if(this.evaluate.length>30){
							this.evaluate = this.evaluate .substring(0, 30)+"......";
						}
						this.credt=result.rows[0].createTime;
						this.havem=true;
						this.evalsize = result.total;
					}					
				});
			},
			moreEval() {
				this.$u.route({
					url: '/pages/detail/evalList',
					params: {
						houseId: this.houseId
					}
				})
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
			getDistance(lat1, lng1, lat2, lng2) {
			    lat1 = lat1 || 0;
				lng1 = lng1 || 0;
				lat2 = lat2 || 0;
				lng2 = lng2 || 0;
				var rad1 = lat1 * Math.PI / 180.0;
				var rad2 = lat2 * Math.PI / 180.0;
				var a = rad1 - rad2;
				var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
				var r = 6378137;
				var distance = r * 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(rad1) * Math.cos(rad2) * Math.pow(Math.sin(b / 2), 2)));
				return (distance/1000).toFixed(2);
			},
			sendProjectId() {
			      EventBus.$emit('projectId', this.ProdectById.id);
			    },
        }
    }
</script>

<style>
    /* page不能写带scope的style标签中，否则无效 */
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
		
		.right {
			display: flex;
			font-size: 28rpx;
			align-items: center;
		    margin-bottom: 10rpx;
			.btn {
				line-height: 80rpx;
				margin-left: 20rpx;
				padding: 5rpx 50rpx;
				color: #ffffff;
				border-radius:  45rpx;
				background-color: #2979ff;
			}
		}
		
	}
</style>
