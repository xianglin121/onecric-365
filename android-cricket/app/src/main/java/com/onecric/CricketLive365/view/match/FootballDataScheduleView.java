package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.DataScheduleBean;
import com.onecric.CricketLive365.model.DataStageBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface FootballDataScheduleView extends BaseView<JsonBean> {
    void getStageDataSuccess(List<DataStageBean> list);

    void getDataSuccess(List<DataScheduleBean> list);
}
