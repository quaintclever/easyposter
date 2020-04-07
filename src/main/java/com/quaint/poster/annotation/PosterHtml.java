package com.quaint.poster.annotation;

import com.quaint.poster.core.decorators.TextDecorator;
import com.quaint.poster.enums.PosterDocumentEnum;

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
public @interface PosterHtml {

    /**
     * @return document 元素类型
     */
    PosterDocumentEnum document() default PosterDocumentEnum.TEXT;

    String id() default "";

    Class decorator() default TextDecorator.class;

}
