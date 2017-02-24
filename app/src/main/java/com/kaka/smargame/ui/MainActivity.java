package com.kaka.smargame.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.kaka.smargame.AppContext;
import com.kaka.smargame.R;
import com.kaka.smargame.ui.activity.base.BaseActivity;
import com.kaka.smargame.ui.activity.fragment.MainMessageFragment;
import com.kaka.smargame.ui.activity.fragment.MainRoomFragment;
import com.kaka.smargame.ui.activity.fragment.MainTaskFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private TextView mTitleBar;
    private LinearLayout llTask, llMessage, llRoom;
    TextView tvLabelTask, tvLabelMessage, tvLabelRoom;
    ImageView mTabLine, ivMore;
    private int screenWidth;

    BadgeView mBadgeViewforTask;
    BadgeView mBadgeViewforMessage;
    BadgeView mBadgeViewforRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();


    }

    private void InitView() {
        initTabLine();
        mTitleBar = (TextView) findViewById(R.id.tvTitleBar);
        mTitleBar.setText("智趣游戏厅");
        tvLabelTask = (TextView) findViewById(R.id.tvLabelTask);
        tvLabelMessage = (TextView) findViewById(R.id.tvLabelMessage);
        tvLabelRoom = (TextView) findViewById(R.id.tvLabelRoom);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        ivMore = (ImageView) findViewById(R.id.ivMore);
        llTask = (LinearLayout) findViewById(R.id.tab_task_ly);
        llMessage = (LinearLayout) findViewById(R.id.tab_message_ly);
        llRoom = (LinearLayout) findViewById(R.id.tab_room_ly);
        ivMore.setOnClickListener(this);
        llTask.setOnClickListener(this);
        llMessage.setOnClickListener(this);
        llRoom.setOnClickListener(this);

        MainTaskFragment tabTask = new MainTaskFragment();
        MainMessageFragment tabMessage = new MainMessageFragment();
        MainRoomFragment tabRoom = new MainRoomFragment();
        mFragments.add(tabTask);
        mFragments.add(tabMessage);
        mFragments.add(tabRoom);

        mBadgeViewforTask = new BadgeView(this);
        mBadgeViewforTask.setTargetView(tvLabelTask);
        mBadgeViewforTask.setBadgeGravity(Gravity.RIGHT);
        mBadgeViewforTask.setBadgeMargin(40, 0, 0, 0);
        mBadgeViewforTask.setBadgeCount(10);
        mBadgeViewforMessage = new BadgeView(MainActivity.this);
        mBadgeViewforMessage.setTargetView(tvLabelMessage);
        mBadgeViewforMessage.setBadgeGravity(Gravity.RIGHT);
        mBadgeViewforMessage.setBadgeMargin(40, 0, 0, 0);

        mBadgeViewforRoom = new BadgeView(this);
        mBadgeViewforRoom.setTargetView(tvLabelRoom);
        mBadgeViewforRoom.setBadgeGravity(Gravity.RIGHT|Gravity.TOP);
        mBadgeViewforRoom.setBadgeMargin(40, 0, 0, 0);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            LinearLayout.LayoutParams lp;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBadgeViewforRoom.setBadgeCount(5);
                mBadgeViewforMessage.setBadgeCount(5);
                mBadgeViewforTask.setBadgeCount(5);
                switch (position) {
                    case 0:
                        lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                                .getLayoutParams();
                        lp.leftMargin = 0;
                        mTabLine.setLayoutParams(lp);
                        mBadgeViewforTask.setBadgeCount(0);
                        break;
                    case 1:
                        lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                                .getLayoutParams();
                        lp.leftMargin = (int) ((screenWidth * 1.0 / 3));
                        mTabLine.setLayoutParams(lp);
                        mBadgeViewforMessage.setBadgeCount(0);
                        break;
                    case 2:
                        lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                                .getLayoutParams();
                        lp.leftMargin = (int) ((screenWidth * 2.0 / 3));
                        mTabLine.setLayoutParams(lp);
                        mBadgeViewforRoom.setBadgeCount(0);
                        break;
                }
                System.out.println("aaaaa " + mBadgeViewforTask.getBadgeCount() + " " + mBadgeViewforMessage.getBadgeCount() + " " + mBadgeViewforRoom.getBadgeCount());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabLine() {
        mTabLine = (ImageView) findViewById(R.id.id_tab_line);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLine.setLayoutParams(lp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected boolean hasToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        LinearLayout.LayoutParams lp;
        switch (v.getId()) {
            case R.id.ivMore:
                break;
            case R.id.tab_task_ly:
//                lp = (android.widget.LinearLayout.LayoutParams) mTabLine
//                        .getLayoutParams();
//                lp.leftMargin = 0;
//                mTabLine.setLayoutParams(lp);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tab_message_ly:
//                lp = (android.widget.LinearLayout.LayoutParams) mTabLine
//                        .getLayoutParams();
//                lp.leftMargin = (int) ((screenWidth * 1.0 / 3));
//                mTabLine.setLayoutParams(lp);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tab_room_ly:
//                lp = (android.widget.LinearLayout.LayoutParams) mTabLine
//                        .getLayoutParams();
//                lp.leftMargin = (int) ((screenWidth * 2.0 / 3));
//                mTabLine.setLayoutParams(lp);
                mViewPager.setCurrentItem(2);
                break;
        }
    }
}
