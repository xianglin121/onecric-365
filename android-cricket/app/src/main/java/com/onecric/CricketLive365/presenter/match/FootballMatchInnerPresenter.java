package com.onecric.CricketLive365.presenter.match;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.event.UpdateFootballCollectCountEvent;
import com.onecric.CricketLive365.model.FootballMatchBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.match.FootballMatchInnerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class FootballMatchInnerPresenter extends BasePresenter<FootballMatchInnerView> {
    public FootballMatchInnerPresenter(FootballMatchInnerView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, int type, int page, String id) {
        addSubscription(apiStores.getFootballList(CommonAppConfig.getInstance().getToken(), type, null, page, type==4?null:id),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        if (!TextUtils.isEmpty(data)) {
                            List<FootballMatchBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), FootballMatchBean.class);
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

    public void doCollect(int type, int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        addSubscription(apiStores.doMatchCollect(CommonAppConfig.getInstance().getToken(), getRequestBody(jsonObject)),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        EventBus.getDefault().post(new UpdateFootballCollectCountEvent());
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
