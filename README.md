# 高亮显示引导页
千帆Android快速开发框架汇总

本文只集成自定义显示图片文字的位置、自定义高亮形状和大小

### 正在慢慢完善中ing

## 集成
    compile 'com.xyx:highlight:1.0.0'

## 功能

```
public  void showKnownTipView(View view)
{
    mHightLight = new HighLight(MainActivity.this)//
            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
            .intercept(false)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
            .setClickCallback(new HighLight.OnClickCallback() {
                @Override
                public void onClick() {
                    Toast.makeText(MainActivity.this, "clicked and remove HightLight view by yourself", Toast.LENGTH_SHORT).show();
                    remove(null);
                }
            })
            .anchor(findViewById(R.id.id_container))//如果是Activity上增加引导层，不需要设置anchor
            .addHighLight(R.id.btn_rightLight,R.layout.info_known,new OnLeftPosCallback(45),new RectLightShape())
            .addHighLight(R.id.btn_light,R.layout.info_known,new OnRightPosCallback(5),new CircleLightShape(0,0,0))
            .addHighLight(R.id.btn_bottomLight,R.layout.info_known,new OnTopPosCallback(),new CircleLightShape())
            .addHighLight(view,R.layout.info_known,new OnBottomPosCallback(10),new OvalLightShape(5,5,20));
    mHightLight.show();
}
```

1. anchor(View anchor) 显示区域--阴影区域  anchor：显示区域view
2. addHighLight(int viewId, int decorLayoutId, OnPosCallback onPosCallback, LightShape lightShape)
    viewId ：显示在viewId旁边
    decorLayoutId ：布局xml的id
    OnPosCallback： 自定义图片显示位置
    LightShape：自定义高亮位置和大小
3. 创建相应的xml布局文件。
4. 自定义显示图片文字的位置、自定义高亮形状和大小。HighLight.OnPosCallback是控制图片的显示位置，drawShape是控制显示圆形和矩形，并控制显示高亮的大小。




## 大致的原理
1. 设置Paint背景阴影，用PorterDuffXfermode的DST_OUT(取下层绘制非交集部分)模式设置画笔，画出高亮部分。
2. inflate加载布局xml，addview到当前view；并设置显示位置。


### [进一步使用，查看wiki](https://github.com/xyxStyle/HighLightGuide)

## 最后
1. 参考 https://github.com/hongyangAndroid/Highlight
2. 本文只集成自定义显示图片文字的位置、自定义高亮形状和大小。没有集成 下一步模式，具体的可以参加官网例子