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
 * 报修进度
 */

public class RepairSscheduleActivity extends BaseActivity {
    private TitleWidget titleWidget;
    private TabLayout tablayout;
    private ViewPager viewPager;
    /** 进度已完成*/
    private RepairCompletFragment repairCompletFragment;
    /** 进度进行中*/
    private RepairingFragment repairingFragment;
    List<BaseFragment> fragmentData;
    private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
    private TabLayout.ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener;
    private RelationRemindAdapter relationRemindAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_sschedule);
        initView();
        initData();
    }

    private void initData() {
        fragmentData=new ArrayList<>();
        repairCompletFragment=new RepairCompletFragment();
        repairingFragment =new RepairingFragment();
        fragmentData.add(repairingFragment);
        fragmentData.add(repairCompletFragment);

        relationRemindAdapter=new RelationRemindAdapter(getSupportFragmentManager());
        viewPager.setAdapter(relationRemindAdapter);
        tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tablayout);
        viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
        viewPagerOnTabSelectedListener = new TabLayout.ViewPagerOnTabSelectedListener(viewPager);
        tablayout.setOnTabSelectedListener(viewPagerOnTabSelectedListener);

    }

    private void initView() {
        titleWidget= (TitleWidget) findViewById(R.id.title);
        tablayout= (TabLayout) findViewById(R.id.tablayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
    }
    class RelationRemindAdapter extends FragmentPagerAdapter {
        public RelationRemindAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Bundle bundle=new Bundle();
            bundle.putString("clientId","");
            bundle.putString("type","");
            fragmentData.get(position).setArguments(bundle);
            return fragmentData.get(position);
        }

        @Override
        public int getCount() {
            return fragmentData.size();
        }
    }
}
