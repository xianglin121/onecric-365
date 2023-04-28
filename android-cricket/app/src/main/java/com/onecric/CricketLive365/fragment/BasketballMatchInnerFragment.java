//package com.onecric.CricketLive365.fragment;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.onecric.CricketLive365.CommonAppConfig;
//import com.onecric.CricketLive365.R;
//import com.onecric.CricketLive365.activity.BasketballMatchDetailActivity;
//import com.onecric.CricketLive365.adapter.BasketballMatchInnerAdapter;
//import com.onecric.CricketLive365.model.BasketballMatchBean;
//import com.onecric.CricketLive365.model.MatchSocketBean;
//import com.onecric.CricketLive365.presenter.match.BasketballMatchInnerPresenter;
//import com.onecric.CricketLive365.util.ToastUtil;
//import com.onecric.CricketLive365.view.MvpFragment;
//import com.onecric.CricketLive365.view.match.BasketballMatchInnerView;
//import com.scwang.smartrefresh.header.MaterialHeader;
//import com.scwang.smartrefresh.layout.SmartRefreshLayout;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
//import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BasketballMatchInnerFragment extends MvpFragment<BasketballMatchInnerPresenter> implements BasketballMatchInnerView {
//
//    public static BasketballMatchInnerFragment newInstance(int type) {
//        BasketballMatchInnerFragment fragment = new BasketballMatchInnerFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("type", type);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private int mType;
//    private SmartRefreshLayout smart_rl;
//    private RecyclerView recyclerview;
//    private BasketballMatchInnerAdapter mAdapter;
//
//    private int mPage = 1;
//    private String mId;
//
//    public void updateId(String id) {
//        mId = id;
//        if (smart_rl != null) {
//            smart_rl.autoRefresh();
//        }
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_basketball_match_inner;
//    }
//
//    @Override
//    protected BasketballMatchInnerPresenter createPresenter() {
//        return new BasketballMatchInnerPresenter(this);
//    }
//
//    @Override
//    protected void initUI() {
//        mType = getArguments().getInt("type");
//        smart_rl = rootView.findViewById(R.id.smart_rl);
//        recyclerview = rootView.findViewById(R.id.recyclerview);
//    }
//
//    @Override
//    protected void initData() {
//        MaterialHeader materialHeader = new MaterialHeader(getContext());
//        materialHeader.setColorSchemeColors(getContext().getResources().getColor(R.color.c_DC3C23));
//        smart_rl.setRefreshHeader(materialHeader);
//        smart_rl.setRefreshFooter(new ClassicsFooter(getContext()));
//        smart_rl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                mvpPresenter.getList(false, mType, mPage, mId);
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                mvpPresenter.getList(true, mType, 1, mId);
//            }
//        });
//
//        mAdapter = new BasketballMatchInnerAdapter(R.layout.item_basketball_match_inner_content, new ArrayList<>());
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                BasketballMatchDetailActivity.forward(getContext(), mAdapter.getItem(position).getId());
//            }
//        });
//        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if (view.getId() == R.id.iv_collect) {
//                    if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
//                        ToastUtil.show(getActivity().getString(R.string.please_login));
//                        return;
//                    }
//                    BasketballMatchBean item = mAdapter.getItem(position);
//                    if (item.getIs_collect() == 1) {
//                        item.setIs_collect(0);
//                    }else {
//                        item.setIs_collect(1);
//                    }
//                    mvpPresenter.doCollect(1, item.getId());
//                    if (mType == 4) {
//                        mAdapter.remove(position);
//                    }else {
//                        mAdapter.notifyItemChanged(position);
//                    }
//                }
//            }
//        });
//        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerview.setAdapter(mAdapter);
//
//        smart_rl.autoRefresh();
//    }
//
//    public void updateData(MatchSocketBean dataBean) {
//        if (mAdapter != null) {
//            for (int i = 0; i < mAdapter.getItemCount(); i++) {
//                BasketballMatchBean item = mAdapter.getItem(i);
//                if (item.getId() == dataBean.getMatch_id()) {
//                    item.setHome_scores_total(dataBean.getHome_scores_num());
//                    item.setAway_scores_total(dataBean.getAway_scores_num());
//                    item.setHome_scores(dataBean.getHome_scores());
//                    item.setAway_scores(dataBean.getAway_scores());
//                    item.setTime_left(dataBean.getTime_left());
//                    mAdapter.notifyItemChanged(i);
//                    break;
//                }
//            }
//        }
//    }
//
//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void getDataSuccess(List<BasketballMatchBean> list) {
//        smart_rl.finishRefresh();
//        if (list != null) {
//            mAdapter.setNewData(list);
//        }
//    }
//
//    @Override
//    public void getDataSuccess(boolean isRefresh, List<BasketballMatchBean> list) {
//        if (isRefresh) {
//            smart_rl.finishRefresh();
//            mPage = 2;
//            if (list != null && list.size() > 0) {
//                hideEmptyView();
//                mAdapter.setNewData(list);
//            }else {
//                mAdapter.setNewData(new ArrayList<>());
//                if (mType == 4) {
//                    setEmptyText(getActivity().getString(R.string.empty_match_collect));
//                }else {
//                    setEmptyText(getActivity().getString(R.string.empty_common));
//                }
//                showEmptyView();
//            }
//        }else {
//            mPage++;
//            if (list != null && list.size() > 0) {
//                smart_rl.finishLoadMore();
//                mAdapter.addData(list);
//            }else {
//                smart_rl.finishLoadMoreWithNoMoreData();
//            }
//        }
//    }
//
//    @Override
//    public void getDataFail(String msg) {
//        ToastUtil.show(msg);
//    }
//
//    public void refreshData() {
//        if (smart_rl != null) {
//            smart_rl.autoRefresh();
//        }
//    }
//}
