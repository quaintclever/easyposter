package com.quaint.poster.content;

import com.quaint.poster.core.abst.AbstractPoster;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * eg: 朋友圈海报
 * @author quaint
 * @date 21 February 2020
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FriendCirclePoster extends AbstractPoster {

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



    @Builder(toBuilder = true)
    public FriendCirclePoster(BufferedImage backgroundImage, BufferedImage logo, String slogan, BufferedImage mainImage, BufferedImage qrcode,
                              List<BufferedImage> prodImages, BufferedImage headImage, String userNickName, String priceRange, String linePrice,
                              String prodName, Integer salesQuantity, Integer limitQuantity, String shopName) {
        super(backgroundImage, logo, slogan, mainImage, qrcode);
        this.prodImages = prodImages;
        this.headImage = headImage;
        this.userNickName = userNickName;
        this.linePrice = linePrice;
        this.priceRange = priceRange;
        this.prodName = prodName;
        this.salesQuantity = salesQuantity;
        this.limitQuantity = limitQuantity;
        this.shopName = shopName;
    }

    @Override
    public BufferedImage draw(BufferedImage image) {
        return image;
    }

}
