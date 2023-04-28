package com.onecric.CricketLive365.presenter.match;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.model.DataScheduleBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.match.BasketballDataScheduleView;

import java.util.List;

public class BasketballDataSchedulePresenter extends BasePresenter<BasketballDataScheduleView> {
    public BasketballDataSchedulePresenter(BasketballDataScheduleView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, int id, int page, int seasonId, int typeId) {
        addSubscription(apiStores.getBasketballMatchDataDetail(id, page, seasonId, typeId),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<DataScheduleBean> list = JSONObject.parseArray(JSONObject.parseObject(JSONObject.parseObject(data).getString("list")).getString("data"), DataScheduleBean.class);
                        mvpView.getDataSuccess(isRefresh, list);
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
