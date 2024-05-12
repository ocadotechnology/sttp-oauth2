(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[922],{3905:function(t,e,n){"use strict";n.d(e,{Zo:function(){return c},kt:function(){return f}});var r=n(7294);function a(t,e,n){return e in t?Object.defineProperty(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[e]=n,t}function i(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(t);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),n.push.apply(n,r)}return n}function o(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{};e%2?i(Object(n),!0).forEach((function(e){a(t,e,n[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(n,e))}))}return t}function s(t,e){if(null==t)return{};var n,r,a=function(t,e){if(null==t)return{};var n,r,a={},i=Object.keys(t);for(r=0;r<i.length;r++)n=i[r],e.indexOf(n)>=0||(a[n]=t[n]);return a}(t,e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(t);for(r=0;r<i.length;r++)n=i[r],e.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(t,n)&&(a[n]=t[n])}return a}var l=r.createContext({}),p=function(t){var e=r.useContext(l),n=e;return t&&(n="function"==typeof t?t(e):o(o({},e),t)),n},c=function(t){var e=p(t.components);return r.createElement(l.Provider,{value:e},t.children)},u={inlineCode:"code",wrapper:function(t){var e=t.children;return r.createElement(r.Fragment,{},e)}},d=r.forwardRef((function(t,e){var n=t.components,a=t.mdxType,i=t.originalType,l=t.parentName,c=s(t,["components","mdxType","originalType","parentName"]),d=p(n),f=a,m=d["".concat(l,".").concat(f)]||d[f]||u[f]||i;return n?r.createElement(m,o(o({ref:e},c),{},{components:n})):r.createElement(m,o({ref:e},c))}));function f(t,e){var n=arguments,a=e&&e.mdxType;if("string"==typeof t||a){var i=n.length,o=new Array(i);o[0]=d;var s={};for(var l in e)hasOwnProperty.call(e,l)&&(s[l]=e[l]);s.originalType=t,s.mdxType="string"==typeof t?t:a,o[1]=s;for(var p=2;p<i;p++)o[p]=n[p];return r.createElement.apply(null,o)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},1592:function(t,e,n){"use strict";n.r(e),n.d(e,{frontMatter:function(){return o},metadata:function(){return s},toc:function(){return l},default:function(){return c}});var r=n(2122),a=n(9756),i=(n(7294),n(3905)),o={sidebar_position:1,description:"Getting started with sttp-oauth2"},s={unversionedId:"getting-started",id:"getting-started",isDocsHomePage:!1,title:"Getting started",description:"Getting started with sttp-oauth2",source:"@site/../mdoc/target/mdoc/getting-started.md",sourceDirName:".",slug:"/getting-started",permalink:"/sttp-oauth2/docs/getting-started",editUrl:"https://github.com/polyvariant/sttp-oauth2/edit/main/docs/getting-started.md",version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1,description:"Getting started with sttp-oauth2"},sidebar:"tutorialSidebar",next:{title:"Client credentials grant",permalink:"/sttp-oauth2/docs/client-credentials"}},l=[{value:"About",id:"about",children:[]},{value:"Installation",id:"installation",children:[]},{value:"Usage",id:"usage",children:[]}],p={toc:l};function c(t){var e=t.components,n=(0,a.Z)(t,["components"]);return(0,i.kt)("wrapper",(0,r.Z)({},p,n,{components:e,mdxType:"MDXLayout"}),(0,i.kt)("h2",{id:"about"},"About"),(0,i.kt)("p",null,"This library aims to provide easy integration with OAuth2 providers based on ",(0,i.kt)("a",{parentName:"p",href:"https://tools.ietf.org/html/rfc6749"},"OAuth2 RFC")," using ",(0,i.kt)("a",{parentName:"p",href:"https://github.com/softwaremill/sttp"},"sttp")," client.\nThere are multiple JSON implementations, see ",(0,i.kt)("a",{parentName:"p",href:"/sttp-oauth2/docs/json-deserialisation"},"JSON deserialisation")," for details."),(0,i.kt)("h2",{id:"installation"},"Installation"),(0,i.kt)("p",null,"To use this library add following dependency to your ",(0,i.kt)("inlineCode",{parentName:"p"},"build.sbt")," file"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-scala"},'"org.polyvariant" %% "sttp-oauth2" % "0.17.0"\n"org.polyvariant" %% "sttp-oauth2-circe" % "0.17.0" // Or other, see JSON support\n')),(0,i.kt)("h2",{id:"usage"},"Usage"),(0,i.kt)("p",null,"Depending on your use case, please see documentation for the grant you want to support."),(0,i.kt)("p",null,"Each grant is implemented in an object with explicit return and error types on methods and additionally, Tagless Final friendly ",(0,i.kt)("inlineCode",{parentName:"p"},"*Provider")," interface."),(0,i.kt)("p",null,"All grant implementations require a set of implicit ",(0,i.kt)("inlineCode",{parentName:"p"},"JsonDecoder"),"s, e.g.:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-scala"},"import org.polyvariant.sttp.oauth2.json.circe.instances._\n")),(0,i.kt)("p",null,"See ",(0,i.kt)("a",{parentName:"p",href:"/sttp-oauth2/docs/json-deserialisation"},"JSON deserialisation")," for details."))}c.isMDXComponent=!0}}]);