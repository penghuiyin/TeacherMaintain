package com.hnrheducation.teachermaintain.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.hnrheducation.teachermaintain.R;
import com.hnrheducation.teachermaintain.base.BaseActivity;
import com.hnrheducation.teachermaintain.view.TitleWidget;

/**
 * Created by My on 2017/10/11.
 * 用户登录
 */

public class LogInActivity extends BaseActivity implements View.OnClickListener {
    private TitleWidget titleWidget;
    /** 用户密码*/
    private AppCompatEditText acet_userpwd;
    /** 用户账号*/
    private AppCompatEditText acet_username;
    /** 用户登录*/
    private AppCompatButton acbtn_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        titleWidget= (TitleWidget) findViewById(R.id.title);
        acet_userpwd= (AppCompatEditText) findViewById(R.id.acet_userpwd);
        acet_username= (AppCompatEditText) findViewById(R.id.acet_username);
        acbtn_Login= (AppCompatButton) findViewById(R.id.acbtn_Login);
        acbtn_Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.acbtn_Login://登录
                break;
        }
    }
}
