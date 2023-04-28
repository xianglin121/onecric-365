package com.onecric.CricketLive365.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.HeadlineDetailActivity;
import com.onecric.CricketLive365.adapter.BannerRoundImageAdapter;
import com.onecric.CricketLive365.adapter.ThemeHeadlineAdapter;
import com.onecric.CricketLive365.model.HeadlineBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.presenter.theme.ThemeHeadlineInnerPresenter;
import com.onecric.CricketLive365.view.MvpFragment;
import com.onecric.CricketLive365.view.theme.ThemeHeadlineInnerView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.listener.OnBannerListener;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.ArrayList;
import java.util.List;

public class ThemeHeadlineInnerFragment extends MvpFragment<ThemeHeadlineInnerPresenter> implements ThemeHeadlineInnerView {

    public static ThemeHeadlineInnerFragment newInstance(int id) {
        ThemeHeadlineInnerFragment fragment = new ThemeHeadlineInnerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int mId;
    private SmartRefreshLayout smart_rl;
    private Banner mBanner;
    private RecyclerView rv_headline;
    private ThemeHeadlineAdapter mAdapter;

    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme_headline_inner;
    }

    @Override
    protected ThemeHeadlineInnerPresenter createPresenter() {
        return new ThemeHeadlineInnerPresenter(this);
    }

    @Override
    protected void initUI() {
        mId = getArguments().getInt("id");
        smart_rl = rootView.findViewById(R.id.smart_rl);
        mBanner = rootView.findViewById(R.id.banner_headline);
        rv_headline = rootView.findViewById(R.id.rv_headline);
        int width = UIUtil.getScreenWidth(getContext());
        android.view.ViewGroup.LayoutParams pp = mBanner.getLayoutParams();
        pp.height = (int)((width-UIUtil.dip2px(getContext(),24)) * 0.6);
        mBanner.setLayoutParams(pp);
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
                mvpPresenter.getList(false, mId, mPage);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getList(true, mId, 1);
            }
        });

        mAdapter = new ThemeHeadlineAdapter(new ArrayList<>(),getActivity());
        mAdapter.setmOnItemClickListener((view, position, bean) -> HeadlineDetailActivity.forward(getContext(), bean.getId()));
        rv_headline.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_headline.setAdapter(mAdapter);

        smart_rl.autoRefresh();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getDataSuccess(boolean isRefresh, List<HeadlineBean> list, List<HeadlineBean> banners) {
        if (isRefresh) {
            smart_rl.finishRefresh();
            mPage = 2;
            if (banners != null) {
               initBanner(banners);
            }else {
                mBanner.setVisibility(View.GONE);
            }
            if (list == null) {
                list = new ArrayList<>();
            }
            if (list.size() > 0) {
                hideEmptyView();
            }else {
                showEmptyView();
            }
            mAdapter.setData(list);
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

    private void initBanner(List<HeadlineBean> banners) {
        if (banners.size() == 0) {
            mBanner.setVisibility(View.GONE);
        }
        mBanner.setIndicator(new RectangleIndicator(getContext()));
        mBanner.setAdapter(new BannerRoundImageAdapter(banners) {
            @Override
            public void onBindView(Object holder, Object data, int position, int size) {
                Glide.with(getContext()).load(((HeadlineBean)data).getImg()).into(((BannerRoundImageHolder)holder).imageView);
                if (!TextUtils.isEmpty(((HeadlineBean)data).getTitle())) {
                    ((BannerRoundImageHolder)holder).textView.setText(((HeadlineBean)data).getTitle());
                }else {
                    ((BannerRoundImageHolder)holder).textView.setText("");
                }
            }
        });
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                HeadlineDetailActivity.forward(getContext(), ((HeadlineBean)data).getId());
            }
        });
        mBanner.addBannerLifecycleObserver(this);
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
    public void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBanner != null) {
            mBanner.destroy();
        }
    }
}
