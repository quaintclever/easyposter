# Easy Poster

**java的awt绘制海报的简单实现**

一个简单的,便于扩展的海报小项目


## 前言

> 微信后台生成海报一般都是一个模板写死，然后就完事了，过了不久让修改个模板，就又要看半天，还要考虑是否重新复制一份改一改，越来越多的重复代码，全在一个图片类里，然后就越来越乱。这两天用设计模式处理了一下，让以后修改模板，新增模板更舒服一点。有第三方好用的轻量级的实现，还请留言。感激！！


## 设计思想

- 装饰者模式
- 责任链模式
- 建造者模式(lombok)


## 快速上手

- 项目仅引入了lombok 和 spring-core(用于读取resources下的图片)
- 看效果请直接运行 PosterTest
- 自定义海报请按照content目录下的类对自身业务兼容
- 如果装饰器不满足你的需求, 请对decorators扩展
- 绘制请让设计给出类似蓝湖的像素图, 仿照PosterTest按着填一下就ok



## 效果展示

<a href="https://images.cnblogs.com/cnblogs_com/quaint/1684854/o_200330135806drawFriendTest.png" target="_blank">图片过大, 如不显示,请跳转查看</a>

![效果图](https://images.cnblogs.com/cnblogs_com/quaint/1684854/o_200330135806drawFriendTest.png)