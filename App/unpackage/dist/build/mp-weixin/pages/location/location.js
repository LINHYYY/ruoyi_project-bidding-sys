(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/location/location"],{6467:function(t,n,i){"use strict";(function(t,n){var e=i("4ea4");i("104b");e(i("66fd"));var a=e(i("c464"));t.__webpack_require_UNI_MP_PLUGIN__=i,n(a.default)}).call(this,i("bc2e")["default"],i("543d")["createPage"])},9612:function(t,n,i){},a51a:function(t,n,i){"use strict";i.r(n);var e=i("fbba"),a=i.n(e);for(var u in e)["default"].indexOf(u)<0&&function(t){i.d(n,t,(function(){return e[t]}))}(u);n["default"]=a.a},c464:function(t,n,i){"use strict";i.r(n);var e=i("cd25"),a=i("a51a");for(var u in a)["default"].indexOf(u)<0&&function(t){i.d(n,t,(function(){return a[t]}))}(u);i("dc24");var c=i("f0c5"),o=Object(c["a"])(a["default"],e["b"],e["c"],!1,null,"00f3e428",null,!1,e["a"],void 0);n["default"]=o.exports},cd25:function(t,n,i){"use strict";i.d(n,"b",(function(){return a})),i.d(n,"c",(function(){return u})),i.d(n,"a",(function(){return e}));var e={uNavbar:function(){return i.e("uview-ui/components/u-navbar/u-navbar").then(i.bind(null,"cb23"))}},a=function(){var t=this.$createElement;this._self._c},u=[]},dc24:function(t,n,i){"use strict";var e=i("9612"),a=i.n(e);a.a},fbba:function(t,n,i){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var i={data:function(){return{locationCity:"",hotList:["杭州市","郑州市","北京市","上海市","广州市","深圳市"]}},onLoad:function(){this.findLocation()},methods:{clickCity:function(n){return this.$u.vuex("vuex_city",n),t.reLaunch({url:"../index/index"})},setLocation:function(){return this.$u.vuex("vuex_city",this.locationCity),t.reLaunch({url:"../index/index"})},findLocation:function(){var n=this,i=this;t.showLoading({title:"定位中....",mask:!0}),this.$u.get("/api/profile/getRealCity").then((function(e){var a=e.msg;if(a){n.locationCity=a;var u=t.getStorageSync("lifeData"),c=u.vuex_city;c||n.$u.vuex("vuex_city",a),t.hideLoading()}else t.request({url:"https://tianqiapi.com/ip?version=v9&appid=23035354&appsecret=8YvlPNrz",success:function(n){var e=n.data.ip;i.$u.get("/api/profile/getRealCityByIP?ip="+e).then((function(n){var e=n.msg;if(e){i.locationCity=e;var a=t.getStorageSync("lifeData"),u=a.vuex_city;u||i.$u.vuex("vuex_city",e),t.hideLoading()}else i.$mytip.toast("定位失败");t.hideLoading()}))}})}))}}};n.default=i}).call(this,i("543d")["default"])}},[["6467","common/runtime","common/vendor"]]]);