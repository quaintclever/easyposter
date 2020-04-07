package com.quaint.poster.core.abst;

/**
 * @author quaint
 *  30 March 2020
 * @since 1.3
 */
public interface AbstractPosterTemplate<E> {


    /**
     * 基于注解的绘制
     * @param content content 类
     * @return 注解绘制
     */
    Poster annotationDrawPoster(E content);


}
