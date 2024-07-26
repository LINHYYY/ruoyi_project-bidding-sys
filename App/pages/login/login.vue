<!-- 蓝色登录页面2 -->
<template>
	<view>
		<u-toast ref="uToast" />
		<view class="img-a">
			<view class="t-b">
				您好，
				<br />
				欢迎使用 xx众投
			</view>
		</view>
		<view class="login-view">
			<view class="t-login">
				<form class="cl">
					<view class="t-a">
						<text class="txt">账号</text>
						<input name="phone" placeholder="请输入您的账号" maxlength="11" v-model="username" />
					</view>
					<view class="t-a">
						<text class="txt">密码</text>
						<input type="password" name="code" maxlength="18" placeholder="请输入您的密码" v-model="password" />
					</view>
					<!-- <view class="t-a">
						<text class="txt">验证码</text>
						<input type="pass" name="code" maxlength="18" placeholder="请输入验证码" v-model="pass" />
						<image :src="imgUrl" @click="getCode" style="height: 50px;width: 60px;" mode=""></image>
					</view> -->
						<!-- <view class="uni-form-item uni-column">
							<radio-group name="idf" @change="radioChange">
								<text class="txt">身 份：</text>
								<label class="idftext">
									<radio value="施工队" checked="checked" /><text>施工队</text>
									<radio value="合伙人" /><text >合伙人</text>
								</label>
							</radio-group>
						</view> -->

					
					<button style="margin-top: 30rpx;" @tap="login()">登 录</button>
					<button style="background: #b7b7ba;color: #000000;margin-top: 30rpx;"  @tap="register()" >注 册</button>
					<!-- <view class="reg" @tap="reg()">短信登录</view> -->
				</form>
			</view>
		</view>
		
		<!-- #ifdef MP-WEIXIN -->  
		<view class="buttom">
			<button open-type="getPhoneNumber" @getphonenumber="weChatLogin" class="clearBtn">
				<view class="loginType">
					<view class="item">
						<view class="icon"><u-icon size="60" name="weixin-fill" color="rgb(83,194,64)"></u-icon></view>
						微信
					</view>
				</view>
			</button>
			<!-- <view class="hint">
				登录代表同意
				<text class="link">开源字节用户协议、隐私政策，</text>
				并授权使用您的账号信息（如昵称、头像、收获地址）以便您统一管理
			</view> -->
		</view>
		<!-- #endif -->
	</view>
</template>
<script>
import { code, Login } from './js/login.js';
export default {
	data() {
		return {
			username: '',
			password: '',
			pass: '',
			imgUrl: ''
			// identity: 'partner',
		}
	},
	mounted() {
		this.getCode()
	},
	methods: {
			radioChange: function(evt) {
				this.sb=evt.detail.value
			},
			
		async getCode() {
			const res = await code();
			this.imgUrl = 'data:image/gif;base64,' + res.data.img
		},
		
		// async login() {  
		//     try {  
		//         // 假设 Login 是一个返回 Promise 的函数，它执行登录逻辑  
		//         const res = await Login({  
		//             username: this.username,  
		//             password: this.password,  
		//             // 注意：通常这里不需要 'pass'，除非你有特殊理由使用它  
		//              pass: this.pass, // 如果你不需要它，就注释掉或删除这行  
		//         });  
		  
		//         // 检查登录结果，这里假设 res.success 是登录成功的标志  
		//         if (res.success) {  
		//             // 登录成功，跳转到 index 页  
		//             // 注意：这里使用的是纯 JavaScript 的方式  
		//            this.$router.push('/pages/index/index'); // 或者你的 index 页面的实际 URL  
		//         } else {  
		//             // 登录失败，可以处理错误情况，比如显示错误信息  
		//             console.error('Login failed:', res.error);  
		//         }  
		//     } catch (error) {  
		//         // 处理 Login 函数执行过程中可能抛出的异常  
		//         console.error('Error during login:', error);  
		//     }  
		// },
		async login() {
			const res = await Login({
				username: this.username,
				password: this.password
			})
			const token = res.data.token
			if( token ) {
				this.$router.push('/pages/index/index')
				// 调用mutation来更新token
				this.$store.commit('$uStore', {  
					name: 'vuex_token',  
					value: token  
				});
			} else {
				this.$mytip.toast(res.data.msg)
			}
		},
		register() {
				uni.navigateTo({
				url: '/pages/register/register'
			});			
		},
		reg(){
			this.$u.route({
				url: 'pages/login/account'
			})
		}
	}
};
</script>


<style>
page {
  background-color: #ffffff;
}
</style>
<style lang="scss" scoped>
.txt {
	font-size: 32rpx;
	font-weight: bold;
	color: #333333;
}
.img-a {
	width: 100%;
	height: 450rpx;
	//background-image: url(https://zhoukaiwen.com/img/loginImg/head.png);
	background-image: url(./sy.jpg);
	background-size: 100%;
}

.login-view {
	width: 100%;
	position: relative;
	margin-top: -120rpx;
	background-color: #ffffff;
	border-radius: 8% 8% 0% 0;
}

.t-login {
	width: 600rpx;
	margin: 0 auto;
	font-size: 28rpx;
	padding-top: 80rpx;
}

.t-login button {
	font-size: 28rpx;
	background: #009d00;
	color: #fff;
	height: 90rpx;
	line-height: 90rpx;
	border-radius: 50rpx;
	font-weight: bold;
}

.t-login input {
	height: 90rpx;
	line-height: 90rpx;
	margin-bottom: 50rpx;
	border-bottom: 1px solid #e9e9e9;
	font-size: 28rpx;
}

.t-login .t-a {
	position: relative;
}

.t-login .t-a image {
	position: absolute;
	
	right: 0;
	top: 0;
}

.t-b {
	text-align: left;
	font-size: 42rpx;
	color: #ffffff;
	padding: 130rpx 0 0 70rpx;
	font-weight: bold;
	line-height: 70rpx;
}

.t-login .t-c {
	position: absolute;
	right: 22rpx;
	top: 22rpx;
	background: #5677fc;
	color: #fff;
	font-size: 24rpx;
	border-radius: 50rpx;
	height: 50rpx;
	line-height: 50rpx;
	padding: 0 25rpx;
}

.t-login .t-d {
	text-align: center;
	color: #999;
	margin: 80rpx 0;
}

.t-login .t-e {
	text-align: center;
	width: 250rpx;
	margin: 80rpx auto 0;
}

.t-login .t-g {
	float: left;
	width: 50%;
}

.t-login .t-e image {
	width: 50rpx;
	height: 50rpx;
}

.t-login .t-f {
	text-align: center;
	margin: 150rpx 0 0 0;
	color: #666;
}

.t-login .t-f text {
	margin-left: 20rpx;
	color: #aaaaaa;
	font-size: 27rpx;
}

.t-login .uni-input-placeholder {
	color: #aeaeae;
}

.cl {
	zoom: 1;
}

.cl:after {
	clear: both;
	display: block;
	visibility: hidden;
	height: 0;
	content: '\20';
}
.idftext radio{
	margin-left: 50rpx;
}

.buttom {
	.loginType {
		display: flex;
		padding: 140rpx 0;
		justify-content:center;
		
		.item {
			display: flex;
			flex-direction: column;
			align-items: center;
			color: $u-tips-color;
			font-size: 22rpx;
		}
	}
	
	.hint {
		position: absolute;
		bottom: 0;
		padding: 20rpx 40rpx;
		font-size: 20rpx;
		color: $u-tips-color;
		
		.link {
			color: #2979ff;
		}
	}
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
</style>
