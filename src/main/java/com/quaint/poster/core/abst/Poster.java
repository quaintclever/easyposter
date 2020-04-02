package com.quaint.poster.core.abst;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * 海报接口, 只有绘制方法
 * @author quaint
 *  21 February 2020
 * @since 1.0
 */
public interface Poster {

    /**
     * 画海报
     * @param image image
     * @return image
     */
    BufferedImage draw(BufferedImage image);

    /**
     * 获取额外 int 属性
     * @return ints
     */
    Map<String,Integer> getExInts();

    /**
     * 获取额外 string 属性
     * @return strings
     */
    Map<String,String> getExStrings();

}
