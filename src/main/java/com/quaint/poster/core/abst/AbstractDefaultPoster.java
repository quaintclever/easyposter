package com.quaint.poster.core.abst;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * 默认空白的 poster
 * @author quaint
 *  30 March 2020
 * @since 1.0
 */
public abstract class AbstractDefaultPoster implements Poster {

    @Override
    public BufferedImage draw(BufferedImage image) {
        return image;
    }

    @Override
    public Map<String, Integer> getExInts() {
        return null;
    }

    @Override
    public Map<String, String> getExStrings() {
        return null;
    }
}
