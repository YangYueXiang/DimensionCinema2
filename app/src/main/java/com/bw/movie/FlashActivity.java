package com.bw.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlashActivity extends AppCompatActivity {

    @BindView(R.id.text_lin)
    LinearLayout textLin;
    @BindView(R.id.text_hide_lin)
    LinearLayout textHideLin;
    @BindView(R.id.image)
    ImageView image;

    /* private int time=3;
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==0){
                    if (time>0){
                        time--;
                        handler.sendEmptyMessageDelayed(0,1000);
                    }else{
                        Intent intent=new Intent(FlashActivity.this,ShowActivity.class);
                        startActivity(intent);
                    }
                }

            }
        };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        //  handler.sendEmptyMessageDelayed(0,1000);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.reflash_anim);
        image.startAnimation(animation);//开始执行动画
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //第一个动画执行完后执行第二个动画就是那个字体显示那部分
                animation=AnimationUtils.loadAnimation(FlashActivity.this,R.anim.reflash_four);
                textLin.startAnimation(animation);
                animation=AnimationUtils.loadAnimation(FlashActivity.this,R.anim.reflash_five);
                textHideLin.startAnimation(animation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(FlashActivity.this, com.bw.movie.ShowActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
