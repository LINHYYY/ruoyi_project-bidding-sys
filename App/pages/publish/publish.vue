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
					<u-form-item label="成本填报" prop="costEstimation" label-width="180" v-if="model.payType === '设计类项目'">
					  <u-input :border="border" placeholder="请填报成本" v-model="model.costEstimation" type="text"></u-input>
					</u-form-item>
					<u-form-item label="分成比例" prop="profitRate" label-width="180" v-if="model.payType === '设计类项目'">
					  <u-input :border="border" placeholder="请填写分成比例" v-model="model.profitRate" type="number"></u-input>
					</u-form-item>
					<!-- <u-form-item label="融资需求" prop="financingNeed" label-width="180" v-if="model.payType === '设计类项目'">
					  <u-input :border="border" placeholder="请填写融资需求" v-model="model.financingNeed" type="text"></u-input>
					</u-form-item> -->
					<u-form-item label="工期" prop="financingPeople" label-width="180" v-if="model.payType === '设计类项目'">
					  <u-input :border="border" placeholder="请填写工期" v-model="model.financingPeople" type="text" maxlength="11"></u-input>
					</u-form-item>
					
					<view class="t-a" v-if="model.payType === '设计类项目'">
						<text class="custom-select" >项目状态</text>
						<select v-model="selectedRole">
							<option value="施工管理人员">计划 </option>
							<option value="设计管理人员">资金筹备</option>
						
						</select>
					</view>
					<u-form-item label="合同" prop="Photo" label-width="180" v-if="model.payType === '设计类项目'">
					  <view>
					    <u-button type="primary" @click="uploadAttament">上传合同</u-button>
					  </view>
					</u-form-item>
			
					
					<u-form-item label="成本填报" prop="collectionProgress" label-width="180" v-if="model.payType === '施工类项目'">
					  <u-input :border="border" placeholder="请填写成本填报" v-model="model.collectionProgress" type="text" maxlength="11"></u-input>
					</u-form-item>
					<u-form-item label="分成比例" prop="financingAmount" label-width="180" v-if="model.payType === '施工类项目'">
					  <u-input :border="border" placeholder="请填写分成比例" v-model="model.financingAmount" type="number" maxlength="11"></u-input>
					</u-form-item>
					<u-form-item label="融资需求" prop="profitRate" label-width="180" v-if="model.payType === '施工类项目'">
					  <u-input :border="border" placeholder="请填写融资需求" v-model="model.profitRate" type="text" maxlength="11"></u-input>
					</u-form-item>
					<u-form-item label="工期" prop="financingPeople" label-width="180" v-if="model.payType === '施工类项目'">
					  <u-input :border="border" placeholder="请填写工期" v-model="model.financingPeople" type="text" maxlength="11"></u-input>
					</u-form-item>
					<!-- <u-form-item label="项目状态" prop="projectSummary" label-width="180" v-if="model.payType === '施工类项目'"> -->
					  <view class="t-a" v-if="model.payType === '施工类项目'">
					  	<text class="custom-select" >项目状态</text>
					  	<select v-model="selectedRole">
					  		<option value="施工管理人员">计划 </option>
					  		<option value="设计管理人员">资金筹备</option>
					  	
					  	</select>
					  </view>
					<!-- </u-form-item> -->
					
					</u-form-item>
					<u-form-item label="合同" prop="Photo" label-width="180" v-if="model.payType === '施工类项目'">
					  <view>
					    <u-button type="primary" @click="uploadAttachment">上传合同</u-button>
					  </view>
					</u-form-item>
				</u-form>
				<!-- 动作表单 -->
				<u-action-sheet :list="actionSheetList" v-model="actionSheetShow" @click="actionSheetCallback"></u-action-sheet>
				<view class="bottom-btn">
					<u-button type="primary" @click="submit">发布</u-button>
				</view>
			</view>
			</view>
	</view>
</template>

<script>
export default {
	data() {
		let that = this;
		return {
				src: '/static/img/index/swiper/swiper3.jpg',
				actionSheetShow: false,
				model: {
					payType: '',
					houseNo: '',
					owerPhone: '',
				
			},
			
			actionSheetList: [
				{  text: '设计类项目'},
				{  text: '施工类项目'},
				
			],
			
			rules: {
					payType: [
						{ required: true, message: '请选择项目类型', trigger: ['change', 'blur'] }
					],
					costEstimation: [
						{ required: true, message: '请成本填报', trigger: ['change', 'blur'] }
					],
					financingAmount: [
						{ required: true, message: '请输入分成比例', trigger: ['change', 'blur'] }
					],
					collectionProgress: [
						{ required: true, message: '请输入收款进度', trigger: ['change', 'blur'] }
					],
					 profitRate: [
						{ required: true, message: '请输融资需求', trigger: ['change', 'blur'] }
					],
					financingPeople: [
						{ required: true, message: '请输入工期', trigger: ['change', 'blur'] }
					],
					projectSummary: [
						{ required: true, message: '请输入项目状态', trigger: ['change', 'blur'] }
					],
					
				},
				border: false,
				errorType: ['toast'],
			};
	},
	
	//项目类型
	// payType
	// 成本填报
	// costEstimation
	// 分成比例
	// profitRate
	// 工期
	// financingPeople
	// 项目状态
	// projectSummary
	// 合同
	// Photo
	// 成本填报
	// collectionProgress
	 //分成比例
	// financingAmount
	// 融资需求
	// profitRate
	// 工期
	// financingPeople
	// 项目状态
	// projectSummary
	// 合同
	// Photo
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
		submit() {
		  this.$refs.uForm.validate(valid => {
		    if (valid) {
		      // 显示发布成功的提示信息
		      uni.showToast({
		        title: '发布成功',
		        icon: 'success',
		        duration: 500 // 显示时间为1000毫秒
		      });
		      // 使用 uni.redirectTo 跳转到首页
		      uni.redirectTo({
		        url: '/pages/index/index', // 路径前需要有斜杠
		        success: function () {
		          console.log('跳转成功');
		        },
		        fail: function (err) {
		          console.error('跳转失败', err);
		        }
		      });
		    } else {
		      // 显示验证失败的提示信息
		      uni.showToast({
		        title: '所以文本不能为空',
		        icon: 'none',
		        duration: 500
		      });
		    }
		  });
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
		// uploadImage() {
		// 	uni.chooseImage({
		// 		count: 1,
		// 		sizeType: ['original', 'compressed'],
		// 		sourceType: ['album', 'camera'],
		// 		success: res => {
		// 			const tempFilePaths = res.tempFilePaths;
		// 			this.src = tempFilePaths[0]; // 将选择的图片路径赋值给src，用于显示在页面上
		// 			this.$mytip.toast('图片上传成功');
		// 		},
		// 		fail: err => {
		// 			console.log('选择图片失败', err);
		// 		}
		// 	});
		// },
		uploadAttachment() {
		      uni.chooseFile({
		        count: 1, // 可以选择的文件个数
		        type: 'all', // 文件类型
		        success: (res) => {
		          const tempFilePaths = res.tempFilePaths;
		          const tempFiles = res.tempFiles; // 包含文件的相关信息
		          // 这里可以根据需要使用tempFilePaths或tempFiles
		          // 例如，将文件路径赋值给src
		          this.src = tempFilePaths[0];
		          this.$mytip.toast('附件上传成功');
		          // 可以在这里添加上传文件到服务器的代码
		        },
		        fail: (err) => {
		          console.log('选择文件失败', err);
		        }
		      });
		    },
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
.custom-select{
  margin-right: 70rpx;
  height: 40px; 
  line-height: 40px; 
  
}
/* #endif */
</style>
