package com.kaka.smargame.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaka.smargame.R;
import com.kaka.smargame.ui.activity.base.BaseActivity;

public class MyInfoActivity extends BaseActivity implements View.OnClickListener{

    private ImageView ivIcon;
    private TextView tvGameInfo,tvBindPhone,tvRecharge,tvVault,tvSet,tvDuihuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_info);
        initView();
        setOnClickListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    private void initView(){
        ivIcon = (ImageView) findViewById(R.id.ivIcon);
        tvGameInfo = (TextView) findViewById(R.id.tvGameInfo);

        tvBindPhone = (TextView) findViewById(R.id.tvBindPhone);
        tvRecharge = (TextView) findViewById(R.id.tvRecharge);
        tvVault = (TextView) findViewById(R.id.tvVault);
        tvSet = (TextView) findViewById(R.id.tvSet);
        tvDuihuan = (TextView) findViewById(R.id.tvDuihuan);

    }

    private void setOnClickListener(){
        ivIcon.setOnClickListener(this);
        tvGameInfo.setOnClickListener(this);
        tvBindPhone.setOnClickListener(this);
        tvRecharge.setOnClickListener(this);
        tvVault.setOnClickListener(this);
        tvSet.setOnClickListener(this);
        tvDuihuan.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivIcon:

                break;
             case R.id.tvGameInfo:
                    finish();
                break;
            case R.id.tvBindPhone:
                startActivity(new Intent(getApplicationContext(),BindPhoneActivity.class));
                break;
            case R.id.tvRecharge:
                break;
            case R.id.tvVault:
                break;
            case R.id.tvSet:
                break;
            case R.id.tvDuihuan:
                break;


        }
    }
}
