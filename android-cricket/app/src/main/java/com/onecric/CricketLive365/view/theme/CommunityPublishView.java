package com.onecric.CricketLive365.view.theme;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

public interface CommunityPublishView extends BaseView<JsonBean> {
    void getTokenSuccess(String token);
}
