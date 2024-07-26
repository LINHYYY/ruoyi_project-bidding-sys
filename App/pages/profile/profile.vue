<template>
	<view class="u-m-20">
		<u-navbar :is-back="true" title="个人信息" :border-bottom="false"></u-navbar>
		<view>
			<u-cell-group>
				<u-cell-item title="头像" :arrow="true" hover-class="none" @click="updateAvatar">
					<u-avatar :src="pic" size="100"></u-avatar>
				</u-cell-item>
			</u-cell-group>
			<u-cell-group>
				<u-cell-item title="用户姓名" :arrow="true" hover-class="none" @click="updateName">
					{{vuex_user.user.nickName}}
				</u-cell-item>
<!-- 				<u-cell-item title="电话号码" :arrow="true" hover-class="none" @click="updatePhone">
					{{vuex_user.user.nickPhone}}
				</u-cell-item>
				<u-cell-item title="公司名称" :arrow="true" hover-class="none" @click="updateCompany">
					{{vuex_user.user.nickCompany}}
				</u-cell-item>
				<u-cell-item title="自我介绍" :arrow="true" hover-class="none" @click="updateIntroduction">
					{{vuex_user.user.nickIntroduction}}
				</u-cell-item>
				<u-cell-item title="身份证后6位" :arrow="true" hover-class="none" @click="updateID">
					{{vuex_user.user.nickID}}
				</u-cell-item>
				<u-cell-item title="用户类别" :arrow="true" hover-class="none" @click="updateType">
					{{vuex_user.user.nickType}}
				</u-cell-item> -->
			</u-cell-group>
		</view>
		
		<u-modal v-model="showModel" @confirm="confirmName" ref="uModal" :async-close="true"
		title="设置昵称">
			<view class="slot-content">
				<u-input v-model="nickName" type="text" :border="false" placeholder="请输入昵称"/>
			</view>
<!-- 		</u-modal>
		<u-modal v-model="showModel" @confirm="confirmPhone" ref="uModal" :async-close="true"
		title="填写电话号码">
			<view class="slot-content">
				<u-input v-model="nickPhone" type="text" :border="false" placeholder="请输入电话号码"/>
			</view>
		</u-modal>
		</u-modal>
		<u-modal v-model="showModel" @confirm="confirmCompany" ref="uModal" :async-close="true"
		title="填写公司名称">
			<view class="slot-content">
				<u-input v-model="nickCompany" type="text" :border="false" placeholder="请输入公司名称"/>
			</view>
		</u-modal>
		</u-modal>
		<u-modal v-model="showModel" @confirm="confirmIntroduction" ref="uModal" :async-close="true"
		title="填写自我介绍">
			<view class="slot-content">
				<u-input v-model="nickIntroduction" type="text" :border="false" placeholder="请输入自我介绍"/>
			</view>
		</u-modal>
		
		<u-modal v-model="showModel" @confirm="confirmID" ref="uModal" :async-close="true"
		title="填写身份证后6位">
			<view class="slot-content">
				<u-input v-model="nickID" type="text" :border="false" placeholder="请输入身份证后6位"/>
			</view>
		</u-modal>
		<u-modal v-model="showModel" @confirm="confirmType" ref="uModal" :async-close="true"
				title="填写用户类别">
					<view class="slot-content">
					<u-input v-model="nickType" type="text" :border="false" placeholder="请输入用户类别"/>
			</view> -->
		</u-modal>

		<!-- <view class="u-m-t-20">
			<u-button type="primary" @click="subProfile">提交</u-button>
		</view> -->
		
		<!-- 如果是微信登录小程序，则获取用户的昵称与头像 -->
		<!-- #ifdef MP-WEIXIN -->  
			<!-- <u-button type="default">使用微信头像与昵称</u-button> -->
		<!-- #endif -->
	</view>
</template>

<script>
	import config from "@/common/config.js" // 全局配置文件
	export default {
		data() {
			return {
				pic:uni.getStorageSync('lifeData').vuex_user.user.avatar.includes(config.staticUrl)?uni.getStorageSync('lifeData').vuex_user.user.avatar:config.staticUrl+uni.getStorageSync('lifeData').vuex_user.user.avatar,
				nickName:null,
				showModel:false,
			}
		},
		methods: {
			updateName(){
				this.showModel = true;
			},
			// updatePhone(){
			// 	this.showModel = true;
			// },
			// updateCompany(){
			// 	this.showModel = true;
			// },
			// updateIntroduction(){
			// 	this.showModel = true;
			// },
			// updateID(){
			// 	this.showModel = true;
			// },
			// updateType(){
			// 	this.showModel = true;
			// },
			confirmName() {
				if(!this.nickName){
					this.showModel = false;
					return this.$mytip.toast('请输入昵称')
				}
				let url = "api/profile/updateProfile";
				let vuex_user = uni.getStorageSync('lifeData').vuex_user
				let user = vuex_user.user
				let userId = user.userId;
				this.$u.put(url,{
					userId:userId,
					nickName:this.nickName,
				}).then(data => {
					// 关闭
					user.nickName = this.nickName
					this.$u.vuex('vuex_user', vuex_user);
					this.showModel = false;
					this.$mytip.toast('昵称修改成功')
				});
			},
			// confirmPhone() {
			// 	if(!this.nickPhone){
			// 		this.showModel = false;
			// 		return this.$mytip.toast('请输入电话号码')
			// 	}
			// 	let url = "api/profile/updateProfile";
			// 	let vuex_user = uni.getStorageSync('lifeData').vuex_user
			// 	let user = vuex_user.user
			// 	let userId = user.userId;
			// 	this.$u.put(url,{
			// 		userId:userId,
			// 		nickPhone:this.nickPhone,
			// 	}).then(data => {
			// 		// 关闭
			// 		user.nickPhone = this.nickPhone
			// 		this.$u.vuex('vuex_user', vuex_user);
			// 		this.showModel = false;
			// 		this.$mytip.toast('电话号码修改成功')
			// 	});
			// },
			// confirmCompany() {
			// 	if(!this.nickCompany){
			// 		this.showModel = false;
			// 		return this.$mytip.toast('请输入公司名称')
			// 	}
			// 	let url = "api/profile/updateProfile";
			// 	let vuex_user = uni.getStorageSync('lifeData').vuex_user
			// 	let user = vuex_user.user
			// 	let userId = user.userId;
			// 	this.$u.put(url,{
			// 		userId:userId,
			// 		nickCompany:this.nickCompany,
			// 	}).then(data => {
			// 		// 关闭
			// 		user.nickCompany = this.nickCompany
			// 		this.$u.vuex('vuex_user', vuex_user);
			// 		this.showModel = false;
			// 		this.$mytip.toast('公司名称修改成功')
			// 	});
			// },
			// confirmIntroduction() {
			// 	if(!this.nickIntroduction){
			// 		this.showModel = false;
			// 		return this.$mytip.toast('请输入自我介绍')
			// 	}
			// 	let url = "api/profile/updateProfile";
			// 	let vuex_user = uni.getStorageSync('lifeData').vuex_user
			// 	let user = vuex_user.user
			// 	let userId = user.userId;
			// 	this.$u.put(url,{
			// 		userId:userId,
			// 		nickIntroduction:this.nickIntroduction,
			// 	}).then(data => {
			// 		// 关闭
			// 		user.nickIntroduction = this.nickIntroduction
			// 		this.$u.vuex('vuex_user', vuex_user);
			// 		this.showModel = false;
			// 		this.$mytip.toast('自我介绍修改成功')
			// 	});
			// },
			// confirmID() {
			// 	if(!this.nickID){
			// 		this.showModel = false;
			// 		return this.$mytip.toast('请输入身份证后6位')
			// 	}
			// 	let url = "api/profile/updateProfile";
			// 	let vuex_user = uni.getStorageSync('lifeData').vuex_user
			// 	let user = vuex_user.user
			// 	let userId = user.userId;
			// 	this.$u.put(url,{
			// 		userId:userId,
			// 		nickID:this.nickID,
			// 	}).then(data => {
			// 		// 关闭
			// 		user.nickID = this.nickID
			// 		this.$u.vuex('vuex_user', vuex_user);
			// 		this.showModel = false;
			// 		this.$mytip.toast('身份证后6位修改成功')
			// 	});
			// },
			// confirmTpye() {
			// 	if(!this.nickTpye){
			// 		this.showModel = false;
			// 		return this.$mytip.toast('请输入用户类别')
			// 	}
			// 	let url = "api/profile/updateProfile";
			// 	let vuex_user = uni.getStorageSync('lifeData').vuex_user
			// 	let user = vuex_user.user
			// 	let userId = user.userId;
			// 	this.$u.put(url,{
			// 		userId:userId,
			// 		nickTpye:this.nickTpye,
			// 	}).then(data => {
			// 		// 关闭
			// 		user.nickTpye = this.nickTpye
			// 		this.$u.vuex('vuex_user', vuex_user);
			// 		this.showModel = false;
			// 		this.$mytip.toast('用户类别修改成功')
			// 	});
			// },
			updateAvatar(){
				this.$u.route('/pages/profile/avatar')
			},
			subProfile(){
				// 1.更新vuex中的用户信息
				this.$mytip.toast('修改成功')
				// 2.页面跳转
				uni.switchTab({
					url: '/pages/center/center'
				})
			},
			getUserProfile(){
				// 如果是微信登录小程序，则获取用户的昵称与头像
				// #ifdef MP-WEIXIN
					// 此处执行微信才执行
				// #endif
			    var that = this;
			    uni.getUserProfile({
			        desc: '获取您的微信信息以授权小程序',
			        lang: 'zh_CN',
			        success: UserProfileRes => {  
			            console.log(UserProfileRes)
			            uni.login({
			                provider: 'weixin',
			                success: function(loginRes) {
			                    let avatarUrl = UserProfileRes.userInfo.avatarUrl; //用户头像
			                    let nickName = UserProfileRes.userInfo.nickName; //用户微信名
			                    let gender = UserProfileRes.userInfo.gender;//性别
								console.log(avatarUrl);
								// 去修改用户的昵称与头像
			                },
			                fail(err) {
			                    console.log(err)
			                }
			            });
			        },
			        fail:err=>{
			            console.log(err)
			        }
			    })
			}
		}
	}
</script>

<style lang="scss" scoped>
	.slot-content{
		padding: 40rpx;
	}
</style>