package com.quaint.poster.content;

import com.quaint.poster.annotation.PosterBackground;
import com.quaint.poster.annotation.PosterFontCss;
import com.quaint.poster.annotation.PosterImageCss;
import com.quaint.poster.core.abst.AbstractDefaultPoster;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import java.awt.image.BufferedImage;

/**
 * @author quaint
 * @date 30 March 2020
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class SamplePoster extends AbstractDefaultPoster {

    /**
     * 背景图
     */
    @PosterBackground
    protected BufferedImage backgroundImage;

    /**
     * logo
     */
    @PosterImageCss(position = {10,10},width = 50, height = 50, circle = true)
    protected BufferedImage logo;

    /**
     * 广告语
     */
    @PosterFontCss(position = {2,2},center = true, size = 12, color = {1,1,1})
    protected String slogan;

    /**
     * 主图
     */
    @PosterImageCss(position = {10,10},width = 100,height = 100)
    protected BufferedImage mainImage;

    /**
     * 二维码
     */
    @PosterImageCss(position = {100,100},width = 60,height = 60)
    protected BufferedImage qrcode;

    @Tolerate
    public SamplePoster() {}
}
