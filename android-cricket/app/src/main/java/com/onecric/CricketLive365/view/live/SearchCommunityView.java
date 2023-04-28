package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface SearchCommunityView extends BaseView<JsonBean> {
    void getList(boolean isRefresh, List<CommunityBean> list);
}
