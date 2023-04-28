package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface MySpaceCommunityOneView extends BaseView<JsonBean> {
    void getList(boolean isRefresh, List<CommunityBean> list);
}
