<template>
    <view>
     <u-gap height="20"></u-gap>
     
     <!-- 工程列表 -->
     		<u-row>
     		  <u-col span="6" v-for="(product, index) in productList" :key="index">
     		    <view class="product-item" @click="goToProductDetail(index)">
     		      <image src="/static/img/index/cover/building3.png"  class="product-image"></image>
     		      <view class="product-title">{{ product.projectName }}</view>
     		    </view>
     		  </u-col>
     		</u-row>
    </view>
</template>

<script>
	import config from "@/common/config.js" // 全局配置文件
	import { findProduct } from "./js/join.js"
    export default {
        data() {
            return {
				productList: [
				       {id: 1, image: '/static/img/index/cover/building3.png', title: '港珠澳大路' ,type:"0"},
				       {id: 2, image: '/static/img/index/cover/building2.png', title: '粤西大桥' ,type:"0"},
				       {id: 3, image: '/static/img/index/cover/building4.png', title: '绿色花园' ,type:"0"},
				       {id: 4, image: '/static/img/index/cover/building5.png', title: '天宇广场',type:"0" },
				       { id: 5,image: '/static/img/index/cover/building1.png', title: '梦幻工程',type:"0" }
				     ],
				
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
                 url: '/pages/join/detail',
                 params: {
                   productId: productId
                 }
               });
             },
      },
	  onLoad() {
	  	this.findAllProduct();
	  }
	}
</script>

<style lang="scss" scoped>
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
	    height: 130px;
	    object-fit: cover;
	    border-radius: 3px;
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

