package com.onecric.CricketLive365.adapter;

import android.text.TextUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.model.FootballHistoryIndexDetailInnerBean;
import com.onecric.CricketLive365.model.MatchListBean;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2021/9/11
 */
public class FootballHistoryIndexDetailAdapter extends BaseMultiItemQuickAdapter<FootballHistoryIndexDetailInnerBean, BaseViewHolder> {

    public FootballHistoryIndexDetailAdapter(List<FootballHistoryIndexDetailInnerBean> data) {
        super(data);
        addItemType(MatchListBean.HEAD, R.layout.item_history_index_detail_head);
        addItemType(MatchListBean.CONTENT, R.layout.item_history_index_detail_content);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FootballHistoryIndexDetailInnerBean item) {
        switch (helper.getItemViewType()) {
            case MatchListBean.HEAD:
                break;
            case MatchListBean.CONTENT:
                LinearLayout rootView = helper.getView(R.id.rootView);
                if (helper.getLayoutPosition()%2 == 0) {
                    rootView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    rootView.setBackgroundColor(mContext.getResources().getColor(R.color.c_FFFBF6));
                }
                if (!TextUtils.isEmpty(item.getMatch_time())) {
                    helper.setText(R.id.tv_date, item.getMatch_time());
                }else {
                    helper.setText(R.id.tv_date, "");
                }
                if (!TextUtils.isEmpty(item.getCompetition())) {
                    helper.setText(R.id.tv_name, item.getCompetition());
                }else {
                    helper.setText(R.id.tv_name, "");
                }
                if (!TextUtils.isEmpty(item.getHome_team())) {
                    helper.setText(R.id.tv_home_team, item.getHome_team());
                }else {
                    helper.setText(R.id.tv_home_team, "");
                }
                if (!TextUtils.isEmpty(item.getAway_team())) {
                    helper.setText(R.id.tv_away_team, item.getAway_team());
                }else {
                    helper.setText(R.id.tv_away_team, "");
                }
                helper.setText(R.id.tv_score, item.getHome_score() + "-" + item.getAway_score());
                if (item.getHome_score() > item.getAway_score()) {
                    helper.setTextColor(R.id.tv_score, mContext.getResources().getColor(R.color.c_E92525));
                }else if (item.getHome_score() < item.getAway_score()) {
                    helper.setTextColor(R.id.tv_score, mContext.getResources().getColor(R.color.c_6F8FC2));
                }else {
                    helper.setTextColor(R.id.tv_score, mContext.getResources().getColor(R.color.c_44AC3D));
                }
                helper.setText(R.id.tv_final, String.valueOf(item.getFinale()));
                if (!TextUtils.isEmpty(item.getIs_win())) {
                    helper.setText(R.id.tv_result, item.getIs_win());
                    if ("输".equals(item.getIs_win())) {
                        helper.setTextColor(R.id.tv_result, mContext.getResources().getColor(R.color.c_6F8FC2));
                    }else if ("赢".equals(item.getIs_win())) {
                        helper.setTextColor(R.id.tv_result, mContext.getResources().getColor(R.color.c_E92525));
                    }else {
                        helper.setTextColor(R.id.tv_result, mContext.getResources().getColor(R.color.c_44AC3D));
                    }
                }else {
                    helper.setText(R.id.tv_result, "");
                }
                break;
        }
    }
}
