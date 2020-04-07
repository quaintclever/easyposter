package com.quaint.poster.annotation;

import java.awt.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author quaint
 *  07 April 2020
 * @since 1.3
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PosterCss {


    /**
     * ===================== 通用属性 =====================
     */
    boolean position() default false;

    int left() default 0;

    int top() default 0;

    int[] padding() default {0,0,0,0};

    int[] margin() default {0,0,0,0};

    int width() default 0;

    int height() default 0;


    /**
     * ===================== 字体属性 =====================
     */
    int fontSize() default 18;

    int fontWeight() default Font.PLAIN;

    int[] color() default {0,0,0};

    /**
     * 是否可以换行
     * 第1个参数 0 false 1 true
     * 第2个参数 换行次数
     */
    int[] canNewLine() default {0,1};


    boolean isDelLine() default false;


    /**
     * ===================== 图片属性 =====================
     */
    boolean circle() default false;

}
