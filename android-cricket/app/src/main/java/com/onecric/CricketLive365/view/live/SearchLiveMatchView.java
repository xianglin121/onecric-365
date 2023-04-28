package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.MatchListBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface SearchLiveMatchView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<MatchListBean> list);

    void doReserveSuccess(int position);
}
