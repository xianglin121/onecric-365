package com.onecric.CricketLive365.presenter.login;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.login.RegisterView;

public class RegisterPresenter extends BasePresenter<RegisterView> {
    public RegisterPresenter(RegisterView view) {
        attachView(view);
    }

    public void getCode(String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", phone);
        jsonObject.put("type", 1);
        addSubscription(apiStores.getCode(getRequestBody(jsonObject)),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.getDataSuccess(null);
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

    public void register(String phone, String code, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", phone);
        jsonObject.put("code", code);
        jsonObject.put("password", password);
        jsonObject.put("channel", 2);
        addSubscription(apiStores.register(getRequestBody(jsonObject)),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.registerSuccess(msg);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.registerFail(msg);
                    }

                    @Override
                    public void onError(String msg) {
                        mvpView.registerFail(msg);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }
}
