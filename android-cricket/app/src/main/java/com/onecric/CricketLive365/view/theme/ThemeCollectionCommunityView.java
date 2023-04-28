package com.onecric.CricketLive365.view.theme;

import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface ThemeCollectionCommunityView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<CommunityBean> list);
}
