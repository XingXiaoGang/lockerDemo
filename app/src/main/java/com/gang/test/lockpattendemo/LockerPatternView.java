package com.gang.test.lockpattendemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by gang on 16-8-14.
 */
public class LockerPatternView extends View implements ILockerView {

    private static final boolean DEBUG = true;
    private static final String TAG = "test.LockerPatternLayout";

    private int mLineWidth;
    private float mDisplayMertics;
    //圆形的填充百分比  (0-100)
    private int mPatternPercent = 60;
    // 列 ,其实 int[] 类型最好，这样就支持特殊的解锁方式,现在先简单弄
    private int COlUMN = 3;
    private int mBorder;

    private Paint mRountPaint;
    private Paint mLinePaint;
    private int mRoundColor1;
    private int mRoundColor2;
    private int mShadowColor1;
    private int mShadowColor2;


    private Rect[][] mPatternRects = new Rect[COlUMN][COlUMN];
    private RadialGradient[][] mMainShader = new RadialGradient[COlUMN][COlUMN];
    private RadialGradient[][] mTopShader = new RadialGradient[COlUMN][COlUMN];
    private Rect mDisplayRect;

    // 当前已经连起来的
    private LinkedList<Position> mLinkedList = new LinkedList<>();


    public LockerPatternView(Context context) {
        super(context);
        initView(context, null);
    }

    public LockerPatternView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }


    public LockerPatternView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mDisplayRect = new Rect();
        for (int r = 0; r < COlUMN; r++) {
            for (int c = 0; c < COlUMN; c++) {
                mPatternRects[r][c] = new Rect();
            }
        }

        mRoundColor1 = Color.rgb(240, 240, 240);
        mRoundColor2 = Color.rgb(235, 230, 230);
        mShadowColor1 = Color.argb(140, 110, 100, 100);
        mShadowColor2 = Color.argb(80, 210, 200, 200);

        mDisplayMertics = context.getResources().getDisplayMetrics().density;
        mBorder = (int) (mDisplayMertics * 8);
        mRountPaint = new Paint();
        mRountPaint.setStyle(Paint.Style.FILL);
        mRountPaint.setStrokeWidth(5 * mDisplayMertics);
        mLinePaint = new Paint(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(5 * mDisplayMertics);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //高度=宽度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getMode(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //计算位置

        mDisplayRect.set(left, top, right, bottom);
        int width = right - left;
        int height = bottom - top;

        int oneWidth = width / COlUMN;
        int oneHeight = height / COlUMN;

        int widthOffset = (int) (oneWidth * ((100f - mPatternPercent) / 100));
        int heightOffset = (int) (oneHeight * ((100f - mPatternPercent) / 100));

        int border = (int) (mDisplayMertics * 8);
        int shadowWidth = (int) (mDisplayMertics * 4);

        for (int r = 0; r < COlUMN; r++) {
            for (int c = 0; c < COlUMN; c++) {
                int l = c * oneWidth + widthOffset / 2;
                int t = r * oneHeight + heightOffset / 2;
                final Rect rect = mPatternRects[r][c];
                rect.set(l, t, l + oneWidth - widthOffset, t + oneHeight - heightOffset);

                mMainShader[r][c] = new RadialGradient(rect.centerX() + border / 3, rect.centerY() + border / 2, rect.width() / 2 - border / 2 * 3 + shadowWidth,
                        new int[]{mShadowColor1, mShadowColor1, Color.TRANSPARENT}
                        , null
                        , Shader.TileMode.CLAMP);

                mTopShader[r][c] = new RadialGradient(rect.centerX() + border, rect.centerY() + border, rect.width() / 2,
                        new int[]{mShadowColor2, Color.TRANSPARENT}
                        , null
                        , Shader.TileMode.CLAMP);

                if (DEBUG) {
                    Log.d(TAG, "rect: [" + r + "][" + c + "]:" + rect);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        for (int r = 0; r < COlUMN; r++) {
            for (int c = 0; c < COlUMN; c++) {
                final Rect rect = mPatternRects[r][c];

                //背景1 每个圆背景
                mRountPaint.setColor(mRoundColor1);
                mRountPaint.setShader(null);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, mRountPaint);


                //背景2 主圆阴影
                mRountPaint.setColor(mRoundColor2);
                mRountPaint.setShader(mMainShader[r][c]);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, mRountPaint);

                //todo 在这里画线
                
                //背景3 主圆
                mRountPaint.setColor(mRoundColor2);
                mRountPaint.setShader(null);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2 - mBorder, mRountPaint);

                //背景4 主圆上阴影
                mRountPaint.setColor(mRoundColor2);
                mRountPaint.setShader(mTopShader[r][c]);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2 - mBorder, mRountPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                break;
            }
            case MotionEvent.ACTION_CANCEL: {

            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    public static class Position {
        public int row;
        public int column;
    }
}
