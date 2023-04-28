package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.HistoryLiveBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.LiveBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface LiveMoreView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<LiveBean> list);
    void getDataHistorySuccess(boolean isRefresh, List<HistoryLiveBean> list);
}
