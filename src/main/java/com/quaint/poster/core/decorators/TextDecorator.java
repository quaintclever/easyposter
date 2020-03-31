package com.quaint.poster.core.decorators;

import com.quaint.poster.core.abst.AbstractPosterDecorator;
import com.quaint.poster.core.abst.Poster;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 文本装饰
 *
 * @author quaint
 * @date 21 February 2020
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TextDecorator extends AbstractPosterDecorator {

    /**
     * 字体
     */
    private Font font = new Font(null);

    /**
     * 字体样式
     */
    private int fontStyle = Font.PLAIN;

    /**
     * 字体大小
     */
    private int fontSize = 16;

    /**
     * 字体颜色
     */
    private Color color = new Color(0, 0, 0);

    /**
     * 内容
     */
    private String content;

    /**
     * 是否包含删除线
     */
    private boolean delLine = false;

    /**
     * 是否根据 width 换行, 配合 width 使用
     */
    private boolean canNewLine = false;

    /**
     * 换行限制次数
     */
    private int newLineLimit = 1;


    public TextDecorator(Poster poster) {
        super(poster);
    }

    @Builder(toBuilder = true)
    public TextDecorator(Poster poster, int positionX, int positionY, int width, int height, Font font, int fontSize, Color color, String content, int fontStyle, boolean delLine, boolean canNewLine, int newLineLimit) {
        super(poster, positionX, positionY, width, height);
        this.font = font;
        this.fontSize = fontSize;
        this.color = color;
        this.content = content;
        this.fontStyle = fontStyle;
        this.delLine = delLine;
        this.canNewLine = canNewLine;
        this.newLineLimit = newLineLimit;
    }

    @Override
    public BufferedImage draw(BufferedImage image) {
        // 绘制 被装饰之前的 图片
        BufferedImage draw = poster.draw(image);
        // 装饰, 绘制文本
        return drawText(draw);
    }

    /**
     * 绘制文本具体实现
     *
     * @param image image
     * @return image
     */
    private BufferedImage drawText(BufferedImage image) {

        if (content == null || content.length() == 0) {
            return image;
        }

        // 实现绘制文本
        font = font.deriveFont(fontStyle, fontSize);
        Graphics2D g = image.createGraphics();
        // 设置文字抗锯齿算法
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font);
        g.setColor(color);

        FontMetrics fm = FontDesignMetrics.getMetrics(font);
        // 换行 并且 设置的换行宽度大于一个字的大小才可以换行
        if (canNewLine && width > fontSize) {
            //【商品名称换行判断逻辑】
            // 换行修正最后一个文字越界.
            width = width - fontSize/2;

            int charWidth = 0;
            int lineLimit = newLineLimit;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; lineLimit >= 0 && i < content.length(); i++) {
                charWidth += fm.charWidth(content.charAt(i));
                sb.append(content.charAt(i));
                // 如果宽度 > width 换行
                if (charWidth > width * (newLineLimit-lineLimit+1)) {
                    g.drawString(sb.toString(), positionX, positionY + fontSize + (newLineLimit-lineLimit) * fontSize);
                    sb.setLength(0);
                    lineLimit--;
                    continue;
                }
                // 如果超过 换行次数限制 则后面省略 改为...
                if (charWidth > width * (newLineLimit + 1) - 2 * fontSize) {
                    sb.append("...");
                    g.drawString(sb.toString(), positionX, positionY + fontSize + (newLineLimit-lineLimit) * fontSize);
                    break;
                }
                // 如果前两个if没有进，则直接添加
                if (i == content.length() - 1) {
                    g.drawString(sb.toString(), positionX, positionY + fontSize + ((newLineLimit-lineLimit)) * fontSize);
                }
            }

        } else {
            g.drawString(content, positionX, positionY + fontSize);
        }

        // 删除线暂时不支持换行
        if (delLine && !canNewLine) {
            // 删除线长度
            int delLineW = fm.stringWidth(content);
            g.drawLine(positionX, positionY + 3 * fontSize / 5, positionX + delLineW, positionY + 3 * fontSize / 5);
        }

        g.dispose();
        return image;
    }

}
