<template>
	<view>
		<u-navbar :is-back="true" title="设置头像" :border-bottom="false"></u-navbar>
		<view class="wrap">
			<u-upload :custom-btn="true" ref="uUpload" :auto-upload="true" :action="action" :max-size="10 * 1024 * 1024" max-count="1" width="690" height="690" :size-type="siteType">
				<view slot="addBtn" class="slot-btn" hover-class="slot-btn__hover" hover-stay-time="150">
					<u-icon name="plus" size="60" :color="$u.color['lightColor']"></u-icon>
				</view>
			</u-upload>
			<view class="u-m-t-20 btn">
				<u-button type="primary" @click="subProfile">提交</u-button>
			</view>
		</view>
	</view>
</template>

<script>
import config from "@/common/config.js" // 全局配置文件
export default {
	data() {
		return {
			// 服务器地址
			action: config.staticUrl + '/common/upload',
			siteType:['compressed']
		};
	},
	methods: {
		subProfile() {
			let files = [];
			// 通过filter，筛选出上传进度为100的文件(因为某些上传失败的文件，进度值不为100，这个是可选的操作)
			files = this.$refs.uUpload.lists.filter(val => {
				return val.progress == 100;
			})
			if(this.$u.test.isEmpty(files)){
				return this.$mytip.toast('请选择图片')
			}
			let imageList = files.map(val => {
				return {
					// imgUrl: val.response.url,
					imgUrl: val.response.fileName
				}
			})
			let avatar =  imageList[0].imgUrl
			let vuex_user = uni.getStorageSync('lifeData').vuex_user
			let user = vuex_user.user
			let userId = user.userId;
			this.$u.put("api/profile/updateProfile",{
				userId:userId,
				avatar:avatar,
			}).then(data => {
				// 关闭
				user.avatar = avatar
				this.$u.vuex('vuex_user', vuex_user);
				this.$mytip.toast('头像修改成功')
				setTimeout(()=>{
					uni.$emit('updateAvatar', {});
					uni.switchTab({
						url: '/pages/center/center'
					})
				},1000);
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

	.slot-btn {
		width: 690rpx;
		height: 690rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		background: rgb(244, 245, 246);
		border-radius: 10rpx;
	}

	.slot-btn__hover {
		background-color: rgb(235, 236, 238);
	}
</style>