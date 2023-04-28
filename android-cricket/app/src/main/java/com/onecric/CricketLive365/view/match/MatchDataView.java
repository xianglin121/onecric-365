package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.MatchDataClassifyBean;
import com.onecric.CricketLive365.model.MatchDataFirstBean;
import com.onecric.CricketLive365.model.MatchDataFollowBean;
import com.onecric.CricketLive365.model.MatchDataSecondBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface MatchDataView extends BaseView<JsonBean> {
    void getFollowListSuccess(List<MatchDataFollowBean> list);

    void getClassifyListSuccess(List<MatchDataClassifyBean> list);

    void getCountryListSuccess(List<MatchDataFirstBean> list);

    void getListSuccess(int position, List<MatchDataSecondBean> list);
}
