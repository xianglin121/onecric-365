package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.DataInfoBean;
import com.onecric.CricketLive365.model.DataMatchTypeBean;
import com.onecric.CricketLive365.model.DataSeasonBean;
import com.onecric.CricketLive365.model.DataStatusBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface BasketballMatchDataView extends BaseView<JsonBean> {
    void getDetailSuccess(List<DataSeasonBean> seasonList, DataInfoBean info, DataStatusBean statusBean, List<DataMatchTypeBean> matchTypeList);
}
