package com.onecric.CricketLive365.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.model.CricketMatchDataBean;
import com.onecric.CricketLive365.model.CricketNewBean;
import com.onecric.CricketLive365.model.LinkDataBean;
import com.onecric.CricketLive365.util.GlideUtil;
import com.onecric.CricketLive365.util.ToastUtil;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/27
 */
public class GuideFragmentAdapter extends BaseQuickAdapter<CricketMatchDataBean, BaseViewHolder> {
    private LinkDataBean linkDataBean;
    private Dialog mLinkDialog;
    private Context context;

    public GuideFragmentAdapter(int layoutResId, @Nullable List<CricketMatchDataBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        initStreamDialog();
    }

    public void setLinkData(LinkDataBean bean) {
        this.linkDataBean = bean;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CricketMatchDataBean item) {
        ImageView iv_home_logo_big = helper.getView(R.id.iv_home_logo_big);
        ImageView iv_away_logo_big = helper.getView(R.id.iv_away_logo_big);
        ImageView iv_home_logo = helper.getView(R.id.iv_home_logo);
        ImageView iv_away_logo = helper.getView(R.id.iv_away_logo);
        helper.getView(R.id.rl_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show("Please choose the following channel to watch the free live broadcast.");
                if (mLinkDialog != null) {
                    mLinkDialog.show();
                }
            }
        });
        helper.getView(R.id.iv_fancode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkDataBean != null) {
                    String url = linkDataBean.getTwo();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
        helper.getView(R.id.iv_onecric).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkDataBean != null) {
                    String url = linkDataBean.getOne();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
        helper.getView(R.id.iv_circbuzz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkDataBean != null) {
                    String url = linkDataBean.getThree();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
        GlideUtil.loadTeamImageDefault(mContext, item.getHome_logo(), iv_home_logo);
        GlideUtil.loadTeamImageDefault(mContext, item.getHome_logo(), iv_home_logo_big);
        GlideUtil.loadTeamImageDefault(mContext, item.getAway_logo(), iv_away_logo);
        GlideUtil.loadTeamImageDefault(mContext, item.getAway_logo(), iv_away_logo_big);
        if (!TextUtils.isEmpty(item.getHome_name())) {
            helper.setText(R.id.tv_home_name, item.getHome_name());
        } else {
            helper.setText(R.id.tv_home_name, "");
        }
        if (!TextUtils.isEmpty(item.getAway_name())) {
            helper.setText(R.id.tv_away_name, item.getAway_name());
        } else {
            helper.setText(R.id.tv_away_name, "");
        }
        helper.setText(R.id.tv_result, item.getMatch_result());
        helper.setText(R.id.tv_home_score, item.getHome_display_score());
        helper.setText(R.id.tv_away_score, item.getAway_display_score());
        helper.setText(R.id.tv_title, item.getTname());
    }

    private void initStreamDialog() {
        mLinkDialog = new Dialog(context, R.style.dialog);
        mLinkDialog.setContentView(R.layout.dialog_choose_link);
        mLinkDialog.setCancelable(true);
        mLinkDialog.setCanceledOnTouchOutside(true);

        mLinkDialog.getWindow().setWindowAnimations(R.style.bottomToTopAnim);
        WindowManager.LayoutParams params = mLinkDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        mLinkDialog.getWindow().setAttributes(params);
        mLinkDialog.findViewById(R.id.ll_all_live).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkDataBean != null) {
                    if (mLinkDialog != null) {
                        mLinkDialog.dismiss();
                    }
                    String url = linkDataBean.getOne();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
        mLinkDialog.findViewById(R.id.ll_author_live).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkDataBean != null) {
                    if (mLinkDialog != null) {
                        mLinkDialog.dismiss();
                    }
                    String url = linkDataBean.getTwo();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
        mLinkDialog.findViewById(R.id.ll_match_live).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkDataBean != null) {
                    if (mLinkDialog != null) {
                        mLinkDialog.dismiss();
                    }
                    String url = linkDataBean.getThree();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
