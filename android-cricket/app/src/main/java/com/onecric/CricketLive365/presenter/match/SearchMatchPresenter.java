package com.onecric.CricketLive365.presenter.match;

import com.alibaba.fastjson.JSONObject;
import com.onecric.CricketLive365.model.Channel;
import com.onecric.CricketLive365.model.MatchListBean;
import com.onecric.CricketLive365.model.MatchSearchBean;
import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.retrofit.ApiCallback;
import com.onecric.CricketLive365.view.match.SearchMatchView;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class SearchMatchPresenter extends BasePresenter<SearchMatchView> {
    public SearchMatchPresenter(SearchMatchView view) {
        attachView(view);
    }

    public void searchMatch(String content,int type,int page,boolean isMore) {
        addSubscription(apiStores.searchMatchNew(TimeZone.getDefault().getID(),content,type==1?"match":"tour",page),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<MatchSearchBean> bean;
                        try{
                            bean = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"),MatchSearchBean.class);
                        }catch (Exception e){
                            bean = new ArrayList<>();
                        }
                        if(isMore){
                            mvpView.getMoreData(type,bean);
                        }else{
                            mvpView.getDataSuccess(bean,type);
                            if(type == 1){
                                searchMatch(content,2,page,false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        if(!isMore){
                            mvpView.getDataFail(msg,type);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if(!isMore){
                            mvpView.getDataFail(msg,type);
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    public void searchMatchOld(String content) {
        addSubscription(apiStores.searchMatch(content),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<MatchListBean> list = JSONObject.parseArray(JSONObject.parseObject(data).getString("data"), MatchListBean.class);
//                        mvpView.getDataSuccess(list);
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

    public void getList() {
        addSubscription(apiStores.getHotMatchClassify(),
                new ApiCallback() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<Channel> hotMatch = JSONObject.parseArray(JSONObject.parseObject(data).getString("hot_match"), Channel.class);
//                        mvpView.getHotMatchClassifySuccess(hotMatch);
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
