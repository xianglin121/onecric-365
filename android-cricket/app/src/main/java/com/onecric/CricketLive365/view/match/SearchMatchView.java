package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.MatchSearchBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface SearchMatchView extends BaseView<MatchSearchBean> {
    void getMoreData(int type,List<MatchSearchBean> list);
    void getDataSuccess(List<MatchSearchBean> bean,int type);
    void getDataFail(String msg,int type);
}
