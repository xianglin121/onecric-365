package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.Channel;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface HotMatchView extends BaseView<JsonBean> {
    void getDataSuccess(List<Channel> hotMatch, List<Channel> football, List<Channel> basketball);
}
