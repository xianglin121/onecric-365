package com.onecric.CricketLive365.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.CricketTeamsActivity;
import com.onecric.CricketLive365.adapter.CricketTeamsAdapter;
import com.onecric.CricketLive365.custom.ItemDecoration;
import com.onecric.CricketLive365.model.CricketTeamBean;
import com.onecric.CricketLive365.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/26
 */
public class CricketTeamsFragment extends BaseFragment {
    public static CricketTeamsFragment newInstance() {
        CricketTeamsFragment fragment = new CricketTeamsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private RecyclerView recyclerView;
    private CricketTeamsAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cricket_teams;
    }

    @Override
    protected void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        mAdapter = new CricketTeamsAdapter(R.layout.item_cricket_teams, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CricketTeamsActivity.forward(getContext(), mAdapter.getItem(position).getName(), mAdapter.getItem(position).getId());
            }
        });
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_common_empty, null, false);
        inflate.findViewById(R.id.ll_empty).setVisibility(View.VISIBLE);
        mAdapter.setEmptyView(inflate);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDecoration(getContext(), getResources().getColor(R.color.c_CCCCCC), 0, 0.5f));
        recyclerView.setAdapter(mAdapter);
    }

    public void setList(List<CricketTeamBean> list) {
        if (list != null) {
            if (mAdapter != null) {
                mAdapter.setNewData(list);
            }
        }
    }
}
