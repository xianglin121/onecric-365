package com.onecric.CricketLive365.view.cricket;

import com.onecric.CricketLive365.model.CricketPointsBean;
import com.onecric.CricketLive365.model.CricketStatsBean;
import com.onecric.CricketLive365.model.CricketTeamBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.UpdatesBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/26
 */
public interface CricketInnerView extends BaseView<JsonBean> {
    void getTeamDataSuccess(List<CricketTeamBean> list);

    void getPointsDataSuccess(List<CricketPointsBean> list);

    void getUpdatesDataSuccess(List<UpdatesBean> list);

    void getStatsDataSuccess(CricketStatsBean cricketStatsBean);
}
