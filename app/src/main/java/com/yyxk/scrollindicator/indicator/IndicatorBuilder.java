package com.yyxk.scrollindicator.indicator;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * 项目名称：CustomViews
 * 包名:com.yyxk.customviews.indicator
 * 类描述：
 * 创建人：LX
 * 创建时间：2017/5/16 下午2:14
 * 修改人：LX
 * 修改时间：2017/5/16 下午2:14
 * 修改备注：
 */

public class IndicatorBuilder {
    private int color;
    private int outRadius;
    private int innerRaidus;
    private int circlePadding;

    public int getColor() {
        return color;
    }

    public IndicatorBuilder setColor(int color) {
        this.color = color;
        return this;
    }

    public int getOutRadius() {
        return outRadius;
    }

    public IndicatorBuilder setOutRadius(int outRadius) {
        this.outRadius = outRadius;
        return this;
    }

    public int getInnerRaidus() {
        return innerRaidus;
    }

    public IndicatorBuilder setInnerRaidus(int innerRaidus) {
        this.innerRaidus = innerRaidus;
        return this;
    }

    public int getCirclePadding() {
        return circlePadding;
    }

    public IndicatorBuilder setCirclePadding(int circlePadding) {
        this.circlePadding = circlePadding;
        return this;
    }
}
