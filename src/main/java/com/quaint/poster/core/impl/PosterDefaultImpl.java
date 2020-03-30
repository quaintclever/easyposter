package com.quaint.poster.core.impl;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author quaint
 * @date 30 March 2020
 * @since 1.0
 */
public class PosterDefaultImpl<E> implements PosterTemplate<E> {


    @Override
    public BufferedImage annotationDrawPoster(E content) {
        // 反射获取所有属性
        Field[] fields = content.getClass().getDeclaredFields();

        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            //获取属性
            String name = field.getName();
            //获取属性值
            try {
                Object value = field.get(content);
                if (value instanceof String){
                    System.out.println("value: " + value);
                } else {
                    System.out.println("value: " + value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("name: " + name);

        });
        return null;
    }


}
