package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.view.BaseView;

public interface UserInfoView extends BaseView<UserBean> {
    void getTokenSuccess(String token);
}
