(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-center-tips"],{"049b":function(t,e,a){"use strict";a.r(e);var n=a("7d55"),r=a("30ae");for(var o in r)"default"!==o&&function(t){a.d(e,t,(function(){return r[t]}))}(o);a("a1a8"),a("8d24");var u,c=a("f0c5"),i=Object(c["a"])(r["default"],n["b"],n["c"],!1,null,"56e3f787",null,!1,n["a"],u);e["default"]=i.exports},"30ae":function(t,e,a){"use strict";a.r(e);var n=a("a2cc"),r=a.n(n);for(var o in n)"default"!==o&&function(t){a.d(e,t,(function(){return n[t]}))}(o);e["default"]=r.a},3333:function(t,e,a){var n=a("5914");n.__esModule&&(n=n.default),"string"===typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);var r=a("4f06").default;r("c983d742",n,!0,{sourceMap:!1,shadowMode:!1})},5914:function(t,e,a){var n=a("24fb");e=n(!1),e.push([t.i,'@charset "UTF-8";\r\n/**\r\n * 下方引入的为uView UI的集成样式文件，为scss预处理器，其中包含了一些"u-"开头的自定义变量\r\n * uView自定义的css类名和scss变量，均以"u-"开头，不会造成冲突，请放心使用 \r\n */.u-content[data-v-56e3f787]{font-size:%?34?%;color:#303133;line-height:1.8;white-space:pre-wrap!important}',""]),t.exports=e},"7c83":function(t,e,a){var n=a("24fb");e=n(!1),e.push([t.i,"uni-page-body[data-v-56e3f787]{background-color:#fff}body.?%PAGE?%[data-v-56e3f787]{background-color:#fff}",""]),t.exports=e},"7d55":function(t,e,a){"use strict";a.d(e,"b",(function(){return r})),a.d(e,"c",(function(){return o})),a.d(e,"a",(function(){return n}));var n={uNavbar:a("caa3").default,uParse:a("60b2").default},r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-uni-view",[a("u-navbar",{attrs:{"is-back":!0,title:t.title,"border-bottom":!1}}),a("v-uni-view",{staticClass:"u-content"},[a("u-parse",{attrs:{html:t.content,autosetTitle:!0,"show-with-animation":!0,selectable:!0}})],1)],1)},o=[]},"8d24":function(t,e,a){"use strict";var n=a("3333"),r=a.n(n);r.a},a1a8:function(t,e,a){"use strict";var n=a("cbbb"),r=a.n(n);r.a},a2cc:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var n={data:function(){return{title:"避坑指南",content:""}},onLoad:function(){var t=this,e="/api/cmsApi/getServiceItemByAjax?type=租房避坑指南";this.$u.get(e).then((function(e){t.content=e.data.content}))}};e.default=n},cbbb:function(t,e,a){var n=a("7c83");n.__esModule&&(n=n.default),"string"===typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);var r=a("4f06").default;r("50fea886",n,!0,{sourceMap:!1,shadowMode:!1})}}]);