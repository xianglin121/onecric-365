package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.CoinBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface ChargeView extends BaseView<List<CoinBean>> {
    void paySuccess(String url);

    void payFail(String msg);
}
