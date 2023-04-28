package com.onecric.CricketLive365.presenter.login;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.activity.MainActivity;
import com.onecric.CricketLive365.model.ConfigurationBean;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.util.SpUtil;
import com.onecric.CricketLive365.util.ToastUtil;
import com.onecric.CricketLive365.view.login.MainView;

public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter(MainView view) {
        attachView(view);
    }

    public void getConfiguration(String versionNumber) {
        addSubscription(apiStores.getDefaultConfiguration(versionNumber),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
//                        CommonAppConfig.getInstance().saveConfig(JSONObject.parseObject(data, ConfigurationBean.class));
                        mvpView.getConfigSuccess(JSONObject.parseObject(data, ConfigurationBean.class));
                    }

                    @Override
                    public void onFailure(String msg) {
                    }

                    @Override
                    public void onError(String msg) {
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    public void getVisitorUserSig() {
        addSubscription(apiStores.getVisitorUserSig(),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.getVisitorUserSigSuccess(JSONObject.parseObject(data).getString("id"), JSONObject.parseObject(data).getString("sig"));
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError(String msg) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    public void getUserInfo() {
        addSubscription(apiStores.getUserInfo(CommonAppConfig.getInstance().getToken(), 0),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        if (!TextUtils.isEmpty(data)) {
                            mvpView.getDataSuccess(JSONObject.parseObject(data, UserBean.class));
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError(String msg) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    public void signOut(Context context) {
        addSubscription(apiStores.signOut(CommonAppConfig.getInstance().getToken()),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        CommonAppConfig.getInstance().clearLoginInfo();
                        SpUtil.getInstance().setBooleanValue(SpUtil.VIDEO_OVERTIME, false);
                        MainActivity.loginForward(context);
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.show(msg);
                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtil.show(msg);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }
}
