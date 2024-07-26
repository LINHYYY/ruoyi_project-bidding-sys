<template>
 <view class="u-p-l-10 u-p-r-10 waterfall">
	<view class="">
		<u-navbar :is-back="true" title="申报" :border-bottom="false"
		back-text="返回" :custom-back="goHome" ></u-navbar>
		<!-- #ifndef MP-WEIXIN --> 
			<u-image width="100%" height="350rpx" :src="src"></u-image>
		<!-- #endif -->
		<view class="wrap">
				<u-form :model="model"  ref="uForm" :errorType="errorType">
					<u-form-item label="用户角色" prop="payType" label-width="180">
						<u-input :border="border" type="select" :select-open="actionSheetShow" v-model="model.payType" placeholder="请选择用户角色" @click="actionSheetShow = true"></u-input>
					</u-form-item>
					<u-form-item label="项目ID" prop="projectId" label-width="180" v-if="model.payType === '施工管理人员'">
					  <u-input :border="border" placeholder="请填写项目ID" v-model="model.projectId" type="number"></u-input>
					</u-form-item>
					<u-form-item label="施工队ID" prop="teamId" label-width="180" v-if="model.payType === '施工管理人员'">
					  <u-input :border="border" placeholder="请填写施工队ID" v-model="model.teamId" type="number"></u-input>
					</u-form-item>
					<u-form-item label="项目照片" prop="photoUrl" label-width="180" v-if="model.payType === '施工管理人员'">
					  <view>
					    <u-button type="primary" @click="uploadImage" v-model="model.photoUrl">上传现场照片</u-button>
					  </view>
					</u-form-item>

					
				</u-form>
				</view>
				<!-- 动作表单 -->
				<u-action-sheet :list="actionSheetList" v-model="actionSheetShow" @click="actionSheetCallback"></u-action-sheet>
				<view class="bottom-btn">
					<u-button type="primary" @click="submit">上传现场图片</u-button>
				</view>
			</view>
			</view>
	</view>
</template>

<script>
	
import { findProduct } from "./js/revise.js"
export default {
	data() {
		let that = this;
		return {
				src: '/static/img/index/swiper/swiper4.jpg',
				actionSheetShow: false,
				
			
			actionSheetList: [
				{  text: '施工管理人员'},
				
			],
			
			rules: {
					payType: [
						{ required: true, message: '请选择用户角色', trigger: ['change', 'blur'] }
					],

					
				},
				border: false,
				errorType: ['toast'],
			};
	},
	// onUnload() {
	// 	// 移除监听事件  
	// 	uni.$off('changeAddressConfig');
	// },
	computed: {
	    model() {
	      switch (this.payType) {
	        case '施工类项目':
	          return {
	            // 施工队相关的模型属性
				projectId: '',
				teamId: '',
				photoUrl: '',
	            // 其他属性...
	          };
	        
	        default:
	          return {}; // 默认情况下返回一个空对象
	      }
	    }
	  },
	onReady() {
		this.$refs.uForm.setRules(this.rules);
	},
	methods: {
		goHome(){
			uni.reLaunch({
				url:'../detail/detail'
			})
		},
		navigateBack() {
		      // 使用 uni.navigateBack() 返回上一页
		      uni.navigateBack();
		    },

	    async submit() {
	      this.$refs.uForm.validate(valid => {
	      	if (valid) {
	      		// 执行提交成功的操作，例如显示提示信息
	      		this.$mytip.toast('上传成功');
	      		// 跳转到首页
	      		setTimeout(() => {
	      			uni.navigateTo({
	      				url: 'pages/index/index',
	      				
	      			});
	      		}, 1000); // 延迟1秒跳转，可以根据需要调整
	      	} else {
	      		console.log('验证失败');
	      	}
	      }
	      );
	      const res = await findProduct(this.model)
	      console.log(res)
	    },

		selectConfirm(e) {
			e.map((val, index) => {
				this.model.villageName = val.label;
				this.model.villageId = val.value
			})
		},
		decorationConfirm(e) {
			e.map((val, index) => {
				this.model.decoration = val.label;
			})
		},
		labelPositionChange(index) {
			this.labelPosition = index == 0 ? 'left' : 'top';
		},
		// 点击actionSheet回调
		actionSheetCallback(index) {
			uni.hideKeyboard();
			this.model.payType = this.actionSheetList[index].text;
		},
		findVillageList() {
			let url = "/api/houseApi/findVillageList";
			this.$u.get(url,{
					city:uni.getStorageSync('lifeData').vuex_city,
            		orderByColumn: 'name',
            		isAsc: 'desc'
            	}).then(result => {
				const data = result.rows
				for (let i = 0; i < data.length; i++) {
				    // 先转成字符串再转成对象，避免数组对象引用导致数据混乱
				    let item = data[i]
					this.selectList.push({
						label: item.name,
						value: item.id
					})
				}
				return data
			});
		},
		uploadImage() {
			uni.chooseImage({
				count: 1,
				sizeType: ['original', 'compressed'],
				sourceType: ['album', 'camera'],
				success: res => {
					const tempFilePath = res.tempFilePaths[0];
					// 更新模型中的合同路径变量，用于显示
						this.model.photoUrl = tempFilePath;
						this.$mytip.toast('图片上传成功');
							                
						this.uploadFileToServer(tempFilePath);
				},
				fail: err => {
					console.log('选择图片失败', err);
				}
			});
		},
		// 上传文件到服务器的函数
				uploadFileToServer(filePath) {
					let lifeData = uni.getStorageSync('lifeData');
					let token = lifeData.vuex_token
						console.log(token);
						uni.uploadFile({
						url: 'http://localhost:8080/common/upload', // 更换为你的服务器API地址
						filePath,
						name: 'file',
						header: {
								'Authorization': `Bearer ${token}`, // 根据你的API需求，可能只是'token': token
							},
						formData: {
						        user: 'test'
						    },
					success: (res) => {
						console.log(res);
						// 根据服务器返回的数据进行处理
						if (res.statusCode === 200) {
							var dataObj = JSON.parse(res.data);  
							this.model.contractFile = dataObj.fileName;  
							this.$mytip.toast('图片上传成功');
						    } else {
						              this.$mytip.toast('图片上传失败');
						            }
						},
					fail: (error) => {
						console.log(error);
						this.$mytip.toast('文件上传失败');
						}
					});
				}
	}
};
</script>

<style>
	page{
		background-color: #FFFFFF;
	}
</style>
<style scoped lang="scss">
.wrap {
	padding: 30rpx;
}

/* #ifdef MP-WEIXIN */  
.bottom-btn {
	margin-top: 30rpx;
	padding: 20rpx;
}
/* #endif */

/* #ifndef MP-WEIXIN */  
.bottom-btn {
	padding: 0 30rpx 30rpx 30rpx;
	background-color: #FFFFFF;
}
/* #endif */
</style>
