package com.onecric.CricketLive365.view.cricket;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.ScorecardBatterBean;
import com.onecric.CricketLive365.model.ScorecardBowlerBean;
import com.onecric.CricketLive365.model.ScorecardWicketBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/26
 */
public interface CricketScorecardView extends BaseView<JsonBean> {
    void getHomeDataSuccess(List<ScorecardBatterBean> batterList, List<ScorecardBowlerBean> bowlerList, List<ScorecardWicketBean> wicketList, String extras, String noBattingName);

    void getAwayDataSuccess(List<ScorecardBatterBean> batterList, List<ScorecardBowlerBean> bowlerList, List<ScorecardWicketBean> wicketList, String extras, String noBattingName);
}
