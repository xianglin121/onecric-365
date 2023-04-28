package com.onecric.CricketLive365.presenter.user;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.CoinBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.user.ChargeView;

public class ChargePresenter extends BasePresenter<ChargeView> {
    public ChargePresenter(ChargeView view) {
        attachView(view);
    }

    public void getList() {
        addSubscription(apiStores.getCoinList(CommonAppConfig.getInstance().getToken()),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.getDataSuccess(JSONObject.parseArray(data, CoinBean.class));
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

    public void doPay(int id, int type) {
        addSubscription(apiStores.getPayUrl(CommonAppConfig.getInstance().getToken(), id, type),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.paySuccess(JSONObject.parseObject(data).getString("url"));
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.payFail(msg);
                    }

                    @Override
                    public void onError(String msg) {
                        mvpView.payFail(msg);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }
}
