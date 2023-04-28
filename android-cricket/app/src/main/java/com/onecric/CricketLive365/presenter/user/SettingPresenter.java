package com.onecric.CricketLive365.presenter.user;

import android.content.Context;

import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.activity.MainActivity;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.util.SpUtil;
import com.onecric.CricketLive365.util.ToastUtil;
import com.onecric.CricketLive365.view.user.SettingView;

public class SettingPresenter extends BasePresenter<SettingView> {
    public SettingPresenter(SettingView view) {
        attachView(view);
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
