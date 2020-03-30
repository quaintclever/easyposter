package com.quaint.poster.core.abst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * 海报抽象类
 * @author quaint
 * @date 21 February 2020
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractPoster implements Poster {


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


}
