package com.onecric.CricketLive365.presenter.user;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.user.MyFansView;

import java.util.List;

public class FansPresenter extends BasePresenter<MyFansView> {
    public FansPresenter(MyFansView view) {
        attachView(view);
    }

    public void getList(boolean isRefresh, int page,int uid) {
        addSubscription(apiStores.getFansList(CommonAppConfig.getInstance().getToken(), page, uid),
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
