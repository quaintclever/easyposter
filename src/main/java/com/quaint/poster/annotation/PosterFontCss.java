package com.quaint.poster.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author quaint
 * @date 30 March 2020
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PosterFontCss {


    /**
     * positionX,positionY
     */
    int[] position() default {0,0};

    /**
     * 字体大小
     */
    int size() default 18;

    /**
     * 颜色
     */
    int[] color() default {0,0,0};

    /**
     * 优先级  设置该属性时 position x 失效
     * center > right > left
     */
    boolean center() default false;

    boolean right() default false;


}
