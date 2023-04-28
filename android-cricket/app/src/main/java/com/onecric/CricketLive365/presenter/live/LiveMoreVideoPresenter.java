package com.onecric.CricketLive365.presenter.live;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.LiveBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.live.LiveMoreVideoView;

import java.util.ArrayList;
import java.util.List;


public class LiveMoreVideoPresenter extends BasePresenter<LiveMoreVideoView> {
    public LiveMoreVideoPresenter(LiveMoreVideoView view) {
        attachView(view);
    }

    public void getList() {
        addSubscription(apiStores.getLivingList(CommonAppConfig.getInstance().getToken(), 1, -1, 0),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        if (!TextUtils.isEmpty(data)) {
                            List<LiveBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), LiveBean.class);
                            mvpView.getDataSuccess(list);
                        }else {
                            mvpView.getDataSuccess(new ArrayList<>());
                        }
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
