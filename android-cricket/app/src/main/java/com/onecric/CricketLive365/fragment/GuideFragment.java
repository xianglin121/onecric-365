package com.onecric.CricketLive365.fragment;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.MainActivity;
import com.onecric.CricketLive365.adapter.GuideFragmentAdapter;
import com.onecric.CricketLive365.custom.AutoWrapTextView;
import com.onecric.CricketLive365.model.CricketMatchDataBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.LinkDataBean;
import com.onecric.CricketLive365.presenter.cricket.CricketGuidePresenter;
import com.onecric.CricketLive365.view.BaseActivity;
import com.onecric.CricketLive365.view.CricketLiveView;
import com.onecric.CricketLive365.view.MvpFragment;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class GuideFragment extends MvpFragment<CricketGuidePresenter> implements CricketLiveView {

    private RecyclerView rv_guide;
    private GuideFragmentAdapter adapter;
    private long singleTimeInMillis = new Date().getTime();
    private SmartRefreshLayout smart_rl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guide;
    }

    @Override
    protected CricketGuidePresenter createPresenter() {
        return new CricketGuidePresenter(this);
    }

    @Override
    protected void initUI() {
        AutoWrapTextView awtv = findViewById(R.id.awtv);
        awtv.setText(getResources().getString(R.string.guide_tip));
        smart_rl = findViewById(R.id.smart_rl);
        rv_guide = (RecyclerView) findViewById(R.id.rv_guide);
        rv_guide.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new GuideFragmentAdapter(R.layout.item_headlines_banner, null, getActivity());
        rv_guide.setAdapter(adapter);
        smart_rl.setEnableLoadMore(false);
        MaterialHeader materialHeader = new MaterialHeader(getContext());
        materialHeader.setColorSchemeColors(getContext().getResources().getColor(R.color.c_DC3C23));
        smart_rl.setRefreshHeader(materialHeader);
        smart_rl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        ((BaseActivity) getActivity()).showLoadingDialog();
        mvpPresenter.getCricketMatchList(new SimpleDateFormat("yyyy-MM-dd").format(singleTimeInMillis));
        mvpPresenter.getInfo("CricketLive365");
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
    public void getDataSuccess(List<CricketMatchDataBean> bean) {
        if (bean != null) {
            adapter.setNewData(bean);
        }
        smart_rl.finishRefresh();
    }

    @Override
    public void getInfoSuccess(LinkDataBean bean) {
        ((BaseActivity) getActivity()).dismissLoadingDialog();
        adapter.setLinkData(bean);
    }

    @Override
    public void getDataFail(String msg) {
        ((BaseActivity) getActivity()).dismissLoadingDialog();
    }

}
