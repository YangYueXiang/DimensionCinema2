package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.LoginActivity;
import com.bw.movie.MyAttentionActivity;
import com.bw.movie.MyInfoActivity;
import com.bw.movie.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by YangYueXiang
 * on 2018/11/3
 */
public class UserFragment extends Fragment {
    @BindView(R.id.ming_remind)
    ImageView mingRemind;
    @BindView(R.id.simp_mine_head)
    SimpleDraweeView simpMineHead;
    @BindView(R.id.text_login_regist)
    TextView textLoginRegist;
    @BindView(R.id.btn_msg_mine)
    LinearLayout btnMsgMine;
    @BindView(R.id.btn_like_mine)
    LinearLayout btnLikeMine;
    @BindView(R.id.btn_rccord_mine)
    LinearLayout btnRccordMine;
    @BindView(R.id.btn_feedback_mine)
    LinearLayout btnFeedbackMine;
    @BindView(R.id.btn_version_mine)
    LinearLayout btnVersionMine;
    Unbinder unbinder;
    @BindView(R.id.img_myinfo)
    ImageView imgMyinfo;
    @BindView(R.id.myattention)
    ImageView myattention;
    @BindView(R.id.mybuyrecord)
    ImageView mybuyrecord;
    @BindView(R.id.my_yijianfankui)
    ImageView myYijianfankui;
    @BindView(R.id.my_zuixinbanben)
    ImageView myZuixinbanben;
    //  private String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        //  EventBus.getDefault().register(this);
        return view;
    }

   /* @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveMessage(MessageEvent msg){
        name = msg.getName();
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        textLoginRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

       /* textLoginRegist.setText(name);
        Log.i("wawawa",name);*/

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        String username = sp.getString("username", "username");
        if (isLogin){
            String touicon = sp.getString("touicon", "haha");
            Uri uri = Uri.parse(touicon);
            simpMineHead.setImageURI(uri);
            textLoginRegist.setText(username);
        }else{
            textLoginRegist.setText("登陆/注册");
            simpMineHead.setImageResource(R.drawable.ic_head);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.img_myinfo, R.id.myattention, R.id.mybuyrecord, R.id.my_yijianfankui, R.id.my_zuixinbanben})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_myinfo:
                SharedPreferences sp1 = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                boolean isLogin = sp1.getBoolean("isLogin", false);
                if (isLogin){
                    Intent intent=new Intent(getActivity(),MyInfoActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.myattention:
                SharedPreferences sp2 = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                boolean isLogin1 = sp2.getBoolean("isLogin", false);
                if (isLogin1){
                    Intent intent=new Intent(getActivity(),MyAttentionActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.mybuyrecord:
                break;
            case R.id.my_yijianfankui:
                break;
            case R.id.my_zuixinbanben:
                break;
        }
    }
}
