webpackJsonp([5],{"8d7q":function(t,e){},LttN:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a={render:function(){this.$createElement;this._self._c;return this._m(0)},staticRenderFns:[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("footer",{attrs:{id:"footer"}},[s("ul",[s("li",[s("a",{attrs:{href:"javascript:;"}},[t._v("关于我们")])]),t._v(" "),s("li",[s("a",{attrs:{href:"javascript:;"}},[t._v("免责声明")])]),t._v(" "),s("li",[s("a",{attrs:{href:"javascript:;"}},[t._v("使用协议")])]),t._v(" "),s("li",[s("a",{attrs:{href:"http://beian.miit.gov.cn/",target:"_blank"}},[t._v("渝ICP备19001371号")])]),t._v(" "),s("li",[t._v("@Copyright 2018-2019. ALL Rights Reserved")])])])}]};var i=s("VU/8")({name:"myFooter"},a,!1,function(t){s("8d7q")},"data-v-6686962e",null).exports,r=s("YtJ0"),n=s("NYxO"),o={store:r.a,components:{"v-footer":i},data:function(){return{flag:!1,user:{}}},created:function(){this.userInfo()},methods:{exit:function(){this.$router.push({path:"/"}),this.$cookies.remove("cname"),this.$cookies.remove("cid")},manage:function(){this.$router.push({path:"/manager"})},userInfo:function(){var t=this.$cookies.get("cname"),e=this.$cookies.get("cid");console.log("studentId"+e),console.log("studentName "+t),this.user.userName=t,this.user.studentId=e},practice:function(){this.$store.commit("practice",!0),this.$router.push({path:"/startExam"})},exam:function(){this.$store.commit("practice",!1),this.$router.push({path:"/student"})}},computed:Object(n.c)(["isPractice"])},c={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{attrs:{id:"student"}},[s("el-row",{staticClass:"padding-50"},[s("el-col",{attrs:{span:24}},[s("ul",{staticClass:"list"},[s("li",{staticClass:"logo"},[s("i",{staticClass:"iconfont icon-kaoshi"}),s("span",[t._v("Exam-Online")])]),t._v(" "),s("li",[s("a",{attrs:{href:"javascript:;"},on:{click:function(e){t.exam()}}},[t._v("我的试卷")])]),t._v(" "),s("li",[s("a",{attrs:{href:"javascript:;"},on:{click:function(e){t.practice()}}},[t._v("我的练习")])]),t._v(" "),s("li",[s("router-link",{attrs:{to:"/scoreTable"}},[t._v("我的分数")])],1),t._v(" "),s("li",[s("router-link",{attrs:{to:"/message"}},[t._v("给我留言")])],1),t._v(" "),s("li",[s("a",{attrs:{href:"javascript:;"}},[t._v("待定")])]),t._v(" "),s("li",{staticClass:"right",on:{mouseenter:function(e){t.flag=!t.flag},mouseleave:function(e){t.flag=!t.flag}}},[s("a",{attrs:{href:"javascript:;"}},[s("i",{staticClass:"iconfont icon-Userselect icon"}),t._v(t._s(t.user.userName))]),t._v(" "),t.flag?s("div",{staticClass:"msg"},[s("p",{on:{click:function(e){t.manage()}}},[t._v("管理中心")]),t._v(" "),s("p",{staticClass:"exit",on:{click:function(e){t.exit()}}},[t._v("退出")])]):t._e()])])])],1),t._v(" "),s("div",{staticClass:"main"},[s("router-view")],1),t._v(" "),s("v-footer")],1)},staticRenderFns:[]};var l=s("VU/8")(o,c,!1,function(t){s("TU3n")},"data-v-2bac985e",null);e.default=l.exports},TU3n:function(t,e){}});
//# sourceMappingURL=5.faf22aad1d06e4533d87.js.map