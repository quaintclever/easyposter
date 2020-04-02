package com.quaint.poster.core.impl;

import com.quaint.poster.annotation.PosterBackground;
import com.quaint.poster.annotation.PosterFontCss;
import com.quaint.poster.annotation.PosterImageCss;
import com.quaint.poster.core.abst.Poster;
import com.quaint.poster.core.decorators.BackgroundDecorator;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

/**
 * @author quaint
 *  02 April 2020
 * @since 1.3
 */
public class PosterDefaultPlusImpl<E> extends PosterDefaultImpl<E>{


    @Override
    public Poster annotationDrawPoster(E content) throws IllegalAccessException{
        // 反射获取所有属性
        Field[] fields = content.getClass().getDeclaredFields();

        // 第一个注解为背景配置
        if (fields.length>0 && fields[0].getAnnotation(PosterBackground.class) != null){

            // 最后需要执行绘制方法的类
            Poster finalDraw;
            PosterBackground ann = fields[0].getAnnotation(PosterBackground.class);

            fields[0].setAccessible(true);
            Object o = fields[0].get(content);
            if (o instanceof BufferedImage){
                BufferedImage bg = (BufferedImage) o;
                finalDraw = new BackgroundDecorator().toBuilder()
                        .bgImage(bg)
                        .width(ann.width())
                        .height(ann.height())
                        .build();
            } else {
                throw new RuntimeException("背景注解标记的类型需要为BufferedImage, 并且不可为空.");
            }

            // 给背景绘制基本属性
            for (Field field: fields) {
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                System.out.println("field: " + name + "-->开始绘制");
                //获取属性值
                Object value = field.get(content);
                if (value instanceof String){
                    String str = (String)value;
                    PosterFontCss posterFontCss = field.getAnnotation(PosterFontCss.class);
                    finalDraw = drawTextImpl(finalDraw,str,posterFontCss);
                } else if (value instanceof BufferedImage){
                    BufferedImage image = (BufferedImage) value;
                    PosterImageCss posterImageCss = field.getAnnotation(PosterImageCss.class);
                    finalDraw = drawImageImpl(finalDraw,image,posterImageCss);
                }

            }
            return finalDraw;
        } else {
            throw new RuntimeException("绘制字段为空,或者第一个属性不是背景,并且没有标记背景注解");
        }

    }



}
