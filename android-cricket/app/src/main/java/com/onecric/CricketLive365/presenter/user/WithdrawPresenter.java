package com.onecric.CricketLive365.presenter.user;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.AccountBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.user.WithdrawView;

public class WithdrawPresenter extends BasePresenter<WithdrawView> {
    public WithdrawPresenter(WithdrawView view) {
        attachView(view);
    }

    public void getBankInfo() {
        addSubscription(apiStores.getBankInfo(CommonAppConfig.getInstance().getToken()),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.getDataSuccess(JSONObject.parseObject(data, AccountBean.class));
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

    public void getBalanceInfo() {
        addSubscription(apiStores.getWithdrawalBalance(CommonAppConfig.getInstance().getToken()),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        JSONObject jsonObject = JSONObject.parseObject(data);
                        mvpView.getBalanceSuccess(jsonObject.getIntValue("is_defray_pass"), jsonObject.getString("withdrawal_balance"), jsonObject.getDoubleValue("withdrawal_fee"));
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

    public void getCode(String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", phone);
        jsonObject.put("type", 8);
        addSubscription(apiStores.getCode(getRequestBody(jsonObject)),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.getCodeSuccess();
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

    public void applyWithdrawal(int amount, String code, String password) {
        addSubscription(apiStores.applyWithdrawal(CommonAppConfig.getInstance().getToken(), amount, code, "0", password),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.applyWithdrawSuccess();
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
