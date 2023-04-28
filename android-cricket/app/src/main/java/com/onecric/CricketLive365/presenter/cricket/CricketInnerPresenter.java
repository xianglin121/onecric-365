package com.onecric.CricketLive365.presenter.cricket;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.model.CricketPointsBean;
import com.onecric.CricketLive365.model.CricketStatsBean;
import com.onecric.CricketLive365.model.CricketTeamBean;
import com.onecric.CricketLive365.model.UpdatesBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.cricket.CricketInnerView;

import java.util.List;

public class CricketInnerPresenter extends BasePresenter<CricketInnerView> {
    public CricketInnerPresenter(CricketInnerView view) {
        attachView(view);
    }

    public void getTeamList(int tournamentId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tournament_id", tournamentId);
        addSubscription(apiStores.getTournamentTeamList(getRequestBody(jsonObject)), new ApiCallback() {
            @Override
            public void onSuccess(String data, String msg) {
                List<CricketTeamBean> list = JSONObject.parseArray(data, CricketTeamBean.class);
                mvpView.getTeamDataSuccess(list);
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

    public void getPointsList(int tournamentId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tournament_id", tournamentId);
        addSubscription(apiStores.getTournamentPointsList(getRequestBody(jsonObject)), new ApiCallback() {
            @Override
            public void onSuccess(String data, String msg) {
                List<CricketPointsBean> list = JSONObject.parseArray(data, CricketPointsBean.class);
                mvpView.getPointsDataSuccess(list);
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

    public void getUpdatesDetail(int matchId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", matchId);
        jsonObject.put("type", 1);
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

    public void getStats(int matchId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", matchId);
        addSubscription(apiStores.getTournamentStats(getRequestBody(jsonObject)), new ApiCallback() {
            @Override
            public void onSuccess(String data, String msg) {
                mvpView.getStatsDataSuccess(JSONObject.parseObject(data, CricketStatsBean.class));
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
