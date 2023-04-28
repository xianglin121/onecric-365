//package com.onecric.CricketLive365.fragment;
//
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.onecric.CricketLive365.R;
//import com.onecric.CricketLive365.activity.MyTaskActivity;
//import com.onecric.CricketLive365.adapter.NoviceTaskAdapter;
//import com.onecric.CricketLive365.event.ToggleTabEvent;
//import com.onecric.CricketLive365.model.TaskBean;
//import com.onecric.CricketLive365.presenter.live.NoviceTaskPresenter;
//import com.onecric.CricketLive365.view.MvpFragment;
//import com.onecric.CricketLive365.view.live.NoviceTaskView;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 开发公司：东莞市梦幻科技有限公司
// * 时间：2022/1/11
// */
//public class NoviceTaskFragment extends MvpFragment<NoviceTaskPresenter> implements NoviceTaskView {
//    public static NoviceTaskFragment newInstance() {
//        NoviceTaskFragment fragment = new NoviceTaskFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private RecyclerView rv_novice;
//    private NoviceTaskAdapter mAdapter;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_novice_task;
//    }
//
//    @Override
//    protected NoviceTaskPresenter createPresenter() {
//        return new NoviceTaskPresenter(this);
//    }
//
//    @Override
//    protected void initUI() {
//        rv_novice = findViewById(R.id.rv_novice);
//    }
//
//    @Override
//    protected void initData() {
//        mAdapter = new NoviceTaskAdapter(R.layout.item_novice_task, new ArrayList<>());
//        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if (view.getId() == R.id.tv_finish) {
//                    if ("邀请好友注册".equals(mAdapter.getItem(position).getTask())) {
//                        ((MyTaskActivity)getActivity()).showDialog();
//                    }else if ("评论奖励".equals(mAdapter.getItem(position).getTask())
//                            || "回复奖励".equals(mAdapter.getItem(position).getTask())
//                            || "点赞奖励".equals(mAdapter.getItem(position).getTask())) {
//                        EventBus.getDefault().post(new ToggleTabEvent(1));
//                        getActivity().finish();
//                    }else if ("关注主播".equals(mAdapter.getItem(position).getTask())) {
//                        getActivity().finish();
//                    }
//                }
//            }
//        });
//        rv_novice.setLayoutManager(new LinearLayoutManager(getContext()));
//        rv_novice.setAdapter(mAdapter);
//
//        mvpPresenter.getList();
//    }
//
//    @Override
//    public void getDataSuccess(List<TaskBean> list) {
//        if (list != null) {
//            mAdapter.setNewData(list);
//        }
//    }
//
//    @Override
//    public void getDataFail(String msg) {
//
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
//}
