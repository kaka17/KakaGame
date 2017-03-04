package com.kaka.smargame.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.kaka.smargame.AppContext;
import com.kaka.smargame.Config;
import com.kaka.smargame.R;
import com.kaka.smargame.api.base.HttpCallBack;
import com.kaka.smargame.api.base.HttpClient;
import com.kaka.smargame.api.base.ResponseBean;
import com.kaka.smargame.ui.MainActivity;
import com.kaka.smargame.ui.activity.base.BaseActivity;
import com.kaka.smargame.widget.dialog.ProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText etName,etPwd;
    private Button btLogin;
    private TextView tvRegist;
    private CheckBox cbIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        initView();
        setOnClistener();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    private void initView(){
        etName = (EditText) findViewById(R.id.etName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btLogin = (Button) findViewById(R.id.btLogin);
        tvRegist = (TextView) findViewById(R.id.tvRegist);
        cbIsTrue = (CheckBox) findViewById(R.id.cbIsTrue);
    }
    private void setOnClistener(){
        btLogin.setOnClickListener(this);
        tvRegist.setOnClickListener(this);
        cbIsTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:
//                Intent intent=new Intent(getApplicationContext(), MainGameActivity.class);
//                startActivity(intent);
                String ureName = etName.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                if (ureName.equals("")||ureName.isEmpty()||pwd.equals("")||pwd.isEmpty()){
                    AppContext.toast("请输入账号密码");
                    return;
                }
                login(ureName,pwd );
                break;
            case R.id.tvRegist:
                startActivity(new Intent(getApplicationContext(),RegistActivity.class));
                break;
        }
    }
    private void login(String ureName,String pwd){
        HttpClient.instance().login02(ureName, pwd, new HttpCallBack() {
            @Override
            public void onStart() {
                super.onStart();
                ProgressDialog.show(LoginActivity.this,"正在登录...");
            }

            @Override
            public void onFailure(String error, String message) {
                super.onFailure(error, message);
                ProgressDialog.disMiss();
                AppContext.toast("网络连接失败");
                startActivity(new Intent(getApplicationContext(),MainGameActivity.class));
            }

            @Override
            public void onSuccess(ResponseBean responseBean) {
                ProgressDialog.disMiss();
                try {
                    JSONObject object=new JSONObject(responseBean.toString());
                    if (object.getString(Config.code).equals("0000")){
                        AppContext.toast(object.getString(Config.msg));
                    }else {
                        AppContext.toast(responseBean.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
        if (AppContext.getProperty(Config.accountId)!=null){
            etName.setText(AppContext.getProperty(Config.accountId));
        }
        if (AppContext.getProperty(Config.userPass)!=null){
            etPwd.setText(AppContext.getProperty(Config.userPass));
        }


    }
}
