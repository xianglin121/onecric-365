package com.onecric.CricketLive365.presenter.match;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.CommonAppConfig;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiClient;
import com.onecric.CricketLive365.retrofit.ApiStores;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SubscribePresenter extends BasePresenter {

    public void doSubscribe(String mid, String type, DisposableObserver observer) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mid", mid);
        jsonObject.put("type", type);
        ApiClient.retrofit().create(ApiStores.class)
                .doSubscribe(CommonAppConfig.getInstance().getToken(), getRequestBody(jsonObject))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }

    public void getSubscribeType(int mid, DisposableObserver observer) {
        ApiClient.retrofit().create(ApiStores.class)
                .getSubscribeType(CommonAppConfig.getInstance().getToken(), mid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }

}
