package com.quaint.poster.content;

import com.quaint.poster.annotation.*;
import com.quaint.poster.core.abst.AbstractDefaultPoster;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    @PosterBackground(width = 750,height = 1229)
    private BufferedImage backgroundImage;


    /**
     * logo背景
     */
    @PosterImageCss(width = 750, height = 120)
    private BufferedImage logoBg;

    /**
     * logo
     */
    private BufferedImage logo;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 用户头像
     */
    @PosterImageCss(position = {43,154},width = 67, height = 67, circle = true)
    private BufferedImage headImage;

    /**
     * 用户昵称
     */
    @PosterFontCss(position = {123,167}, size = 28, color = {102, 102, 102})
    private String userNickName;


    /**
     * 广告语
     */
    @PosterSignNotice
    @PosterFontCss(position = {50,244}, size = 30, style = Font.BOLD, color = {76, 76, 76}, canNewLine={1,651,1})
    private String slogan;


    /**
     * 商品图 / 主图(单张)
     */
    @PosterImageCss(position = {47,350},width = 654, height = 654)
    private BufferedImage prodImage;

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
    @PosterSignNotice(sign = "prodNameAdd")
    @PosterSign
    @PosterFontCss(position = {43,1068}, size = 30, style = Font.BOLD, color = {102,102,102}, canNewLine={1,467,1})
    private String prodName;

    /**
     * 价格范围
     */
    @PosterSign(sign = {"default","prodNameAdd"})
    @PosterFontCss(position = {40,1157}, size = 28, style = Font.BOLD, color = {216, 11, 42})
    private String priceRange;

    /**
     * 划线价
     */
    @PosterSign(sign = {"default","prodNameAdd"})
    @PosterFontCss(position = {40+200+18,1157}, size = 22, color = {153,153,153},hasDelLine = true)
    private String linePrice;

    /**
     * 二维码
     */
    @PosterSign
    @PosterImageCss(position = {558,1020},width = 133, height = 133)
    private BufferedImage qrcode;


}
