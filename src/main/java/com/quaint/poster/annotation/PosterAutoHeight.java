package com.quaint.poster.annotation;

/**
 * 如果该 字段 绘制的时候会有换行(超过预设高度). 则加入该注解,自动拉伸背景高度
 * 并绘制下方带有PosterSign注解字段时增加该高度
 * (推荐背景为白色)
 * @author quaint
 * 02 April 2020
 * @since 1.3
 */
public @interface PosterAutoHeight {

    /**
     * @return 为0时, 下方所有的属性都被标记 为要增加该字段扩展的高度
     */
    int sign() default 0;

}
