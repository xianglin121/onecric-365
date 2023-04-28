package com.onecric.CricketLive365.presenter.cricket;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.CricketMatchDataBean;
import com.onecric.CricketLive365.model.LinkDataBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.util.ToastUtil;
import com.onecric.CricketLive365.view.CricketLiveView;

import java.util.List;
import java.util.TimeZone;

public class CricketGuidePresenter extends BasePresenter<CricketLiveView> {
    public CricketGuidePresenter(CricketLiveView view) {
        attachView(view);
    }

    public void getCricketMatchList(String data) {
        if (TextUtils.isEmpty(data)) {
            ToastUtil.show("No data yet");
            return;
        }

        addSubscription(apiStores.getCricketNewMatchList(CommonAppConfig.getInstance().getToken(), TimeZone.getDefault().getID(), data), new ApiCallback() {
            @Override
            public void onSuccess(String data, String msg) {
                List<CricketMatchDataBean> bean;
                try {
                    bean = JSONObject.parseArray(data, CricketMatchDataBean.class);
                    mvpView.getDataSuccess(bean);
                } catch (Exception e) {
//                    bean = null;
                }
//                mvpView.getDataSuccess(bean);
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

    public void getInfo(String data) {
        addSubscription(apiStores.getInfo(CommonAppConfig.getInstance().getToken(), data), new ApiCallback() {
            @Override
            public void onSuccess(String data, String msg) {
                LinkDataBean bean;
                try {
                    bean = JSONObject.parseObject(data, LinkDataBean.class);
                    mvpView.getInfoSuccess(bean);
                } catch (Exception e) {
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
