package com.onecric.CricketLive365.view.cricket;

import com.onecric.CricketLive365.model.BattingBean;
import com.onecric.CricketLive365.model.BowlingBean;
import com.onecric.CricketLive365.model.FieldingBean;
import com.onecric.CricketLive365.model.PlayerProfileBean;
import com.onecric.CricketLive365.model.RecentMatchesBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/26
 */
public interface PlayerProfileView extends BaseView<PlayerProfileBean> {
    void getDataSuccess(String teams, List<RecentMatchesBean> list, String profile);

    void getDataSuccess(BattingBean batting, BowlingBean bowling, FieldingBean fielding, List<RecentMatchesBean> list);
}
