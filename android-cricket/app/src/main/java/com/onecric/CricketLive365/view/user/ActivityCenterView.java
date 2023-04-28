package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.ActivityBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface ActivityCenterView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<ActivityBean> list);
}
