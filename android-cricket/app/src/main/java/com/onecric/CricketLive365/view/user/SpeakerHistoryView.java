package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.SpeakerBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface SpeakerHistoryView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<SpeakerBean> list);
}
