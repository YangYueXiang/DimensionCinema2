package com.bw.movie.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by YangYueXiang
 * on 2018/11/2
 */
public class App extends Application {

    public static Context context;
    public static IWXAPI mWxApi;

    private static String appid= "wxb3852e6a6b7d9516";
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context=getApplicationContext();

        registerToWX();
    }
    public static Context getApp(){
        return context;
    }

    private void registerToWX() {
        mWxApi = WXAPIFactory.createWXAPI(this, appid, false);
        // 将该app注册到微信
        mWxApi.registerApp(appid);
    }
}
