<p align="center">
  <a href="https://whynotteaming.pamalee.cn">
    <img alt="WhyNotTeaming" src="./img/Logo.png" width="250"/>
  </a>
</p>
<div align="center">
<img src="https://img.shields.io/badge/License-MIT_License-green?style=for-the-badge" alt="license">
<img src="https://img.shields.io/badge/build-v0.0.1-blue?style=for-the-badge" alt="build">
<img src="https://img.shields.io/badge/Version-V0.0.1-brown?style=for-the-badge" alt="version">
</div>
<div style="background:orangered;color:white;" align="center"><h2>这个项目正在开发中,功能性暂不全面,欢迎对本项目做出贡献</h2></div>

## 🤔什么是 <span style="color:orange">WhyNotTeaming</span> ?

---
<div style="color:white">
<h4><span style="color:orange">WhyNotTeaming</span> 是一个简单的团队及项目管理解决方案</h4>
</div>
## 目前WhyNotTeaming正在规划的
依托SpringBoot对于网关的协助,通过自编写其逻辑处理层,将从SpringBoot传递的来自用户的请求进行模块化分发，以达到开发便携性以及原子化开发
## 目前WhyNotTeaming正在思考的模块化
目前正在规划的是，项目通过启动SpringBoot服务,获取到来自用户的请求后将其分发给WhyNotTeaming进行托管处理, WhyNotTeaming内部由多个「独立」的「APP」对不同的请求进行处理。 **「Plugin」也被支持「APP」的相关性质**

**例如: **

**WhyNotTeaming内部存在一个名为[Login]的「APP」, 当SpringBoot获取到来自\*\*.com/Login/LoginMethod 的请求并转发给WhynotTeaming后, WhynotTeaming将会对其处理，将请求分发给[Login]中的[LoginMethod]模块，LoginMethod模块的定义是Login给出的, WhyNotTeaming并不会在此层面上对Method进行筛选，而是将请求交给[Login],通常, WhyNotTeaming会将请求发送给已经在Server中注册的「Login」中的onCall() **

## 「APP」的构成以及后续可添加的组件「Plugin」

#### 「APP」由两部分组成：

- 位于核心源码中cn.devspace.whynotteaming.App中的主类与其他定义的其他类
- 位于资源文件[resources]中的app文件夹内各「APP」的配置Yaml文件

#### 【规划中】「Plugin」原则上是一个打包好的.jar文件,其中包含

- 可由您自己命名的类路径及其中的类,例如 com.helloworld.welcome
- jar内根目录的app.yml配置文件

#### 「APP」和「Plugin」都包含生命周期

- onLoad - 当服务器开始加载插件时的事件
- onEnable - 当服务器准备就绪后的事件
- onCall - 当服务器将请求分发给该「APP」或「Plugin」的事件
- onDisable - 当「APP」或「Plugin」被卸载时的事件