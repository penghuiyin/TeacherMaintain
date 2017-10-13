package com.hnrheducation.teachermaintain.activity;

import android.os.Bundle;

import com.hnrheducation.teachermaintain.R;
import com.hnrheducation.teachermaintain.base.BaseActivity;
import com.hnrheducation.teachermaintain.view.TitleWidget;

/**
 * Created by My on 2017/10/12.
 * 报修详情
 */

public class RepairsActivity extends BaseActivity {
    private TitleWidget titleWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairs);
        initView();
        initData();
    }

    private void initData() {


    }

    private void initView() {
        titleWidget= (TitleWidget) findViewById(R.id.title);

    }

}
