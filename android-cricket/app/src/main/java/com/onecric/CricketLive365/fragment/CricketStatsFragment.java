package com.onecric.CricketLive365.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.CricketInnerActivity;
import com.onecric.CricketLive365.activity.CricketStatsActivity;
import com.onecric.CricketLive365.model.CricketStatsBean;
import com.onecric.CricketLive365.view.BaseFragment;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/26
 */
public class CricketStatsFragment extends BaseFragment implements View.OnClickListener {
    public static CricketStatsFragment newInstance() {
        CricketStatsFragment fragment = new CricketStatsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private int mId;
    private TextView tv_highest_score_name;
    private TextView tv_highest_score;
    private TextView tv_most_run_name;
    private TextView tv_most_run;
    private TextView tv_best_batting_name;
    private TextView tv_best_batting;
    private TextView tv_highest_strike_name;
    private TextView tv_highest_strike;
    private TextView tv_maximum_six_name;
    private TextView tv_maximum_six;
    private TextView tv_most_wicket_name;
    private TextView tv_most_wicket;
    private TextView tv_best_bowling_av_name;
    private TextView tv_best_bowling_av;
    private TextView tv_best_bowling_ec_name;
    private TextView tv_best_bowling_ec;
    private TextView tv_most_catches_name;
    private TextView tv_most_catches;
    private TextView tv_most_run_out_name;
    private TextView tv_most_run_out;
    private TextView tv_most_stumping_name;
    private TextView tv_most_stumping;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cricket_stats;
    }

    @Override
    protected void initUI() {
        tv_highest_score_name = findViewById(R.id.tv_highest_score_name);
        tv_highest_score = findViewById(R.id.tv_highest_score);
        tv_most_run_name = findViewById(R.id.tv_most_run_name);
        tv_most_run = findViewById(R.id.tv_most_run);
        tv_best_batting_name = findViewById(R.id.tv_best_batting_name);
        tv_best_batting = findViewById(R.id.tv_best_batting);
        tv_highest_strike_name = findViewById(R.id.tv_highest_strike_name);
        tv_highest_strike = findViewById(R.id.tv_highest_strike);
        tv_maximum_six_name = findViewById(R.id.tv_maximum_six_name);
        tv_maximum_six = findViewById(R.id.tv_maximum_six);
        tv_most_wicket_name = findViewById(R.id.tv_most_wicket_name);
        tv_most_wicket = findViewById(R.id.tv_most_wicket);
        tv_best_bowling_av_name = findViewById(R.id.tv_best_bowling_av_name);
        tv_best_bowling_av = findViewById(R.id.tv_best_bowling_av);
        tv_best_bowling_ec_name = findViewById(R.id.tv_best_bowling_ec_name);
        tv_best_bowling_ec = findViewById(R.id.tv_best_bowling_ec);
        tv_most_catches_name = findViewById(R.id.tv_most_catches_name);
        tv_most_catches = findViewById(R.id.tv_most_catches);
        tv_most_run_out_name = findViewById(R.id.tv_most_run_out_name);
        tv_most_run_out = findViewById(R.id.tv_most_run_out);
        tv_most_stumping_name = findViewById(R.id.tv_most_stumping_name);
        tv_most_stumping = findViewById(R.id.tv_most_stumping);


        findViewById(R.id.ll_highest_individual_score).setOnClickListener(this);
        findViewById(R.id.ll_most_runs).setOnClickListener(this);
        findViewById(R.id.ll_best_batting_average).setOnClickListener(this);
        findViewById(R.id.ll_highest_strike_rate).setOnClickListener(this);
        findViewById(R.id.ll_maximum_sixes).setOnClickListener(this);
        findViewById(R.id.ll_most_wickets).setOnClickListener(this);
        findViewById(R.id.ll_best_bowling_average).setOnClickListener(this);
        findViewById(R.id.ll_best_bowling_economy).setOnClickListener(this);
        findViewById(R.id.ll_most_catches).setOnClickListener(this);
        findViewById(R.id.ll_most_run_outs).setOnClickListener(this);
        findViewById(R.id.ll_most_stumpings).setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    public void setData(int id, CricketStatsBean bean) {
        mId = id;
        if (bean != null) {
            if (!TextUtils.isEmpty(bean.getHighest_score_name())) {
                tv_highest_score_name.setText(bean.getHighest_score_name() + " - ");
                tv_highest_score.setText(bean.getHighest_score());
            }else{
                findViewById(R.id.ll_highest_individual_score).setVisibility(View.GONE);
                findViewById(R.id.view_line1).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getMost_runs_name())) {
                tv_most_run_name.setText(bean.getMost_runs_name() + " - ");
                tv_most_run.setText(bean.getMost_runs());
            }else{
                findViewById(R.id.ll_most_runs).setVisibility(View.GONE);
                findViewById(R.id.view_line2).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getBest_battingAv_name())) {
                tv_best_batting_name.setText(bean.getBest_battingAv_name() + " - ");
                tv_best_batting.setText(bean.getBest_battingAv());
            }else{
                findViewById(R.id.ll_best_batting_average).setVisibility(View.GONE);
                findViewById(R.id.view_line3).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getHighest_strikeRate_name())) {
                tv_highest_strike_name.setText(bean.getHighest_strikeRate_name() + " - ");
                tv_highest_strike.setText(bean.getHighest_strikeRate());
            }else{
                findViewById(R.id.ll_highest_strike_rate).setVisibility(View.GONE);
                findViewById(R.id.view_line4).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getMaximum_sixes_name())) {
                tv_maximum_six_name.setText(bean.getMaximum_sixes_name() + " - ");
                tv_maximum_six.setText(bean.getMaximum_sixes());
            }else{
                findViewById(R.id.ll_maximum_sixes).setVisibility(View.GONE);
                findViewById(R.id.view_line4).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getMost_wickets_name())) {
                tv_most_wicket_name.setText(bean.getMost_wickets_name() + " - ");
                tv_most_wicket.setText(bean.getMost_wickets());
            }else{
                findViewById(R.id.ll_most_wickets).setVisibility(View.GONE);
                findViewById(R.id.view_line5).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getBest_bowling_average_name())) {
                tv_best_bowling_av_name.setText(bean.getBest_bowling_average_name() + " - ");
                tv_best_bowling_av.setText(bean.getBest_bowling_average());
            }else{
                findViewById(R.id.ll_best_bowling_average).setVisibility(View.GONE);
                findViewById(R.id.view_line6).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getBest_bowling_economy_name())) {
                tv_best_bowling_ec_name.setText(bean.getBest_bowling_economy_name() + " - ");
                tv_best_bowling_ec.setText(bean.getBest_bowling_economy());
            }else{
                findViewById(R.id.ll_best_bowling_economy).setVisibility(View.GONE);
                findViewById(R.id.view_line6).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getMost_catches_name())) {
                tv_most_catches_name.setText(bean.getMost_catches_name() + " - ");
                tv_most_catches.setText(bean.getMost_catches());
            }else{
                findViewById(R.id.ll_most_catches).setVisibility(View.GONE);
                findViewById(R.id.view_line7).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getMost_run_outs_name())) {
                tv_most_run_out_name.setText(bean.getMost_run_outs_name() + " - ");
                tv_most_run_out.setText(bean.getMost_run_outs());
            }else{
                findViewById(R.id.ll_most_run_outs).setVisibility(View.GONE);
                findViewById(R.id.view_line8).setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(bean.getMost_stumpings_name())) {
                tv_most_stumping_name.setText(bean.getMost_stumpings_name() + " - ");
                tv_most_stumping.setText(bean.getMost_stumpings());
            }else{
                findViewById(R.id.ll_most_stumpings).setVisibility(View.GONE);
                findViewById(R.id.view_line8).setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_highest_individual_score:
                CricketStatsActivity.forward(getContext(), 0, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_most_runs:
                CricketStatsActivity.forward(getContext(), 1, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_best_batting_average:
                CricketStatsActivity.forward(getContext(), 2, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_highest_strike_rate:
                CricketStatsActivity.forward(getContext(), 3, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_maximum_sixes:
                CricketStatsActivity.forward(getContext(), 4, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_most_wickets:
                CricketStatsActivity.forward(getContext(), 5, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_best_bowling_average:
                CricketStatsActivity.forward(getContext(), 6, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_best_bowling_economy:
                CricketStatsActivity.forward(getContext(), 7, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_most_catches:
                CricketStatsActivity.forward(getContext(), 8, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_most_run_outs:
                CricketStatsActivity.forward(getContext(), 9, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
            case R.id.ll_most_stumpings:
                CricketStatsActivity.forward(getContext(), 10, mId, ((CricketInnerActivity)getActivity()).mType);
                break;
        }
    }
}
