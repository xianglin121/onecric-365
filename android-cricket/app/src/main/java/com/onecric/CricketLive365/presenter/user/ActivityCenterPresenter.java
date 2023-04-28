package com.onecric.CricketLive365.presenter.user;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.ActivityBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.user.ActivityCenterView;

import java.util.List;

public class ActivityCenterPresenter extends BasePresenter<ActivityCenterView> {
    public ActivityCenterPresenter(ActivityCenterView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, int page) {
        addSubscription(apiStores.getActivityList(CommonAppConfig.getInstance().getToken(), page),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<ActivityBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), ActivityBean.class);
                        mvpView.getDataSuccess(isRefresh, list);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onError(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }
}
