package com.onecric.CricketLive365.presenter.live;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.live.SearchCommunityView;

import java.util.List;


public class SearchCommunityPresenter extends BasePresenter<SearchCommunityView> {
    public SearchCommunityPresenter(SearchCommunityView view) {
        attachView(view);
    }

    public void getData(boolean isRefresh, int page, String content) {
        addSubscription(apiStores.searchCommunity(CommonAppConfig.getInstance().getToken(), content),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<CommunityBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), CommunityBean.class);
                        mvpView.getList(isRefresh, list);
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

    public void doCommunityLike(int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        addSubscription(apiStores.doCommunityLike(CommonAppConfig.getInstance().getToken(), getRequestBody(jsonObject)),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {

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
