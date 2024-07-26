<!-- <template>
  <div>
    <router-tabbar :tab-bar-list="tabBarList" @click="handleTabBarClick"></router-tabbar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tabBarList: [
        {
         "pagePath": "pages/index/index",
         "iconPath": "static/index.png",
         "selectedIconPath": "static/index-selected.png",
         "text": "首页"
        },
        {
         "pagePath": "pages/center/center",
         "iconPath": "static/center.png",
         "selectedIconPath": "static/center-selected.png",
         "text": "我的"
        }
      ]
    }
  },
  methods: {
  		handleTabBarClick(item) {
  		  // 在这里做判断
  		  if (item.pagePath === '/pages/center/center') {
  			lifeData = uni.getStorageSync('lifeData');
  			token = lifeData.vuex_token;
  			console.log(token)
  			if (!token) {
  				// 没有token 则跳转到登录
  				uni.reLaunch({
  					url: '../login/login'
  				});
  				
  				return false // 阻止跳转
  			} else {
  				// // 判断Token是否有效
  				// let url = "/api/profile/isExpiration";
  				// this.$u.get(url, {
  				// 	token: token
  				// }).then(obj => {
  				// 	if (obj.data) {
  				// 		// Token过期则跳转到登录
  				// 		uni.reLaunch({
  				// 			url: '../login/login'
  				// 		});
  				// 	} else {
  						// Token有效，跳转到发布项目页面
  					this.$u.route('/pages/center/center');
  					// }
  				// });
  			}
  		  }
  		}
  	}
  } -->
<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
		}
</script>

<style lang="scss">
	@import "uview-ui/index.scss";
</style>
