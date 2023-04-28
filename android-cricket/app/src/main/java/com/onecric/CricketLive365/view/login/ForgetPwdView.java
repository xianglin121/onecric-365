package com.onecric.CricketLive365.view.login;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

public interface ForgetPwdView extends BaseView<JsonBean> {
    void forgetPwdSuccess(String msg);

    void forgetPwdFail(String msg);
}
