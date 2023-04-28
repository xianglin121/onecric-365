package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

public interface FootballMatchView extends BaseView<JsonBean> {
    void getCollectCountSuccess(int count);
}
