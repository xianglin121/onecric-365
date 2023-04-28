package com.onecric.CricketLive365.presenter.theme;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.theme.ThemeCollectionCommunityView;

import java.util.List;


public class ThemeCollectionCommunityPresenter extends BasePresenter<ThemeCollectionCommunityView> {
    public ThemeCollectionCommunityPresenter(ThemeCollectionCommunityView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, int page, int uid) {
        addSubscription(apiStores.getCommunityCollectionList(CommonAppConfig.getInstance().getToken(), page, uid, 1),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        if (!TextUtils.isEmpty(data)) {
                            List<CommunityBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), CommunityBean.class);
                            mvpView.getDataSuccess(isRefresh, list);
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

    public void doCommunityCollect(int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        addSubscription(apiStores.doCommunityCollect(CommonAppConfig.getInstance().getToken(), getRequestBody(jsonObject)),
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
