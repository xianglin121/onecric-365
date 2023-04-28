package com.onecric.CricketLive365.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.adapter.MyWalletAdapter;
import com.onecric.CricketLive365.adapter.WithdrawRecordAdapter;
import com.onecric.CricketLive365.model.BalanceBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.WithdrawBean;
import com.onecric.CricketLive365.presenter.user.MyWalletPresenter;
import com.onecric.CricketLive365.view.MvpFragment;
import com.onecric.CricketLive365.view.user.MyWalletView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2021/10/25
 */
public class MyWalletFragment extends MvpFragment<MyWalletPresenter> implements MyWalletView {
    public static MyWalletFragment newInstance(int type) {
        MyWalletFragment fragment = new MyWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int mType;
    private SmartRefreshLayout smart_rl;
    private RecyclerView rv_wallet;
    private MyWalletAdapter mAdapter;
    private WithdrawRecordAdapter mWithdrawAdapter;

    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_wallet;
    }

    @Override
    protected MyWalletPresenter createPresenter() {
        return new MyWalletPresenter(this);
    }

    @Override
    public void initUI() {
        mType = getArguments().getInt("type");
        smart_rl = findViewById(R.id.smart_rl);
        rv_wallet = findViewById(R.id.rv_wallet);
    }

    @Override
    public void initData() {
        MaterialHeader materialHeader = new MaterialHeader(getContext());
        materialHeader.setColorSchemeColors(getContext().getResources().getColor(R.color.c_DC3C23));
        smart_rl.setRefreshHeader(materialHeader);
        smart_rl.setRefreshFooter(new ClassicsFooter(getContext()));
        smart_rl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mType == 4) {
                    mvpPresenter.getList(false, mPage);
                }else {
                    mvpPresenter.getList(false, mType, mPage);
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (mType == 4) {
                    mvpPresenter.getList(true, 1);
                }else {
                    mvpPresenter.getList(true, mType, 1);
                }
            }
        });

        rv_wallet.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mType == 4) {
            mWithdrawAdapter = new WithdrawRecordAdapter(R.layout.item_my_wallet, new ArrayList<>());
            rv_wallet.setAdapter(mWithdrawAdapter);
        }else {
            mAdapter = new MyWalletAdapter(R.layout.item_my_wallet, new ArrayList<>());
            rv_wallet.setAdapter(mAdapter);
        }

        smart_rl.autoRefresh();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
    public void getDataSuccess(boolean isRefresh, List<BalanceBean> list) {
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
    public void getWithdrawDataSuccess(boolean isRefresh, List<WithdrawBean> list) {
        if (isRefresh) {
            smart_rl.finishRefresh();
            mPage = 2;
            if (list != null) {
                mWithdrawAdapter.setNewData(list);
            }
        }else {
            mPage++;
            if (list != null && list.size() > 0) {
                smart_rl.finishLoadMore();
                mWithdrawAdapter.addData(list);
            }else {
                smart_rl.finishLoadMoreWithNoMoreData();
            }
        }
    }
}
