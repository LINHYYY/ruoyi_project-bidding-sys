(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-login-code"],{"0c27":function(t,e,a){"use strict";a.r(e);var n=a("f4ed"),i=a("9c0c");for(var o in i)"default"!==o&&function(t){a.d(e,t,(function(){return i[t]}))}(o);a("8bd0");var r,c=a("f0c5"),u=Object(c["a"])(i["default"],n["b"],n["c"],!1,null,"ab731c8c",null,!1,n["a"],r);e["default"]=u.exports},1644:function(t,e,a){"use strict";a.d(e,"b",(function(){return i})),a.d(e,"c",(function(){return o})),a.d(e,"a",(function(){return n}));var n={uMessageInput:a("0c27").default},i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-uni-view",{staticClass:"wrap"},[a("v-uni-view",{staticClass:"key-input"},[a("v-uni-view",{staticClass:"title"},[t._v("输入验证码")]),a("v-uni-view",{staticClass:"tips"},[t._v("验证码已发送至 "+t._s(t.loginName))]),a("u-message-input",{attrs:{focus:!0,value:t.value,mode:"bottomLine",maxlength:t.maxlength},on:{change:function(e){arguments[0]=e=t.$handleEvent(e),t.change.apply(void 0,arguments)},finish:function(e){arguments[0]=e=t.$handleEvent(e),t.finish.apply(void 0,arguments)}}}),a("v-uni-text",{class:{error:t.error}},[t._v("验证码错误，请重新输入")]),a("v-uni-view",{staticClass:"captcha"},[a("v-uni-text",{class:{noCaptcha:t.show},on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.noCaptcha.apply(void 0,arguments)}}},[t._v("收不到验证码点这里")]),a("v-uni-text",{class:{regain:!t.show}},[t._v(t._s(t.second)+"秒后重新获取验证码")])],1)],1)],1)},o=[]},"58bf":function(t,e,a){var n=a("24fb");e=n(!1),e.push([t.i,'@charset "UTF-8";\r\n/**\r\n * 下方引入的为uView UI的集成样式文件，为scss预处理器，其中包含了一些"u-"开头的自定义变量\r\n * uView自定义的css类名和scss变量，均以"u-"开头，不会造成冲突，请放心使用 \r\n */.wrap[data-v-10c02c42]{padding:%?80?%}.box[data-v-10c02c42]{margin:%?30?% 0;font-size:%?30?%;color:555}.key-input[data-v-10c02c42]{padding:%?30?% 0}.key-input uni-text[data-v-10c02c42]{display:none}.key-input .error[data-v-10c02c42]{display:block;color:red;font-size:%?30?%;margin:%?20?% 0}.title[data-v-10c02c42]{font-size:%?50?%;color:#333}.key-input .tips[data-v-10c02c42]{font-size:%?30?%;color:#333;margin-top:%?20?%;margin-bottom:%?60?%}.captcha[data-v-10c02c42]{color:#f90;font-size:%?30?%;margin-top:%?40?%}.captcha .noCaptcha[data-v-10c02c42]{display:block}.captcha .regain[data-v-10c02c42]{display:block}',""]),t.exports=e},"66ca":function(t,e,a){var n=a("58bf");n.__esModule&&(n=n.default),"string"===typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);var i=a("4f06").default;i("f98379a0",n,!0,{sourceMap:!1,shadowMode:!1})},"7e4e":function(t,e,a){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var a={data:function(){return{maxlength:4,value:"",second:60,show:!1,error:!1,loginName:""}},onLoad:function(t){var e=this;this.loginName=t.loginName,this.getCaptcha();var a=setInterval((function(){e.second--,e.second<=0&&(e.show=!0,4!=e.value.lenth&&(e.error=!0),clearInterval(a))}),1e3)},methods:{getCaptcha:function(){t("log","用户===>"+this.loginName," at pages/login/code.vue:46");var e="/api/captchaSms";this.$u.get(e,{loginName:this.loginName}).then((function(e){t("log","验证码发送"+e.send," at pages/login/code.vue:51")}))},noCaptcha:function(){var t=this;uni.showActionSheet({itemList:["重新获取验证码"],success:function(e){t.getCaptcha(),t.$mytip.toast("验证码发送成功")},fail:function(t){}})},change:function(t){},finish:function(t){var e=this,a="/api/thirdRegister";this.$u.post(a,{username:this.loginName,code:t}).then((function(t){e.$u.vuex("vuex_token",t.token),e.$u.vuex("vuex_user",t.loginUser),uni.switchTab({url:"/pages/index/index"})}))}}};e.default=a}).call(this,a("0de9")["log"])},"8bd0":function(t,e,a){"use strict";var n=a("d61a"),i=a.n(n);i.a},"93b5":function(t,e,a){"use strict";a.r(e);var n=a("1644"),i=a("b46d");for(var o in i)"default"!==o&&function(t){a.d(e,t,(function(){return i[t]}))}(o);a("a342");var r,c=a("f0c5"),u=Object(c["a"])(i["default"],n["b"],n["c"],!1,null,"10c02c42",null,!1,n["a"],r);e["default"]=u.exports},"9a5d":function(t,e,a){"use strict";a("a9e3"),a("ac1f"),a("1276"),Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var n={name:"u-message-input",props:{maxlength:{type:[Number,String],default:4},dotFill:{type:Boolean,default:!1},mode:{type:String,default:"box"},value:{type:[String,Number],default:""},breathe:{type:Boolean,default:!0},focus:{type:Boolean,default:!1},bold:{type:Boolean,default:!1},fontSize:{type:[String,Number],default:60},activeColor:{type:String,default:"#2979ff"},inactiveColor:{type:String,default:"#606266"},width:{type:[Number,String],default:"80"},disabledKeyboard:{type:Boolean,default:!1}},watch:{value:{immediate:!0,handler:function(t){t=String(t),this.valueModel=t.substring(0,this.maxlength)}}},data:function(){return{valueModel:""}},computed:{animationClass:function(){var t=this;return function(e){return t.breathe&&t.charArr.length==e?"u-breathe":""}},charArr:function(){return this.valueModel.split("")},charArrLength:function(){return this.charArr.length},loopCharArr:function(){return new Array(this.maxlength)}},methods:{getVal:function(t){var e=t.detail.value;this.valueModel=e,String(e).length>this.maxlength||(this.$emit("change",e),String(e).length==this.maxlength&&this.$emit("finish",e))}}};e.default=n},"9c0c":function(t,e,a){"use strict";a.r(e);var n=a("9a5d"),i=a.n(n);for(var o in n)"default"!==o&&function(t){a.d(e,t,(function(){return n[t]}))}(o);e["default"]=i.a},a342:function(t,e,a){"use strict";var n=a("66ca"),i=a.n(n);i.a},b14e:function(t,e,a){var n=a("24fb");e=n(!1),e.push([t.i,'@charset "UTF-8";\r\n/**\r\n * 下方引入的为uView UI的集成样式文件，为scss预处理器，其中包含了一些"u-"开头的自定义变量\r\n * uView自定义的css类名和scss变量，均以"u-"开头，不会造成冲突，请放心使用 \r\n */@-webkit-keyframes breathe-data-v-ab731c8c{0%{opacity:.3}50%{opacity:1}100%{opacity:.3}}@keyframes breathe-data-v-ab731c8c{0%{opacity:.3}50%{opacity:1}100%{opacity:.3}}.u-char-box[data-v-ab731c8c]{text-align:center}.u-char-flex[data-v-ab731c8c]{display:flex;flex-direction:row;justify-content:center;flex-wrap:wrap;position:relative}.u-input[data-v-ab731c8c]{position:absolute;top:0;left:-100%;width:200%;height:100%;text-align:left;z-index:9;opacity:0;background:none}.u-char-item[data-v-ab731c8c]{position:relative;width:%?90?%;height:%?90?%;margin:%?10?% %?10?%;font-size:%?60?%;font-weight:700;color:#303133;line-height:%?90?%;display:flex;flex-direction:row;justify-content:center;align-items:center}.u-middle-line[data-v-ab731c8c]{border:none}.u-box[data-v-ab731c8c]{box-sizing:border-box;border:%?2?% solid #ccc;border-radius:%?6?%}.u-box-active[data-v-ab731c8c]{overflow:hidden;-webkit-animation-timing-function:ease-in-out;animation-timing-function:ease-in-out;-webkit-animation-duration:1.5s;animation-duration:1.5s;-webkit-animation-iteration-count:infinite;animation-iteration-count:infinite;-webkit-animation-direction:alternate;animation-direction:alternate;border:%?2?% solid #2979ff}.u-middle-line-active[data-v-ab731c8c]{background:#2979ff}.u-breathe[data-v-ab731c8c]{-webkit-animation:breathe-data-v-ab731c8c 2s infinite ease;animation:breathe-data-v-ab731c8c 2s infinite ease}.u-placeholder-line[data-v-ab731c8c]{display:none;position:absolute;left:50%;top:50%;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%);width:%?2?%;height:%?40?%;background:#333;-webkit-animation:twinkling 1.5s infinite ease;animation:twinkling 1.5s infinite ease}.u-animation-breathe[data-v-ab731c8c]{-webkit-animation-name:breathe-data-v-ab731c8c;animation-name:breathe-data-v-ab731c8c}.u-dot[data-v-ab731c8c]{font-size:%?34?%;line-height:%?34?%}.u-middle-line[data-v-ab731c8c]{height:4px;background:#000;width:80%;position:absolute;border-radius:2px;top:50%;left:50%;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%)}.u-buttom-line-active[data-v-ab731c8c]{background:#2979ff}.u-bottom-line[data-v-ab731c8c]{height:4px;background:#000;width:80%;position:absolute;border-radius:2px;bottom:0;left:50%;-webkit-transform:translate(-50%);transform:translate(-50%)}',""]),t.exports=e},b46d:function(t,e,a){"use strict";a.r(e);var n=a("7e4e"),i=a.n(n);for(var o in n)"default"!==o&&function(t){a.d(e,t,(function(){return n[t]}))}(o);e["default"]=i.a},d61a:function(t,e,a){var n=a("b14e");n.__esModule&&(n=n.default),"string"===typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);var i=a("4f06").default;i("8e1bea4c",n,!0,{sourceMap:!1,shadowMode:!1})},f4ed:function(t,e,a){"use strict";var n;a.d(e,"b",(function(){return i})),a.d(e,"c",(function(){return o})),a.d(e,"a",(function(){return n}));var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-uni-view",{staticClass:"u-char-box"},[a("v-uni-view",{staticClass:"u-char-flex"},[a("v-uni-input",{staticClass:"u-input",attrs:{disabled:t.disabledKeyboard,value:t.valueModel,type:"number",focus:t.focus,maxlength:t.maxlength},on:{input:function(e){arguments[0]=e=t.$handleEvent(e),t.getVal.apply(void 0,arguments)}}}),t._l(t.loopCharArr,(function(e,n){return a("v-uni-view",{key:n},[a("v-uni-view",{class:[t.breathe&&t.charArrLength==n?"u-breathe":"","u-char-item",t.charArrLength===n&&"box"==t.mode?"u-box-active":"","box"===t.mode?"u-box":""],style:{fontWeight:t.bold?"bold":"normal",fontSize:t.fontSize+"rpx",width:t.width+"rpx",height:t.width+"rpx",color:t.inactiveColor,borderColor:t.charArrLength===n&&"box"==t.mode?t.activeColor:t.inactiveColor}},["middleLine"!==t.mode?a("v-uni-view",{staticClass:"u-placeholder-line",style:{display:t.charArrLength===n?"block":"none",height:.5*t.width+"rpx"}}):t._e(),"middleLine"===t.mode&&t.charArrLength<=n?a("v-uni-view",{staticClass:"u-middle-line",class:[t.breathe&&t.charArrLength==n?"u-breathe":"",t.charArrLength===n?"u-middle-line-active":""],style:{height:t.bold?"4px":"2px",background:t.charArrLength===n?t.activeColor:t.inactiveColor}}):t._e(),"bottomLine"===t.mode?a("v-uni-view",{staticClass:"u-bottom-line",class:[t.breathe&&t.charArrLength==n?"u-breathe":"",t.charArrLength===n?"u-buttom-line-active":""],style:{height:t.bold?"4px":"2px",background:t.charArrLength===n?t.activeColor:t.inactiveColor}}):t._e(),t.dotFill?[a("v-uni-text",{staticClass:"u-dot"},[t._v(t._s(t.charArr[n]?"●":""))])]:[t._v(t._s(t.charArr[n]?t.charArr[n]:""))]],2)],1)}))],2)],1)},o=[]}}]);