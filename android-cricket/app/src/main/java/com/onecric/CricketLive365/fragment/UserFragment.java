package com.onecric.CricketLive365.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.ActivityCenterActivity;
import com.onecric.CricketLive365.activity.ChargeActivity;
import com.onecric.CricketLive365.activity.CommonProblemActivity;
import com.onecric.CricketLive365.activity.DefaultAvatarActivity;
import com.onecric.CricketLive365.activity.MyFollowActivity;
import com.onecric.CricketLive365.activity.MyLevelActivity;
import com.onecric.CricketLive365.activity.MyMessageActivity;
import com.onecric.CricketLive365.activity.MyReserveActivity;
import com.onecric.CricketLive365.activity.MySpaceActivity;
import com.onecric.CricketLive365.activity.PayPwdSettingActivity;
import com.onecric.CricketLive365.activity.SettingActivity;
import com.onecric.CricketLive365.activity.SpeakerHistoryActivity;
import com.onecric.CricketLive365.activity.UserInfoActivity;
import com.onecric.CricketLive365.activity.WithdrawActivity;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.presenter.user.UserPresenter;
import com.onecric.CricketLive365.util.CommonUtil;
import com.onecric.CricketLive365.util.GlideUtil;
import com.onecric.CricketLive365.util.ToastUtil;
import com.onecric.CricketLive365.view.MvpFragment;
import com.onecric.CricketLive365.view.user.UserView;

public class UserFragment extends MvpFragment<UserPresenter> implements UserView, View.OnClickListener {

    private ImageView iv_avatar;
    private LinearLayout ll_login_and_register;
    private TextView tv_name;
    private TextView tv_desc;
    private TextView tv_my_diamond_count;
    private TextView tv_withdraw_diamond_count;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    protected void initUI() {
        iv_avatar = rootView.findViewById(R.id.iv_avatar);
        ll_login_and_register = rootView.findViewById(R.id.ll_login_and_register);
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_desc = rootView.findViewById(R.id.tv_desc);
        tv_my_diamond_count = rootView.findViewById(R.id.tv_my_diamond_count);
        tv_withdraw_diamond_count = rootView.findViewById(R.id.tv_withdraw_diamond_count);

        iv_avatar.setOnClickListener(this);
        tv_name.setOnClickListener(this);
        ll_login_and_register.setOnClickListener(this);
        rootView.findViewById(R.id.iv_setting).setOnClickListener(this);
        rootView.findViewById(R.id.iv_msg_center).setOnClickListener(this);
//        rootView.findViewById(R.id.tv_login).setOnClickListener(this);
//        rootView.findViewById(R.id.tv_register).setOnClickListener(this);
        rootView.findViewById(R.id.tv_charge).setOnClickListener(this);
        rootView.findViewById(R.id.tv_withdraw).setOnClickListener(this);
        rootView.findViewById(R.id.ll_follow).setOnClickListener(this);
        rootView.findViewById(R.id.ll_reserve).setOnClickListener(this);
        rootView.findViewById(R.id.ll_space).setOnClickListener(this);
        rootView.findViewById(R.id.ll_msg).setOnClickListener(this);
        rootView.findViewById(R.id.cl_level).setOnClickListener(this);
        rootView.findViewById(R.id.cl_activity).setOnClickListener(this);
        rootView.findViewById(R.id.cl_speaker).setOnClickListener(this);
        rootView.findViewById(R.id.cl_customer).setOnClickListener(this);
        rootView.findViewById(R.id.cl_problem).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
            ll_login_and_register.setVisibility(View.GONE);
            tv_name.setVisibility(View.VISIBLE);
            tv_desc.setVisibility(View.GONE);
        }else {
            ll_login_and_register.setVisibility(View.VISIBLE);
            tv_name.setVisibility(View.GONE);
            tv_desc.setVisibility(View.VISIBLE);
        }
        UserBean userBean = CommonAppConfig.getInstance().getUserBean();
        if (userBean != null) {
            GlideUtil.loadUserImageDefault(getContext(), userBean.getAvatar(), iv_avatar);
            if (!TextUtils.isEmpty(userBean.getUser_nickname())) {
                tv_name.setText(userBean.getUser_nickname());
            }else {
                tv_name.setText("");
            }
            if (!TextUtils.isEmpty(userBean.getBalance())) {
                tv_my_diamond_count.setText(userBean.getBalance());
            }else {
                tv_my_diamond_count.setText("");
            }
            if (!TextUtils.isEmpty(userBean.getWithdrawal_balance())) {
                tv_withdraw_diamond_count.setText(userBean.getWithdrawal_balance());
            }else {
                tv_withdraw_diamond_count.setText("");
            }
        }
    }

    public void updateUserInfo() {
        mvpPresenter.getUserInfo();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getDataSuccess(UserBean userBean) {
        if (userBean != null) {
            CommonAppConfig.getInstance().saveUserInfo(JSONObject.toJSONString(userBean));
            GlideUtil.loadUserImageDefault(getContext(), userBean.getAvatar(), iv_avatar);
            if (!TextUtils.isEmpty(userBean.getUser_nickname())) {
                tv_name.setText(userBean.getUser_nickname());
            }else {
                tv_name.setText("");
            }
            if (!TextUtils.isEmpty(userBean.getBalance())) {
                tv_my_diamond_count.setText(userBean.getBalance());
            }else {
                tv_my_diamond_count.setText("");
            }
        }
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_avatar:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getString(R.string.please_login));
                }else {
                    DefaultAvatarActivity.forward(getActivity());
                }
                break;
            case R.id.tv_name:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                UserInfoActivity.forward(getActivity());
                break;
            case R.id.iv_setting:
                SettingActivity.forward(getActivity());
                break;
            case R.id.ll_login_and_register:
                ToastUtil.show(getString(R.string.please_login));
                break;
            case R.id.tv_charge:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                ChargeActivity.forward(getContext());
                break;
            case R.id.tv_withdraw:
                if (CommonAppConfig.getInstance().getUserBean() != null) {
                    if (CommonAppConfig.getInstance().getUserBean().getIs_defray_pass() == 1) {
                        WithdrawActivity.forward(getContext());
                    }else {
                        PayPwdSettingActivity.forward(getContext(), PayPwdSettingActivity.ENTER_BY_WITHDRAW);
                    }
                }
                break;
            case R.id.ll_follow:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                MyFollowActivity.forward(getContext());
                break;
            case R.id.ll_reserve:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                MyReserveActivity.forward(getContext());
                break;
            case R.id.ll_space:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                MySpaceActivity.forward(getContext(), Integer.valueOf(CommonAppConfig.getInstance().getUid()));
                break;
            case R.id.iv_msg_center:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                MyMessageActivity.forward(getContext());
                break;
            case R.id.ll_msg:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                MyMessageActivity.forward(getContext());
                break;
            case R.id.cl_level:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                MyLevelActivity.forward(getContext());
                break;
            case R.id.cl_activity:
                ActivityCenterActivity.forward(getContext());
                break;
            case R.id.cl_speaker:
                if (TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
                    ToastUtil.show(getContext().getString(R.string.please_login));
                    return;
                }
                SpeakerHistoryActivity.forward(getContext());
                break;
            case R.id.cl_problem:
                CommonProblemActivity.forward(getContext());
                break;
            case R.id.cl_customer:
                CommonUtil.forwardCustomer(getContext());
                break;
        }
    }
}
