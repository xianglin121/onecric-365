package com.onecric.CricketLive365.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.model.CricketTeamBean;
import com.onecric.CricketLive365.util.GlideUtil;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/27
 */
public class CricketTeamsAdapter extends BaseQuickAdapter<CricketTeamBean, BaseViewHolder> {
    public CricketTeamsAdapter(int layoutResId, @Nullable List<CricketTeamBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CricketTeamBean item) {
        ImageView iv_logo = helper.getView(R.id.iv_logo);
        GlideUtil.loadTeamImageDefault(mContext, item.getTeam_logo(), iv_logo);
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.tv_name, item.getName());
        }else {
            helper.setText(R.id.tv_name, "");
        }
        if (!TextUtils.isEmpty(item.getAbbreviation())) {
            helper.setText(R.id.tv_abbreviation, item.getAbbreviation());
        }else {
            helper.setText(R.id.tv_abbreviation, "");
        }
    }
}
