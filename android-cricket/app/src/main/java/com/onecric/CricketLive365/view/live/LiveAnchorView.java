package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.MovingBean;
import com.onecric.CricketLive365.model.ReserveLiveBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface LiveAnchorView extends BaseView<List<MovingBean>> {
    void getReserveListSuccess(List<ReserveLiveBean> list);

    void doReserveSuccess(int position);
}
