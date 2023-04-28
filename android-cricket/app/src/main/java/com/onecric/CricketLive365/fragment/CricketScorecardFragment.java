package com.onecric.CricketLive365.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.adapter.ScorecardBatterAdapter;
import com.onecric.CricketLive365.adapter.ScorecardBowlerAdapter;
import com.onecric.CricketLive365.adapter.ScorecardWicketAdapter;
import com.onecric.CricketLive365.model.CricketMatchBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.ScorecardBatterBean;
import com.onecric.CricketLive365.model.ScorecardBowlerBean;
import com.onecric.CricketLive365.model.ScorecardWicketBean;
import com.onecric.CricketLive365.presenter.cricket.CricketScorecardPresenter;
import com.onecric.CricketLive365.view.MvpFragment;
import com.onecric.CricketLive365.view.cricket.CricketScorecardView;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/27
 */
public class CricketScorecardFragment extends MvpFragment<CricketScorecardPresenter> implements CricketScorecardView, View.OnClickListener {
    public static CricketScorecardFragment newInstance() {
        CricketScorecardFragment fragment = new CricketScorecardFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private ViewGroup ll_home;
    private TextView tv_home_name;
    private TextView tv_home_round;
    private TextView tv_home_score;
    private ImageView iv_arrow_one;
    private NestedScrollView sv_home;
    private RecyclerView rv_home_batter;
    private ScorecardBatterAdapter mHomeBatterAdapter;
    private TextView tv_home_extras;
    private TextView tv_home_no_bat;
    private RecyclerView rv_home_bowler;
    private ScorecardBowlerAdapter mHomeBowlerAdapter;
    private RecyclerView rv_home_wicket;
    private ScorecardWicketAdapter mHomeWicketAdapter;

    private ViewGroup ll_away;
    private TextView tv_away_name;
    private TextView tv_away_round;
    private TextView tv_away_score;
    private ImageView iv_arrow_two;
    private NestedScrollView sv_away;
    private RecyclerView rv_away_batter;
    private ScorecardBatterAdapter mAwayBatterAdapter;
    private TextView tv_away_extras;
    private TextView tv_away_no_bat;
    private RecyclerView rv_away_bowler;
    private ScorecardBowlerAdapter mAwayBowlerAdapter;
    private RecyclerView rv_away_wicket;
    private ScorecardWicketAdapter mAwayWicketAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cricket_scorecard;
    }

    @Override
    protected CricketScorecardPresenter createPresenter() {
        return new CricketScorecardPresenter(this);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initUI() {
        ll_home = findViewById(R.id.ll_home);
        tv_home_name = findViewById(R.id.tv_home_name);
        tv_home_round = findViewById(R.id.tv_home_round);
        tv_home_score = findViewById(R.id.tv_home_score);
        iv_arrow_one = findViewById(R.id.iv_arrow_one);
        sv_home = findViewById(R.id.sv_home);
        rv_home_batter = findViewById(R.id.rv_home_batter);
        tv_home_extras = findViewById(R.id.tv_home_extras);
        tv_home_no_bat = findViewById(R.id.tv_home_no_bat);
        rv_home_bowler = findViewById(R.id.rv_home_bowler);
        rv_home_wicket = findViewById(R.id.rv_home_wicket);

        ll_away = findViewById(R.id.ll_away);
        tv_away_name = findViewById(R.id.tv_away_name);
        tv_away_round = findViewById(R.id.tv_away_round);
        tv_away_score = findViewById(R.id.tv_away_score);
        iv_arrow_two = findViewById(R.id.iv_arrow_two);
        sv_away = findViewById(R.id.sv_away);
        rv_away_batter = findViewById(R.id.rv_away_batter);
        tv_away_extras = findViewById(R.id.tv_away_extras);
        tv_away_no_bat = findViewById(R.id.tv_away_no_bat);
        rv_away_bowler = findViewById(R.id.rv_away_bowler);
        rv_away_wicket = findViewById(R.id.rv_away_wicket);

        findViewById(R.id.ll_home).setOnClickListener(this);
        findViewById(R.id.ll_away).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ll_away.setSelected(true);
//        ll_away.setBackgroundColor(getResources().getColor(R.color.c_1D2550));
        sv_away.setVisibility(View.VISIBLE);
        iv_arrow_two.setBackgroundResource(R.mipmap.icon_arrow_up_two);
//        tv_away_name.setTextColor(getResources().getColor(R.color.white));
//        tv_away_score.setTextColor(getResources().getColor(R.color.white));
//        tv_away_round.setTextColor(getResources().getColor(R.color.c_999999));
    }

    public void getData(CricketMatchBean model) {
        if (!TextUtils.isEmpty(model.getHome_name())) {
            tv_home_name.setText(model.getHome_name());
        }
        if (!TextUtils.isEmpty(model.getHome_display_score())) {
            tv_home_score.setText(model.getHome_display_score());
        }
        if (!TextUtils.isEmpty(model.getHome_display_overs())) {
            tv_home_round.setText("(" + model.getHome_display_overs() + ")");
        }
        if (!TextUtils.isEmpty(model.getAway_name())) {
            tv_away_name.setText(model.getAway_name());
        }
        if (!TextUtils.isEmpty(model.getAway_display_score())) {
            tv_away_score.setText(model.getAway_display_score());
        }
        if (!TextUtils.isEmpty(model.getAway_display_overs())) {
            tv_away_round.setText("(" + model.getAway_display_overs() + ")");
        }
        mvpPresenter.getData(true, model.getMatch_id(), model.getHome_id());
        mvpPresenter.getData(false, model.getMatch_id(), model.getAway_id());
    }

    private boolean isScrolledToTop = true;// 初始化的时候设置一下值
    private boolean isScrolledToBottom = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                if (ll_home.isSelected()) {
                    ll_home.setSelected(false);
                    sv_home.setVisibility(View.GONE);
//                    collapseView(sv_home,200, 0);
//                    sv_home.post(new Runnable() {
//                        public void run() {
//                            sv_home.fullScroll(View.FOCUS_DOWN);
//                        }
//                    });
//                    ll_home.setBackgroundColor(getResources().getColor(R.color.c_FFE6E2));
//                    sv_home.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.topview_anim_exit));
//                    sv_home.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//                        @Override
//                        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                            if (v.getScrollY() == 0) {
//                                isScrolledToTop = true;
//                                isScrolledToBottom = false;
//                                System.out.println("onScrollChanged isScrolledToTop:" + isScrolledToTop);
//                            } else if (v.getScrollY() + v.getHeight() - v.getPaddingTop() - v.getPaddingBottom() == v.getChildAt(0).getHeight()) {
//                                isScrolledToBottom = true;
//                                //实例化对象
//                                UiUtils.collapseView(sv_home, sv_home.getMeasuredHeight(), 0);
//                                System.out.println("onScrollChanged isScrolledToBottom:" + isScrolledToBottom);
//                                isScrolledToTop = false;
//                            } else {
//                                isScrolledToTop = false;
//                                isScrolledToBottom = false;
//                            }
//                        }
//                    });
//                    sv_home.post(new Runnable() {
//
//                        public void run() {
//                            sv_home.fullScroll(View.FOCUS_DOWN);
//                        }
//
//                    });

                    iv_arrow_one.setBackgroundResource(R.mipmap.icon_arrow_down_four);
//                    tv_home_name.setTextColor(getResources().getColor(R.color.c_333333));
//                    tv_home_score.setTextColor(getResources().getColor(R.color.c_333333));
//                    tv_home_round.setTextColor(getResources().getColor(R.color.c_666666));
                } else {
                    ll_home.setSelected(true);
//                    ll_home.setBackgroundColor(getResources().getColor(R.color.c_1D2550));
                    sv_home.setVisibility(View.VISIBLE);
                    iv_arrow_one.setBackgroundResource(R.mipmap.icon_arrow_up_two);
//                    tv_home_name.setTextColor(getResources().getColor(R.color.white));
//                    tv_home_score.setTextColor(getResources().getColor(R.color.white));
//                    tv_home_round.setTextColor(getResources().getColor(R.color.c_999999));
                    if (ll_away.isSelected()) {
                        ll_away.setSelected(false);
//                        ll_away.setBackgroundColor(getResources().getColor(R.color.c_FFE6E2));
                        sv_away.setVisibility(View.GONE);
                        iv_arrow_two.setBackgroundResource(R.mipmap.icon_arrow_down_four);
//                        tv_away_name.setTextColor(getResources().getColor(R.color.c_333333));
//                        tv_away_score.setTextColor(getResources().getColor(R.color.c_333333));
//                        tv_away_round.setTextColor(getResources().getColor(R.color.c_666666));
                    }
                }
                break;
            case R.id.ll_away:
                if (ll_away.isSelected()) {
                    ll_away.setSelected(false);
//                    ll_away.setBackgroundColor(getResources().getColor(R.color.c_FFE6E2));
                    sv_away.setVisibility(View.GONE);
//                    sv_away.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.topview_anim_exit));
                    iv_arrow_two.setBackgroundResource(R.mipmap.icon_arrow_down_four);
//                    tv_away_name.setTextColor(getResources().getColor(R.color.c_333333));
//                    tv_away_score.setTextColor(getResources().getColor(R.color.c_333333));
//                    tv_away_round.setTextColor(getResources().getColor(R.color.c_666666));
                } else {
                    ll_away.setSelected(true);
//                    ll_away.setBackgroundColor(getResources().getColor(R.color.c_1D2550));
                    sv_away.setVisibility(View.VISIBLE);
                    iv_arrow_two.setBackgroundResource(R.mipmap.icon_arrow_up_two);
//                    tv_away_name.setTextColor(getResources().getColor(R.color.white));
//                    tv_away_score.setTextColor(getResources().getColor(R.color.white));
//                    tv_away_round.setTextColor(getResources().getColor(R.color.c_999999));
                    if (ll_home.isSelected()) {
                        ll_home.setSelected(false);
//                        ll_home.setBackgroundColor(getResources().getColor(R.color.c_FFE6E2));
                        sv_home.setVisibility(View.GONE);
                        iv_arrow_one.setBackgroundResource(R.mipmap.icon_arrow_down_four);
//                        tv_home_name.setTextColor(getResources().getColor(R.color.c_333333));
//                        tv_home_score.setTextColor(getResources().getColor(R.color.c_333333));
//                        tv_home_round.setTextColor(getResources().getColor(R.color.c_666666));
                    }
                }
                break;
        }
    }

    @Override
    public void getHomeDataSuccess(List<ScorecardBatterBean> batterList, List<ScorecardBowlerBean> bowlerList, List<ScorecardWicketBean> wicketList, String extras, String noBattingName) {
        if (batterList != null && batterList.size() > 0) {
            mHomeBatterAdapter = new ScorecardBatterAdapter(R.layout.item_cricket_scorecard_batter, batterList);
            rv_home_batter.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_home_batter.setAdapter(mHomeBatterAdapter);
        }
        if (!TextUtils.isEmpty(extras)) {
            tv_home_extras.setText(extras);
        }
        if (!TextUtils.isEmpty(noBattingName)) {
            tv_home_no_bat.setText(noBattingName);
        }
        if (bowlerList != null && bowlerList.size() > 0) {
            mHomeBowlerAdapter = new ScorecardBowlerAdapter(R.layout.item_cricket_scorecard_bowler, bowlerList);
            rv_home_bowler.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_home_bowler.setAdapter(mHomeBowlerAdapter);
        }
        if (wicketList != null && wicketList.size() > 0) {
            mHomeWicketAdapter = new ScorecardWicketAdapter(R.layout.item_cricket_scorecard_wicket, wicketList);
            rv_home_wicket.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_home_wicket.setAdapter(mHomeWicketAdapter);
        }
    }

    @Override
    public void getAwayDataSuccess(List<ScorecardBatterBean> batterList, List<ScorecardBowlerBean> bowlerList, List<ScorecardWicketBean> wicketList, String extras, String noBattingName) {
        if (batterList != null && batterList.size() > 0) {
            mAwayBatterAdapter = new ScorecardBatterAdapter(R.layout.item_cricket_scorecard_batter, batterList);
            rv_away_batter.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_away_batter.setAdapter(mAwayBatterAdapter);
        }
        if (!TextUtils.isEmpty(extras)) {
            tv_away_extras.setText(extras);
        }
        if (!TextUtils.isEmpty(noBattingName)) {
            tv_away_no_bat.setText(noBattingName);
        }
        if (bowlerList != null && bowlerList.size() > 0) {
            mAwayBowlerAdapter = new ScorecardBowlerAdapter(R.layout.item_cricket_scorecard_bowler, bowlerList);
            rv_away_bowler.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_away_bowler.setAdapter(mAwayBowlerAdapter);
        }
        if (wicketList != null && wicketList.size() > 0) {
            mAwayWicketAdapter = new ScorecardWicketAdapter(R.layout.item_cricket_scorecard_wicket, wicketList);
            rv_away_wicket.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_away_wicket.setAdapter(mAwayWicketAdapter);
        }
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
    public void getDataFail(String msg) {

    }
}
