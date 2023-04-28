package com.onecric.CricketLive365.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.model.Channel;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2021/9/14
 */
public class UnSelectedChannelAdapter extends BaseQuickAdapter<Channel, BaseViewHolder> {
    public UnSelectedChannelAdapter(int layoutResId, @Nullable List<Channel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Channel item) {
        helper.setText(R.id.tvChannel, item.short_name_zh).setVisible(R.id.ivDelete, false);
    }
}
