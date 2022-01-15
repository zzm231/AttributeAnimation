package com.swu.zzm.java7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private int[] resId = {R.id.ib_del,R.id.ib_photo,R.id.ib_time};
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        // 给菜单按钮添加动画
        ImageButton menu = findViewById(R.id.ib_icon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历数组 取出每一个按钮
                for (int i = 0; i < resId.length; i++) {
                    // 判断是打开还是关闭
                    if (isOpen == true){
                        // 之前是打开 现在需要关闭
                        close(i);
                    }else {
                        // 之前是关闭 现在需要打开
                        open(i);
                    }
                }
                isOpen = !isOpen;
            }
        });
    }

    public void open(int i){
        animate(i,true);
    }

    public void close(int i){
        animate(i,false);
    }

    public void animate(int i,boolean state){
        // 计算平分之后两个之间的角度
        double angle = Math.PI/ (resId.length+1);

        // 计算当前控件对应的角度
        double mAngle = (i + 1) * angle;

        // 计算x距离
        float x = (float) (Math.cos(mAngle) * 400);
        // 计算y的距离
        float y = (float) (Math.sin(mAngle) * 400);

        // 获取对应id控件
        ImageButton ib = findViewById(resId[i]);

        float startX;
        float toX;
        float startY;
        float toY;
        Interpolator interpolator;
        if (state == true){
            startX = 0;
            startY = 0;
            toX = x;
            toY = -y;
            interpolator = new BounceInterpolator();
        }else {
            startX = x;
            startY = -y;
            toX = 0;
            toY = 0;
            interpolator = new AnticipateInterpolator();
        }

        // 记录上一次动画之后的X和Y位置
        // ib.getTranslationX();
        // ib.getTranslationY();

        //旋转动画
        RotateAnimation rAnim = new RotateAnimation(0,360*3,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rAnim.setDuration(500);
        rAnim.setFillAfter(true);

        // 移动的动画
        TranslateAnimation tAnim = new TranslateAnimation(
                startX,toX,
                startY,toY);

        tAnim.setDuration(500);
        tAnim.setFillAfter(true);
        tAnim.setInterpolator(interpolator);

        // 一个AnimationSet集合 包裹多个动画
        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(true);
        set.addAnimation(rAnim);
        set.addAnimation(tAnim);

        // 开始动画
        ib.startAnimation(tAnim);
    }

}
















