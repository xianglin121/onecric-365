package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.CommunityBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface CommunityCommentView extends BaseView<JsonBean> {
    void getData(CommunityBean bean);

    void getTokenSuccess(String token);

    void getList(List<CommunityBean> list);

    void doCommentSuccess(Integer cid);
}
