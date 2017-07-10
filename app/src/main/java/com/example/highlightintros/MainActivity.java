package com.example.highlightintros;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.xyx.highlightguide.HighLight;
import com.xyx.highlightguide.shape.BaseLightShape;

//  https://github.com/hongyangAndroid/Highlight
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HighLight mHightLight;//高亮显示控件
    private HighLight mHightLight2;//高亮显示控件
    private HighLight mHightLight3;//高亮显示控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //页面加载完成，自动显示
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getWindow().getDecorView().getViewTreeObserver()
                            .removeOnGlobalLayoutListener(this);
                }

                //界面初始化后显示高亮布局
                initHighLight1();
                mHightLight.show();
            }

        });

        findViewById(R.id.textView).setOnClickListener(this);
        findViewById(R.id.textView2).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                initHighLight2();
                mHightLight2.show();
                break;
            case R.id.textView2:
                initHighLight3();
                mHightLight3.show();
                break;
        }
    }

    /**
     * 显示两者
     */
    private void initHighLight1() {
        if (mHightLight == null) {
            mHightLight = new HighLight(MainActivity.this).
                    anchor(findViewById(R.id.cl_root)).//显示区域--阴影区域，如果是Activity上增加引导层，不需要设置anchor
                    addHighLight(R.id.textView, R.layout.view_highlight_main,//画在textview的高亮框，布局文件为view_highlight_main
                    new HighLight.OnPosCallback() {
                        @Override
                        public void getPos(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                            marginInfo.bottomMargin = bottomMargin + rectF.height(); //底部的距离为textview的底部位置+ 自己的高度
                            marginInfo.leftMargin = rectF.left;//左边的间距为textview的间距
                        }
                    }, new BaseLightShape() {
                        @Override
                        protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
                            //缩小高亮控件范围
                            viewPosInfoRectF.inset(dx, dy);
                        }

                        @Override
                        protected void drawShape(Bitmap bitmap, HighLight.ViewPosInfo viewPosInfo) {
                            //custom your hight light shape 自定义高亮形状
                            // 显示成矩形
                            Canvas canvas = new Canvas(bitmap);
                            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                            paint.setDither(true);
                            paint.setAntiAlias(true);
                            paint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.SOLID));
                            canvas.drawRoundRect(viewPosInfo.rectF, 10, 10, paint);
                        }
                    })
                    .addHighLight(R.id.textView2, R.layout.view_highlight_main1, new HighLight.OnPosCallback() {
                        @Override
                        public void getPos(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                            marginInfo.topMargin = rectF.bottom + rectF.width() / 2; //顶部的距离为textview的底部位置+ 圆心的半径
//                            marginInfo.leftMargin = rectF.left;//左边的间距需要调整可以设置。
                        }
                    }, new BaseLightShape() {
                        @Override
                        protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
                            //缩小高亮控件范围
                            viewPosInfoRectF.inset(dx, dy);
                        }

                        @Override
                        protected void drawShape(Bitmap bitmap, HighLight.ViewPosInfo viewPosInfo) {
                            //custom your hight light shape 自定义高亮形状
                            // 显示成圆形
                            Canvas canvas = new Canvas(bitmap);
                            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                            paint.setDither(true);
                            paint.setAntiAlias(true);
                            paint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.SOLID));
                            RectF rectF = viewPosInfo.rectF;
                            canvas.drawCircle(rectF.left + rectF.width() / 2, rectF.top + rectF.height() / 2
                                    , rectF.width() / 2, paint);
                        }
                    });
        }
    }

    /**
     * 显示textview1
     */
    private void initHighLight2() {
        if (mHightLight2 == null) {
            mHightLight2 = new HighLight(MainActivity.this).
                    anchor(findViewById(R.id.cl_root)).//显示区域--阴影区域，如果是Activity上增加引导层，不需要设置anchor
                    addHighLight(R.id.textView, R.layout.view_highlight_main,//画在textview的高亮框，布局文件为view_highlight_main
                    new HighLight.OnPosCallback() {
                        @Override
                        public void getPos(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                            marginInfo.bottomMargin = bottomMargin + rectF.height(); //底部的距离为textview的底部位置+ 自己的高度
                            marginInfo.leftMargin = rectF.left;//左边的间距为textview的间距
                        }
                    }, new BaseLightShape() {
                        @Override
                        protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
                            //缩小高亮控件范围
                            viewPosInfoRectF.inset(dx, dy);
                        }

                        @Override
                        protected void drawShape(Bitmap bitmap, HighLight.ViewPosInfo viewPosInfo) {
                            //custom your hight light shape 自定义高亮形状
                            // 显示成矩形
                            Canvas canvas = new Canvas(bitmap);
                            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                            paint.setDither(true);
                            paint.setAntiAlias(true);
                            paint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.SOLID));
                            canvas.drawRoundRect(viewPosInfo.rectF, 10, 10, paint);
                        }
                    });
        }
    }

    /**
     * 显示textview2
     */
    private void initHighLight3() {
        if (mHightLight3 == null) {
            mHightLight3 = new HighLight(MainActivity.this).
                    anchor(findViewById(R.id.cl_root)).//显示区域--阴影区域，如果是Activity上增加引导层，不需要设置anchor
                    addHighLight(R.id.textView2, R.layout.view_highlight_main1, new HighLight.OnPosCallback() {//画在textview的高亮框，布局文件为view_highlight_main
                @Override
                public void getPos(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                    marginInfo.topMargin = rectF.bottom + rectF.width() / 2; //顶部的距离为textview的底部位置+ 圆心的半径
//                            marginInfo.leftMargin = rectF.left;//左边的间距需要调整可以设置。
                }
            }, new BaseLightShape() {
                @Override
                protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
                    //缩小高亮控件范围
                    viewPosInfoRectF.inset(dx, dy);
                }

                @Override
                protected void drawShape(Bitmap bitmap, HighLight.ViewPosInfo viewPosInfo) {
                    //custom your hight light shape 自定义高亮形状
                    // 显示成圆形
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setDither(true);
                    paint.setAntiAlias(true);
                    paint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.SOLID));
                    RectF rectF = viewPosInfo.rectF;
                    canvas.drawCircle(rectF.left + rectF.width() / 2, rectF.top + rectF.height() / 2
                            , rectF.width() / 2, paint);
                }
            });
        }
    }
}
