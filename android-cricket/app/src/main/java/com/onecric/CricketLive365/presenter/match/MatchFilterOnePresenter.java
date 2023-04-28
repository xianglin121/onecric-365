package com.onecric.CricketLive365.presenter.match;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.model.FilterBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.match.MatchFilterOneView;

import java.util.List;

public class MatchFilterOnePresenter extends BasePresenter<MatchFilterOneView> {
    public MatchFilterOnePresenter(MatchFilterOneView view) {
        attachView(view);
    }

    public void getList(int filter, int type) {
        addSubscription(apiStores.getMatchFilterList(filter, type),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        int competitionCount = JSONObject.parseObject(data).getIntValue("count_competition");
                        int selectCompetitionCount = JSONObject.parseObject(data).getIntValue("count_competition_check");
                        List<FilterBean> competitionList = JSONObject.parseArray(JSONObject.parseObject(data).getString("competition"), FilterBean.class);
                        List<FilterBean> countryList = JSONObject.parseArray(JSONObject.parseObject(data).getString("country"), FilterBean.class);
                        mvpView.getDataSuccess(competitionList, countryList, competitionCount, selectCompetitionCount);
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
