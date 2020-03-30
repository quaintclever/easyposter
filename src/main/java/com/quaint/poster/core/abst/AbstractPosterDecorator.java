package com.quaint.poster.core.abst;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @author quaint
 * @date 21 February 2020
 * @since 1.34
 */
@Data
@AllArgsConstructor
public abstract class AbstractPosterDecorator implements Poster {

    protected Poster poster;

    protected int positionX;

    protected int positionY;

    protected int width;

    protected int height;

    public AbstractPosterDecorator(Poster poster){
        this.poster = poster;
    }

    @Override
    public BufferedImage draw(BufferedImage image) {
        System.out.println("默认绘制方法");
        return poster.draw(image);
    }
}
