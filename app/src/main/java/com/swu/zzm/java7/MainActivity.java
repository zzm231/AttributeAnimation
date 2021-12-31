package com.swu.zzm.java7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private int[] resId = {R.id.ib_del,R.id.ib_photo,R.id.ib_time};

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
                // 计算平分之后两个之间的角度
                float angle = 180 / (resId.length+1);

                // 遍历数组 取出每一个按钮
                for (int i = 0; i < resId.length; i++) {
                    // 获取对应id控件
                    ImageButton ib = findViewById(resId[i]);

                    // 计算当前控件对应的角度
                    float mAngle = (i + 1) * angle;

                    // 计算x距离
                    float x = (float) (Math.cos(mAngle) * 400);
                    // 计算y的距离
                    float y = (float) (Math.sin(mAngle) * 400);

                    // 移动的动画
                    TranslateAnimation translateAnimation = new TranslateAnimation(
                            0,x,0,-y);
                    translateAnimation.setDuration(500);
                    translateAnimation.setFillAfter(true);
                    translateAnimation.setInterpolator(new BounceInterpolator());

                    // 开始动画
                    ib.startAnimation(translateAnimation);
                }
            }
        });

    }

}