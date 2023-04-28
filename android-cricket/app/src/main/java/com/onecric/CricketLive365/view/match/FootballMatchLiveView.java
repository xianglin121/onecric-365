package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.LiveAnchorBean;
import com.onecric.CricketLive365.model.LiveBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface FootballMatchLiveView extends BaseView<JsonBean> {
    void getDataSuccess(List<LiveAnchorBean> list);

    void getDataSuccess(boolean isRefresh, List<LiveBean> list);
}
