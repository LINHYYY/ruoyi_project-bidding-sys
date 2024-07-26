<template>
	<view class="register-view">
		<view class="img-a">
			<view class="t-b">
				您好，
				<br />
				欢迎注册 xxxx
			</view>
		</view>
		<view class="register-form">
			<form class="cl" >
				
				<view class="t-a">
					<text class="txt">用户名</text>
					<input type="text" name="name" placeholder="请输入用户名" maxlength="20" v-model="registerForm.username"/>
				</view>
				<view class="t-a">
					<text class="txt">手机号</text>
					<input type="number" name="phone" placeholder="请输入您的联系电话" maxlength="11" v-model="registerForm.phonenumber"/>
				</view>
				<view class="t-a">
					<text class="txt">身份证后六位</text>
					<input type="number" name="Id" placeholder="请输入您的身份证后六位" maxlength="11" v-model="registerForm.ID6"/>
				</view>
				<view class="t-a">
					<text class="txt">密码</text>
					<input type="password" name="password" maxlength="18" placeholder="请输入您的密码" v-model="registerForm.password"/>
				</view>
				<view class="t-a">
					<text class="txt">确认密码</text>
					<input type="password" name="confirmPassword" maxlength="18" placeholder="请再次输入您的密码" v-model="registerForm.confirmPassword"/>
				</view>
				<view class="t-a">
					<text class="custom-select">角色选择</text>
					<select v-model="registerForm.roleId">
						<option value=101>施工管理人员</option>
						<option value=102>设计管理人员</option>
						<option value=2>合伙人</option>
						<option value=2>施工队</option>
						<option value=2>甲方</option>
					</select>
				</view>
				<!-- <view class="t-a">
					<text class="txt">验证码</text>
					<input type="pass" name="code" maxlength="18" placeholder="请输入验证码" v-model="pass" />
					<image src="../../static/yzm.png" style="height: 60px;width: 80px;" mode=""></image>
				</view> -->
				
				<button @tap="Register()">注 册</button>
				<view @tap="goLogin()">已有账号，登录</view>
			</form>
		</view>
	</view>
</template>

<script>
	import { register } from "./register/register.js"
export default {
	data() {
		return {
			registerForm:{
				username:"",//名称
				phonenumber: "",//电话
				ID6:"",//后六位
				password: "",//密码
				confirmPassword: "",//确认密码
				roleId: 0,
				nickname: " "//角色
			}
			
		};
	},
	methods: {
		goLogin() {
			uni.navigateTo({
				url: '/pages/login/login'
			});
		},
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
		async Register(){
			// 检查负责人名称是否为空
			if (!this.registerForm.username) {
			    return uni.showToast({
			        title: '负责人名称不能为空',
			        icon: 'none',
			    });
			}
				
			// 检查密码是否一致
			if (this.registerForm.password !== this.registerForm.confirmPassword) {
			    return uni.showToast({
			        title: '两次输入的密码不一致',
			        icon: 'none',
			    });
			}
				
			// 检查联系电话是否为空以及格式是否正确
			if (!this.registerForm.phonenumber) {
			    return uni.showToast({
			        title: '联系电话不能为空',
			        icon: 'none',
			    });
			}
			const phoneRegex = /^1[345789]\d{9}$/;
			if (!phoneRegex.test(this.registerForm.phonenumber)) {
			    return uni.showToast({
			        title: '联系电话格式不正确，必须为11位数字，以13、14、15、16、17、18或19开头',
			        icon: 'none',
			    });
			}
				       
			if (!this.registerForm.ID6) {
			    return uni.showToast({
			        title: '请输入您的身份证后六位',
			        icon: 'none',
			    });
			}
			// 检查密码是否为空
			if (!this.registerForm.password) {
			    return uni.showToast({
			        title: '密码不能为空',
			        icon: 'none',
			    });
			}
			
			console.log(this.registerForm);
			// if(this.registerForm.roleId.equals("施工管理人员")){
			// 	this.registerForm.roleId=101;
			// };
			// if(this.registerForm.roleId.equals("设计管理人员")){
			// 	this.registerForm.roleId=101;
			// };
			const res = await register(this.registerForm)
			console.log(res);
			// .then(response=>{
			// 	console.log("注册成功");
			// }).catch(error=>{
			// 	console.log("失败",error);
			// })
			
		},
	//     register() {
	//         // 检查负责人名称是否为空
	//         if (!this.registerName) {
	//             return uni.showToast({
	//                 title: '负责人名称不能为空',
	//                 icon: 'none',
	//             });
	//         }
	
	//         // 检查密码是否一致
	//         if (this.registerPassword !== this.registerConfirmPassword) {
	//             return uni.showToast({
	//                 title: '两次输入的密码不一致',
	//                 icon: 'none',
	//             });
	//         }
	
	//         // 检查联系电话是否为空以及格式是否正确
	//         if (!this.registerPhone) {
	//             return uni.showToast({
	//                 title: '联系电话不能为空',
	//                 icon: 'none',
	//             });
	//         }
	//         const phoneRegex = /^1[345789]\d{9}$/;
	//         if (!phoneRegex.test(this.registerPhone)) {
	//             return uni.showToast({
	//                 title: '联系电话格式不正确，必须为11位数字，以13、14、15、16、17、18或19开头',
	//                 icon: 'none',
	//             });
	//         }
	       
	// 		if (!this.registerId) {
	// 		    return uni.showToast({
	// 		        title: '请输入您的身份证后六位',
	// 		        icon: 'none',
	// 		    });
	// 		}
	//         // 检查密码是否为空
	//         if (!this.registerPassword) {
	//             return uni.showToast({
	//                 title: '密码不能为空',
	//                 icon: 'none',
	//             });
	//         }
	// 		if(!this.pass){
	// 			return this.$refs.uToast.show({
	// 				title: '验证码错误',
	// 				type: 'warning',
	// 			})
	// 		}
	        // 如果所有验证都通过，执行注册逻辑
	        // 例如，发送数据到服务器进行注册
	        // this.submitRegistration();
	     //    uni.showToast({
	     //                title: '注册成功',
	     //                icon: 'success',
	     //                success: () => {
	     //                    // 延迟1秒进行跳转（例如，等待提示消失）
	     //                    setTimeout(() => {
	     //                        // 注册成功后跳转至登录界面
	     //                        uni.navigateTo({
	     //                            url: '/pages/login/login',
	     //                            success: function () {
	     //                                console.log('注册并跳转至登录界面成功');
	     //                            },
	     //                            fail: function (err) {
	     //                                console.error('跳转至登录界面失败', err);
	     //                                uni.showToast({
	     //                                    title: '跳转登录界面失败',
	     //                                    icon: 'none'
	     //                                });
	     //                            }
	     //                        });
	     //                    }, 1000);
						//  }
						// });
	    // },
	}
};
</script>

<style>
.register-view {
	background-color: #ffffff;
}

.img-a {
	width: 100%;
	height: 450rpx;
	background-image: url(../login/sy.jpg);
	background-size: 100%;
}
.t-b {
	text-align: left;
	font-size: 42rpx;
	color: #ffffff;
	padding: 130rpx 0 0 70rpx;
	font-weight: bold;
	line-height: 70rpx;
}
.register-form {
	width: 100%;
	padding:20rpx 20rpx;
	position: relative;
	margin-top: -120rpx;
	background-color: #ffffff;
	border-radius: 8% 8% 0% 0;
}

.t-a {
	margin-left:40rpx ;
	/* border: 1px solid black; */
	position: relative;
}

.t-a .txt {
	/* border: 1px solid red; */
	font-size: 32rpx;
	font-weight: bold;
	color: #333333;
}

.t-a input {
	height: 90rpx;
	margin-bottom: 20rpx;
	border-bottom: 1px solid #e9e9e9;
	font-size: 28rpx;
}

button {
	font-size: 28rpx;
	background: #009d00;
	color: #fff;
	height: 90rpx;
	line-height: 90rpx;
	border-radius: 50rpx;
	font-weight: bold;
	margin-top: 30rpx;
}

/* 选择框的基本样式 */
.custom-select{
  margin-right: 60rpx;
  font-weight: bold;
  font-size: 33rpx; /* 设置字体大小 */
  color: #333; /* 设置字体颜色 */
  height: 40px; /* 设置选择框的高度 */
  line-height: 40px; /* 设置行高 */
  
}



</style>