package com.onecric.CricketLive365.presenter.theme;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.ThemeClassifyBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.theme.ThemeHeadlineView;

import java.util.List;


public class ThemeHeadlinePresenter extends BasePresenter<ThemeHeadlineView> {
    public ThemeHeadlinePresenter(ThemeHeadlineView view) {
        attachView(view);
    }

    public void getClassify() {
        addSubscription(apiStores.getHeadlineClassify(CommonAppConfig.getInstance().getToken()),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<ThemeClassifyBean> list = JSONObject.parseArray(data, ThemeClassifyBean.class);
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
