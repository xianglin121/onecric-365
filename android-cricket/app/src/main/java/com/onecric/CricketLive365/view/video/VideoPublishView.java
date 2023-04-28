package com.onecric.CricketLive365.view.video;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

public interface VideoPublishView extends BaseView<JsonBean> {
    void getTokenSuccess(String token);
}
