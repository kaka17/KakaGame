package com.kaka.smargame.ui.activity;

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

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvGoBack;
    private EditText etPhone,etNum;
    private TextView tvGetNum;
    private Button btSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bind_phone);
        initView();
        setOnClickListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }
    private void initView(){
        tvGoBack = (TextView) findViewById(R.id.tvGoBack);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etNum = (EditText) findViewById(R.id.etNum);
        tvGetNum = (TextView) findViewById(R.id.tvGetNum);
        btSure = (Button) findViewById(R.id.btSure);

    }

    private void setOnClickListener(){
        tvGoBack.setOnClickListener(this);
        tvGetNum.setOnClickListener(this);
        btSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvGoBack:
                finish();
                break;
             case R.id.tvGetNum:
                break;
             case R.id.btSure:
                break;

        }
    }
}
