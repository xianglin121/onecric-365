package com.onecric.CricketLive365.presenter.user;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.MessageBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.user.MyMessageView;

import java.util.List;

public class MyMessagePresenter extends BasePresenter<MyMessageView> {
    public MyMessagePresenter(MyMessageView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, int page) {
        addSubscription(apiStores.getMessageList(CommonAppConfig.getInstance().getToken(), page),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<MessageBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), MessageBean.class);
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
}
