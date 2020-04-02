package com.quaint.poster.content;

import com.quaint.poster.core.abst.AbstractDefaultPoster;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * eg: 朋友圈海报 , 非注解绘制海报
 * @author quaint
 *  21 February 2020
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class NonAnnotatedPoster extends AbstractDefaultPoster {

    /**
     * 背景图
     */
    protected BufferedImage backgroundImage;

    /**
     * logo
     */
    protected BufferedImage logo;

    /**
     * 广告语
     */
    protected String slogan;

    /**
     * 主图
     */
    protected BufferedImage mainImage;

    /**
     * 二维码
     */
    protected BufferedImage qrcode;

    /**
     * 商品图
     */
    private List<BufferedImage> prodImages;

    /**
     * 用户头像
     */
    private BufferedImage headImage;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 价格范围
     */
    private String priceRange;

    /**
     * 划线价
     */
    private String linePrice;


    /**
     * 活动销量
     */
    private Integer salesQuantity;

    /**
     * 限购数量
     */
    private Integer limitQuantity;

    /**
     * 店铺名称
     */
    private String shopName;


    @Tolerate
    public NonAnnotatedPoster() {
    }
}
