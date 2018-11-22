package com.bw.movie.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/9
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles={"热门影片","正在上映","即将上映"};
    private List<Fragment> list;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
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

    //用来设置tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
