package com.quaint.poster;

import com.quaint.poster.content.ComplexPoster;
import com.quaint.poster.content.NonAnnotatedPoster;
import com.quaint.poster.content.SamplePoster;
import com.quaint.poster.core.abst.Poster;
import com.quaint.poster.core.decorators.*;
import com.quaint.poster.core.impl.PosterDefaultImpl;
import org.springframework.core.io.ClassPathResource;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 绘制海报本地测试
 * @author quaint
 *  21 February 2020
 * @since 1.0
 */
public class PosterTest {

    public static void main(String[] args) throws Exception{

        // 复杂 注解绘制海报测试
        complexPosterTest();
        // 简单 注解绘制海报测试
//        samplePosterTest();
        // 原生非注解 创建海报测试
//        nonAnnotatedPosterTest();
    }

    private static void complexPosterTest() throws Exception{

        // 导入背景
        BufferedImage whiteBackground = ImageIO.read(new ClassPathResource("image/whitebackground.jpg").getInputStream());
        // 导入logo
        BufferedImage redLogoBg = ImageIO.read(new ClassPathResource("image/redlogobg.png").getInputStream());
        // 临时导入
        BufferedImage head = ImageIO.read(new ClassPathResource("image/headimage.jpg").getInputStream());
        ComplexPoster poster = ComplexPoster.builder()
                .backgroundImage(whiteBackground)
                .logoBg(redLogoBg)
                .headImage(head)
                .prodImages(Arrays.asList(head,head))
                .qrcode(head)
                .logo(head)
                .shopName("Quaint小白")
                .salesQuantity(3).limitQuantity(1)
                .userNickName("quaint easyposter")
                .prodName("github quaintclever easyposter 海报绘制")
                .priceRange("￥00.00~00.00")
                .linePrice("零售价:￥00.00")
                .slogan("命运多舛，痴迷淡然。挥别了青春，数不尽的车站。甘于平凡，却不甘平凡地溃败。").build();

        PosterDefaultImpl<ComplexPoster> impl = new PosterDefaultImpl<>();
        Poster drawAnn = impl.annotationDrawPoster(poster);
        // 手动绘制 注解不支持的 自定义复杂 代码
        List<String> contentTip = new ArrayList<>();
        contentTip.add("Star数量:"+poster.getSalesQuantity().toString());
        contentTip.add("投币");
        contentTip.add("Fork数量:"+poster.getLimitQuantity().toString());
        contentTip.add("许愿");
        BufferedImage tipBg = ImageIO.read(new ClassPathResource("image/tipbg.png").getInputStream());
        LabelTextDecorator drawLabelText = new LabelTextDecorator(drawAnn).toBuilder()
                .positionX(43).positionY(1022)
                .tipBg(tipBg).contentList(contentTip)
                .tipMargin(30)
                .color(new Color(216, 11, 42))
                .build();

        // 本地测试
        BufferedImage draw = drawLabelText.draw(null);
        ImageIO.write(draw,"png",new FileOutputStream("complexAnnTest.png"));

    }


    private static void samplePosterTest() throws Exception{
        // 测试简单注解
        BufferedImage background = ImageIO.read(new ClassPathResource("image/yayi.png").getInputStream());
        BufferedImage head = ImageIO.read(new ClassPathResource("image/headimage.jpg").getInputStream());
        SamplePoster poster = SamplePoster.builder()
                .backgroundImage(background)
                .head(head)
                .nickName("Quaint")
                .delTest("菜鸡互啄")
                .slogan("命运多舛，痴迷淡然。挥别了青春，数不尽的车站。甘于平凡，却不甘平凡地溃败。")
                .mainImage(head)
                .build();
        PosterDefaultImpl<SamplePoster> impl = new PosterDefaultImpl<>();
        BufferedImage test = impl.annotationDrawPoster(poster).draw(null);
        ImageIO.write(test,"png",new FileOutputStream("sampleAnnTest.png"));
    }

    /**
     * 分享给朋友测试
     * @throws Exception ex
     */
    private static void nonAnnotatedPosterTest() throws Exception{

        // 导入背景
        BufferedImage whiteBackground = ImageIO.read(new ClassPathResource("image/whitebackground.jpg").getInputStream());
        // 导入logo
        BufferedImage redLogoBg = ImageIO.read(new ClassPathResource("image/redlogobg.png").getInputStream());
        // 临时导入
        BufferedImage qrcode = ImageIO.read(new ClassPathResource("image/headimage.jpg").getInputStream());

        // 创建海报, 设置海报属性
        NonAnnotatedPoster poster = NonAnnotatedPoster.builder()
                .headImage(qrcode)
                .prodImages(Arrays.asList(qrcode,qrcode))
                .qrcode(qrcode)
                .logo(qrcode)
                .shopName("Quaint小白")
                .salesQuantity(3).limitQuantity(1)
                .userNickName("quaint easyposter")
                .prodName("github quaintclever easyposter 海报绘制")
                .priceRange("￥00.00~00.00")
                .linePrice("零售价:￥00.00")
                .slogan("命运多舛，痴迷淡然。挥别了青春，数不尽的车站。甘于平凡，却不甘平凡地溃败。").build();


        // 设置海报属性
        poster.setBackgroundImage(whiteBackground);

        // 计算背景的高度 请确保 getProdImages >= 1;
        AtomicInteger bgHeight = new AtomicInteger();
        poster.getProdImages().forEach(image -> bgHeight.addAndGet(image.getHeight() * 653 / image.getWidth()));
        // 1229 原背景高度, 653 原商品图高度
        bgHeight.addAndGet(1229 - 653);
        // 商品图后扩充高度
        int addHeight = bgHeight.get() - 1229;

        // 1. 绘制背景 + logo
        BackgroundDecorator drawBg = new BackgroundDecorator().toBuilder()
                .width(750).bgImage(whiteBackground)
                .height(bgHeight.get()).build();
        ImageDecorator drawBgLogo = new ImageDecorator(drawBg).toBuilder()
                .width(750).height(120)
                .image(redLogoBg).build();

        // 计算店铺名称长度
        FontMetrics fm = FontDesignMetrics.getMetrics(new Font(null).deriveFont(Font.BOLD,36));
        int nameWidth = fm.stringWidth(poster.getShopName());
        // logoMarginLeft = (背景宽度 - logo宽度 - 店铺名称宽度 - logo & shop 间隔) /2
        int logoMarginLeft = (750- 62 - nameWidth - 30)/2;
        ImageDecorator drawLogo = new ImageDecorator(drawBgLogo).toBuilder()
                .positionX(logoMarginLeft).positionY(15)
                .width(88).height(88)
                .circle(true)
                .image(poster.getLogo()).build();
        // shopNameLeft = logoMarginLeft + logoWidth + 间隔
        int shopNameLeft = logoMarginLeft + 62 + 30;
        TextDecorator shopName = new TextDecorator(drawLogo).toBuilder()
                .positionX(shopNameLeft).positionY(32)
                .content(poster.getShopName())
                .color(new Color(255,255,255))
                .fontStyle(Font.BOLD)
                .fontSize(36).build();
        // 2. 绘制头像
        ImageDecorator drawHead = new ImageDecorator(shopName).toBuilder()
//                .positionX(271-28).positionY(154)
                .positionX(43).positionY(154)
                .width(67).height(67)
                .circle(true)
                .image(poster.getHeadImage()).build();
        // 3. 绘制昵称
        TextDecorator drawNick = new TextDecorator(drawHead).toBuilder()
//                .positionX(351-28).positionY(167)
                .positionX(123).positionY(167)
                .fontSize(28)
                .content(poster.getUserNickName())
                .color(new Color(102, 102, 102)).build();
        // 4. 绘制广告语
        int advLineWidth = 651;
        TextDecorator drawSlogan = new TextDecorator(drawNick).toBuilder()
                .positionX(50).positionY(244)
                .fontSize(30)
                .fontStyle(Font.BOLD)
                .content(poster.getSlogan())
                .canNewLine(true).width(advLineWidth)
                .color(new Color(76, 76, 76)).build();
        fm = FontDesignMetrics.getMetrics(drawSlogan.getFont().deriveFont(drawSlogan.getFontStyle(), drawSlogan.getFontSize()));
        int x = fm.stringWidth(poster.getSlogan());
        int lineFix = 0;
        if (x <= advLineWidth) {
            lineFix = -39;
        }
        // 5. 合并商品图片
        MergeImageDecorator drawProdImgs = new MergeImageDecorator(drawSlogan).toBuilder()
                .positionX(47).positionY(350 + lineFix)
                .width(654).height(654)
                .images(poster.getProdImages()).build();
        // 6. 绘制二维码
        ImageDecorator drawQrcode;

        // ======= 插入活动相关绘制开始 =======
        int activityAdd;
        // 绘制多少人
        List<String> contentTip = new ArrayList<>();
        contentTip.add("Star数量:"+poster.getSalesQuantity().toString());
        contentTip.add("投币");
        contentTip.add("Fork数量:"+poster.getLimitQuantity().toString());
        contentTip.add("许愿");
        BufferedImage tipBg = ImageIO.read(new ClassPathResource("image/tipbg.png").getInputStream());
        LabelTextDecorator drawLabelText = new LabelTextDecorator(drawProdImgs).toBuilder()
                .positionX(43).positionY(1022 + addHeight + lineFix)
                .tipBg(tipBg).contentList(contentTip)
                .tipMargin(30)
                .color(new Color(216, 11, 42))
                .build();

        activityAdd = tipBg.getHeight();

        drawQrcode = new ImageDecorator(drawLabelText).toBuilder()
                .positionX(558).positionY(1020 + addHeight + lineFix + activityAdd)
                .width(133).height(133)
                .image(poster.getQrcode()).build();

        // ======= 插入活动相关绘制结束 =======

        // 7. 绘制商品名称
        TextDecorator drawProdName = new TextDecorator(drawQrcode).toBuilder()
                    .positionX(43).positionY(1038 + addHeight + lineFix + activityAdd)
                    .fontSize(30).fontStyle(Font.BOLD)
                    .content(poster.getProdName())
                    .canNewLine(true).width(467)
                    .color(new Color(102, 102, 102)).build();

        // 8. 绘制价格区间
        TextDecorator drawPriceRange = new TextDecorator(drawProdName).toBuilder()
                .positionX(40).positionY(1127 + addHeight + lineFix + activityAdd)
                .fontSize(28).fontStyle(Font.BOLD)
                .content(poster.getPriceRange())
                .color(new Color(216, 11, 42)).build();


        // 9. 绘制 删除线 原价
        fm = FontDesignMetrics.getMetrics(drawPriceRange.getFont().deriveFont(drawPriceRange.getFontStyle(), drawPriceRange.getFontSize()));
        x = fm.stringWidth(poster.getPriceRange());
        TextDecorator drawLinePrice = new TextDecorator(drawPriceRange).toBuilder()
                .positionX(40 + x + 18).positionY(1127 + addHeight + lineFix + activityAdd)
                .fontSize(28)
                .content(poster.getLinePrice())
                .delLine(true)
                .color(new Color(153, 153, 153)).build();
        // 10. 绘制 长按识别二维码
        TextDecorator drawIdentifyQrcode = new TextDecorator(drawLinePrice).toBuilder()
                .positionX(567).positionY(1153 + addHeight + lineFix + activityAdd)
                .fontSize(16)
                .content("长按识别二维码")
                .color(new Color(153, 153, 153)).build();

        // 调用最后一个包装类的 draw 方法
        BufferedImage drawResult = drawIdentifyQrcode.draw(null);
        // 本地测试效果
        ImageIO.write(drawResult,"png",new FileOutputStream("drawFriendTest.png"));


    }



}
