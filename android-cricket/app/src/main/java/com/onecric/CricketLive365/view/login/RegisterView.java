package com.onecric.CricketLive365.view.login;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

public interface RegisterView extends BaseView<JsonBean> {
    void registerSuccess(String msg);

    void registerFail(String msg);
}
