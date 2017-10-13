package com.hnrheducation.teachermaintain.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hnrheducation.teachermaintain.R;
import com.hnrheducation.teachermaintain.base.BaseFragment;

/**
 * Created by My on 2017/10/12.
 * 设备维修历史记录
 */

public class EquipmentRepairsHostorylFragment extends BaseFragment {
    private RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.e("TAG ","onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repairing_hostory, null, false);
        initView(view);
        initData();
        return view;
    }

    /** 初始化View控件*/
    private void initView(View view) {
        rv=view.findViewById(R.id.rv);
    }
    /** 初始化数值*/
    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
    }

}
