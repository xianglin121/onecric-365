package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.UserBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface MyFollowInnerView extends BaseView<List<UserBean>> {
    void getDataSuccess(boolean isRefresh, List<UserBean> list);

    void doFollowSuccess(int id);
}
