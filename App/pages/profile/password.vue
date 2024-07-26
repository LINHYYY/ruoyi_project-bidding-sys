<template>
	<view>
		<u-navbar :is-back="true" title="修改密码" :border-bottom="false"></u-navbar>
		<view class="u-m-20">
			<u-toast ref="uToast" />
			<view>
				<u-cell-group>
					<u-field
						v-model="oldpassword"
						label="旧密码"
						placeholder="请填写旧密码"
						icon="lock"
						iconColor="#d5d5d5"
						label-width = "180"
						password
						focus
					>
					</u-field>
					<u-field
						v-model="newpassword"
						label="新密码"
						placeholder="请填写新密码"
						icon="lock"
						iconColor="#d5d5d5"
						label-width = "180"
						password
					>
					</u-field>
					<u-field
						v-model="password"
						label="确认密码"
						placeholder="请再次填写密码"
						icon="lock-fill"
						iconColor="#d5d5d5"
						label-width = "180"
						password
					>
					</u-field>
				</u-cell-group>
			</view>
		</view>
		<view class="btn">
			<u-button type="primary" @tap="changePasswold">提交</u-button>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			oldpassword: '',
			newpassword: '',
			password: '',
		}
	},
	methods: {
		changePasswold() {
			if(!this.oldpassword){
				return this.$mytip.toast('请输入旧密码')
			}
			if(!this.newpassword){
				return this.$mytip.toast('请输入新密码')
			}
			if(!this.password){
				return this.$mytip.toast('请确认密码')
			}
			if(this.newpassword != this.password){
				return this.$mytip.toast('密码不一致,请确认')
			}
			let url = "api/profile/updatePwd";
			let userId = uni.getStorageSync('lifeData').vuex_user.user.userId;
			this.$u.put(url,{
				userId:userId,
				oldPassword:this.oldpassword,
				newPassword:this.newpassword
			}).then(data => {
				//提示后跳转页面
				this.$mytip.toast('密码修改成功')
				setTimeout(()=>{
					uni.switchTab({
						url: '/pages/center/center'
					})
				},1000);
			});
		},
	}
};
</script>

<style lang="scss" scoped>
	page{
		overflow: hidden;
	}
	
	.logo{
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		height: 550rpx;
		img {
			text-align: center;
		}
	}

	.version {
		position:fixed;
		bottom:0;
		width: 100%;
		text-align: center;
		padding: 15rpx;
	}
	
	.btn {
		margin: 20rpx;
	}
</style>
