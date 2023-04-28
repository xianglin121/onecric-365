package com.onecric.CricketLive365.view.login;

import com.onecric.CricketLive365.model.ConfigurationBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.view.BaseView;

public interface MainView extends BaseView<JsonBean> {
    void getVisitorUserSigSuccess(String userId, String userSig);

    void getDataSuccess(UserBean model);

    void getConfigSuccess(ConfigurationBean bean);
}
