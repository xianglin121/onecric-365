package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.BalanceBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.WithdrawBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface MyWalletView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<BalanceBean> list);

    void getWithdrawDataSuccess(boolean isRefresh, List<WithdrawBean> list);
}
