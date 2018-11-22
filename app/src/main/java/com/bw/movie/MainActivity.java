package com.bw.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;


import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.view.adapter.MyWelcomeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager_welcome)
    ViewPager viewpagerWelcome;
    @BindView(R.id.bt_main)
    Button btMain;
    private ArrayList<Integer> integers;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        integers = new ArrayList<>();
        integers.add(R.drawable.welcome_1);
        integers.add(R.drawable.welcome_2);
        integers.add(R.drawable.welcome_3);
        integers.add(R.drawable.welcome_4);
        MyWelcomeAdapter myWelcomeAdapter = new MyWelcomeAdapter(MainActivity.this, integers);
        viewpagerWelcome.setAdapter(myWelcomeAdapter);

        viewpagerWelcome.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == integers.size()-1) {
                    btMain.setVisibility(View.VISIBLE);
                    AlphaAnimation aa = new AlphaAnimation(0, 1f);
                    aa.setDuration(1000);
                    btMain.startAnimation(aa);
                } else {
                    btMain.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        sp = getSharedPreferences("WelCome", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if(isFirst==true){
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();
        }else{
            Intent intent = new Intent(MainActivity.this,FlashActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @OnClick(R.id.bt_main)
    public void onViewClicked() {
        Intent intent= new Intent(MainActivity.this,FlashActivity.class);
        startActivity(intent);
        finish();
    }
}
