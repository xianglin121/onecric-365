package com.onecric.CricketLive365.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.adapter.IndexDetailAdapter;
import com.onecric.CricketLive365.model.BasketballIndexListBean;
import com.onecric.CricketLive365.presenter.match.RangqiuIndexDetailPresenter;
import com.onecric.CricketLive365.view.MvpFragment;
import com.onecric.CricketLive365.view.match.RangqiuIndexDetailView;

import java.util.ArrayList;
import java.util.List;

public class RangqiuIndexDetailFragment extends MvpFragment<RangqiuIndexDetailPresenter> implements RangqiuIndexDetailView {

    public static RangqiuIndexDetailFragment newInstance() {
        RangqiuIndexDetailFragment fragment = new RangqiuIndexDetailFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rangqiu_index_detail;
    }

    private RecyclerView recyclerview;
    private IndexDetailAdapter mAdapter;

    @Override
    protected RangqiuIndexDetailPresenter createPresenter() {
        return new RangqiuIndexDetailPresenter(this);
    }

    @Override
    protected void initUI() {
        recyclerview = rootView.findViewById(R.id.recyclerview);
    }

    @Override
    protected void initData() {
        mAdapter = new IndexDetailAdapter(R.layout.item_index_detail, new ArrayList<>());
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getDataSuccess(List<BasketballIndexListBean> list) {

    }

    @Override
    public void getDataFail(String msg) {

    }
}
