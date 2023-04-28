package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.BannerBean;
import com.onecric.CricketLive365.model.HistoryLiveBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.LiveBean;
import com.onecric.CricketLive365.model.LiveMatchBean;
import com.onecric.CricketLive365.model.LiveMatchListBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface LiveRecommendView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<LiveBean> list);

    void getDataHistorySuccess(boolean isRefresh, List<HistoryLiveBean> list);

    void getDataSuccess(List<LiveMatchBean> list);

    void getDataSuccess(List<LiveBean> freeList, List<LiveBean> todayList, List<LiveBean> historyList);

    void doReserveSuccess(int position);

    void getBannerSuccess(List<BannerBean> list, int position);

    void getMatchSuccess(List<LiveMatchListBean.MatchItemBean> today,List<LiveMatchListBean.MatchItemBean> upcoming);


}
