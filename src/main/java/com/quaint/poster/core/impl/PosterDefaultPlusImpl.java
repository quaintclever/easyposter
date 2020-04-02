package com.quaint.poster.core.impl;

import com.quaint.poster.annotation.PosterBackground;
import com.quaint.poster.annotation.PosterFontCss;
import com.quaint.poster.annotation.PosterImageCss;
import com.quaint.poster.annotation.PosterSignNotice;
import com.quaint.poster.core.abst.AbstractPosterDecorator;
import com.quaint.poster.core.abst.Poster;
import com.quaint.poster.core.decorators.BackgroundDecorator;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author quaint
 *  02 April 2020
 * @since 1.3
 */
public class PosterDefaultPlusImpl<E> extends PosterDefaultImpl<E>{


    @Override
    public Poster annotationDrawPoster(E content) {
        // 反射获取所有属性
        Field[] fields = content.getClass().getDeclaredFields();
        if (fields.length == 0){
            throw new RuntimeException("Annotation draw field is empty exception!");
        }

        // 最后需要执行绘制方法的类
        AbstractPosterDecorator finalDraw;
        BufferedImage bg = null;
        int bgWidth = 0;
        int bgHeight = 0;
        Map<String,Integer> exInts = new HashMap<>(8);
        boolean error = false;

        for (Field field : fields) {
            field.setAccessible(true);
            // 如果是背景字段
            if (field.getAnnotation(PosterBackground.class) != null){

                if (error){
                    throw new RuntimeException("There can be only one background annotation!");
                }

                PosterBackground ann = field.getAnnotation(PosterBackground.class);
                Object o;
                try {
                    o = field.get(content);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("反射获取属性异常");
                }
                if (o instanceof BufferedImage){
                    bg = (BufferedImage) o;
                    bgWidth += ann.width() == 0 ? bg.getWidth() : ann.width();
                    bgHeight += ann.height() == 0 ? bg.getHeight() : ann.height();
                } else {
                    throw new RuntimeException("The background annotation tag needs to be of type BufferedImage and must not be empty.");
                }

                error = true;
            }
            // 非背景字段
            else if (field.getAnnotation(PosterSignNotice.class) != null){

                PosterFontCss annFont = field.getAnnotation(PosterFontCss.class);
                // 计算文字扩展高度 , 如果换行计算换行后高度
                if (annFont != null && annFont.canNewLine()[0] == 1 && annFont.canNewLine()[1] > annFont.size()){

                    Object value;
                    try {
                        value = field.get(content);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("反射获取属性异常");
                    }
                    if (value instanceof String){

                        PosterSignNotice annNotice = field.getAnnotation(PosterSignNotice.class);
                        Font font = new Font(null).deriveFont(annFont.style(),annFont.size());
                        FontMetrics fm = FontDesignMetrics.getMetrics(font);
                        String str = (String)value;
                        int line = fm.stringWidth(str)/annFont.canNewLine()[1] > annFont.canNewLine()[2]
                                ? annFont.canNewLine()[2]
                                : fm.stringWidth(str)/annFont.canNewLine()[1];
                        if (exInts.get(annNotice.sign()) == null){
                            exInts.put(annNotice.sign(),line * annFont.size());
                        } else {
                            exInts.put(annNotice.sign(), exInts.get(annNotice.sign())+line* annFont.size());
                        }
                    }

                }
                // 计算图片扩展高度
                else if (field.getAnnotation(PosterImageCss.class) != null){
                    // 暂未实现
                }
            }

        }

        if (bg != null){
            AtomicInteger add = new AtomicInteger(0);
            exInts.forEach((k,v)-> add.addAndGet(v));
            finalDraw = new BackgroundDecorator().toBuilder()
                    .bgImage(bg)
                    .width(bgWidth)
                    .height(bgHeight + add.get())
                    .exInts(exInts)
                    .build();
        } else {
            throw new RuntimeException("No background found error!");
        }

        return loopDrawing(content,finalDraw,fields);

    }



}
