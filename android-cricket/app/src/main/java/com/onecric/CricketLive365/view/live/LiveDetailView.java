package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.BasketballDetailBean;
import com.onecric.CricketLive365.model.CricketMatchBean;
import com.onecric.CricketLive365.model.FootballDetailBean;
import com.onecric.CricketLive365.model.LiveRoomBean;
import com.onecric.CricketLive365.model.UpdatesBean;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface LiveDetailView extends BaseView<LiveRoomBean> {
    void getDataSuccess(FootballDetailBean bean);

    void getDataSuccess(BasketballDetailBean bean);

    void doFollowSuccess();

    void getDataSuccess(UserBean bean);

    void getUpdateUserData(LiveRoomBean bean);

    void sendBgDanmuSuccess(int id, int anchorId, int level, String text, String msg);

    void sendBroadcastResponse(boolean isSuccess, String msg);

    void getMatchDataSuccess(CricketMatchBean model);

    void getUpdatesDataSuccess(List<UpdatesBean> list);

    void showLikeSuccess();
}
