package com.onecric.CricketLive365.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.HeadlineDetailActivity;
import com.onecric.CricketLive365.adapter.SearchHeadlineAdapter;
import com.onecric.CricketLive365.model.HeadlineBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.presenter.live.SearchHeadlinePresenter;
import com.onecric.CricketLive365.view.MvpFragment;
import com.onecric.CricketLive365.view.live.SearchHeadlineView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class SearchHeadlineFragment extends MvpFragment<SearchHeadlinePresenter> implements SearchHeadlineView {

    public static SearchHeadlineFragment newInstance(String content) {
        SearchHeadlineFragment fragment = new SearchHeadlineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        fragment.setArguments(bundle);
        return fragment;
    }

    private String mContent;
    private SmartRefreshLayout smart_rl;
    private RecyclerView recyclerView;
    private SearchHeadlineAdapter mAdapter;

    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme_collection_headline;
    }

    @Override
    protected SearchHeadlinePresenter createPresenter() {
        return new SearchHeadlinePresenter(this);
    }

    @Override
    protected void initUI() {
        mContent = getArguments().getString("content");
        smart_rl = findViewById(R.id.smart_rl);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        MaterialHeader materialHeader = new MaterialHeader(getContext());
        materialHeader.setColorSchemeColors(getContext().getResources().getColor(R.color.c_DC3C23));
        smart_rl.setRefreshHeader(materialHeader);
        smart_rl.setRefreshFooter(new ClassicsFooter(getContext()));
        smart_rl.setEnableRefresh(false);
        smart_rl.setEnableLoadMore(false);
        smart_rl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getList(false, mPage, mContent);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getList(true, 1, mContent);
            }
        });

        mAdapter = new SearchHeadlineAdapter(R.layout.item_search_headline, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HeadlineDetailActivity.forward(getContext(), mAdapter.getItem(position).getId());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        mvpPresenter.getList(true, 1, mContent);
    }

    public void updateData(String content) {
        if (!TextUtils.isEmpty(content)) {
            mContent = content;
            if (mvpPresenter != null) {
                mvpPresenter.getList(true, 1, mContent);
            }
        }
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
}
