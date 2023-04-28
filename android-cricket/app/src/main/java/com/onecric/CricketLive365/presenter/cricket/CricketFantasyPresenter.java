package com.onecric.CricketLive365.presenter.cricket;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.model.CricketFantasyBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.cricket.CricketFantasyView;

public class CricketFantasyPresenter extends BasePresenter<CricketFantasyView> {
    public CricketFantasyPresenter(CricketFantasyView view) {
        attachView(view);
    }

    public void getData(int matchId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("match_id", matchId);
        addSubscription(apiStores.getCricketDetailFantasy(getRequestBody(jsonObject)), new ApiCallback() {
            @Override
            public void onSuccess(String data, String msg) {
                mvpView.getDataSuccess(JSONObject.parseObject(data, CricketFantasyBean.class));
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
