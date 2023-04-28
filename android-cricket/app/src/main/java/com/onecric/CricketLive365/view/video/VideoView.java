package com.onecric.CricketLive365.view.video;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.ShortVideoBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface VideoView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<ShortVideoBean> list);
}