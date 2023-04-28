package com.onecric.CricketLive365.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.PersonalHomepageActivity;
import com.onecric.CricketLive365.custom.ButtonFollowView;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.util.GlideUtil;
import com.onecric.CricketLive365.view.MvpActivity;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2021/9/14
 */
public class MyFollowAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {
    public MyFollowAdapter(int layoutResId, @Nullable List<UserBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserBean item) {
        ImageView iv_avatar = helper.getView(R.id.iv_avatar);
        GlideUtil.loadUserImageDefault(mContext, item.getAvatar(), iv_avatar);
        iv_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!((MvpActivity) mContext).isFastDoubleClick())
                PersonalHomepageActivity.forward(mContext, item.getFollowed_id() + "");
            }
        });
        int is_anchor = item.getIs_anchor();
        if (is_anchor != 1) {
            helper.getView(R.id.iv_live).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.iv_live).setVisibility(View.VISIBLE);
        }
        if (item.getIs_live() == 0) {
            helper.getView(R.id.iv_live).setSelected(false);
        } else {
            helper.getView(R.id.iv_live).setSelected(true);
        }
        if (!TextUtils.isEmpty(item.getUser_nickname())) {
            helper.setText(R.id.tv_name, item.getUser_nickname());
        } else {
            helper.setText(R.id.tv_name, "");
        }
        if (!TextUtils.isEmpty(item.getOnlineTime())) {
            helper.setText(R.id.tv_online_time, item.getOnlineTime());
        } else {
            helper.setText(R.id.tv_online_time, "");
        }
        ImageView iv_level = helper.getView(R.id.iv_level);
        GlideUtil.loadImageDefault(mContext, item.getExp_icon(), iv_level);
        helper.setText(R.id.tv_fans_count, mContext.getString(R.string.fans) + item.getAttention());
        ButtonFollowView buttonFollowView = helper.getView(R.id.iv_follow);
        buttonFollowView.setFollow(true);
        helper.addOnClickListener(R.id.iv_follow);
    }
}
