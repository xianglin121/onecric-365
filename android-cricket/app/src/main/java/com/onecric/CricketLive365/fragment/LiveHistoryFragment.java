//package com.onecric.CricketLive365.fragment;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.onecric.CricketLive365.CommonAppConfig;
//import com.onecric.CricketLive365.R;
//import com.onecric.CricketLive365.activity.LiveDetailActivity;
//import com.onecric.CricketLive365.adapter.LiveRecommendHistoryAdapter;
//import com.onecric.CricketLive365.adapter.decoration.GridDividerItemDecoration;
//import com.onecric.CricketLive365.fragment.dialog.LoginDialog;
//import com.onecric.CricketLive365.model.HistoryLiveBean;
//import com.onecric.CricketLive365.model.JsonBean;
//import com.onecric.CricketLive365.model.LiveBean;
//import com.onecric.CricketLive365.presenter.live.LiveMorePresenter;
//import com.onecric.CricketLive365.util.SpUtil;
//import com.onecric.CricketLive365.util.ToastUtil;
//import com.onecric.CricketLive365.view.MvpFragment;
//import com.onecric.CricketLive365.view.live.LiveMoreView;
//import com.scwang.smartrefresh.header.MaterialHeader;
//import com.scwang.smartrefresh.layout.SmartRefreshLayout;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
//import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LiveHistoryFragment extends MvpFragment<LiveMorePresenter> implements LiveMoreView, View.OnClickListener {
//    public static LiveHistoryFragment newInstance() {
//        LiveHistoryFragment fragment = new LiveHistoryFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private SmartRefreshLayout smart_rl;
//    private RecyclerView recyclerview;
//    private int mPage = 1;
//    private LiveRecommendHistoryAdapter mHistoryAdapter;
//    private LoginDialog loginDialog;
//
//    public void setLoginDialog(LoginDialog dialog){
//        loginDialog = dialog;
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_live_history;
//    }
//
//    @Override
//    protected LiveMorePresenter createPresenter() {
//        return new LiveMorePresenter(this);
//    }
//
//    @Override
//    protected void initUI() {
//        smart_rl = findViewById(R.id.smart_rl);
//        recyclerview = findViewById(R.id.recyclerview);
//    }
//
//    @Override
//    protected void initData() {
//        MaterialHeader materialHeader = new MaterialHeader(getContext());
//        materialHeader.setColorSchemeColors(getResources().getColor(R.color.c_DC3C23));
//        smart_rl.setRefreshHeader(materialHeader);
//        smart_rl.setRefreshFooter(new ClassicsFooter(getContext()));
//        smart_rl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                mvpPresenter.getHistoryList(false, mPage);
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                mvpPresenter.getHistoryList(true, 1);
//            }
//        });
//
//        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        recyclerview.addItemDecoration(new GridDividerItemDecoration(getContext(), 10, 2));
//        mHistoryAdapter = new LiveRecommendHistoryAdapter(R.layout.item_live_recommend, new ArrayList<>());
//        mHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                String url = mHistoryAdapter.getItem(position).getMediaUrl();
//                if (TextUtils.isEmpty(url)) {
//                    return;
//                }
//                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken()) && SpUtil.getInstance().getBooleanValue(SpUtil.VIDEO_OVERTIME)){
//                    if(loginDialog!=null){
//                        loginDialog.show();
//                    }else{
//                        ToastUtil.show(getString(R.string.please_login));
//                    }
//                }else{
//                    LiveDetailActivity.forward(getContext(),Integer.parseInt(mHistoryAdapter.getItem(position).getUid()),mHistoryAdapter.getItem(position).getMatchId(),
//                            mHistoryAdapter.getItem(position).getMediaUrl(),mHistoryAdapter.getItem(position).getLive_id());
//                }
//            }
//        });
//        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.layout_common_empty, null, false);
//        inflate2.findViewById(R.id.ll_empty).setVisibility(View.VISIBLE);
//        mHistoryAdapter.setEmptyView(inflate2);
//        recyclerview.setAdapter(mHistoryAdapter);
//
//        smart_rl.autoRefresh();
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
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
//    public void getDataSuccess(JsonBean model) {
//
//    }
//
//    @Override
//    public void getDataFail(String msg) {
//        smart_rl.finishRefresh();
//        smart_rl.finishLoadMore();
//    }
//
//    @Override
//    public void getDataSuccess(boolean isRefresh, List<LiveBean> list) {
//
//    }
//
//    @Override
//    public void getDataHistorySuccess(boolean isRefresh, List<HistoryLiveBean> list) {
//        if (isRefresh) {
//            smart_rl.finishRefresh();
//            mPage = 2;
//            if (list != null && list.size() > 0) {
//                mHistoryAdapter.setNewData(list);
//            }else {
//                mHistoryAdapter.setNewData(new ArrayList<>());
//            }
//        }else {
//            mPage++;
//            if (list != null && list.size() > 0) {
//                smart_rl.finishLoadMore();
//                mHistoryAdapter.addData(list);
//            }else {
//                smart_rl.finishLoadMoreWithNoMoreData();
//            }
//        }
//    }
//}
