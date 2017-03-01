package com.kaka.smargame.ui.activity;

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
    private TextView tvGameInfo;

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

    }

    private void setOnClickListener(){
        ivIcon.setOnClickListener(this);
        tvGameInfo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivIcon:

                break;
             case R.id.tvGameInfo:
                    finish();
                break;

        }
    }
}
