package com.onecric.CricketLive365.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.adapter.DefaultAvatarAdapter;
import com.onecric.CricketLive365.model.AvatarBean;
import com.onecric.CricketLive365.presenter.user.DefaultAvatarPresenter;
import com.onecric.CricketLive365.util.GlideUtil;
import com.onecric.CricketLive365.util.ToastUtil;
import com.onecric.CricketLive365.view.MvpActivity;
import com.onecric.CricketLive365.view.user.DefaultAvatarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/1/21
 */
public class DefaultAvatarActivity extends MvpActivity<DefaultAvatarPresenter> implements DefaultAvatarView {
    public static void forward(Context context) {
        Intent intent = new Intent(context, DefaultAvatarActivity.class);
        context.startActivity(intent);
    }

    private ImageView iv_avatar;
    private RecyclerView rv_avatar;
    private DefaultAvatarAdapter mAdapter;

    @Override
    protected DefaultAvatarPresenter createPresenter() {
        return new DefaultAvatarPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_default_avatar;
    }

    @Override
    protected void initView() {
        iv_avatar = findViewById(R.id.iv_avatar);
        rv_avatar = findViewById(R.id.rv_avatar);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String avatar = "";
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if (mAdapter.getItem(i).isSelect()) {
                        avatar = mAdapter.getItem(i).getAvatar();
                    }
                }
                if (TextUtils.isEmpty(avatar)) {
                    ToastUtil.show(getString(R.string.please_select_your_avatar));
                    return;
                }
                mvpPresenter.updateAvatar(avatar);
            }
        });
    }

    @Override
    protected void initData() {
        GlideUtil.loadUserImageDefault(this, CommonAppConfig.getInstance().getUserBean().getAvatar(), iv_avatar);

        iv_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PersonalHomepageActivity.forward(DefaultAvatarActivity.this, CommonAppConfig.getInstance().getUid());
            }
        });

        mAdapter = new DefaultAvatarAdapter(R.layout.item_default_avatar, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if (i == position) {
                        mAdapter.getItem(i).setSelect(true);
                    } else {
                        mAdapter.getItem(i).setSelect(false);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        rv_avatar.setLayoutManager(new GridLayoutManager(this, 4));
        rv_avatar.setAdapter(mAdapter);

        mvpPresenter.getList();
    }

    @Override
    public void getDataSuccess(List<AvatarBean> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void getDataFail(String msg) {

    }
}
