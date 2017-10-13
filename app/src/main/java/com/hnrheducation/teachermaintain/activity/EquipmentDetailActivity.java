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
import com.hnrheducation.teachermaintain.fragment.EquipmentDetailFragment;
import com.hnrheducation.teachermaintain.fragment.EquipmentRepairsHostorylFragment;
import com.hnrheducation.teachermaintain.fragment.RepairCompletFragment;
import com.hnrheducation.teachermaintain.fragment.RepairingFragment;
import com.hnrheducation.teachermaintain.view.TitleWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2017/10/12.
 * 设备详情
 */

public class EquipmentDetailActivity extends BaseActivity {
    private TitleWidget titleWidget;
    private TabLayout tablayout;
    private ViewPager viewPager;
    /** 设备记录*/
    private EquipmentDetailFragment repairCompletFragment;
    /** 维修历史记录*/
    private EquipmentRepairsHostorylFragment repairingFragment;
    List<BaseFragment> fragmentData;
    private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
    private TabLayout.ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener;
    private RelationRemindAdapter relationRemindAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_detial);
        initView();
        initData();
    }

    private void initData() {
        fragmentData=new ArrayList<>();
        repairCompletFragment=new EquipmentDetailFragment();
        repairingFragment =new EquipmentRepairsHostorylFragment();
        fragmentData.add(repairCompletFragment);
        fragmentData.add(repairingFragment);

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
