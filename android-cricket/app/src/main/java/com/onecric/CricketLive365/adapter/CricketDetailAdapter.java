package com.onecric.CricketLive365.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.model.CricketMatchBean;
import com.onecric.CricketLive365.util.GlideUtil;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/27
 */
public class CricketDetailAdapter extends BaseQuickAdapter<CricketMatchBean, BaseViewHolder> {
    public CricketDetailAdapter(int layoutResId, @Nullable List<CricketMatchBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CricketMatchBean item) {
        helper.setTextColor(R.id.tv_time, mContext.getResources().getColor(R.color.c_901D2550));
        TextView resultTv = helper.getView(R.id.tv_result);
        if (item.getStatus() == 2) {//已结束
            resultTv.setTypeface(ResourcesCompat.getFont(mContext, R.font.noto_sans_display_regular));
            helper.getView(R.id.ll_alarm).setVisibility(View.GONE);
        } else {
            resultTv.setTypeface(ResourcesCompat.getFont(mContext, R.font.noto_sans_display_regular));
            helper.getView(R.id.ll_alarm).setVisibility(View.VISIBLE);
            if (item.getStatus() == 0) {//未开始
                helper.getView(R.id.iv_alarm).setVisibility(View.GONE);
                if (!TextUtils.isEmpty(item.getLive_time())) {
                    helper.setText(R.id.tv_time, item.getLive_time());
                } else {
                    helper.getView(R.id.tv_time).setVisibility(View.GONE);
                }
            } else {//已开始
                resultTv.setTypeface(ResourcesCompat.getFont(mContext, R.font.noto_sans_display_semibold));
                helper.getView(R.id.iv_alarm).setVisibility(View.GONE);
                helper.setText(R.id.tv_time, mContext.getString(R.string.live2));
                helper.setTextColor(R.id.tv_time, mContext.getResources().getColor(R.color.c_DC3C23));
            }
        }
        if (!TextUtils.isEmpty(item.getMatch_num())) {
            helper.setText(R.id.tv_date, item.getMatch_num());
        } else {
            helper.setText(R.id.tv_date, "");
        }
        ImageView iv_home_logo = helper.getView(R.id.iv_home_logo);
        GlideUtil.loadTeamImageDefault(mContext, item.getHome_logo(), iv_home_logo);
        if (!TextUtils.isEmpty(item.getHome_name())) {
            helper.setText(R.id.tv_home_name, item.getHome_name());
        } else {
            helper.setText(R.id.tv_home_name, "");
        }
        if (!TextUtils.isEmpty(item.getHome_display_score())) {
            if (item.getHome_display_score().contains(" ")) {
                String[] split = item.getHome_display_score().split(" ");
                helper.setText(R.id.tv_home_score, split[0]);
                helper.setText(R.id.tv_home_score2, " " + split[1]);
            } else {
                helper.setText(R.id.tv_home_score, item.getHome_display_score());
            }
        } else {
            helper.setText(R.id.tv_home_score, "");
            helper.setText(R.id.tv_home_score2, " ");
        }
        ImageView iv_away_logo = helper.getView(R.id.iv_away_logo);
        GlideUtil.loadTeamImageDefault(mContext, item.getAway_logo(), iv_away_logo);
        if (!TextUtils.isEmpty(item.getAway_name())) {
            helper.setText(R.id.tv_away_name, item.getAway_name());
        } else {
            helper.setText(R.id.tv_away_name, "");
        }
        if (!TextUtils.isEmpty(item.getAway_display_score())) {
            if (item.getAway_display_score().contains(" ")) {
                String[] split = item.getAway_display_score().split(" ");
                helper.setText(R.id.tv_away_score, split[0]);
                helper.setText(R.id.tv_away_score2, " " + split[1]);
            } else {
                helper.setText(R.id.tv_away_score, item.getAway_display_score());
            }
        } else {
            helper.setText(R.id.tv_away_score, "");
            helper.setText(R.id.tv_away_score2, "");
        }
        if (!TextUtils.isEmpty(item.getMatch_result())) {
            helper.setText(R.id.tv_result, item.getMatch_result());
        } else {
            helper.setText(R.id.tv_result, "");
        }

//        if (item.getHome_id() == item.getWinner_id()) {
//            helper.setTextColor(R.id.tv_home_score, mContext.getResources().getColor(R.color.c_333333));
//            helper.setTextColor(R.id.tv_home_score2, mContext.getResources().getColor(R.color.c_333333));
//            helper.setTextColor(R.id.tv_away_score, mContext.getResources().getColor(R.color.c_666666));
//            helper.setTextColor(R.id.tv_away_score2, mContext.getResources().getColor(R.color.c_666666));
//
//        } else {
//            helper.setTextColor(R.id.tv_home_score, mContext.getResources().getColor(R.color.c_666666));
//            helper.setTextColor(R.id.tv_home_score2, mContext.getResources().getColor(R.color.c_666666));
//            helper.setTextColor(R.id.tv_away_score, mContext.getResources().getColor(R.color.c_333333));
//            helper.setTextColor(R.id.tv_away_score2, mContext.getResources().getColor(R.color.c_333333));
//        }
        if (item.getStatus() == 2) {
            helper.setTextColor(R.id.tv_result, mContext.getResources().getColor(R.color.c_DC3C23));
            if (item.getHome_id() == item.getWinner_id()) {
                helper.setTextColor(R.id.tv_home_score, mContext.getResources().getColor(R.color.c_333333));
                helper.setTextColor(R.id.tv_home_score2, mContext.getResources().getColor(R.color.c_333333));
                helper.setTextColor(R.id.tv_away_score, mContext.getResources().getColor(R.color.c_9D9EA3));
                helper.setTextColor(R.id.tv_away_score2, mContext.getResources().getColor(R.color.c_9D9EA3));
            } else if(item.getAway_id() == item.getWinner_id()){
                helper.setTextColor(R.id.tv_home_score, mContext.getResources().getColor(R.color.c_9D9EA3));
                helper.setTextColor(R.id.tv_home_score2, mContext.getResources().getColor(R.color.c_9D9EA3));
                helper.setTextColor(R.id.tv_away_score, mContext.getResources().getColor(R.color.c_333333));
                helper.setTextColor(R.id.tv_away_score2, mContext.getResources().getColor(R.color.c_333333));
            }
        } else if (item.getStatus() == 1) {
            helper.setTextColor(R.id.tv_result, mContext.getResources().getColor(R.color.c_1D2550));
            helper.setTextColor(R.id.tv_home_score, mContext.getResources().getColor(R.color.c_4DA74F));
            helper.setTextColor(R.id.tv_home_score2, mContext.getResources().getColor(R.color.c_4DA74F));
            helper.setTextColor(R.id.tv_away_score, mContext.getResources().getColor(R.color.c_4DA74F));
            helper.setTextColor(R.id.tv_away_score2, mContext.getResources().getColor(R.color.c_4DA74F));
        } else {
            helper.setTextColor(R.id.tv_result, mContext.getResources().getColor(R.color.c_1D2550));
        }
    }
}
