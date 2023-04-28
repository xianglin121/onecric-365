package com.onecric.CricketLive365.view.theme;

import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.ThemeClassifyBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface ThemeCommunityHotView extends BaseView<JsonBean> {
    void getDataSuccess(List<ThemeClassifyBean> classifyList, CommunityBean refining);

    void getList(boolean isRefresh, List<CommunityBean> list);
}
