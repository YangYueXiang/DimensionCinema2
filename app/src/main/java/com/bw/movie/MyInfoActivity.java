package com.bw.movie;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInfoActivity extends AppCompatActivity {

    @BindView(R.id.simp_myinfohead)
    SimpleDraweeView simpMyinfohead;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthdate)
    TextView tvBirthdate;
    @BindView(R.id.tv_telephone)
    TextView tvTelephone;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.img_resetpwd)
    ImageView imgResetpwd;
    @BindView(R.id.img_myinfoback)
    ImageView imgMyinfoback;
    @BindView(R.id.btn_exitlogin)
    Button btnExitlogin;
    private SharedPreferences sp;
    private String username;
    private String sex;
    private String birthday;
    private String telephone;
    private String email;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        imgMyinfoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnExitlogin.setOnClickListener(new View.OnClickListener() {

            private SharedPreferences sp;

            @Override
            public void onClick(View v) {
                sp = getSharedPreferences("user", MODE_PRIVATE);
                boolean isLogin = sp.getBoolean("isLogin", false);
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("isLogin",false).commit();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sp = getSharedPreferences("user", MODE_PRIVATE);
        isLogin = sp.getBoolean("isLogin", false);
        if (isLogin) {
            username = sp.getString("username", "");
            sex = sp.getString("sex", "");
            birthday = sp.getString("birthday", "");
            telephone = sp.getString("telephone", "");
            email = sp.getString("email", "");
            String touicon = sp.getString("touicon", "");
            simpMyinfohead.setImageURI(touicon);
            tvUsername.setText(username);
            tvSex.setText(sex);
            tvBirthdate.setText(birthday);
            tvTelephone.setText(telephone);
            tvEmail.setText(email);
        }

    }
}
