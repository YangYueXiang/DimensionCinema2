package com.bw.movie.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.bw.movie.R;
import com.bw.movie.model.bean.EventCode;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static IWXAPI iwxapi;
    private static final String APP_ID ="wxb3852e6a6b7d9516";
    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        //如果没回调onResp，八成是这句没有写
        iwxapi=WXAPIFactory.createWXAPI(this,APP_ID,false);
        iwxapi.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int type = baseResp.getType();
        if(type==RETURN_MSG_TYPE_LOGIN){
            //登录回调
            SendAuth.Resp resp = (SendAuth.Resp) baseResp;
            switch (resp.errCode){
                case BaseResp.ErrCode.ERR_OK:
                    String code = resp.code;
                   // Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().postSticky(new EventCode(code));
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    break;
            }
        }
    }
}
