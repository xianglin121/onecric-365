package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.BasketballMatchBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface BasketballMatchScheduleView extends BaseView<List<BasketballMatchBean>> {
    void getDataSuccess(boolean isRefresh, List<BasketballMatchBean> list);
}
