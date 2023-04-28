package com.onecric.CricketLive365.presenter.match;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.model.FootballDataBestRightBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.match.FootballDataBestView;

import java.util.List;

public class FootballDataBestPresenter extends BasePresenter<FootballDataBestView> {
    public FootballDataBestPresenter(FootballDataBestView view) {
        attachView(view);
    }

    public void getTeamList(int seasonId, int type) {
        addSubscription(apiStores.getFootballMatchDataBestTeam(seasonId, type),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<FootballDataBestRightBean> list = JSONObject.parseArray(data, FootballDataBestRightBean.class);
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

    public void getMemberList(int seasonId, int type) {
        addSubscription(apiStores.getFootballMatchDataBestMember(seasonId, type),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<FootballDataBestRightBean> list = JSONObject.parseArray(data, FootballDataBestRightBean.class);
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
