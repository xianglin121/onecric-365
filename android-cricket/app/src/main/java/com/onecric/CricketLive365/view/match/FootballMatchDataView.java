package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.DataInfoBean;
import com.onecric.CricketLive365.model.DataSeasonBean;
import com.onecric.CricketLive365.model.FootballDataStatusBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface FootballMatchDataView extends BaseView<JsonBean> {
    void getDetailSuccess(List<DataSeasonBean> seasonList, DataInfoBean info, FootballDataStatusBean statusBean);
}
