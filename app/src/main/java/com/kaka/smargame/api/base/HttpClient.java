package com.kaka.smargame.api.base;

import android.content.Context;

import com.thetransactioncompany.jsonrpc2.*;
import com.kaka.smargame.AppContext;
import com.kaka.smargame.base.BaseApplication;
import com.kaka.smargame.util.LogUtil;

import org.apache.http.protocol.HTTP;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HttpClient extends BaseHttpClient {
    public static final String BASEPATH = "http://192.168.1.84/smartlock/server.php"; //测试服务器
    // public static final String BASEPATH = "http://192.168.1.120/smartlock/server.php";//huangrenxin
    // public static final String BASEPATH = "http://192.168.1.113/smartlock/server.php";//zhangxiwang
    // public static final String BASEPATH = "http://192.168.1.117/test.ashx";
    //   public static final String BASEPATH = "https://www.demo.com";

    //    public static final String LOGIN = "test.subtract";



    private static HttpClient instance = new HttpClient();

    public HttpClient() {
    }

    public static HttpClient instance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                instance = new HttpClient();
            }
        }
        return instance;
    }

    public static String getUrl(String url) {
        return BASEPATH + url;
    }

    public void login(String username, String password, HttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_name", username);
        params.put("password", password);
        send(BaseApplication.context(), "", params, callBack);
    }



    public void send(Context context, String method, Map<String, Object> params, HttpCallBack callBack) {
        try {
            JSONRPC2Request reqOut = new JSONRPC2Request(method, params, "123");
            reqOut.appendNonStdAttribute("auth", AppContext.getShareUserSessinid());
            StringEntity entity = new StringEntity(reqOut.toString(), HTTP.UTF_8);
            LogUtil.log("paramss:" + reqOut);
            BaseHttpClient.post(context, BASEPATH, entity, "application/json-rpc", new ResponseHandler(context, callBack));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }




}
