(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["components/likeFx/likeFx"],{4276:function(t,e,i){"use strict";i.d(e,"b",(function(){return n})),i.d(e,"c",(function(){return a})),i.d(e,"a",(function(){}));var n=function(){var t=this.$createElement;this._self._c},a=[]},7762:function(t,e,i){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i={name:"LikeFx",data:function(){return{queue:{},ctx:null,timer:0}},props:{height:{type:Number,default:1920},width:{type:Number,default:375}},mounted:function(){this.ctx=t.createCanvasContext("bubble"),this.queue={},console.log("likeFx mounted")},destroyed:function(){console.log("likeFx destoryed"),this.clearTimer()},methods:{likeClick:function(){console.log("开始点赞啦");var t="/static/img/likeFx/"+this.getRandomInt(1,33)+".png",e={id:(new Date).getTime(),timer:0,opacity:0,pathData:this.generatePathData(),image:t,factor:{speed:6e-4,t:.6}};Object.keys(this.queue).length>0?this.queue[e.id]=e:(this.queue[e.id]=e,this.bubbleAnimate())},getRandom:function(t,e){return Math.random()*(e-t)+t},getRandomInt:function(t,e){return Math.floor(Math.random()*(e-t+1))+t},generatePathData:function(){var t=this.width,e=this.height,i={x:.72*t,y:e},n={x:this.getRandom(.22*t,.33*t),y:this.getRandom(.5*e,.75*e)},a={x:this.getRandom(0,.88*t),y:this.getRandom(.25*e,.5*e)},u={x:this.getRandom(0,.88*t),y:this.getRandom(0,.125*e)};return[i,n,a,u]},updatePath:function(t,e){var i=t[0],n=t[1],a=t[2],u=t[3],o=e.t,r=3*(n.x-i.x),c=3*(a.x-n.x)-r,s=u.x-i.x-r-c,d=3*(n.y-i.y),h=3*(a.y-n.y)-d,l=u.y-i.y-d-h,f=s*(o*o*o)+c*(o*o)+r*o+i.x,m=l*(o*o*o)+h*(o*o)+d*o+i.y;return{x:f,y:m}},bubbleAnimate:function(){var t=this,e=(this.width,this.height);Object.keys(this.queue).forEach((function(i){var n=t.queue[+i],a=t.updatePath(n.pathData,n.factor),u=a.x,o=a.y,r=n.factor.speed;n.factor.t+=r;var c=30;c=(e-o)/1.5,c=Math.min(30,c);var s=n.opacity;s=o/(.3*e),s=Math.min(1,s),t.ctx.globalAlpha=s,t.ctx.drawImage(n.image,u-c/2,o,c,c),n.factor.t>1&&delete t.queue[n.id],o>e&&delete t.queue[n.id]})),this.ctx.draw(),Object.keys(this.queue).length>0?this.timer=setTimeout((function(){t.bubbleAnimate()}),5):(this.clearTimer(),this.ctx.draw())},clearTimer:function(){this.timer&&clearTimeout(this.timer)}}};e.default=i}).call(this,i("543d")["default"])},"7c3e":function(t,e,i){"use strict";i.r(e);var n=i("7762"),a=i.n(n);for(var u in n)["default"].indexOf(u)<0&&function(t){i.d(e,t,(function(){return n[t]}))}(u);e["default"]=a.a},9623:function(t,e,i){"use strict";i.r(e);var n=i("4276"),a=i("7c3e");for(var u in a)["default"].indexOf(u)<0&&function(t){i.d(e,t,(function(){return a[t]}))}(u);i("e50e");var o=i("f0c5"),r=Object(o["a"])(a["default"],n["b"],n["c"],!1,null,null,null,!1,n["a"],void 0);e["default"]=r.exports},e50e:function(t,e,i){"use strict";var n=i("e7bc"),a=i.n(n);a.a},e7bc:function(t,e,i){}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'components/likeFx/likeFx-create-component',
    {
        'components/likeFx/likeFx-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("9623"))
        })
    },
    [['components/likeFx/likeFx-create-component']]
]);
