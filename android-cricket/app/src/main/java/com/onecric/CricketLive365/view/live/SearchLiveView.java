package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.Channel;
import com.onecric.CricketLive365.model.MatchListBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface SearchLiveView extends BaseView<List<MatchListBean>> {
    void getHotMatchClassifySuccess(List<Channel> list);
}
