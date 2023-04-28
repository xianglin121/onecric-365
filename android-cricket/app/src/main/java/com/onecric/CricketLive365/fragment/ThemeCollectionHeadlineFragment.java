package com.onecric.CricketLive365.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.HeadlineDetailActivity;
import com.onecric.CricketLive365.adapter.ThemeCollectionHeadlineAdapter;
import com.onecric.CricketLive365.model.HeadlineBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.presenter.theme.ThemeCollectionHeadlinePresenter;
import com.onecric.CricketLive365.view.MvpFragment;
import com.onecric.CricketLive365.view.theme.ThemeCollectionHeadlineView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class ThemeCollectionHeadlineFragment extends MvpFragment<ThemeCollectionHeadlinePresenter> implements ThemeCollectionHeadlineView {

    public static ThemeCollectionHeadlineFragment newInstance(int uid) {
        ThemeCollectionHeadlineFragment fragment = new ThemeCollectionHeadlineFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("uid", uid);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int mUid;
    private SmartRefreshLayout smart_rl;
    private RecyclerView recyclerView;
    private ThemeCollectionHeadlineAdapter mAdapter;

    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme_collection_headline;
    }

    @Override
    protected ThemeCollectionHeadlinePresenter createPresenter() {
        return new ThemeCollectionHeadlinePresenter(this);
    }

    @Override
    protected void initUI() {
        mUid = getArguments().getInt("uid");
        smart_rl = findViewById(R.id.smart_rl);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        MaterialHeader materialHeader = new MaterialHeader(getContext());
        materialHeader.setColorSchemeColors(getContext().getResources().getColor(R.color.c_DC3C23));
        smart_rl.setRefreshHeader(materialHeader);
        smart_rl.setRefreshFooter(new ClassicsFooter(getContext()));
        smart_rl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getList(false, mPage, mUid);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getList(true, 1, mUid);
            }
        });

        mAdapter = new ThemeCollectionHeadlineAdapter(R.layout.item_theme_collection_headline, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HeadlineDetailActivity.forward(getContext(), mAdapter.getItem(position).getCircle_id());
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_collect) {
                    mvpPresenter.doHeadlineCollect(mAdapter.getItem(position).getId());
                    mAdapter.remove(position);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        smart_rl.autoRefresh();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getDataSuccess(boolean isRefresh, List<HeadlineBean> list) {
        if (isRefresh) {
            smart_rl.finishRefresh();
            mPage = 2;
            if (list != null) {
                if (list.size() > 0) {
                    hideEmptyView();
                }else {
                    showEmptyView();
                }
                mAdapter.setNewData(list);
            }else {
                mAdapter.setNewData(new ArrayList<>());
                showEmptyView();
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
}
