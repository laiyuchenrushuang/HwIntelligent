package com.example.administrator.k_means;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class KmeansView extends View {
    private float mViewWide;
    private float mViewHeight;
    private int mPoints;
    private Kcluster kmean;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        mViewWide = widthSize;
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        mViewHeight = heightSize;
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drownPiont(canvas);

    }

    //随机产生点的算法
    public void drownPiont(Canvas canvas) {
        // 创建笔刷
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        //点的大小
        p.setStrokeWidth(10.0f);
        //设置点的总数
        kmean.productpoint(mViewWide,mViewHeight);
        for (point px:kmean.ypo){
            canvas.drawPoint(px.x, px.y, p);//画一个点rec
        }
        kmean.movecore();
        Paint p1 = new Paint();
        p1.setColor(Color.RED);
        for (point px:kmean.pacoren){
            canvas.drawCircle(px.x, px.y, 10, p1);// 画圆，圆心的坐标(cx,cy)和半径radius
        }

    }

    public void makeMuchPoint(int i){
        kmean.pacore =null;
        kmean.pacoren = null;

        invalidate();
        kmean.setmPointTotal(i);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public KmeansView(Context context) {
        super(context);
    }

    public KmeansView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
         kmean = new Kcluster();
    }

    public KmeansView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KmeansView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
