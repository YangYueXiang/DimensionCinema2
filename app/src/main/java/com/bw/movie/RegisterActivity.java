package com.bw.movie;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.net.EncryptUtil;
import com.bw.movie.presenter.RegisterPresenter;
import com.bw.movie.view.IView.RegisterView;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.edit_regiter_name)
    EditText editRegiterName;
    @BindView(R.id.edit_register_number)
    EditText editRegisterNumber;
    @BindView(R.id.edit_register_pwd)
    EditText editRegisterPwd;
    @BindView(R.id.edit_register_pwd2)
    EditText editRegisterPwd2;
    @BindView(R.id.edit_register_email)
    EditText editRegisterEmail;
    @BindView(R.id.edit_register_data)
    TextView editRegisterData;
    @BindView(R.id.btn_boys)
    RadioButton btnBoys;
    @BindView(R.id.btn_girl)
    RadioButton btnGirl;
    @BindView(R.id.radio_register)
    RadioGroup radioRegister;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private int setNum;
    private String name;
    private String number;
    private String pwd;
    private String pwd2;
    private String email;
    private String data;
    private String pwdEncrypt;
    private String pwd2Encrypt;
    private RegisterPresenter registerPresenter;
    private SharedPreferences user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //沉浸式
        ImmersionBar.with(this).init();
        //选中性别赋数字
        radioRegister.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_boys:
                        setNum=1;
                        break;
                    case R.id.btn_girl:
                        setNum=2;
                        break;
                }
            }
        });

        editRegisterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editRegisterData.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2000, 1, 2).show();
            }
        });


        initData();

    }
    //请求数据
    private void initData() {
        registerPresenter = new RegisterPresenter();
        registerPresenter.attachView(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {

        HashMap<String,String> map=new HashMap<>();
        name = editRegiterName.getText().toString();
        number = editRegisterNumber.getText().toString();
        pwd = editRegisterPwd.getText().toString();
        pwd2 = editRegisterPwd2.getText().toString();
        email = editRegisterEmail.getText().toString();
        data = editRegisterData.getText().toString();

        if(!TextUtils.isEmpty(name)&&name.trim()!=""){
            if(!TextUtils.isEmpty(number)&number.trim()!=""&isMatcherFinded("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$",number)){
                if(!TextUtils.isEmpty(pwd)&pwd.trim()!=null){
                    if(!isMatcherFinded("^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,18}$",pwd)){
                        if(TextUtils.isEmpty(pwd)&pwd.trim()!=""){
                            Toast.makeText(this, "输入密码不能为空", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this, "密码格式不对，必须密码(6-18个字母、字符、数字、相结合)", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(pwd.equals(pwd2)){
                            if(!isMatcherFinded("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$",email)){
                                if(TextUtils.isEmpty(email)&email.trim()!=""){
                                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(this, "邮箱格式不对", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                pwdEncrypt = EncryptUtil.encrypt(pwd);
                                pwd2Encrypt = EncryptUtil.encrypt(pwd2);
                                map.put("nickName",name);
                                map.put("phone",number);
                                map.put("pwd",pwdEncrypt);
                                map.put("pwd2",pwd2Encrypt);
                                map.put("sex",setNum+"");
                                map.put("birthday",data);
                                map.put("email",email);

                                user = RegisterActivity.this.getSharedPreferences("user", MODE_PRIVATE);

                                SharedPreferences.Editor edit = user.edit();
                                edit.putString("name",name).commit();

                                registerPresenter.getRegisterData(map);
                            }
                        }else{
                            if(pwd2.trim()!=""&TextUtils.isEmpty(pwd2)){
                                Toast.makeText(this, "输入的重复密码不能为空", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else {
                    Toast.makeText(this, "输入密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }else{
                if(TextUtils.isEmpty(number)&number.trim()==""){
                    Toast.makeText(RegisterActivity.this, "输入的账号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "必须是手机号", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(RegisterActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    //正则表达式
    public static boolean isMatcherFinded(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    @Override
    public void registersuccess(RegisterBean registerBean) {
        String status = registerBean.getStatus();
        String message = registerBean.getMessage();
        if(status.equalsIgnoreCase("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.detachView();
    }

    @Override
    public void registererror() {

    }
}
