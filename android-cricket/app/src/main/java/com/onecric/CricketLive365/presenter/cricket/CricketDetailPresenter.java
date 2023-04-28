package com.onecric.CricketLive365.presenter.cricket;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.CricketMatchBean;
import com.onecric.CricketLive365.model.UpdatesBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.cricket.CricketDetailView;

import java.util.TimeZone;

public class CricketDetailPresenter extends BasePresenter<CricketDetailView> {
    public CricketDetailPresenter(CricketDetailView view) {
        attachView(view);
    }

    public void getDetail(int matchId) {
        JSONObject jsonObject = new JSONObject();
        TimeZone timeZone = TimeZone.getDefault();
        jsonObject.put("timezone", timeZone.getID());
        jsonObject.put("match_id", matchId);
        addSubscription(apiStores.getCricketDetail(CommonAppConfig.getInstance().getToken(),getRequestBody(jsonObject)), new ApiCallback() {
            @Override
            public void onSuccess(String data, String msg) {
                mvpView.getDataSuccess(JSONObject.parseObject(data, CricketMatchBean.class));
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

    public void getUpdatesDetail(int matchId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", matchId);
        jsonObject.put("type", 0);
        addSubscription(apiStores.getCricketDetailUpdates(getRequestBody(jsonObject)), new ApiCallback() {
            @Override
            public void onSuccess(String data, String msg) {
                mvpView.getUpdatesDataSuccess(JSONObject.parseArray(data, UpdatesBean.class));
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
}
