(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["uview-ui/components/u-sticky/u-sticky"],{"1b7f":function(t,e,n){},"22a9":function(t,e,n){"use strict";n.d(e,"b",(function(){return i})),n.d(e,"c",(function(){return s})),n.d(e,"a",(function(){}));var i=function(){var t=this.$createElement;this._self._c},s=[]},6273:function(t,e,n){"use strict";n.r(e);var i=n("22a9"),s=n("d993");for(var r in s)["default"].indexOf(r)<0&&function(t){n.d(e,t,(function(){return s[t]}))}(r);n("d292");var o=n("f0c5"),u=Object(o["a"])(s["default"],i["b"],i["c"],!1,null,"d1fe2c48",null,!1,i["a"],void 0);e["default"]=u.exports},d292:function(t,e,n){"use strict";var i=n("1b7f"),s=n.n(i);s.a},d993:function(t,e,n){"use strict";n.r(e);var i=n("e0da"),s=n.n(i);for(var r in i)["default"].indexOf(r)<0&&function(t){n.d(e,t,(function(){return i[t]}))}(r);e["default"]=s.a},e0da:function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var n={name:"u-sticky",props:{offsetTop:{type:[Number,String],default:0},index:{type:[Number,String],default:""},enable:{type:Boolean,default:!0},h5NavHeight:{type:[Number,String],default:44},bgColor:{type:String,default:"#ffffff"},zIndex:{type:[Number,String],default:""}},data:function(){return{fixed:!1,height:"auto",stickyTop:0,elClass:this.$u.guid(),left:0,width:"auto"}},watch:{offsetTop:function(t){this.initObserver()},enable:function(t){0==t?(this.fixed=!1,this.disconnectObserver("contentObserver")):this.initObserver()}},computed:{uZIndex:function(){return this.zIndex?this.zIndex:this.$u.zIndex.sticky}},mounted:function(){this.initObserver()},methods:{initObserver:function(){var e=this;this.enable&&(this.stickyTop=0!=this.offsetTop?t.upx2px(this.offsetTop):0,this.disconnectObserver("contentObserver"),this.$uGetRect("."+this.elClass).then((function(t){e.height=t.height,e.left=t.left,e.width=t.width,e.$nextTick((function(){e.observeContent()}))})))},observeContent:function(){var t=this;this.disconnectObserver("contentObserver");var e=this.createIntersectionObserver({thresholds:[.95,.98,1]});e.relativeToViewport({top:-this.stickyTop}),e.observe("."+this.elClass,(function(e){t.enable&&t.setFixed(e.boundingClientRect.top)})),this.contentObserver=e},setFixed:function(t){var e=t<this.stickyTop;e?this.$emit("fixed",this.index):this.fixed&&this.$emit("unfixed",this.index),this.fixed=e},disconnectObserver:function(t){var e=this[t];e&&e.disconnect()}},beforeDestroy:function(){this.disconnectObserver("contentObserver")}};e.default=n}).call(this,n("543d")["default"])}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'uview-ui/components/u-sticky/u-sticky-create-component',
    {
        'uview-ui/components/u-sticky/u-sticky-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("6273"))
        })
    },
    [['uview-ui/components/u-sticky/u-sticky-create-component']]
]);
