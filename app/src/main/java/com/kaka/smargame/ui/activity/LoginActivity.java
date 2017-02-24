package com.kaka.smargame.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kaka.smargame.R;
import com.kaka.smargame.ui.MainActivity;
import com.kaka.smargame.ui.activity.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText etName,etPwd;
    private Button btLogin;
    private TextView tvRegist;

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

    }
    private void setOnClistener(){
        btLogin.setOnClickListener(this);
        tvRegist.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:
                Intent intent=new Intent(getApplicationContext(), MainGameActivity.class);
                startActivity(intent);
                break;
            case R.id.tvRegist:
                startActivity(new Intent(getApplicationContext(),RegistActivity.class));
                break;
        }
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
}
