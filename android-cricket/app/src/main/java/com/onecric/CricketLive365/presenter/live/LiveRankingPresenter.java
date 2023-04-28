package com.onecric.CricketLive365.presenter.live;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.RankingBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.live.LiveRankingView;

import java.util.List;


public class LiveRankingPresenter extends BasePresenter<LiveRankingView> {
    public LiveRankingPresenter(LiveRankingView view) {
        attachView(view);
    }

    public void getList(int anchorId, int type) {
        addSubscription(apiStores.getLiveRankingList(CommonAppConfig.getInstance().getToken(), anchorId, type),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<RankingBean> list = JSONObject.parseArray(data, RankingBean.class);
                        mvpView.getDataSuccess(list);
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
