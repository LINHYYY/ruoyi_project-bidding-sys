(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/chooseAddress/cityList"],{3051:function(t,e,n){"use strict";var r=n("9202"),i=n.n(r);i.a},"33e7":function(t,e,n){"use strict";(function(t,e){var r=n("4ea4");n("104b");r(n("66fd"));var i=r(n("e21d"));t.__webpack_require_UNI_MP_PLUGIN__=n,e(i.default)}).call(this,n("bc2e")["default"],n("543d")["createPage"])},"407e":function(t,e,n){"use strict";(function(t){var r=n("4ea4");Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i=r(n("2eee")),o=r(n("c973")),a=r(n("2ece")),c=r(n("0070")),u=[],s=!1,f=0,d={data:function(){return{keyChanged:!1,keywordCurrentName:"",keywordCurrent:0,renderList:[],list:[]}},onPageScroll:function(t){if(!s)for(var e=t.scrollTop,n=0;n<u.length;n++)e>u[n]&&(this.keywordCurrent=n)},watch:{keywordCurrent:function(t){var e=this;this.keywordCurrentName=this.renderList[t].key,f&&clearTimeout(f),this.keyChanged=!0,f=setTimeout((function(){e.keyChanged=!1}),500)}},onLoad:function(t){this.initList()},methods:{chooseCity:function(e){var n=getCurrentPages(),r=n[n.length-2].$vm;r.city=e.label,r.searchList(),t.navigateBack()},initList:function(){var t=this,e=c.default,n={};e.forEach((function(t){var e=a.default.getLetter(t.label).firstletter;n[e]||(n[e]=[]),n[e].push(t)}));var r=[];for(var i in n)r.push(i);r.sort();var o=[];r.forEach((function(t){for(var e in n)e==t&&o.push({key:e,list:n[e]})})),this.renderList=o,setTimeout((function(){t.calcAnchor()}),500)},calcAnchor:function(){var t=this,e=this.renderList;this.systemInfo.navigationBarHeight,this.systemInfo.statusBarHeight;e.forEach(function(){var e=(0,o.default)(i.default.mark((function e(n){var r;return i.default.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.getElSize("keyword"+n.key);case 2:r=e.sent,n.top=r.top,u.push(r.top);case 5:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}())},onKeywordClick:function(e){s=!0,setTimeout((function(){s=!1}),500),this.keywordCurrent=e,t.pageScrollTo({scrollTop:this.renderList[e].top})},getElSize:function(e){return new Promise((function(n){var r=t.createSelectorQuery().select("#"+e);r.boundingClientRect((function(t){n(t)})).exec()}))}}};e.default=d}).call(this,n("543d")["default"])},"4dc9":function(t,e,n){"use strict";n.d(e,"b",(function(){return r})),n.d(e,"c",(function(){return i})),n.d(e,"a",(function(){}));var r=function(){var t=this.$createElement;this._self._c},i=[]},9202:function(t,e,n){},9526:function(t,e,n){"use strict";var r=n("dea5"),i=n.n(r);i.a},c11f:function(t,e,n){"use strict";n.r(e);var r=n("407e"),i=n.n(r);for(var o in r)["default"].indexOf(o)<0&&function(t){n.d(e,t,(function(){return r[t]}))}(o);e["default"]=i.a},dea5:function(t,e,n){},e21d:function(t,e,n){"use strict";n.r(e);var r=n("4dc9"),i=n("c11f");for(var o in i)["default"].indexOf(o)<0&&function(t){n.d(e,t,(function(){return i[t]}))}(o);n("9526"),n("3051");var a=n("f0c5"),c=Object(a["a"])(i["default"],r["b"],r["c"],!1,null,"5124820a",null,!1,r["a"],void 0);e["default"]=c.exports}},[["33e7","common/runtime","common/vendor"]]]);