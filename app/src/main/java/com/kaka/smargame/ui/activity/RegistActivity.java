package com.kaka.smargame.ui.activity;

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
import com.kaka.smargame.ui.activity.base.BaseActivity;

public class RegistActivity extends BaseActivity implements View.OnClickListener{

    private EditText etName,etPwd,etNickName;
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
                break;
            case R.id.btgoBack:
                finish();
                break;
        }

    }
}
