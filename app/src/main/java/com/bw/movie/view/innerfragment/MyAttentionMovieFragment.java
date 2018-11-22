package com.bw.movie.view.innerfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bw.movie.R;
import com.bw.movie.model.bean.MyAttentionBean;
import com.bw.movie.presenter.MyAttentionPresenter;
import com.bw.movie.view.IView.MyAttentionView;
import com.bw.movie.view.adapter.MyAttentionMovieAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by YangYueXiang
 * on 2018/11/21
 */
public class MyAttentionMovieFragment extends Fragment implements MyAttentionView {
    @BindView(R.id.recycler_myattentionmovie)
    RecyclerView recyclerMyattentionmovie;
    Unbinder unbinder;
    private MyAttentionPresenter myAttentionPresenter;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myattention_movie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String userId = sp.getString("userId", "");
        String sessionId = sp.getString("sessionId", "");
        myAttentionPresenter = new MyAttentionPresenter();
        myAttentionPresenter.attachView(this);
        myAttentionPresenter.getAttentionData(userId,sessionId,String.valueOf(1),String.valueOf(10));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void attentionsuccess(MyAttentionBean myAttentionBean) {
       // Toast.makeText(getActivity(), "myAttentionBean:" + myAttentionBean, Toast.LENGTH_SHORT).show();
        recyclerMyattentionmovie.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        MyAttentionMovieAdapter myAttentionMovieAdapter = new MyAttentionMovieAdapter(getActivity(), myAttentionBean.getResult());
        recyclerMyattentionmovie.setAdapter(myAttentionMovieAdapter);
    }

    @Override
    public void attentionerror() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myAttentionPresenter.detachView();
    }
}
