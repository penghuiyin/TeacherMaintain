package com.hnrheducation.teachermaintain;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hnrheducation.teachermaintain.activity.RepairSscheduleActivity;
import com.hnrheducation.teachermaintain.activity.RepairsDetailActivity;
import com.hnrheducation.teachermaintain.base.BaseActivity;
import com.hnrheducation.teachermaintain.view.TitleWidget;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TitleWidget title;
    /** 用户位置*/
    private AppCompatTextView actv_adress;
    /** 用户LOGO显示*/
    private ImageView iv_xxlogo;
    /** 用户学校的名字*/
    private AppCompatTextView actv_xyname;
    /** 我要审批*/
    private RelativeLayout rlyt_repairs;
    /** 维修中*/
    private RelativeLayout rlyt_maintain;
    /** 已完成*/
    private RelativeLayout rlyt_accomplish;
    /** 我要报修*/
    private RelativeLayout rlyt_baoxiu;
    /** 报修进度*/
    private RelativeLayout rlyt_baoxiujd;
    /** 我的设备*/
    private RelativeLayout rlyt_wdsb;
    /** 学习交流*/
    private RelativeLayout rlyt_xxjl;
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintView();

    }

    private void inintView() {
        title=(TitleWidget)findViewById(R.id.title);
        actv_adress=(AppCompatTextView)findViewById(R.id.actv_adress);
        iv_xxlogo=(ImageView)findViewById(R.id.iv_xxlogo);
        actv_xyname=(AppCompatTextView)findViewById(R.id.actv_xyname);
        rlyt_repairs=(RelativeLayout)findViewById(R.id.rlyt_repairs);
        rlyt_maintain=(RelativeLayout)findViewById(R.id.rlyt_maintain);
        rlyt_accomplish=(RelativeLayout)findViewById(R.id.rlyt_accomplish);
        rlyt_baoxiu=(RelativeLayout)findViewById(R.id.rlyt_baoxiu);
        rlyt_baoxiujd=(RelativeLayout)findViewById(R.id.rlyt_baoxiujd);
        rlyt_wdsb=(RelativeLayout)findViewById(R.id.rlyt_wdsb);
        rlyt_xxjl=(RelativeLayout)findViewById(R.id.rlyt_xxjl);

        rlyt_repairs.setOnClickListener(this);
        rlyt_maintain.setOnClickListener(this);
        rlyt_accomplish.setOnClickListener(this);
        rlyt_baoxiu.setOnClickListener(this);
        rlyt_baoxiujd.setOnClickListener(this);
        rlyt_wdsb.setOnClickListener(this);
        rlyt_xxjl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlyt_repairs://我要审批
                break;
            case R.id.rlyt_maintain://维修中
                break;
            case R.id.rlyt_accomplish://已完成
                break;
            case R.id.rlyt_baoxiu://我要报修
                Intent intent = new Intent(getApplication(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.rlyt_baoxiujd://报修进度
                Intent baoxiuintent = new Intent(getApplication(), RepairSscheduleActivity.class);
                startActivity(baoxiuintent);
                break;
            case R.id.rlyt_wdsb://我的设备
                break;
            case R.id.rlyt_xxjl://学习交流
                Intent repairsDetail = new Intent(getApplication(), RepairsDetailActivity.class);
                startActivity(repairsDetail);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
