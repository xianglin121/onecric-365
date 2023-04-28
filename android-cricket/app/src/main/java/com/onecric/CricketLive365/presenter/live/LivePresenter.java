package com.onecric.CricketLive365.presenter.live;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.LiveBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.live.LiveView;

import java.util.ArrayList;
import java.util.List;


public class LivePresenter extends BasePresenter<LiveView> {
    public LivePresenter(LiveView view) {
        attachView(view);
    }

    public void getOtherList(int type, int page) {
        addSubscription(apiStores.getLivingList(CommonAppConfig.getInstance().getToken(), page, type, 0),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        if (!TextUtils.isEmpty(data)) {
                            List<LiveBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), LiveBean.class);
                            mvpView.getOtherDataSuccess(list);
                        }else {
                            mvpView.getOtherDataSuccess(new ArrayList<>());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getOtherDataSuccess(new ArrayList<>());
                    }

                    @Override
                    public void onError(String msg) {
                        mvpView.getOtherDataSuccess(new ArrayList<>());
                    }

                    @Override
                    public void onFinish() {
                    }
                });
    }
}
