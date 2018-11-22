package com.bw.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bw.movie.app.App;
import com.bw.movie.model.bean.EventCode;
import com.bw.movie.model.bean.WxBean;
import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.net.EncryptUtil;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.view.IView.LoginView;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.ed_login_number)
    EditText edLoginNumber;
    @BindView(R.id.ed_login_password)
    EditText edLoginPassword;
    @BindView(R.id.btn_ying)
    ToggleButton btnYing;
    @BindView(R.id.save_pwd)
    CheckBox savePwd;
    @BindView(R.id.zd_login)
    CheckBox zdLogin;
    @BindView(R.id.btn_regirect_tiao)
    TextView btnRegirectTiao;
    @BindView(R.id.member_activity_login_layout)
    LinearLayout memberActivityLoginLayout;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.weixin_login)
    ImageView weixinLogin;
    private String loginName;
    private String loginPwd;
    private String loencryptPwd;
    private LoginPresenter loginPresenter;
    private SharedPreferences user;
    private String sessionId;
    private String userId;
    private SharedPreferences.Editor edit;

    private static IWXAPI iwxapi;
    private static final String APP_ID ="wxb3852e6a6b7d9516";


    private void regToWx() {
        iwxapi=WXAPIFactory.createWXAPI(LoginActivity.this,APP_ID,true);
        iwxapi.registerApp(APP_ID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        regToWx();
        EventBus.getDefault().register(this);
        //绑定
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @OnClick({R.id.btn_ying, R.id.btn_regirect_tiao, R.id.btn_login, R.id.weixin_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ying:
                btnYing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            edLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }else{
                            edLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                    }
                });
                break;
            case R.id.btn_regirect_tiao:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                HashMap<String,String> loginMap=new HashMap<>();
                loginName = edLoginNumber.getText().toString();
                loginPwd = edLoginPassword.getText().toString();

                if(TextUtils.isEmpty(loginName)){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    if(TextUtils.isEmpty(loginPwd)){
                        Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }else{
                        loencryptPwd = EncryptUtil.encrypt(loginPwd);
                        loginMap.put("phone",loginName);
                        loginMap.put("pwd",loencryptPwd);
                        Log.d("LoginActivity", loginName + "=====" + loencryptPwd);
                        loginPresenter.getLoginData(loginMap);
                    }
                }
                break;
            case R.id.weixin_login:
                //Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show();
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wxb3852e6a6b7d9516";
                //向微信发送请求
                App.mWxApi.sendReq(req);
                break;
        }
    }
    //成功
    @Override
    public void loginsuccess(LoginBean loginBean) {
        String status = loginBean.getStatus();
        String message = loginBean.getMessage();
       if(message.equals("登陆成功")){
           LoginBean.ResultBean result = loginBean.getResult();
           //   Toast.makeText(this, "loginBean.getResult():" + loginBean.getResult(), Toast.LENGTH_SHORT).show();
           sessionId = loginBean.getResult().getSessionId();
           userId = loginBean.getResult().getUserId();
           Log.i("sessionId",sessionId+"");
           Log.i("userId",userId+"");
           Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
           user=getSharedPreferences("user",MODE_PRIVATE);
           edit = user.edit();
           edit.putString("touicon",result.getUserInfo().getHeadPic());
           edit.putString("username",result.getUserInfo().getNickName());
           edit.putString("userId",userId);
           edit.putString("sessionId",sessionId);
           if (Integer.parseInt(result.getUserInfo().getSex())==1){
               edit.putString("sex","男");
           }else{
               edit.putString("sex","女");
           }
           edit.putString("birthday",String.valueOf(result.getUserInfo().getBirthday()));
           edit.putString("telephone",result.getUserInfo().getPhone());
           edit.putString("email", String.valueOf(result.getUserInfo().getLastLogStringime()));
           edit.putBoolean("isLogin",true);
           edit.commit();
           // EventBus.getDefault().post(new MessageEvent(4));
           finish();
       }else{
           Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
       }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void loginerror(LoginBean loginBean) {
        //Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void wxloginsuccess(WxBean loginBean) {
        String status = loginBean.getStatus();
        String message = loginBean.getMessage();
        if(message.equals("登陆成功")){
            WxBean.ResultBean result = loginBean.getResult();
            //   Toast.makeText(this, "loginBean.getResult():" + loginBean.getResult(), Toast.LENGTH_SHORT).show();
            sessionId = loginBean.getResult().getSessionId();
            userId = loginBean.getResult().getUserId();
            Log.i("sessionId",sessionId+"");
            Log.i("userId",userId+"");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            user=getSharedPreferences("user",MODE_PRIVATE);
            edit = user.edit();
            edit.putString("touicon",result.getUserInfo().getHeadPic());
            edit.putString("username",result.getUserInfo().getNickName());
            edit.putString("userId",userId);
            edit.putString("sessionId",sessionId);
            if (Integer.parseInt(result.getUserInfo().getSex())==1){
                edit.putString("sex","男");
            }else{
                edit.putString("sex","女");
            }
            edit.putString("birthday",String.valueOf(result.getUserInfo().getBirthday()));
            edit.putString("telephone",result.getUserInfo().getPhone());
            edit.putString("email", String.valueOf(result.getUserInfo().getLastLogStringime()));
            edit.putBoolean("isLogin",true);
            edit.commit();
            // EventBus.getDefault().post(new MessageEvent(4));
            finish();
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void wxloginerror() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getcode(EventCode eventCode){
        String code = eventCode.getCode();
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
        loginPresenter.getWXLoginData(code);
    }
}
