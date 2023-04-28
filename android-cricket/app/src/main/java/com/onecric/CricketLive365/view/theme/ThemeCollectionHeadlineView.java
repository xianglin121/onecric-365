package com.onecric.CricketLive365.view.theme;

import com.onecric.CricketLive365.model.HeadlineBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface ThemeCollectionHeadlineView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<HeadlineBean> list);
}
