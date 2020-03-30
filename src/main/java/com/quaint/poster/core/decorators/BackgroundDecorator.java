package com.quaint.poster.core.decorators;

import com.quaint.poster.core.abst.AbstractPosterDecorator;
import com.quaint.poster.core.abst.Poster;
import lombok.Builder;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 背景装饰
 * @author quaint
 * @date 21 February 2020
 * @since 1.34
 */
public class BackgroundDecorator extends AbstractPosterDecorator {

    public BackgroundDecorator(Poster poster) {
        super(poster);
    }

    @Builder(toBuilder = true)
    public BackgroundDecorator(Poster poster, int positionX, int positionY, int width, int height) {
        super(poster,positionX,positionY,width,height);
    }


    @Override
    public BufferedImage draw(BufferedImage image) {
        // 绘制 被装饰之前的 图片
        BufferedImage draw = poster.draw(image);
        // 装饰, 绘制头像
        return drawBackground(draw);
    }

    /**
     * 绘制背景具体实现
     * @param image image
     * @return image
     */
    private BufferedImage drawBackground(BufferedImage image){

        // 如果宽度没变化, 或者没设置
        if (width == 0 && height == 0){
            return image;
        }
        // 调整背景宽度
        if (width != image.getWidth() || height != image.getHeight()){
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = newImage.createGraphics();
            g.drawImage(image,0,0,width,height,null);
            g.dispose();
            return newImage;
        }
        // 绘制背景
        return image;
    }
}
