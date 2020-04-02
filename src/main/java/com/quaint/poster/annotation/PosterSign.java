package com.quaint.poster.annotation;

/**
 * @author quaint
 * 02 April 2020
 * @since 1.3
 */
public @interface PosterSign {

    int[] sign() default {0};

}
