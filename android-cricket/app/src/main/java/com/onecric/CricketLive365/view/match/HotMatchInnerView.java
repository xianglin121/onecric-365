package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.MatchListBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface HotMatchInnerView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<MatchListBean> list);
}
