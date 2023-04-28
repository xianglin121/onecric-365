package com.onecric.CricketLive365.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.adapter.ActivityCenterAdapter;
import com.onecric.CricketLive365.model.ActivityBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.presenter.user.ActivityCenterPresenter;
import com.onecric.CricketLive365.view.MvpActivity;
import com.onecric.CricketLive365.view.user.ActivityCenterView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityCenterActivity extends MvpActivity<ActivityCenterPresenter> implements ActivityCenterView, View.OnClickListener {

    public static void forward(Context context) {
        Intent intent = new Intent(context, ActivityCenterActivity.class);
        context.startActivity(intent);
    }

    private SmartRefreshLayout smart_rl;
    private RecyclerView recyclerview;
    private ActivityCenterAdapter mAdapter;

    private int mPage = 1;

    @Override
    protected ActivityCenterPresenter createPresenter() {
        return new ActivityCenterPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_activity_center;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.user_activity_center));

        smart_rl = findViewById(R.id.smart_rl);
        recyclerview = findViewById(R.id.recyclerview);
    }

    @Override
    protected void initData() {
        MaterialHeader materialHeader = new MaterialHeader(this);
        materialHeader.setColorSchemeColors(getResources().getColor(R.color.c_DC3C23));
        smart_rl.setRefreshHeader(materialHeader);
        smart_rl.setRefreshFooter(new ClassicsFooter(this));
        smart_rl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getList(false, mPage);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getList(true, 1);
            }
        });

        mAdapter = new ActivityCenterAdapter(R.layout.item_activity_center, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebViewActivity.forward(mActivity, mAdapter.getItem(position).getUrl());
            }
        });
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mAdapter);

        mvpPresenter.getList(true, 1);
    }

    @Override
    public void getDataSuccess(boolean isRefresh, List<ActivityBean> list) {
        if (isRefresh) {
            smart_rl.finishRefresh();
            mPage = 2;
            if (list != null) {
                mAdapter.setNewData(list);
            }
        }else {
            mPage++;
            if (list != null && list.size() > 0) {
                smart_rl.finishLoadMore();
                mAdapter.addData(list);
            }else {
                smart_rl.finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void getDataSuccess(JsonBean model) {

    }

    @Override
    public void getDataFail(String msg) {
        smart_rl.finishRefresh();
        smart_rl.finishLoadMore();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
