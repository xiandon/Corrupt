package com.pen.wind.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.pen.wind.R;

/**
 * Created by pen on 2018/1/18.
 */

public class CustomThermometer extends View {

    private int mCrustColor;
    private int mLiquidColor;
    private float mCrustWidth;
    private float mCrustHeight;
    private float mInitTemperature;
    //创建画笔
    Paint p;
    Paint lp;

    public CustomThermometer(Context context) {
        this(context, null);
    }

    public CustomThermometer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获取自定义属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomThermometer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //获取自定义属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyThermometer, defStyle, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.MyThermometer_liquid_color) {
                mLiquidColor = typedArray.getColor(attr, Color.BLUE);

                //设置默认颜色为黑色
            } else if (attr == R.styleable.MyThermometer_crust_color) {
                mCrustColor = typedArray.getColor(attr, Color.BLACK);

                //设置温度计默认宽
            } else if (attr == R.styleable.MyThermometer_crust_width) {
                mCrustWidth = typedArray.getDimensionPixelOffset(attr,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()));

                //设置温度计默认高
            } else if (attr == R.styleable.MyThermometer_crust_height) {
                mCrustHeight = typedArray.getDimensionPixelOffset(attr,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, getResources().getDisplayMetrics()));

                //设置温度计默认温度
            } else if (attr == R.styleable.MyThermometer_init_temperature) {
                mInitTemperature = typedArray.getDimensionPixelOffset(attr,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));

            }
        }
        typedArray.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float circleX;
        float circleY;
        /**
         * 先画温度计外壳
         */
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(mCrustColor);
        // p.setColor(Color.BLUE);
        p.setAntiAlias(true);
        p.setStrokeWidth(5);
        circleX = canvas.getWidth() / 2;
        circleY = canvas.getHeight() - canvas.getHeight() / 4;
        float mRadius = mCrustWidth / 2;
        canvas.drawCircle(circleX, circleY, mRadius, p);
//        p.setColor(Color.YELLOW);
//        canvas.drawRect(0, 0, circleX, circleY, p);
        canvas.drawRect(circleX - mRadius * 0.5f, circleY - mRadius * 0.86f - mCrustHeight, circleX + mRadius * 0.5f, circleY - mRadius, p);
        /**
         * 画温度计里的液体
         */
        lp = new Paint();
        lp.setStyle(Paint.Style.FILL);
        lp.setColor(mLiquidColor);
        lp.setAntiAlias(true);
        float mLRadius = mRadius - 15;
        canvas.drawCircle(circleX, circleY, mLRadius, lp);
        //canvas.drawRect(circleX - mLRadius * 0.5f, circleY - mLRadius * 0.86f - 400, circleX + mLRadius * 0.5f, circleY - mLRadius *0.86f+0,lp);
        lp.setStrokeWidth(mLRadius);
        canvas.drawLine(circleX, circleY, circleX, circleY - 100 - mInitTemperature * 10, lp);
    }

    public void setTemperature(float mTemperature) {
        mInitTemperature = mTemperature;
        postInvalidate();
    }
}
