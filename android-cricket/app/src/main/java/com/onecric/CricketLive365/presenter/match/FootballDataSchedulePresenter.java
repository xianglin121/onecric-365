package com.onecric.CricketLive365.presenter.match;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.model.DataScheduleBean;
import com.onecric.CricketLive365.model.DataStageBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.match.FootballDataScheduleView;

import java.util.List;

public class FootballDataSchedulePresenter extends BasePresenter<FootballDataScheduleView> {
    public FootballDataSchedulePresenter(FootballDataScheduleView view) {
        attachView(view);
    }

    public void getStageList(int id) {
        addSubscription(apiStores.getFootballMatchDataStage(id),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<DataStageBean> list = JSONObject.parseArray(data, DataStageBean.class);
                        mvpView.getStageDataSuccess(list);
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

    public void getList(int id, int seasonId, int stageId, int sonId) {
        addSubscription(apiStores.getFootballMatchDataDetail(id, seasonId, stageId, sonId),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<DataScheduleBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("list"), DataScheduleBean.class);
                        mvpView.getDataSuccess(list);
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
