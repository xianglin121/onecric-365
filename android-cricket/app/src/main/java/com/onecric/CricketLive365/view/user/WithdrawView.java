package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.AccountBean;
import com.onecric.CricketLive365.view.BaseView;

public interface WithdrawView extends BaseView<AccountBean> {
    void getCodeSuccess();

    void getBalanceSuccess(int isPayPwd, String balance, double withdrawalRadio);

    void applyWithdrawSuccess();
}
