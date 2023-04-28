package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.FilterBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface MatchFilterOneView extends BaseView<JsonBean> {
    void getDataSuccess(List<FilterBean> competitionList, List<FilterBean> countryList, int competitionCount, int selectCompetitionCount);
}
