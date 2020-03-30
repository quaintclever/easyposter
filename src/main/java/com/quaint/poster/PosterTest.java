package com.quaint.poster;

import com.quaint.poster.content.FriendCirclePoster;
import com.quaint.poster.core.decorators.*;
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
 * @date 21 February 2020
 * @since 1.0
 */
public class PosterTest {

    public static void main(String[] args) throws Exception{

        // 创建海报测试
        createPlaybillTest();
    }

    /**
     * 分享给朋友测试
     * @throws Exception ex
     */
    private static void createPlaybillTest() throws Exception{

        // 导入背景
        BufferedImage whiteBackground = ImageIO.read(new ClassPathResource("image/whitebackground.jpg").getInputStream());
        // 导入logo
        BufferedImage redLogoBg = ImageIO.read(new ClassPathResource("image/redlogobg.png").getInputStream());
        // 临时导入
        BufferedImage qrcode = ImageIO.read(new ClassPathResource("image/headimage.jpg").getInputStream());

        // 创建海报, 设置海报属性
        FriendCirclePoster playbill = new FriendCirclePoster().toBuilder()
                .headImage(qrcode)
                .prodImages(Arrays.asList(qrcode,qrcode))
                .qrcode(qrcode)
                .logo(qrcode)
                .shopName("Quaint小白")
                .salesQuantity(100).limitQuantity(10)
                .userNickName("quaint easyposter")
                .prodName("quaint easyposter 海报绘制")
                .priceRange("￥00.00~00.00")
                .linePrice("零售价:￥00.00")
                .slogan("命运多舛，痴迷淡然。挥别了青春，数不尽的车站。甘于平凡，却不甘平凡地溃败。").build();
        playbill.setBackgroundImage(whiteBackground);


        // 设置海报属性
        playbill.setBackgroundImage(whiteBackground);

        // 计算背景的高度 请确保 getProdImages >= 1;
        AtomicInteger bgHeight = new AtomicInteger();
        playbill.getProdImages().forEach(image -> bgHeight.addAndGet(image.getHeight() * 653 / image.getWidth()));
        // 1229 原背景高度, 653 原商品图高度
        bgHeight.addAndGet(1229 - 653);
        // 商品图后扩充高度
        int addHeight = bgHeight.get() - 1229;

        // 1. 绘制背景 + logo
        BackgroundDecorator drawBg = new BackgroundDecorator(playbill).toBuilder()
                .width(750)
                .height(bgHeight.get()).build();
        ImageDecorator drawBgLogo = new ImageDecorator(drawBg).toBuilder()
                .width(750).height(120)
                .image(redLogoBg).build();

        // 计算店铺名称长度
        FontMetrics fm = FontDesignMetrics.getMetrics(new Font(null).deriveFont(Font.BOLD,36));
        int nameWidth = fm.stringWidth(playbill.getShopName());
        // logoMarginLeft = (背景宽度 - logo宽度 - 店铺名称宽度 - logo & shop 间隔) /2
        int logoMarginLeft = (750- 62 - nameWidth - 30)/2;
        ImageDecorator drawLogo = new ImageDecorator(drawBgLogo).toBuilder()
                .positionX(logoMarginLeft).positionY(15)
                .width(88).height(88)
                .circle(true)
                .image(playbill.getLogo()).build();
        // shopNameLeft = logoMarginLeft + logoWidth + 间隔
        int shopNameLeft = logoMarginLeft + 62 + 30;
        TextDecorator shopName = new TextDecorator(drawLogo).toBuilder()
                .positionX(shopNameLeft).positionY(32)
                .content(playbill.getShopName())
                .fontStyle(Font.BOLD)
                .fontSize(36).build();
        // 2. 绘制头像
        ImageDecorator drawHead = new ImageDecorator(shopName).toBuilder()
//                .positionX(271-28).positionY(154)
                .positionX(43).positionY(154)
                .width(67).height(67)
                .circle(true)
                .image(playbill.getHeadImage()).build();
        // 3. 绘制昵称
        TextDecorator drawNick = new TextDecorator(drawHead).toBuilder()
//                .positionX(351-28).positionY(167)
                .positionX(123).positionY(167)
                .fontSize(28)
                .content(playbill.getUserNickName())
                .color(new Color(102, 102, 102)).build();
        // 4. 绘制广告语
        int advLineWidth = 651;
        TextDecorator drawSlogan = new TextDecorator(drawNick).toBuilder()
                .positionX(50).positionY(244)
                .fontSize(30)
                .fontStyle(Font.BOLD)
                .content(playbill.getSlogan())
                .newLine(true).width(advLineWidth)
                .color(new Color(76, 76, 76)).build();
        fm = FontDesignMetrics.getMetrics(drawSlogan.getFont().deriveFont(drawSlogan.getFontStyle(), drawSlogan.getFontSize()));
        int x = fm.stringWidth(playbill.getSlogan());
        int lineFix = 0;
        if (x <= advLineWidth) {
            lineFix = -39;
        }
        // 5. 合并商品图片
        MergeImgDecorator drawProdImgs = new MergeImgDecorator(drawSlogan).toBuilder()
                .positionX(47).positionY(350 + lineFix)
                .width(654).height(654)
                .images(playbill.getProdImages()).build();
        // 6. 绘制二维码
        ImageDecorator drawQrcode;
        // ======= 插入活动相关绘制开始 =======
        int activityAdd = 0;
        // 绘制多少人
        List<String> contentTip = new ArrayList<>();
        contentTip.add("限量"+playbill.getSalesQuantity().toString()+"件");
        contentTip.add("每人限购"+playbill.getLimitQuantity().toString()+"件");
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
                .image(playbill.getQrcode()).build();

        // ======= 插入活动相关绘制结束 =======

        // 7. 绘制商品名称
        TextDecorator drawProdName = new TextDecorator(drawQrcode).toBuilder()
                    .positionX(43).positionY(1038 + addHeight + lineFix + activityAdd)
                    .fontSize(30).fontStyle(Font.BOLD)
                    .content(playbill.getProdName())
                    .newLine(true).width(467)
                    .color(new Color(102, 102, 102)).build();

        // 8. 绘制价格区间
        TextDecorator drawPriceRange = new TextDecorator(drawProdName).toBuilder()
                .positionX(40).positionY(1131 + addHeight + lineFix + activityAdd)
                .fontSize(28).fontStyle(Font.BOLD)
                .content(playbill.getPriceRange())
                .color(new Color(216, 11, 42)).build();


        // 9. 绘制 删除线 原价
        fm = FontDesignMetrics.getMetrics(drawPriceRange.getFont().deriveFont(drawPriceRange.getFontStyle(), drawPriceRange.getFontSize()));
        x = fm.stringWidth(playbill.getPriceRange());
        TextDecorator drawLinePrice = new TextDecorator(drawPriceRange).toBuilder()
                .positionX(40 + x + 18).positionY(1131 + addHeight + lineFix + activityAdd)
                .fontSize(28)
                .content(playbill.getLinePrice())
                .delLine(true)
                .color(new Color(153, 153, 153)).build();
        // 10. 绘制 长按识别二维码
        TextDecorator drawIdentifyQrcode = new TextDecorator(drawLinePrice).toBuilder()
                .positionX(571).positionY(1153 + addHeight + lineFix + activityAdd)
                .fontSize(16)
                .content("长按识别二维码")
                .color(new Color(153, 153, 153)).build();

        // 调用最后一个包装类的 draw 方法
        BufferedImage drawResult = drawIdentifyQrcode.draw(playbill.getBackgroundImage());
        // 本地测试效果
        ImageIO.write(drawResult,"png",new FileOutputStream("drawFriendTest.png"));


    }



}
