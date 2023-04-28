package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.LiveBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface LiveView extends BaseView<JsonBean> {
    void getOtherDataSuccess(List<LiveBean> list);
}
