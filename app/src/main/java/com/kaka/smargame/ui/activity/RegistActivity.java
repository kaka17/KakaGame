package com.kaka.smargame.ui.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kaka.smargame.AppContext;
import com.kaka.smargame.Config;
import com.kaka.smargame.R;
import com.kaka.smargame.api.base.HttpCallBack;
import com.kaka.smargame.api.base.HttpClient;
import com.kaka.smargame.api.base.ResponseBean;
import com.kaka.smargame.ui.activity.base.BaseActivity;
import com.kaka.smargame.ui.entity.MyInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistActivity extends BaseActivity implements View.OnClickListener{

    private EditText etName,etPwd,etSurePwd,etNickName;
    private Button btSure;
    private Button btgoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_regist);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    private void initView(){
        etName = (EditText) findViewById(R.id.etName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        etSurePwd = (EditText) findViewById(R.id.etSurePwd);
        etNickName = (EditText) findViewById(R.id.etNickName);
        btSure = (Button) findViewById(R.id.btSure);
        btgoBack = (Button) findViewById(R.id.btgoBack);
        btgoBack.setOnClickListener(this);
        btSure.setOnClickListener(this);

    }
    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
//        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }

        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSure:
                String pwd01 = etPwd.getText().toString().trim();
                String pwd02 = etSurePwd.getText().toString().trim();
                String nickName = etNickName.getText().toString().trim();
                if (pwd01.isEmpty()||pwd02.isEmpty()||nickName.isEmpty()){
                    AppContext.toast("请输入昵称和密码");
                    return;
                }
            if (!pwd01.equals(pwd02)){
                AppContext.toast("密码不一致，请输入输入");
                etPwd.setText("");
                etSurePwd.setText("");
            }else {
                initRegist(nickName,pwd01);
            }
                break;
            case R.id.btgoBack:
                finish();
                break;
        }

    }
    private void  initRegist(String nickName,String pwd){
        HttpClient.instance().regist(nickName, pwd, new HttpCallBack() {
            @Override
            public void onSuccess(ResponseBean responseBean) {

                try {
                    JSONObject object=new JSONObject(responseBean.toString());
                if (object.getString(Config.code).equals("0000")){
                    AppContext.toast(object.getString(Config.msg));
                    Log.e("Tag", "----->" + responseBean.toString());
                    MyInfo data = responseBean.getData(MyInfo.class);
                    AppContext.setProperty(Config.accountId,data.getAccountId());
                    AppContext.setProperty(Config.accountName,data.getAccountName());
                    AppContext.setProperty(Config.userPass,data.getUserPass());
                    Log.e("TAAAG", data.toString());
                }else {
                    AppContext.toast(responseBean.toString());
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                    AppContext.toast(responseBean.toString()+";;"+e.toString());
                }
            }
        });
    }

}
