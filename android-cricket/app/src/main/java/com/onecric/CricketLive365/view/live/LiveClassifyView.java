package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.LiveClassifyBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface LiveClassifyView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<LiveClassifyBean> list);

    void doReserveSuccess(int position);
}
