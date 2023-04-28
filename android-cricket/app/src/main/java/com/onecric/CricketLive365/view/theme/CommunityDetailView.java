package com.onecric.CricketLive365.view.theme;

import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.model.ThemeClassifyBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface CommunityDetailView extends BaseView<ThemeClassifyBean> {
    void getDataSuccess(boolean isRefresh, List<CommunityBean> list);
}
