package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.model.HeadlineBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.LiveBean;
import com.onecric.CricketLive365.model.MatchListBean;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface SearchComplexView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<UserBean> anchorList, List<LiveBean> liveList, List<MatchListBean> matchList, List<HeadlineBean> headlineList, List<CommunityBean> communityList);

    void doFollowSuccess(int id);

    void doReserveSuccess(int position);
}
