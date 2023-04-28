package com.onecric.CricketLive365.view.theme;

import com.onecric.CricketLive365.model.HeadlineBean;
import com.onecric.CricketLive365.model.MovingBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface HeadlineDetailView extends BaseView<HeadlineBean> {
    void getDataSuccess(HeadlineBean model, List<HeadlineBean> list);

    void getTokenSuccess(String token);

    void getList(boolean isRefresh, List<MovingBean> list);

    void doCommentSuccess(Integer cid);

    void doFollowSuccess();
}
