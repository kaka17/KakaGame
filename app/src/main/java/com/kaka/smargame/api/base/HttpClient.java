package com.kaka.smargame.api.base;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.thetransactioncompany.jsonrpc2.*;
import com.kaka.smargame.AppContext;
import com.kaka.smargame.base.BaseApplication;
import com.kaka.smargame.util.LogUtil;

import org.apache.http.protocol.HTTP;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient extends BaseHttpClient {
//    public static final String BASEPATH = "http://192.168.1.105:9000/"; //测试服务器
     public static final String BASEPATH = "http://1x6703n563.imwork.net/";//huangrenxin
    // public static final String BASEPATH = "http://192.168.1.113/smartlock/server.php";//zhangxiwang
    // public static final String BASEPATH = "http://192.168.1.117/test.ashx";
    //   public static final String BASEPATH = "https://www.demo.com";

    public static final String LOGIN = "kaka/user/login";
    public static final String regist = "kaka/user/regist";


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

    public void sedByAsyncHttpClientPost(Context context, String ureName, String UrlPath, Map<String, Object> maps, HttpCallBack callBack) {
        // 创建异步的客户端对象
//        AsyncHttpClient client = new AsyncHttpClient();
        // 请求的地址
        String url = BASEPATH + UrlPath;
        LogUtil.log("paramss:" + url);
//        RequestParams params=new RequestParams();
        RequestParams params = new RequestParams();
        params.put("accountId", ureName);
        params.put("token", "");
        params.put("onlineId", "");
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(maps);
        params.put("data", mapList);
        Log.e("TAg", "---->" + params.toString());
        BaseHttpClient.post(url, params, new ResponseHandler(context, callBack));
    }

    public void sedPost(Context context, String ureName, String UrlPath, Map<String, Object> maps, HttpCallBack callBack) {
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("accountId",ureName);
            jsonObject.put("token","");
            jsonObject.put("onlineId","");
            JSONObject object=new JSONObject(maps);
            jsonObject.put("data",object);
            StringEntity entity = new StringEntity(jsonObject.toString(), HTTP.UTF_8);
            LogUtil.log("paramss:" + jsonObject);
            Log.e("paramss:" ,jsonObject.toString());
            BaseHttpClient.post(context, BASEPATH+UrlPath, entity, "application/json-rpc", new ResponseHandler(context, callBack));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login02(String username, String password, HttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("userPass", password);
//        params.put("token", "");
        sedPost(BaseApplication.context(), username, LOGIN, params, callBack);
//        sedByAsyncHttpClientPost(BaseApplication.context(), username, LOGIN, params, callBack);
    }
    public void regist(String accountName,String userPass, HttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountName", accountName);
        params.put("userPass", userPass);
        sedPost(BaseApplication.context(), "", regist, params, callBack);
    }

}
