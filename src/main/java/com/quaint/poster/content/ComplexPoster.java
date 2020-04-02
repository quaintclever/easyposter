package com.quaint.poster.content;

import com.quaint.poster.annotation.*;
import com.quaint.poster.core.abst.AbstractDefaultPoster;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quaint
 *  02 April 2020
 * @since 1.3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ComplexPoster extends AbstractDefaultPoster {


    /**
     * 背景图
     */
    @PosterBackground(width = 666,height = 365)
    private BufferedImage backgroundImage;


    /**
     * logo背景
     */
    @PosterImageCss(position = {27,27},width = 36, height = 36, circle = true)
    private BufferedImage logoBg;

    /**
     * logo
     */
    @PosterImageCss(position = {27,27},width = 36, height = 36, circle = true)
    private BufferedImage logo;

    /**
     * 用户头像
     */
    @PosterImageCss(position = {27,27},width = 36, height = 36, circle = true)
    private BufferedImage headImage;

    /**
     * 用户昵称
     */
    @PosterFontCss(position = {27,70}, size = 22, color = {255,255,255}, canNewLine={1,221,7})
    private String userNickName;

    /**
     * 店铺名称
     */
    @PosterFontCss(position = {27,70}, size = 22, color = {255,255,255}, canNewLine={1,221,7})
    private String shopName;

    /**
     * 广告语
     */
    @PosterAutoHeight
    @PosterFontCss(position = {27,70}, size = 22, color = {255,255,255}, canNewLine={1,221,7})
    private String slogan;


    /**
     * 商品图 / 主图(多张)
     */
    @PosterAutoHeight
    @PosterImageCss(position = {27,27},width = 36, height = 36, circle = true)
    private List<BufferedImage> prodImages;

    /**
     * 活动销量
     */
    private Integer salesQuantity;

    /**
     * 限购数量
     */
    private Integer limitQuantity;

    /**
     * 商品名称
     */
    @PosterAutoHeight(sign = 1)
    @PosterSign
    @PosterFontCss(position = {27,70}, size = 22, color = {255,255,255}, canNewLine={1,221,1})
    private String prodName;

    /**
     * 价格范围
     */
    @PosterSign(sign = {0,1})
    @PosterFontCss(position = {27,70}, size = 22, color = {255,255,255}, canNewLine={1,221,1})
    private String priceRange;

    /**
     * 划线价
     */
    @PosterSign(sign = {0,1})
    @PosterFontCss(position = {27,70}, size = 22, color = {255,255,255}, canNewLine={1,221,1})
    private String linePrice;

    /**
     * 二维码
     */
    @PosterSign
    @PosterImageCss(position = {27,27},width = 36, height = 36, circle = true)
    private BufferedImage qrcode;

    /**
     * 额外的int 列表
     */
    private Map<String,Integer> exInts;

}
