package com.onecric.CricketLive365.view.login;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.view.BaseView;

public interface LoginView extends BaseView<JsonBean> {
    void loginIsSuccess(boolean isSuccess);
    void showCountryList();
}
