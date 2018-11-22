package com.bw.movie;

import android.os.Bundle;


import com.gyf.barlibrary.ImmersionBar;

public class BaseActivity extends CheckPermissionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ImmersionBar.with(this).init();
    }
}
