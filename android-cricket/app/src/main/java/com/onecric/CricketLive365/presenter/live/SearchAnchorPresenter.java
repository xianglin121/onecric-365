package com.onecric.CricketLive365.presenter.live;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.live.SearchAnchorView;

import java.util.List;


public class SearchAnchorPresenter extends BasePresenter<SearchAnchorView> {
    public SearchAnchorPresenter(SearchAnchorView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, String content, int page) {
        addSubscription(apiStores.searchAnchor(CommonAppConfig.getInstance().getToken(), content),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<UserBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), UserBean.class);
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

    public void doFollow(int id) {
        addSubscription(apiStores.doFollow(CommonAppConfig.getInstance().getToken(), id),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        mvpView.doFollowSuccess(id);
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
