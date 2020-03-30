package com.quaint.poster.core.impl;

import java.awt.image.BufferedImage;

/**
 * @author quaint
 * @date 30 March 2020
 * @since 1.0
 */
public interface PosterTemplate<E> {

    /**
     * 基于注解的绘制
     * @param content content 类
     * @return 注解绘制
     */
    BufferedImage annotationDrawPoster(E content);

}
