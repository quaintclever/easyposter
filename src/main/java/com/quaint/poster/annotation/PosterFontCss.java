package com.quaint.poster.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author quaint
 *  30 March 2020
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
     * 颜色 默认黑色
     */
    int[] color() default {0,0,0};

    /**
     * 是否可以换行
     * 第一个参数 0 false 1 true
     * 第二个参数 换行的限制宽度, 如果小于字体宽度 则不会换行
     * 第三个参数 换行次数
     */
    int[] canNewLine() default {0,0,1};


    /**
     * @return 是否包含删除线, 目前不支持与换行同时使用
     */
    boolean hasDelLine() default false;

    /**
     * 优先级  设置该属性时 position x 失效 (暂未实现)
     * center  right  left
     */
    boolean center() default false;

    boolean right() default false;


}
