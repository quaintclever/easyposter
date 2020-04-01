# Easy Poster

**java的awt绘制海报的简单工具**

一个简单的,便于扩展的绘制海报工具包


## 前言

> 微信后台生成海报一般都是一个模板写死，然后就完事了，过了不久让修改个模板，就又要看半天，还要考虑是否重新复制一份改一改，越来越多的重复代码，全在一个图片类里，然后就越来越乱。这两天用设计模式处理了一下，让以后修改模板，新增模板更舒服一点。


## 效果展示

<a href="https://images.cnblogs.com/cnblogs_com/quaint/1684854/o_200330135806drawFriendTest.png" target="_blank">图片过大, 如不显示,请跳转查看</a>


## 设计思想

- 装饰者模式
- 责任链模式
- 建造者模式(lombok)


## 快速上手

- 项目仅引入了lombok简化代码
- 看效果请直接运行 PosterTest
- 自定义海报请按照content目录下的类对自身业务兼容
- 如果装饰器不满足你的需求, 请对decorators扩展
- 绘制请让设计给出类似蓝湖的像素图, 仿照PosterTest按着填一下就ok
- 现在有简单的注解支持了(请看下面的注解支持)

```xml
<dependency>
  <groupId>com.github.quaintclever</groupId>
  <artifactId>easyposter</artifactId>
  <version>1.2</version>
</dependency>
```

## 注解样例

**海报定义类**

```java
/**
 * @author quaint
 * 30 March 2020
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class SamplePoster extends AbstractDefaultPoster {

    /**
     * 背景图
     */
    @PosterBackground(width = 666,height = 365)
    private BufferedImage backgroundImage;

    /**
     * 头像
     */
    @PosterImageCss(position = {27,27},width = 36, height = 36, circle = true)
    private BufferedImage head;

    /**
     * 昵称
     */
    @PosterFontCss(position = {71,32}, color = {255,255,255})
    private String nickName;

    /**
     * 广告语
     */
    @PosterFontCss(position = {27,70},center = true, size = 22, color = {255,255,255}, canNewLine={1,221,7})
    private String slogan;

    /**
     * 主图
     */
    @PosterImageCss(position = {27,172},width = 168,height = 168)
    private BufferedImage mainImage;

    @Tolerate
    public SamplePoster() {}
}

```

**海报绘制**

```java
/**
 * 绘制海报本地测试
 * @author quaint
 * 21 February 2020
 * @since 1.0
 */
public class PosterTest {

    public static void main(String[] args) throws Exception{

        // 测试注解, 图片请自行添加到resources下 ClassPathResource 需要引入spring
        BufferedImage background = ImageIO.read(new ClassPathResource("image/yayi.png").getInputStream());
        BufferedImage head = ImageIO.read(new ClassPathResource("image/headimage.jpg").getInputStream());
        SamplePoster poster = SamplePoster.builder()
                .backgroundImage(background)
                .head(head)
                .nickName("Quaint")
                .slogan("命运多舛，痴迷淡然。挥别了青春，数不尽的车站。甘于平凡，却不甘平凡地溃败。")
                .mainImage(head)
                .build();
        PosterDefaultImpl<SamplePoster> impl = new PosterDefaultImpl<>();
        BufferedImage test = impl.annotationDrawPoster(poster).draw(null);
        ImageIO.write(test,"png",new FileOutputStream("annTest.png"));

    }
}
```
