<template>
 <view class="u-p-l-10 u-p-r-10 waterfall">
	<view class="">
		<u-navbar :is-back="true" title="投标" :border-bottom="false"
		back-text="返回" :custom-back="goHome" ></u-navbar>
		<!-- #ifndef MP-WEIXIN --> 
			<u-image width="100%" height="350rpx" :src="src"></u-image>
		<!-- #endif -->
		<view class="wrap">
			<!-- 合伙人-->	
				<u-form :model="model" :rules="rules" ref="uForm" :errorType="errorType">
					<u-form-item label="参与类型" prop="payType" label-width="180">
						<u-input :border="border" type="select" :select-open="actionSheetShow" v-model="model.payType" placeholder="请选择参与类型" @click="actionSheetShow = true"></u-input>
					</u-form-item>
				<u-form-item label="成本填报" prop="scostReporting" label-width="180" v-if="model.payType === '合伙人'">
					<u-input :border="border" placeholder="成本填报" v-model="model.costReporting" type="text"></u-input>
					</u-form-item>
				<u-form-item label="分成金额" prop="profitEstimate" label-width="180" v-if="model.payType === '合伙人'">
					<u-input :border="border" placeholder="分成金额" v-model="model.profitEstimate" type="text"></u-input>
					</u-form-item>
				<u-form-item label="分成比例" prop="bonusRatio" label-width="180" v-if="model.payType === '合伙人'">
					<u-input :border="border" placeholder="分成比例" v-model="model.bonusRatio" type="text"></u-input>
					</u-form-item>
				
				<u-form-item label="投标人ID" prop="bidderId" label-width="180" v-if="model.payType === '合伙人'">
					<u-input :border="border" placeholder="投标人id" v-model="model.bidderId" type="number"></u-input>
				</u-form-item>
				<u-form-item label="联系方式" prop="phoneNumber" label-width="180" v-if="model.payType === '合伙人'">
					<u-input :border="border" placeholder="电话号码" v-model="model.phoneNumber" type="text"></u-input>
				</u-form-item>
				
				<u-form-item label="投标文件" prop="bidDocument" label-width="180" v-if="model.payType === '合伙人'">
					<view>
					<u-button type="primary" @click="uploadAttachment" v-model="model.bidDocument">上传投标文件</u-button>
					</view>
				</u-form-item>
				
				<view class="t-a">
					<text class="custom-select" v-if="model.payType === '合伙人'" >是否为敏感文件</text>
					<select v-model="model.sensitiveFlag" v-if="model.payType === '合伙人'">
						<option value='1'>是</option>
						<option value='0'>否</option>
					</select>
				</view>




			<!-- 施工队-->	
					
					<u-form-item label="项目ID" prop="projectId" label-width="180" v-if="model.payType === '施工队'">
						<u-input :border="border" placeholder="项目ID" v-model="model.projectId" type="number" maxlength="11"></u-input>
					</u-form-item>	
					<u-form-item label="施工队ID" prop="teamId" label-width="180" v-if="model.payType === '施工队'">
						<u-input :border="border" placeholder="施工队ID" v-model="model.teamId" type="number" maxlength="11"></u-input>
					</u-form-item>
					<!--
					<u-form-item label="预计开始时间" prop="estimatedStartdate" label-width="180" v-if="model.payType === '施工队'">
						<u-input :border="border" placeholder="请填写预计开始时间" v-model="model.estimatedStartdate" type="date"></u-input>
						</u-form-item>
					<u-form-item label="预计结束时间" prop="estimatedEnddate" label-width="180" v-if="model.payType === '施工队'">
					  <u-input :border="border" placeholder="请填写预计结束时间" v-model="model.estimatedEnddate" type="date"></u-input>
					</u-form-item>
					-->	
					<u-form-item label="项目计划内容" prop="planContent" label-width="180" v-if="model.payType === '施工队'">
						<u-input :border="border" placeholder="请填写计划内容" v-model="model.planContent" type="text"></u-input>
						</u-form-item>
					<u-form-item label="施工项目负责人" prop="responsiblePerson" label-width="180" v-if="model.payType === '施工队'">
						<u-input :border="border" placeholder="请填写施工负责人" v-model="model.responsiblePerson" type="text"></u-input>
						</u-form-item>
			
			
			
			
			<!-- 设计队-->	
					<u-form-item label="项目ID" prop="projectId" label-width="180" v-if="model.payType === '设计队'">
						<u-input :border="border" placeholder="项目ID" v-model="model.projectId" type="number" maxlength="11"></u-input>
					</u-form-item>	
					<u-form-item label="设计队ID" prop="teamId" label-width="180" v-if="model.payType === '设计队'">
						<u-input :border="border" placeholder="设计队ID" v-model="model.teamId" type="number" maxlength="11"></u-input>
					</u-form-item>
					<!--
					<u-form-item label="预计开始时间" prop="estimatedStartdate" label-width="180" v-if="model.payType === '设计队'">
						<u-input :border="border" placeholder="请填写预计开始时间" v-model="model.estimatedStartdate" type="date"></u-input>
						</u-form-item>
					<u-form-item label="预计结束时间" prop="estimatedEnddate" label-width="180" v-if="model.payType === '设计队'">
					  <u-input :border="border" placeholder="请填写预计结束时间" v-model="model.estimatedEnddate" type="date"></u-input>
					</u-form-item>
					-->	
					<u-form-item label="项目计划内容" prop="planContent" label-width="180" v-if="model.payType === '设计队'">
						<u-input :border="border" placeholder="请填写计划内容" v-model="model.planContent" type="text"></u-input>
						</u-form-item>
					<u-form-item label="设计项目负责人" prop="responsiblePerson" label-width="180" v-if="model.payType === '设计队'">
						<u-input :border="border" placeholder="请填写设计负责人" v-model="model.responsiblePerson" type="text"></u-input>
						</u-form-item>
					
				</u-form>
			
			
			
			
			<!-- 动作表单 -->
				<u-action-sheet :list="actionSheetList" v-model="actionSheetShow" @click="actionSheetCallback"></u-action-sheet>

				<view class="bottom-btn" >
					<u-button type="primary" @click="submit">投标</u-button>
				</view>
			</view>
			</view>
	</view>
</template>

<script>
import  { findProduct } from './js/preHouse.js';
import { EventBus } from './js/event-bus.js';
export default {
	data() {
		let that = this;
		return {
				src: '/static/img/index/swiper/swiper1.jpg',
				actionSheetShow: false,
				payType: '',
				
			actionSheetList: [
				{  text: '合伙人'},
				{  text: '施工队'},
				{  text: '设计队'},
				
			],
			
			
			rules: {
					payType: [
						{ required: true, message: '请选择参与类型', trigger: ['change', 'blur'] }
					],
					bidderId: [
						{ required: true, message: '请完整输入', trigger: ['change', 'blur'] }
					],
					phoneNumber: [
						{ required: true, message: '请完整输入', trigger: ['change', 'blur'] }
					],
					bidDocument:	[
						{ required: true, message: '请完整输入', trigger: ['change', 'blur'] }
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
	        case '合伙人':
	          return {
	            // 合伙人相关的模型属性
	            bidderId: '',
	            phoneNumber: '',
	            bidDocument:'',
	            sensitiveFlag:'',
	            costReporting:'',
	            profitEstimate:'',
	            bonusRatio:'',
	          };
	        case '施工队':
	          return {
	            // 施工队相关的模型属性
	            projectId: '',
				teamId: '',
	            //estimatedStartdate: '',
				//estimatedEnddate: '',
				planContent: '',
				responsiblePerson: '',
	            // 其他属性...
	          };
	        case '设计队':
	          return {
	            // 设计队相关的模型属性
	            projectId: '',
	            teamId: '',
	            //estimatedStartdate: '',
	            //estimatedEnddate: '',
	            planContent: '',
	            responsiblePerson: '',
	            // 其他属性...
	          };
	        default:
	          return {}; // 默认情况下返回一个空对象
	      }
	    }
	  },
	
	// onUnload() {
	// 	// 移除监听事件  
	// 	uni.$off('changeAddressConfig');
	// },
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
	      		this.$mytip.toast('参与成功，请等待管理员审核');
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
					const tempFilePaths = res.tempFilePaths;
					this.src = tempFilePaths[0]; // 将选择的图片路径赋值给src，用于显示在页面上
					this.$mytip.toast('图片上传成功');
				},
				fail: err => {
					console.log('选择图片失败', err);
				}
			});
		},
		uploadAttachment() {
		      uni.chooseFile({
		        count: 1, // 可以选择的文件个数
		        type: 'all', // 文件类型
		        success: (res) => {
		          const tempFilePath = res.tempFilePaths[0];
		          // 更新模型中的合同路径变量，用于显示
		          	this.model.bidDocument = tempFilePath;
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
		
	},

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
