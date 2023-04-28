package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.FootballMatchBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface FootballMatchScheduleView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<FootballMatchBean> list);
}
