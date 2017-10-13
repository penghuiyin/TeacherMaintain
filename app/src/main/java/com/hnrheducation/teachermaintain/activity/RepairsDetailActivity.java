package com.hnrheducation.teachermaintain.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.hnrheducation.teachermaintain.R;
import com.hnrheducation.teachermaintain.base.BaseActivity;
import com.hnrheducation.teachermaintain.base.BaseFragment;
import com.hnrheducation.teachermaintain.fragment.RepairCompletFragment;
import com.hnrheducation.teachermaintain.fragment.RepairingFragment;
import com.hnrheducation.teachermaintain.view.TitleWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2017/10/12.
 * 报修详情
 */

public class RepairsDetailActivity extends BaseActivity {
    private TitleWidget titleWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairs_detail);
        initView();
        initData();
    }

    private void initData() {


    }

    private void initView() {
        titleWidget= (TitleWidget) findViewById(R.id.title);

    }

}
