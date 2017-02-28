package com.kaka.smargame.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kaka.smargame.R;
import com.kaka.smargame.ui.activity.base.BaseActivity;

public class MainGameActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvUrseInfo,tvGameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_game);
        initView();
        setOnClickListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_game;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    private void initView(){
        tvUrseInfo = (TextView) findViewById(R.id.tvUrseInfo);
        tvGameInfo = (TextView) findViewById(R.id.tvGameInfo);
    }

    private void setOnClickListener(){
        tvUrseInfo.setOnClickListener(this);
        tvGameInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvUrseInfo:
                startActivity(new Intent(getApplicationContext(),MyInfoActivity.class));
                break;
            case R.id.tvGameInfo:
                break;
        }
    }
}
