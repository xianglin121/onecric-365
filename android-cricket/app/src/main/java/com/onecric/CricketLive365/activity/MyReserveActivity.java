package com.onecric.CricketLive365.activity;

import android.content.Context;
import android.content.Intent;
import android.util.ArrayMap;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.adapter.MyReserveAdapter;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.ReserveMatchBean;
import com.onecric.CricketLive365.presenter.user.MyReservePresenter;
import com.onecric.CricketLive365.view.MvpActivity;
import com.onecric.CricketLive365.view.user.MyReserveView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyReserveActivity extends MvpActivity<MyReservePresenter> implements MyReserveView, View.OnClickListener {

    public static void forward(Context context) {
        Intent intent = new Intent(context, MyReserveActivity.class);
        context.startActivity(intent);
    }

    private SmartRefreshLayout smart_rl;
    private RecyclerView recyclerview;
    private MyReserveAdapter mAdapter;

    private int mPage = 1;
    private Map<String, Integer> mValueMap = new ArrayMap<>();
    private List<String> mAddDateList = new ArrayList<>();

    @Override
    protected MyReservePresenter createPresenter() {
        return new MyReservePresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_reserve;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.user_my_reserve));
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

        mAdapter = new MyReserveAdapter(new ArrayList<>());
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_reserve) {
                    mvpPresenter.doReserve(position, mAdapter.getItem(position).getMatch_id(), mAdapter.getItem(position).getType());
                }
            }
        });
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mAdapter);

        smart_rl.autoRefresh();
    }

    @Override
    public void getDataSuccess(boolean isRefresh, List<ReserveMatchBean> list) {
        if (isRefresh) {
            smart_rl.finishRefresh();
            mPage = 2;
            if (list != null) {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        ReserveMatchBean reserveMatchBean = list.get(i);
                        if (!mValueMap.containsKey(reserveMatchBean.getStart2())) {
                            mValueMap.put(reserveMatchBean.getStart2(), i);
                        }
                    }
                    int headCount = 0;
                    for (String date : mValueMap.keySet()) {
                        Integer position = mValueMap.get(date);
                        ReserveMatchBean head = new ReserveMatchBean();
                        head.setItemType(ReserveMatchBean.HEAD);
                        head.setStart2(date);
                        list.add(position+headCount, head);
                        headCount++;
                    }
                }
                mAdapter.setNewData(list);
            }
        }else {
            mPage++;
            if (list != null && list.size() > 0) {
                smart_rl.finishLoadMore();
                for (int i = 0; i < list.size(); i++) {
                    ReserveMatchBean reserveMatchBean = list.get(i);
                    if (!mValueMap.containsKey(reserveMatchBean.getStart2())) {
                        mValueMap.put(reserveMatchBean.getStart2(), i);
                    }
                }
                int headCount = 0;
                for (String date : mValueMap.keySet()) {
                    boolean has = false;
                    for (int i = 0; i < mAddDateList.size(); i++) {
                        if (date.equals(mAddDateList.get(i))) {
                            has = true;
                            break;
                        }
                    }
                    if (!has) {
                        Integer position = mValueMap.get(date);
                        ReserveMatchBean head = new ReserveMatchBean();
                        head.setItemType(ReserveMatchBean.HEAD);
                        head.setStart2(date);
                        list.add(position+headCount, head);
                        headCount++;
                    }
                }
                mAdapter.addData(list);
            }else {
                smart_rl.finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void doReserveSuccess(int position) {
        mAdapter.remove(position);
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
