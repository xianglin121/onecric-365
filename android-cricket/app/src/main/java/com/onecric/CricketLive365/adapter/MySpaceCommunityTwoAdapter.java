package com.onecric.CricketLive365.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.util.GlideUtil;
import com.tencent.qcloud.tuikit.tuichat.component.face.FaceManager;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2021/9/14
 */
public class MySpaceCommunityTwoAdapter extends BaseQuickAdapter<CommunityBean, BaseViewHolder> {
    public MySpaceCommunityTwoAdapter(int layoutResId, @Nullable List<CommunityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommunityBean item) {
        ImageView iv_avatar = helper.getView(R.id.iv_avatar);
        GlideUtil.loadUserImageDefault(mContext, item.getAvatar(), iv_avatar);
        if (!TextUtils.isEmpty(item.getUser_nickname())) {
            helper.setText(R.id.tv_name, item.getUser_nickname());
        }else {
            helper.setText(R.id.tv_name, "");
        }
        if (!TextUtils.isEmpty(item.getAddtime())) {
            helper.setText(R.id.tv_time, item.getAddtime());
        }else {
            helper.setText(R.id.tv_time, "");
        }
        helper.setText(R.id.tv_like, String.valueOf(item.getComment_likes()));
        ImageView iv_like = helper.getView(R.id.iv_like);
        if (item.getIs_comment_likes() == 1) {
            iv_like.setSelected(true);
        }else {
            iv_like.setSelected(false);
        }
        if (!TextUtils.isEmpty(item.getContent())) {
            helper.setText(R.id.tv_content, FaceManager.handlerEmojiText(item.getContent()));
        }else {
            helper.setText(R.id.tv_content, "");
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_title, item.getTitle());
        }else {
            helper.setText(R.id.tv_title, "");
        }
        if (item.getImg() != null) {
            if (item.getImg().size() == 0) {
                helper.getView(R.id.iv_img).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.iv_img).setVisibility(View.VISIBLE);
            }
        }else {
            helper.getView(R.id.iv_img).setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.iv_delete);
        helper.addOnClickListener(R.id.ll_like);
        helper.addOnClickListener(R.id.ll_title);
    }
}
