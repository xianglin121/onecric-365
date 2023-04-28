package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

public interface MyTaskView extends BaseView<JsonBean> {
    void getDataSuccess(String url, String img);
}
