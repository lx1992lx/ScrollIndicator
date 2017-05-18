package com.yyxk.scrollindicator.indicator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.yyxk.scrollindicator.R;


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
 * 创建时间：2017/5/15 上午10:01
 * 修改人：LX
 * 修改时间：2017/5/15 上午10:01
 * 修改备注：
 */

public class ScrollIndicator extends View {
    private ViewPager mViewPager;
    private int mCount, mWidth, mHeight;
    private Canvas mCanvas;
    private int mRadius = 30;//外部轮廓半径
    private int mInnerRadius = 20;//内部球半径
    private Paint mBackPaint, mInnerPaint;
    private int mCirclePadding = 15;//原点之间的间距
    private Path mBackPath = new Path();//背景圆轨迹
    private Path mInnerPath = new Path();//内部圆轨迹
    private float mInnerPoint, mStartPoint;//内部圆圆心
    private OnPagerScrollListener mListener;
    private int mColor;//颜色

    public ScrollIndicator(Context context) {
        super(context);
        init();
    }

    public ScrollIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScrollIndicator);
        mCirclePadding = (int) array.getDimension(R.styleable.ScrollIndicator_si_circlePadding, 15);
        mRadius = (int) array.getDimension(R.styleable.ScrollIndicator_si_outRadius, 30);
        mInnerRadius = (int) array.getDimension(R.styleable.ScrollIndicator_si_innerRadius, 20);
        mColor = array.getColor(R.styleable.ScrollIndicator_si_color, Color.WHITE);
        array.recycle();
        init();
    }

    /**
     * 通过builder设置属性
     *
     * @param builder
     */
    public void setBuild(IndicatorBuilder builder) {
        if (builder == null) {
            return;
        }
        mCirclePadding = builder.getCirclePadding();
        mInnerRadius = builder.getInnerRaidus();
        mRadius = builder.getOutRadius();
        mColor = builder.getColor();
    }

    /**
     * 初始化
     */
    private void init() {
        mBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackPaint.setStrokeWidth(3);
        mBackPaint.setColor(mColor);
        mBackPaint.setStyle(Paint.Style.STROKE);

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mInnerPaint.setColor(mColor);
    }

    int mPrePosition = 0;

    /**
     * ViewPager监听
     */
    ViewPager.OnPageChangeListener mOnPagerChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mListener != null)
                mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            if (mListener != null) {
                mListener.onPagerSelected(position);
            }
            Log.i("taggggg", "position=" + position);
            if (mPrePosition > position) {
                scrollToRight();
            } else {
                scrollToLeft();
            }
            mPrePosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mListener != null) {
                mListener.onPageScrollStateChanged(state);
            }
        }
    };

    /**
     * 向右滑动
     */
    private void scrollToRight() {
        final int length = mRadius * 2 + mCirclePadding;
        ValueAnimator animator = ValueAnimator.ofFloat(mInnerPoint, mStartPoint + (mPrePosition - 1) * length);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mInnerPoint = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    /**
     * 向左滑动
     */
    private void scrollToLeft() {
        final int length = mRadius * 2 + mCirclePadding;
        ValueAnimator animator = ValueAnimator.ofFloat(mInnerPoint, mStartPoint + (mPrePosition + 1) * length);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mInnerPoint = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }


    /**
     * 装载ViewPager
     *
     * @param mViewPager
     */
    public void setUpWithViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        mCount = mViewPager.getAdapter().getCount();
//        mPrePosition = mViewPager.getCurrentItem();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mStartPoint = getWidth() / 2 - (mCount * mRadius * 2 + mCirclePadding * (mCount - 1)) / 2 + mRadius;//起始点
        final int length = mRadius * 2 + mCirclePadding;
        mInnerPoint = mStartPoint + length * mViewPager.getCurrentItem();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPrePosition=mViewPager.getCurrentItem();
        mViewPager.removeOnPageChangeListener(mOnPagerChangeListener);
        mViewPager.addOnPageChangeListener(mOnPagerChangeListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        drawUnderCircle();//绘制底部空洞
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            drawInnerCircle();//绘制内部小球
        }
    }

    /**
     * 绘制中间圆点
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void drawInnerCircle() {
        mInnerPath.reset();
        mInnerPath.addCircle(mInnerPoint, mHeight / 2, mInnerRadius, Path.Direction.CCW);
        mInnerPath.op(mBackPath, Path.Op.INTERSECT);
        mCanvas.drawPath(mInnerPath, mInnerPaint);
    }


    /**
     * 绘制底部空洞
     */
    private void drawUnderCircle() {
        mBackPath.reset();
        int centerX = mWidth / 2;
        int centerY = mHeight / 2;
        int pointLength = mCirclePadding + 2 * mRadius;
        int startX = centerX - (mCount * mRadius * 2 + mCirclePadding * (mCount - 1)) / 2 + mRadius;//起始点
        for (int i = 0; i < mCount; i++) {
            drawOneBackCircle(startX + i * pointLength, centerY);
        }
    }

    /**
     * 绘制一个底部空洞
     *
     * @param w 中心点W
     * @param h 中心点高
     */
    private void drawOneBackCircle(int w, int h) {
        mBackPath.addCircle(w, h, mRadius, Path.Direction.CCW);
        mCanvas.drawPath(mBackPath, mBackPaint);
    }

    interface OnPagerScrollListener {
        void onPagerSelected(int position);

        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageScrollStateChanged(int state);
    }
}
