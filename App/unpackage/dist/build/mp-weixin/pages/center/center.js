(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/center/center"],{"08a0":function(e,t,n){"use strict";n.r(t);var u=n("1c1f"),a=n.n(u);for(var r in u)["default"].indexOf(r)<0&&function(e){n.d(t,e,(function(){return u[e]}))}(r);t["default"]=a.a},"0a9a":function(e,t,n){"use strict";n.r(t);var u=n("2479"),a=n("08a0");for(var r in a)["default"].indexOf(r)<0&&function(e){n.d(t,e,(function(){return a[e]}))}(r);n("4f55");var i=n("f0c5"),o=Object(i["a"])(a["default"],u["b"],u["c"],!1,null,null,null,!1,u["a"],void 0);t["default"]=o.exports},"1c1f":function(e,t,n){"use strict";(function(e){var u=n("4ea4");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var a=u(n("7624")),r={data:function(){return{avatar:e.getStorageSync("lifeData").vuex_user.user.avatar.includes(a.default.staticUrl)?e.getStorageSync("lifeData").vuex_user.user.avatar:a.default.staticUrl+e.getStorageSync("lifeData").vuex_user.user.avatar,show:!0,navList:[{name:"浏览记录",icon:"checkmark-circle",url:"pages/center/history"},{name:"我的收藏",icon:"heart",url:"pages/center/heart"},{name:"我的委托",icon:"order",url:"pages/center/order"},{name:"问题反馈",icon:"info-circle"}]}},onLoad:function(){var t=this;e.$on("updateAvatar",(function(e){t.updateAvatar()}))},onUnload:function(){e.$off("updateAvatar")},onShow:function(){this.checkToken()},methods:{logout:function(){this.$u.vuex("vuex_token",""),this.$u.vuex("vuex_user",{}),this.$u.route("/pages/login/login")},profile:function(){this.$u.route("/pages/profile/profile")},setting:function(){this.$u.route("/pages/profile/setting")},callPhoneNumber:function(){e.makePhoneCall({phoneNumber:"18720989281"})},problem:function(){this.$u.route({url:"pages/login/problem"})},aboutMe:function(){this.$u.route("/pages/profile/aboutMe")},checkToken:function(){var t=e.getStorageSync("lifeData"),n=t.vuex_token;if(!n)return e.reLaunch({url:"../login/login"});this.$u.get("/api/profile/isExpiration",{token:n}).then((function(t){if(t.data)return e.reLaunch({url:"../login/login"})}))},code:function(){this.$mytip.toast("敬请期待")},clickNav:function(e){e?this.$u.route(e):this.$mytip.toast("敬请期待")},updateAvatar:function(){this.avatar=e.getStorageSync("lifeData").vuex_user.user.avatar.includes(a.default.staticUrl)?e.getStorageSync("lifeData").vuex_user.user.avatar:a.default.staticUrl+e.getStorageSync("lifeData").vuex_user.user.avatar},moreInfo:function(){this.$u.route("/pages/webview/webview",{title:"关于我们",src:"https://mp.weixin.qq.com/s/quuexyNJ0NHe8r2ZRLGB5g"})}}};t.default=r}).call(this,n("543d")["default"])},2479:function(e,t,n){"use strict";n.d(t,"b",(function(){return a})),n.d(t,"c",(function(){return r})),n.d(t,"a",(function(){return u}));var u={uNavbar:function(){return n.e("uview-ui/components/u-navbar/u-navbar").then(n.bind(null,"cb23"))},uAvatar:function(){return n.e("uview-ui/components/u-avatar/u-avatar").then(n.bind(null,"a93f"))},uRow:function(){return n.e("uview-ui/components/u-row/u-row").then(n.bind(null,"b03c"))},uCol:function(){return n.e("uview-ui/components/u-col/u-col").then(n.bind(null,"90d2"))},uIcon:function(){return n.e("uview-ui/components/u-icon/u-icon").then(n.bind(null,"ef11"))},uCellGroup:function(){return n.e("uview-ui/components/u-cell-group/u-cell-group").then(n.bind(null,"1fc3"))},uCellItem:function(){return n.e("uview-ui/components/u-cell-item/u-cell-item").then(n.bind(null,"3e8a"))}},a=function(){var e=this.$createElement;this._self._c},r=[]},"46ae":function(e,t,n){},"4f55":function(e,t,n){"use strict";var u=n("46ae"),a=n.n(u);a.a},"7fc1":function(e,t,n){"use strict";(function(e,t){var u=n("4ea4");n("104b");u(n("66fd"));var a=u(n("0a9a"));e.__webpack_require_UNI_MP_PLUGIN__=n,t(a.default)}).call(this,n("bc2e")["default"],n("543d")["createPage"])}},[["7fc1","common/runtime","common/vendor"]]]);