package com.quaint.poster.core.abst;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * 海报装饰器抽象.
 * @author quaint
 *  21 February 2020
 * @since 1.0
 */
@Data
@AllArgsConstructor
public abstract class AbstractPosterDecorator implements Poster {

    /**
     * 海报对象
     */
    protected Poster poster;

    protected int positionX;

    protected int positionY;

    protected int width;

    protected int height;

    /**
     * 额外的int 列表
     */
    protected Map<String,Integer> exInts;

    /**
     * 额外的 string 列表
     */
    protected Map<String,String> exStrings;


    public AbstractPosterDecorator(Poster poster){
        this.poster = poster;
    }

    @Override
    public BufferedImage draw(BufferedImage image) {
        return image;
    }

    @Override
    public Map<String, Integer> getExInts() {
        return exInts;
    }

    @Override
    public Map<String, String> getExStrings() {
        return exStrings;
    }
}
