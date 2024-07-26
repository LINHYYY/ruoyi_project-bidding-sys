<template>
	<view>
		<u-navbar :is-back="true" title="通知公告" :border-bottom="false"></u-navbar>
		
		
		<li style="list-style-type: none; margin: 15px 25px;" v-for="notification in notifications" :key="notification.noticeId">
        <view class="notification">
          <h3>标题：{{ notification.noticeTitle }}</h3>
          <p>内容：{{ notification.noticeContent }}</p>
          <small>{{ notification.createTime }}</small>
        </view>
		</li>
    
	</view>
</template>

<script>
import config from "@/common/config.js" // 全局配置文件
import { findProduct } from "./notice.js"


export default {
	name: 'MessageNotification',
	data() {
		return {
			notifications: []
		};
	},
created() {
    this.findAllProduct();
  },
  methods: {
		async findAllProduct(){
				const res = await findProduct();
				this.notifications=res.data.rows;
		
				console.log(res);
				console.log(this.notifications);
		},
  }
};
</script>


<style lang="scss" scoped>
	
.notification {
  padding: 20rpx;
  margin-bottom: 20rpx;
  background-color: #4c98e4;
  border-radius: 10rpx;
  color: #ffffff;
  box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
}
	
.tabSwiper {
	width: 710rpx;
	background-color: #ffffff;
	margin: 20rpx auto;
	border-radius: 20rpx;
	box-sizing: border-box;
	padding: 20rpx;
	font-size: 28rpx;
	.top {
		display: flex;
		justify-content: space-between;
		.left {
			display: flex;
			align-items: center;
			.title {
				margin: 0 10rpx;
				font-size: 32rpx;
				font-weight: bold;
			}
		}
		.right {
			color: $u-tips-color;
		}
	}
	.item {
		display: flex;
		margin: 20rpx 0 0;
		.left {
			margin-right: 20rpx;
			image {
				width: 200rpx;
				height: 200rpx;
				border-radius: 10rpx;
			}
		}
		.content {
			.title {
				font-size: 28rpx;
				line-height: 50rpx;
			}
		}
		.right {
			margin-left: 10rpx;
			padding-top: 20rpx;
			text-align: right;
		}
	}
}
.wrap {
	display: flex;
	flex-direction: column;
	height: calc(100vh - var(--window-top));
	width: 100%;
}
.swiper-box {
	flex: 1;
}
.swiper-item {
	height: 100%;
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
	    height: 150px;
	    object-fit: cover;
	    border-radius: 8px;
	  }
	
	  .product-title {
	    margin-top: 10px;
	    font-size: 16px;
	    color: #333;
	    text-align: center;
		font-weight: bold;
	  }
	}
</style>
