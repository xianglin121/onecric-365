package com.onecric.CricketLive365.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.adapter.HistoryIndexDetailAdapter;
import com.onecric.CricketLive365.model.HistoryIndexBean;
import com.onecric.CricketLive365.model.HistoryIndexDetailBean;
import com.onecric.CricketLive365.presenter.match.HistoryIndexDetailPresenter;
import com.onecric.CricketLive365.util.ToastUtil;
import com.onecric.CricketLive365.util.WordUtil;
import com.onecric.CricketLive365.view.MvpActivity;
import com.onecric.CricketLive365.view.match.HistoryIndexDetailView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoryIndexDetailActivity extends MvpActivity<HistoryIndexDetailPresenter> implements HistoryIndexDetailView, View.OnClickListener {

    public static void forward(Context context, int id, int companyId, List<HistoryIndexBean> historyList, boolean isAll) {
        Intent intent = new Intent(context, HistoryIndexDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("companyId", companyId);
        intent.putExtra("list", (Serializable) historyList);
        intent.putExtra("isAll", isAll);
        context.startActivity(intent);
    }

    private int mId = 0;
    private int mCompanyId;
    private List<HistoryIndexBean> mHistoryList;
    private boolean mIsAll;

    private ImageView iv_toggle;
    private TextView tv_company;
    private TextView tv_win_rate;
    private TextView tv_lose_rate;
    private TextView tv_home_percent, tv_flat_percent, tv_away_percent;
    private TextView tv_home_rate, tv_flat_rate, tv_away_rate;
    private OptionsPickerView pvCustomOptions;
    private RecyclerView rv_history;
    private HistoryIndexDetailAdapter mAdapter;

    private HistoryIndexDetailBean mBean;

    @Override
    protected HistoryIndexDetailPresenter createPresenter() {
        return new HistoryIndexDetailPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_index_detail;
    }

    @Override
    protected void initView() {
        mId = getIntent().getIntExtra("id", 0);
        mCompanyId = getIntent().getIntExtra("companyId", 0);
        mHistoryList = (List<HistoryIndexBean>) getIntent().getSerializableExtra("list");
        mIsAll = getIntent().getBooleanExtra("isAll", false);
        setTitleText(WordUtil.getString(this, R.string.history_index_detail));

        iv_toggle = findViewById(R.id.iv_toggle);
        tv_company = findViewById(R.id.tv_company);
        tv_win_rate = findViewById(R.id.tv_win_rate);
        tv_lose_rate = findViewById(R.id.tv_lose_rate);
        tv_home_percent = findViewById(R.id.tv_home_percent);
        tv_flat_percent = findViewById(R.id.tv_flat_percent);
        tv_away_percent = findViewById(R.id.tv_away_percent);
        tv_home_rate = findViewById(R.id.tv_home_rate);
        tv_flat_rate = findViewById(R.id.tv_flat_rate);
        tv_away_rate = findViewById(R.id.tv_away_rate);
        tv_company = findViewById(R.id.tv_company);
        rv_history = findViewById(R.id.rv_history);

        findViewById(R.id.ll_toggle).setOnClickListener(this);
        findViewById(R.id.ll_company).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        iv_toggle.setSelected(!mIsAll);
//        List<IndexBean> list = new ArrayList<>();
//        IndexBean indexOne = new IndexBean();
//        indexOne.setItemType(IndexBean.HEAD);
//        list.add(indexOne);
//        IndexBean indexTwo = new IndexBean();
//        indexTwo.setItemType(IndexBean.CONTENT);
//        list.add(indexTwo);
//        IndexBean indexThree = new IndexBean();
//        indexThree.setItemType(IndexBean.CONTENT);
//        list.add(indexThree);
        mAdapter = new HistoryIndexDetailAdapter(new ArrayList<>());
        rv_history.setLayoutManager(new LinearLayoutManager(this));
        rv_history.setAdapter(mAdapter);

        mvpPresenter.getDetail(mId, mCompanyId);
    }

    @Override
    public void getDataSuccess(HistoryIndexDetailBean bean) {
        if (bean != null) {
            mBean = bean;
            if (!TextUtils.isEmpty(bean.getName())) {
                tv_company.setText(bean.getName());
            }else {
                tv_company.setText("");
            }
            if (!TextUtils.isEmpty(bean.getAnalysis().getWin_rate())) {
                tv_win_rate.setText(bean.getAnalysis().getWin_rate());
            }
            if (!TextUtils.isEmpty(bean.getAnalysis().getLoss_rate())) {
                tv_lose_rate.setText(bean.getAnalysis().getLoss_rate());
            }
            toggleData();
        }
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_toggle:
                mIsAll = !mIsAll;
                iv_toggle.setSelected(!mIsAll);
                toggleData();
                break;
            case R.id.ll_company:
                showPickerView();
                break;
        }
    }

    private void toggleData() {
        if (mBean != null) {
            if (mIsAll) {
                if (!TextUtils.isEmpty(mBean.getAll().getHome_percentage())) {
                    tv_home_percent.setText(mBean.getAll().getHome_percentage() + "%");
                }
                if (!TextUtils.isEmpty(mBean.getAll().getFlat_percentage())) {
                    tv_flat_percent.setText(mBean.getAll().getFlat_percentage() + "%");
                }
                if (!TextUtils.isEmpty(mBean.getAll().getAway_percentage())) {
                    tv_away_percent.setText(mBean.getAll().getAway_percentage() + "%");
                }
                tv_home_rate.setText(mBean.getAll().getHome() + "赢");
                tv_flat_rate.setText(mBean.getAll().getFlat() + "走");
                tv_away_rate.setText(mBean.getAll().getAway() + "输");
                if (mBean.getList() != null) {
                    mAdapter.setNewData(mBean.getList());
                }
            }else {
                if (!TextUtils.isEmpty(mBean.getTonglian().getHome_percentage())) {
                    tv_home_percent.setText(mBean.getTonglian().getHome_percentage() + "%");
                }
                if (!TextUtils.isEmpty(mBean.getTonglian().getFlat_percentage())) {
                    tv_flat_percent.setText(mBean.getTonglian().getFlat_percentage() + "%");
                }
                if (!TextUtils.isEmpty(mBean.getTonglian().getAway_percentage())) {
                    tv_away_percent.setText(mBean.getTonglian().getAway_percentage() + "%");
                }
                tv_home_rate.setText(mBean.getTonglian().getHome() + "赢");
                tv_flat_rate.setText(mBean.getTonglian().getFlat() + "走");
                tv_away_rate.setText(mBean.getTonglian().getAway() + "输");
                if (mBean.getTonglian_list() != null) {
                    mAdapter.setNewData(mBean.getTonglian_list());
                }
            }
        }
    }

    private void showPickerView() {
        if (mHistoryList != null) {
            List<String> optionList = new ArrayList<>();
            for (int i = 0; i < mHistoryList.size(); i++) {
                optionList.add(mHistoryList.get(i).getName());
            }
            pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    int companyId = mHistoryList.get(options1).getId();
                    mvpPresenter.getDetail(mId, companyId);
                }
            })
                    .setLayoutRes(R.layout.pickerview_index_company, new CustomListener() {
                        @Override
                        public void customLayout(View v) {
                            TextView tv_cancel = v.findViewById(R.id.tv_cancel);
                            TextView tv_confirm = v.findViewById(R.id.tv_confirm);
                            tv_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pvCustomOptions.returnData();
                                    pvCustomOptions.dismiss();
                                }
                            });

                            tv_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pvCustomOptions.dismiss();
                                }
                            });
                        }
                    })
                    .isDialog(false)
                    .setOutSideCancelable(true)
                    .build();

            pvCustomOptions.setPicker(optionList);//添加数据
            pvCustomOptions.show();
        }
    }
}
