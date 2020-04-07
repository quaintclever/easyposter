package com.quaint.poster.test.content;

import com.quaint.poster.annotation.PosterCss;
import com.quaint.poster.annotation.PosterHtml;
import com.quaint.poster.core.abst.AbstractDefaultPoster;
import com.quaint.poster.core.decorators.*;
import com.quaint.poster.enums.PosterDocumentEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author quaint
 *  07 April 2020
 * @since 1.3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TestPoster extends AbstractDefaultPoster {

    /**
     * 背景信息
     */
    @PosterHtml(document = PosterDocumentEnum.BODY, decorator = BackgroundDecorator.class)
    @PosterCss
    private BufferedImage background;

    /**
     * logo 信息
     */
    @PosterHtml(document = PosterDocumentEnum.DIV, id = "logo", decorator = Object.class)
    @PosterCss
    private BufferedImage logoBg;

    @PosterHtml(document = PosterDocumentEnum.DIV, id = "logo")
    @PosterCss
    private BufferedImage logo;

    @PosterHtml(document = PosterDocumentEnum.DIV, id = "logo")
    @PosterCss
    private String shopName;

    /**
     * 用户头像
     */
    @PosterHtml(document = PosterDocumentEnum.DIV, id = "userInfo", decorator = Object.class)
    @PosterCss
    private BufferedImage headImage;

    /**
     * 用户昵称
     */
    @PosterHtml(document = PosterDocumentEnum.DIV, id = "userInfo")
    @PosterCss
    private String userNickName;

    /**
     * 广告语
     */
    @PosterHtml
    @PosterCss
    private String slogan;

    /**
     * 商品主图
     */
    @PosterHtml(document = PosterDocumentEnum.DIV, id = "prodImages", decorator = MergeImageDecorator.class)
    @PosterCss
    private List<BufferedImage> prodImages;


    /**
     * 商品标签列表
     */
    @PosterHtml(document = PosterDocumentEnum.DIV, id = "prodTips", decorator = LabelTextDecorator.class)
    @PosterCss
    private BufferedImage tipBg;

    @PosterHtml(document = PosterDocumentEnum.DIV, id = "prodTips")
    @PosterCss
    private List<String> prodTips;

    /**
     * 商品名称
     */
    @PosterHtml
    @PosterCss
    private String prodName;

    /**
     * 价格范围
     */
    @PosterHtml
    @PosterCss
    private String priceRange;

    /**
     * 划线价
     */
    @PosterHtml
    @PosterCss
    private String linePrice;

    /**
     * 二维码
     */
    @PosterHtml(document = PosterDocumentEnum.IMAGE, decorator = ImageDecorator.class)
    @PosterCss
    private BufferedImage qrcode;


}
