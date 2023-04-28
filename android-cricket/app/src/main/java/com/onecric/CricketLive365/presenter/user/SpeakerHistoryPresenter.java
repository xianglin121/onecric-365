package com.onecric.CricketLive365.presenter.user;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.SpeakerBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.user.SpeakerHistoryView;

import java.util.ArrayList;
import java.util.List;

public class SpeakerHistoryPresenter extends BasePresenter<SpeakerHistoryView> {
    public SpeakerHistoryPresenter(SpeakerHistoryView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, int page) {
        addSubscription(apiStores.getSpeakerHistoryList(CommonAppConfig.getInstance().getToken(), page),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        if (!TextUtils.isEmpty(data)) {
                            List<SpeakerBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), SpeakerBean.class);
                            mvpView.getDataSuccess(isRefresh, list);
                        }else {
                            mvpView.getDataSuccess(isRefresh, new ArrayList<>());
                        }
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
