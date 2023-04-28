package com.onecric.CricketLive365.presenter.user;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.ReserveMatchBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.user.MyReserveView;

import java.util.List;

public class MyReservePresenter extends BasePresenter<MyReserveView> {
    public MyReservePresenter(MyReserveView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, int page) {
        addSubscription(apiStores.getReserveList(CommonAppConfig.getInstance().getToken(), page),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<ReserveMatchBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), ReserveMatchBean.class);
                        mvpView.getDataSuccess(isRefresh, list);
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

    public void doReserve(int position, int matchId, int type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("match_id", matchId);
        jsonObject.put("type", type);
        addSubscription(apiStores.reserveMatchTwo(CommonAppConfig.getInstance().getToken(), getRequestBody(jsonObject)),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.doReserveSuccess(position);
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
