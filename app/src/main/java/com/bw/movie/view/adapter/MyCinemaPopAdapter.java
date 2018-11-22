package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by YangYueXiang
 * on 2018/11/14
 */
public class MyCinemaPopAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private String[] titles={"热门影片","正在上映","即将上映"};

    public MyCinemaPopAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
