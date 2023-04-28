package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.HeadlineBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface MySpaceHeadlineOneView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<HeadlineBean> list);

    void doDeleteSuccess(int position);
}
