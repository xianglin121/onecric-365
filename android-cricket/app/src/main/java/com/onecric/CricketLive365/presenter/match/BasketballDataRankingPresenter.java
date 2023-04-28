package com.onecric.CricketLive365.presenter.match;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.model.DataRankingBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.match.BasketballDataRankingView;

import java.util.List;

public class BasketballDataRankingPresenter extends BasePresenter<BasketballDataRankingView> {
    public BasketballDataRankingPresenter(BasketballDataRankingView view) {
        attachView(view);
    }

    public void getList(int seasonId) {
        addSubscription(apiStores.getBasketballMatchDataRanking(seasonId),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<DataRankingBean> list = JSONObject.parseArray(data, DataRankingBean.class);
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
