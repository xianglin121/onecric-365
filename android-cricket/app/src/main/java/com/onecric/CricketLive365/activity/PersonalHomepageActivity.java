package com.onecric.CricketLive365.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.google.android.material.tabs.TabLayout;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.fragment.PersonalVideoFragment;
import com.onecric.CricketLive365.fragment.dialog.LoginDialog;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.presenter.user.PersonalHomepagePresenter;
import com.onecric.CricketLive365.util.GlideUtil;
import com.onecric.CricketLive365.util.ToastUtil;
import com.onecric.CricketLive365.view.MvpActivity;
import com.onecric.CricketLive365.view.user.PersonalHomepageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalHomepageActivity extends MvpActivity<PersonalHomepagePresenter> implements PersonalHomepageView, View.OnClickListener {

    private CircleImageView head_pic;
    private TextView anchor_num;
    private TextView author_num;
    private TextView fans_num;
    private TextView user_name;
    private TextView user_profile;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mViewList;
    private String id;
    private View ll_follow;
    private ImageView iv_icon;
    private TextView tv_follow;
    public LoginDialog loginDialog;
    private WebView webview;
    private WebSettings webSettings;
    UserBean userBean;
    private ImageView iv_live;


    public static void forward(Context context, String id) {
        Intent intent = new Intent(context, PersonalHomepageActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_homepage;
    }

    @Override
    public boolean getStatusBarTextColor() {
        return false;
    }


    @Override
    protected void initView() {
        id = getIntent().getStringExtra("id");
        ll_follow = findViewById(R.id.ll_follow);
        if (id.equals(CommonAppConfig.getInstance().getUid())) {
            ll_follow.setVisibility(View.GONE);
        }
        iv_icon = findViewById(R.id.iv_icon);
        tv_follow = findViewById(R.id.tv_follow);
        head_pic = findViewById(R.id.person_head_pic);
        user_name = findViewById(R.id.tv_user_name);
        user_profile = findViewById(R.id.tv_user_profile);
        iv_live = findViewById(R.id.iv_live);
        findViewById(R.id.ll_follow).setOnClickListener(this);
        head_pic.setOnClickListener(this);
        anchor_num = findViewById(R.id.follow_the_anchor);
        author_num = findViewById(R.id.follow_the_author);
        fans_num = findViewById(R.id.fans);
        tabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        findViewById(R.id.ll_post).setOnClickListener(this);
        findViewById(R.id.ll_group).setOnClickListener(this);
        findViewById(R.id.ll_following).setOnClickListener(this);
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.post)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.videos)));

        mViewList = new ArrayList<>();
//        mViewList.add(PersonalPostFragment.newInstance(id));
        mViewList.add(PersonalVideoFragment.newInstance(id));
        initWebView();
        loginDialog = new LoginDialog(this, R.style.dialog, true, () -> {
            loginDialog.dismiss();
            webview.setVisibility(View.VISIBLE);
            webview.loadUrl("javascript:ab()");
        });
        initViewPager();
    }


    private void initViewPager() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //初始化viewpager
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tabLayout.getTabAt(i).select();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewPager.setOffscreenPageLimit(mViewList.size());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mViewList.get(i);
            }

            @Override
            public int getCount() {
                return mViewList.size();
            }
        });
    }


    @Override
    protected void initData() {
        mvpPresenter.getUserInfo(id);
    }

    @Override
    protected PersonalHomepagePresenter createPresenter() {
        return new PersonalHomepagePresenter(this);
    }

    @Override
    public void getDataSuccess(UserBean userBean) {
        if (userBean != null) {
            if (!id.equals(CommonAppConfig.getInstance().getUid())) {
                ll_follow.setVisibility(View.VISIBLE);
            }
            int is_anchor = userBean.getIs_anchor();
            if (is_anchor != 1) {
                iv_live.setVisibility(View.GONE);
            } else {
                iv_live.setVisibility(View.VISIBLE);
            }
            if (userBean.getIs_live() == 0) {
                iv_live.setSelected(false);
            } else {
                iv_live.setSelected(true);
            }
            this.userBean = userBean;
            if (userBean.isIs_attention() == 1) {
                ll_follow.setBackgroundColor(getResources().getColor(R.color.c_D5D5D5));
                tv_follow.setText(getString(R.string.followed));
                iv_icon.setVisibility(View.GONE);
            } else {
                ll_follow.setBackgroundResource(R.mipmap.bg_live_follow);
                tv_follow.setText(getString(R.string.follow));
                iv_icon.setVisibility(View.VISIBLE);
            }
            GlideUtil.loadUserImageDefault(this, userBean.getAvatar(), head_pic);
            if (!TextUtils.isEmpty(userBean.getUser_nickname())) {
                user_name.setText(userBean.getUser_nickname());
            } else {
                user_name.setText("");
            }
            if (!TextUtils.isEmpty(userBean.getSignature())) {
                user_profile.setText(userBean.getSignature());
            } else {
                user_profile.setText("");
            }
            anchor_num.setText(userBean.getFollow_the_anchor() + "");
            author_num.setText(userBean.getFollow_the_author() + "");
            fans_num.setText(userBean.getFans() + "");
        }
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_follow:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getString(R.string.please_login));
                    loginDialog.show();
                    return;
                }
                mvpPresenter.doFollow(Integer.parseInt(id));
                break;
            case R.id.person_head_pic:
                if (id.equals(CommonAppConfig.getInstance().getUid())) {
                    UserInfoActivity.forward(mActivity);
                }
                break;
            case R.id.ll_post:
//                AttentionActivity.forward(this, 0, Integer.parseInt(id));
                break;
            case R.id.ll_group:
//                AttentionActivity.forward(this, 1, Integer.parseInt(id));
                break;
            case R.id.ll_following:
//                AttentionActivity.forward(this, 2, Integer.parseInt(id));
                break;
        }
    }

    @Override
    public void doFollowSuccess(int id) {
        if (userBean.isIs_attention() == 1) {
            userBean.setIs_attention(0);
        } else {
            userBean.setIs_attention(1);
        }
//        ll_follow.setBackgroundColor(getResources().getColor(R.color.c_D5D5D5));
//        tv_follow.setText(getString(R.string.followed));
//        anchor_num.setText((userBean.getFollow_the_anchor() + 1) + "");
//        iv_icon.setVisibility(View.GONE);
        if (userBean.isIs_attention() == 1) {
            ll_follow.setBackgroundColor(getResources().getColor(R.color.c_D5D5D5));
            tv_follow.setText(getString(R.string.followed));
            userBean.setFollow_the_anchor(userBean.getFollow_the_anchor() + 1);
            anchor_num.setText(userBean.getFollow_the_anchor() + "");
            iv_icon.setVisibility(View.GONE);
        } else {
            ll_follow.setBackgroundResource(R.mipmap.bg_live_follow);
            tv_follow.setText(getString(R.string.follow));
            userBean.setFollow_the_anchor(userBean.getFollow_the_anchor() - 1);
            anchor_num.setText(userBean.getFollow_the_anchor() + "");
            iv_icon.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        webview = (WebView) findViewById(R.id.webview);
        webSettings = webview.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 禁用缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webSettings.setDefaultTextEncodingName("utf-8");
        webview.setBackgroundColor(0); // 设置背景色
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        // 开启js支持
        webSettings.setJavaScriptEnabled(true);
        webview.addJavascriptInterface(this, "jsBridge");
        webview.loadUrl("file:///android_asset/index.html");
    }

    @JavascriptInterface
    public void getData(String data) {
        webview.postDelayed(new Runnable() {
            @Override
            public void run() {
                webview.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(data)) {
                    JSONObject jsonObject = JSONObject.parseObject(data);
                    if (jsonObject.getIntValue("ret") == 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                dialog.show();
                                loginDialog.show();
                                loginDialog.passWebView();
                            }
                        });
                    }
                }
            }
        }, 500);
    }

}
