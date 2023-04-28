package com.onecric.CricketLive365.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.PlayerStatsActivity;
import com.onecric.CricketLive365.activity.TeamComparisonActivity;
import com.onecric.CricketLive365.adapter.PlayerMatchDataAdapter;
import com.onecric.CricketLive365.model.CricketFantasyBean;
import com.onecric.CricketLive365.model.PlayerDataBean;
import com.onecric.CricketLive365.presenter.cricket.CricketFantasyPresenter;
import com.onecric.CricketLive365.util.DpUtil;
import com.onecric.CricketLive365.util.GlideUtil;
import com.onecric.CricketLive365.view.MvpFragment;
import com.onecric.CricketLive365.view.cricket.CricketFantasyView;

import java.util.ArrayList;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/27
 */
public class CricketFantasyFragment extends MvpFragment<CricketFantasyPresenter> implements CricketFantasyView, View.OnClickListener {

    private RecyclerView rv_player;
    private PlayerMatchDataAdapter mAdapter;

    public static CricketFantasyFragment newInstance() {
        CricketFantasyFragment fragment = new CricketFantasyFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private TextView tv_home_name;
    private ImageView iv_home_logo;
    private TextView tv_away_name;
    private ImageView iv_away_logo;
    private TextView tv_home_win_rate;
    private TextView tv_away_win_rate;
    private ProgressBar progress_bar;
    private TextView tv_home_round;
    private TextView tv_away_round;
    private LinearLayout ll_home_round;
    private LinearLayout ll_away_round;

    private int mMatchId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cricket_fantasy;
    }

    @Override
    protected CricketFantasyPresenter createPresenter() {
        return new CricketFantasyPresenter(this);
    }

    @Override
    protected void initUI() {
        tv_home_name = findViewById(R.id.tv_home_name);
        iv_home_logo = findViewById(R.id.iv_home_logo);
        tv_away_name = findViewById(R.id.tv_away_name);
        iv_away_logo = findViewById(R.id.iv_away_logo);
        tv_home_win_rate = findViewById(R.id.tv_home_win_rate);
        tv_away_win_rate = findViewById(R.id.tv_away_win_rate);
        progress_bar = findViewById(R.id.progress_bar);
        tv_home_round = findViewById(R.id.tv_home_round);
        tv_away_round = findViewById(R.id.tv_away_round);
        ll_home_round = findViewById(R.id.ll_home_round);
        ll_away_round = findViewById(R.id.ll_away_round);
        rv_player = findViewById(R.id.rv_player_match_data);
        mAdapter = new PlayerMatchDataAdapter(R.layout.item_player_match_data, new ArrayList<>());
        rv_player.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_player.setAdapter(mAdapter);

        mAdapter.getData().clear();
        //伪数据
        ArrayList<PlayerDataBean> list = new ArrayList<>();
        PlayerDataBean player1 = new PlayerDataBean();
        PlayerDataBean player2 = new PlayerDataBean();
        PlayerDataBean player3 = new PlayerDataBean();
        player1.setName("James");
        player1.setRole("Bowler");
        player1.setPoints(100);
        player1.setMatchNum(1);
        player1.setDt(100);
        player2.setName("Wade");
        player2.setRole("Wicket Keeper");
        player2.setPoints(100);
        player2.setMatchNum(1);
        player2.setDt(100);
        player3.setName("Anthony");
        player3.setRole("All Rounder");
        player3.setPoints(100);
        player3.setMatchNum(1);
        player3.setDt(100);
        list.add(player1);
        list.add(player2);
        list.add(player3);
        mAdapter.getData().addAll(list);
        mAdapter.notifyItemInserted(list.size());

        findViewById(R.id.cv_player_stats).setOnClickListener(this);
//        findViewById(R.id.cv_expert_analysis).setOnClickListener(this);
        findViewById(R.id.cv_team_comparison).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    public void getData(int matchId, String homeName, String homeLogo, String awayName, String awayLogo) {
        mMatchId = matchId;
        mvpPresenter.getData(matchId);
        if (!TextUtils.isEmpty(homeName)) {
            tv_home_name.setText(homeName);
        }
        GlideUtil.loadTeamImageDefault(getContext(), homeLogo, iv_home_logo);
        if (!TextUtils.isEmpty(awayName)) {
            tv_away_name.setText(awayName);
        }
        GlideUtil.loadTeamImageDefault(getContext(), awayLogo, iv_away_logo);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getDataSuccess(CricketFantasyBean model) {
        if (model != null) {
            tv_home_win_rate.setText(model.getHome_win_probabilities() + "%");
            tv_away_win_rate.setText(model.getAway_win_probabilities() + "%");
            progress_bar.setProgress((int) model.getHome_win_probabilities());
            if (model.getHome() != null) {
                int winCount = 0;
                for (int i = 0; i < model.getHome().size(); i++) {
                    TextView textView = new TextView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DpUtil.dp2px(24), DpUtil.dp2px(24));
                    layoutParams.rightMargin = DpUtil.dp2px(7);
                    textView.setLayoutParams(layoutParams);
                    if ("L".equals(model.getHome().get(i))) {
                        textView.setBackgroundColor(getResources().getColor(R.color.c_DC3C23));
                        textView.setText("L");
                    } else {
                        winCount++;
                        textView.setBackgroundColor(getResources().getColor(R.color.c_219430));
                        textView.setText("W");
                    }
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextSize(12);
                    textView.setTextColor(Color.WHITE);
                    ll_home_round.addView(textView);
                }
                tv_home_round.setText(winCount + "/5");
            }

            if (model.getAway() != null) {
                int winCount = 0;
                for (int i = 0; i < model.getAway().size(); i++) {
                    TextView textView = new TextView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DpUtil.dp2px(24), DpUtil.dp2px(24));
                    layoutParams.rightMargin = DpUtil.dp2px(7);
                    textView.setLayoutParams(layoutParams);
                    if ("L".equals(model.getAway().get(i))) {
                        textView.setBackgroundColor(getResources().getColor(R.color.c_DC3C23));
                        textView.setText("L");
                    } else {
                        winCount++;
                        textView.setBackgroundColor(getResources().getColor(R.color.c_219430));
                        textView.setText("W");
                    }
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextSize(12);
                    textView.setTextColor(Color.WHITE);
                    ll_away_round.addView(textView);
                }
                tv_away_round.setText(winCount + "/5");
            }
        }
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_player_stats:
                PlayerStatsActivity.forward(getContext());
                break;
//            case R.id.cv_expert_analysis:
//                ExpertAnalysisActivity.forward(getContext());
//                break;
            case R.id.cv_team_comparison:
                TeamComparisonActivity.forward(getContext(), mMatchId);
                break;
        }
    }
}
