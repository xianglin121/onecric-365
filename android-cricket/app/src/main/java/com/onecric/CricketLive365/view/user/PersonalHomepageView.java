package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.view.BaseView;

public interface PersonalHomepageView extends BaseView<UserBean> {
    void doFollowSuccess(int id);
}
