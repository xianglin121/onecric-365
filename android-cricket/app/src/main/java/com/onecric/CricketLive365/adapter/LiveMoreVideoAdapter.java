package com.onecric.CricketLive365.adapter;

import static com.onecric.CricketLive365.util.DateUtil.getRelativeLocalDate;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.model.LiveBean;
import com.onecric.CricketLive365.util.GlideUtil;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2021/9/14
 */
public class LiveMoreVideoAdapter extends BaseQuickAdapter<LiveBean, BaseViewHolder>{
    //    private SimpleDateFormat sfdate = new SimpleDateFormat("EEE,dd MMM.", Locale.ENGLISH);
    private SimpleDateFormat sfdate2 = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

    public LiveMoreVideoAdapter(int layoutResId, @Nullable List<LiveBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LiveBean item) {
        ImageView iv_cover = helper.getView(R.id.iv_cover);
        ImageView iv_avatar = helper.getView(R.id.iv_avatar);
        ImageView iv_live = helper.getView(R.id.iv_live);
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_num = helper.getView(R.id.tv_num);
        TextView tv_time = helper.getView(R.id.tv_time);
        ImageView iv_hot = helper.getView(R.id.iv_hot);

        GlideUtil.loadLiveImageDefault(mContext, item.getThumb(), iv_cover);
        GlideUtil.loadUserImageDefault(mContext, item.getAvatar(), iv_avatar);
        if (!TextUtils.isEmpty(item.getTitle())) {
            tv_title.setText(item.getTitle());
        }else {
            tv_title.setText("");
        }
        if (!TextUtils.isEmpty(item.getUser_nickname())) {
            tv_name.setText(item.getUser_nickname());
        }else {
            tv_name.setText("");
        }
        tv_num.setText(item.getHeat() > 1000 ? String.format("%.1f",(float)item.getHeat()/1000) + "K" :item.getHeat()+"");
        if(item.getIslive() == 0){
            iv_live.setVisibility(View.GONE);
            tv_time.setVisibility(View.VISIBLE);
//            iv_cover.setColorFilter(Color.parseColor("#40000000"));
            tv_time.setText("Watch live at "+getRelativeLocalDate(sfdate2,item.getStarttime()));
            iv_hot.setVisibility(View.GONE);
            tv_num.setVisibility(View.GONE);
        }else{
            iv_cover.setColorFilter(null);
            iv_live.setVisibility(View.VISIBLE);
            tv_time.setVisibility(View.GONE);
            iv_hot.setVisibility(View.VISIBLE);
            tv_num.setVisibility(View.VISIBLE);
        }

    }

}
