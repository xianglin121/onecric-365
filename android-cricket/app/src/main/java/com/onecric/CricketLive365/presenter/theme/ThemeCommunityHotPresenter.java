package com.onecric.CricketLive365.presenter.theme;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.model.ThemeClassifyBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.theme.ThemeCommunityHotView;

import java.util.List;


public class ThemeCommunityHotPresenter extends BasePresenter<ThemeCommunityHotView> {
    public ThemeCommunityHotPresenter(ThemeCommunityHotView view) {
        attachView(view);
    }

    public void getData(boolean isRefresh, int page) {
        addSubscription(apiStores.getHotCommunity(CommonAppConfig.getInstance().getToken(), page),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<ThemeClassifyBean> classifyList = JSONObject.parseArray(JSONObject.parseObject(data).getString("classification"), ThemeClassifyBean.class);
                        CommunityBean refining = JSONObject.parseObject(JSONObject.parseObject(data).getString("refining"), CommunityBean.class);
                        List<CommunityBean> list = JSONObject.parseArray(JSONObject.parseObject(JSONObject.parseObject(data).getString("list")).getString("data"), CommunityBean.class);
                        mvpView.getDataSuccess(classifyList, refining);
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
