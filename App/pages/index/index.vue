<template>
  <view class="u-p-l-10 u-p-r-10">
    <u-navbar :is-back="false" title="xx众投小程序">
      <view style="display: flex;justify-content: center;align-items: center;">
      </view>
    </u-navbar>
    <view>
      <!-- 轮播图组件 -->
      <u-swiper :list="swiperList" height="550" @click="moreInfo"></u-swiper>
      <!-- 导航图标组件 -->
      <view class="rowClass">
        <u-row>
          <u-col span="6" text-align="center" v-for="(item,index) in navList" :key="index">
            <view class="u-padding-20" @tap="clickNav(item)" hover-class="hoverClass">
              <view class="photo">
                <image :src="item.src" style="width: 75rpx; height: 80rpx;" mode="widthFix"></image>
              </view>
              <view class="tabName">{{item.name}}</view>
            </view>
          </u-col>
        </u-row>
      </view>
      <u-gap height="20"></u-gap>
	  
	  <!-- 工程列表 -->
		<u-row>
		  <u-col span="6" v-for="(product, index) in productList" :key="index">
		    <view class="product-item" @click="goToProductDetail(index)">
		      <image src="/static/img/index/cover/building3.png" class="product-image"></image>
		      <view class="product-title">{{ product.projectName }}</view>
		    </view>
		  </u-col>
		</u-row>

    </view>	
  </view>
</template>
<script>
	import config from "@/common/config.js" // 全局配置文件
	import { findProduct } from "./js/index.js"
	export default {
		data() {
			return {				
				keyword: '',
				pageNum: 1,
				pageSize: 20,
				scrollTop: 0,
				houseList: [],
				swiperList: [
					{
						image: '/static/img/index/swiper/swiper7.jpg',
					},
					{
						image: '/static/img/index/swiper/swiper5.jpg',
					},{
						image: '/static/img/index/swiper/swiper6.jpg',
					},
					
                ],
				 // productList: [
				 //        {id: 1, image: '/static/img/index/cover/building3.png', title: '港珠澳大路' ,type:"0"},
				 //        {id: 2, image: '/static/img/index/cover/building2.png', title: '粤西大桥' ,type:"0"},
				 //        {id: 3, image: '/static/img/index/cover/building4.png', title: '绿色花园' ,type:"0"},
				 //        {id: 4, image: '/static/img/index/cover/building5.png', title: '天宇广场',type:"0" },
				 //        { id: 5,image: '/static/img/index/cover/building1.png', title: '梦幻工程',type:"0" }
				 //      ],
					  productList: [
					         ],
				noticeList: [],
				navList:[
				   {name:"我的项目",src:"/static/img/index/cover_2022/xiangmu.png",type:"0"},
				   {name:"通知",src:"/static/img/index/cover_2022/tongzhi.png",type:"1"},
				   //{name:"项目发布",src:"/static/img/index/cover_2022/fabu.png",type:"2"},
				],
				loadStatus: 'loadmore',
				flowList: [],
				uvCode: uni.getStorageSync('uvCode')
			}
		},
		
		
		
		methods: {	
			async findAllProduct(){
					const res = await findProduct();
					this.productList=res.data.rows;

					console.log(res);
					console.log(this.productList);
			},
			goToProductDetail(productId) {
			      // 使用 this.$u.route 进行页面跳转，或者使用 uni.navigateTo 或 this.$router.push
			      this.$u.route({
			        url: '/pages/detail/detail',
			        params: {
			          productId: productId
			        }
			      });
			    },
			findHouseList(type = 0) {
				if(type == 1){
					this.pageNum = 1
					this.flowList = []
					this.$refs.uWaterfall.clear();
				}		
				
			},
			
			clickSearch() {
			    this.$u.route('/pages/join/join');
			},
			clickImage(houseId) {
				this.$u.route({
					url: '/pages/detail/detail',
					params: {
						houseId: houseId
					}
				})
			},
		
			clickNav(item) {
				switch(item.type) {
					case "0": // 我的项目
						let lifeData = uni.getStorageSync('lifeData');
						let token = lifeData.vuex_token;
						if (!token) {
							// 没有token 则跳转到登录
							uni.reLaunch({
								url: '../login/login'
							});
						} else {
							this.$u.route('/pages/join/join');
						}
						break;
					case "1": // 消息
						this.$u.route('/pages/notice/notice');
						break;
					case "2": // 发布项目
						// 判断是否有token
						lifeData = uni.getStorageSync('lifeData');
						token = lifeData.vuex_token;
						console.log(token)
						if (!token) {
							// 没有token 则跳转到登录
							uni.reLaunch({
								url: '../login/login'
							});
						} else {
							// // 判断Token是否有效
							// let url = "/api/profile/isExpiration";
							// this.$u.get(url, {
							// 	token: token
							// }).then(obj => {
							// 	if (obj.data) {
							// 		// Token过期则跳转到登录
							// 		uni.reLaunch({
							// 			url: '../login/login'
							// 		});
							// 	} else {
									// Token有效，跳转到发布项目页面
									this.$u.route('/pages/publish/publish');
								// }
							// });
						}
						break;
					default:
						break;
				}
			},
			
			server(){
				// window.open ('https://sourcebyte.cn&#39;) 
				uni.makePhoneCall({
				  phoneNumber: "18720989281",
				});
			},
		
			moreInfo(){
				uni.navigateToMiniProgram({
					appId:'wxbca64173e772915e',// 此为开源字节appid
					path:'/pages/index/index',// 此为开源字节首页路径
					envVersion:"release",
					success: res => {
						 // 打开成功
						 console.log("打开成功", res);
					},
					fail: err => {
						 console.log(err);
					}
				})
			},
		},
		onLoad() {
			this.findAllProduct();
		}
	}
</script>


<style lang="scss" scoped>
	
	
	
	.nomore {
		background-color: $u-bg-color;
	}
	
	.search{
		width: 54px;
		height: 44px;
		
		&:active{
			background-color: $u-bg-color;
		}
	}
	
	.rowClass{
		border-radius: 8px;
		background-color: rgb(255, 255, 255);
		margin-top: 10rpx;
	}
	
	.hoverClass{
		background-color: #E4E7ED;
	}
	
	.tabName{
		font-size: 28rpx;
		color: $u-main-color;
	}
	
	.demo-warter {
	    border-radius: 8px;
	    margin-top: 3px;
	    background-color: #ffffff;
	    padding: 3px;
	    position: relative;
	}
	
	.u-close {
	    position: absolute;
	    top: 20rpx;
	    right: 20rpx;
	}
	
	.item-cover {
	    font-size: 55rpx;
	    color: $u-type-warning;
	}
	
	.item-title {
	    font-size: 28rpx;
	    color: $u-main-color;
	    font-weight: bold;
		padding-top: 5rpx;
		padding-left: 10rpx;
	}
	
	.item-price {
	    font-weight: normal;
	    font-size: 32rpx;
	    color: $u-type-warning;
	}
	
	.item-desc {
	    font-weight: normal;
	    font-size: 26rpx;
	    color: $u-tips-color;
		padding-bottom: 5rpx;
		padding-left: 10rpx;
	}
	
	.item-tag {
	    font-size: 24rpx;
	    color: $u-tips-color;
	    margin-top: 3px;
	}
	.product-item {
	  padding: 10px;
	  background-color: #f7f7f7;
	  border: 1px solid #e0e0e0;
	  border-radius: 8px;
	  overflow: hidden;
	  transition: all 0.3s ease;
	
	  &:hover {
	    transform: translateY(-5px);
	    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	  }
	
	  .product-image {
	    width: 100%;
	    height: 100px;
	    object-fit: cover;
	    border-radius: 6px;
	  }
	
	  .product-title {
	    margin-top: 10px;
	    font-size: 14px;
	    color: #333;
	    text-align: center;
		font-weight: bold;
	  }
	}
</style>
