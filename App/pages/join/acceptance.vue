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
					<u-form-item label="项目类型" prop="payType" label-width="180">
						<u-input :border="border" type="select" :select-open="actionSheetShow" v-model="model.payType" placeholder="请选择参与类型" @click="actionSheetShow = true"></u-input>
					</u-form-item>
				<!-- 施工类项目-->	
				<u-form-item label="项目ID" prop="projectId" label-width="180" v-if="model.payType === '施工类项目'">
					<u-input :border="border" placeholder="项目ID" v-model="model.projectId" type="number"></u-input>
				</u-form-item>	
				<u-form-item label="施工队ID" prop="teamId" label-width="180" v-if="model.payType === '施工类项目'">
					<u-input :border="border" placeholder="施工队ID" v-model="model.teamId" type="number"></u-input>
				</u-form-item>	
					<u-form-item label="文件名称" prop="fileName" label-width="180" v-if="model.payType === '施工类项目'">
					  <u-input :border="border" placeholder="请填写文件名称" v-model="model.fileName" type="text"></u-input>
					</u-form-item>
					<u-form-item label="上传附件" prop="fileUrl" label-width="180" v-if="model.payType === '施工类项目'">
					  <view>
					    <u-button type="primary" @click="uploadAttachment" v-model="model.fileUrl" >上传附件</u-button>
					  </view>
					</u-form-item>
					
					
				<!-- 设计类项目-->
				<u-form-item label="项目ID" prop="projectId" label-width="180" v-if="model.payType === '设计类项目'">
					<u-input :border="border" placeholder="项目ID" v-model="model.projectId" type="number"></u-input>
				</u-form-item>	
				<u-form-item label="设计队ID" prop="teamId" label-width="180" v-if="model.payType === '设计类项目'">
					<u-input :border="border" placeholder="设计队ID" v-model="model.teamId" type="number"></u-input>
				</u-form-item>	
					<u-form-item label="文件名称" prop="fileName" label-width="180" v-if="model.payType === '设计类项目'">
					  <u-input :border="border" placeholder="请填写文件名称" v-model="model.fileName" type="text"></u-input>
					</u-form-item>
					
					</u-form-item>
					<u-form-item label="上传附件" prop="fileUrl" label-width="180" v-if="model.payType === '设计类项目'">
					  <view>
					    <u-button type="primary" @click="uploadAttachment2" v-model="model.fileUrl">上传附件</u-button>
					  </view>
					</u-form-item>
				</u-form>
				<!-- 动作表单 -->
				<u-action-sheet :list="actionSheetList" v-model="actionSheetShow" @click="actionSheetCallback"></u-action-sheet>
				<view class="bottom-btn">
					<u-button type="primary" @click="submit">上传</u-button>
				</view>
			</view>
			</view>
	</view>
</template>

<script>
	
import { findProduct } from "./js/acceptance.js"
export default {
	data() {
		let that = this;
		return {
				src: '/static/img/index/swiper/swiper3.jpg',
				actionSheetShow: false,
				
			
			actionSheetList: [
				{  text: '设计类项目'},
				{  text: '施工类项目'},
				
			],
			
			rules: {
					payType: [
						{ required: true, message: '请选择项目类型', trigger: ['change', 'blur'] }
					],
					fileName: [
						{ required: true, message: '请填写文件描述', trigger: ['change', 'blur'] }
					],
					
				},
				border: false,
				errorType: ['toast'],
			};
	},
	// 使用计算属性来根据 payType 返回不同的模型
		computed: {
		    model() {
		      switch (this.payType) {
		        case '施工类项目':
		          return {
		            // 施工队相关的模型属性
					projectId: '',
					teamId: '',
		            fileName: '',
					fileUrl: '',
		            // 其他属性...
		          };
		        case '设计类项目':
		          return {
		            // 设计队相关的模型属性
		            projectId: '',
		            teamId: '',
		            fileName: '',
		            fileUrl: '',
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
				url:'../join/detail'
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
		uploadAttachment() {
		      uni.chooseFile({
		        count: 1, // 可以选择的文件个数
		        type: 'all', // 文件类型
		        success: (res) => {
		          const tempFilePath = res.tempFilePaths[0];
		          // 更新模型中的合同路径变量，用于显示
		          	this.model.fileUrl = tempFilePath;
		          	this.$mytip.toast('附件上传成功');
		          		                
		          	this.uploadFileToServer(tempFilePath);
		          	},
		        fail: (err) => {
		          	console.log('选择文件失败', err);
		          	}
		      });
		    },
		uploadAttachment2() {
		      uni.chooseFile({
		        count: 1, // 可以选择的文件个数
		        type: 'all', // 文件类型
		        success: (res) => {
		          const tempFilePath = res.tempFilePaths[0];
		          // 更新模型中的合同路径变量，用于显示
		          	this.model.fileUrl = tempFilePath;
		          	this.$mytip.toast('附件上传成功');
		          		                
		          	this.uploadFileToServer(tempFilePath);
		          	},
		        fail: (err) => {
		          	console.log('选择文件失败', err);
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
								this.$mytip.toast('文件上传成功');
							    } else {
							              this.$mytip.toast('文件上传失败');
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
