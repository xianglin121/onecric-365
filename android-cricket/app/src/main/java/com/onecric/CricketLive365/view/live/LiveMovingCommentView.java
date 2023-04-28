package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.MovingBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface LiveMovingCommentView extends BaseView<JsonBean> {
    void getData(MovingBean bean);

    void getTokenSuccess(String token);

    void getList(List<MovingBean> list);

    void doCommentSuccess(Integer cid);
}
