package com.kaka.smargame.api.base;

import android.content.Context;
import android.os.Handler;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.kaka.smargame.AppContext;
import com.kaka.smargame.util.LogUtil;


import org.apache.http.Header;

public class ResponseHandler extends AsyncHttpResponseHandler {
    private HttpCallBack mHandler;
    private Context context;

    public ResponseHandler(Context context, HttpCallBack mHandler) {
        this.context = context;
        this.mHandler = mHandler;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (context == null || !AppContext.instance().isNetworkConnected()) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mHandler.onStart();
            }
        });
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (context == null) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mHandler.onFinish();
            }
        });
    }

    private static final int DELAYTIME = 700;

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable arg3) {
        if (context == null) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 mHandler.onFailure("", "");
             }
        }, DELAYTIME);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, final byte[] responseBody) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (context == null) {
                        return;
                    }
                    String result = new String(responseBody);
                    LogUtil.log("paramss onsuccess " + result);
                    mHandler.onSuccess(new ResponseBean(result));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, DELAYTIME);
    }
}
