package com.onecric.CricketLive365.presenter.match;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.HistoryIndexDetailBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.match.HistoryIndexDetailView;

public class HistoryIndexDetailPresenter extends BasePresenter<HistoryIndexDetailView> {
    public HistoryIndexDetailPresenter(HistoryIndexDetailView view) {
        attachView(view);
    }

    public void getDetail(int id, int companyId) {
        addSubscription(apiStores.getBasketballHistoryIndexDetail(CommonAppConfig.getInstance().getToken(), id, companyId),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.getDataSuccess(JSONObject.parseObject(data, HistoryIndexDetailBean.class));
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
