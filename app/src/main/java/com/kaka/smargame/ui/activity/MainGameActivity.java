package com.kaka.smargame.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.kaka.smargame.R;
import com.kaka.smargame.ui.activity.base.BaseActivity;

public class MainGameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_game);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_game;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }





}
