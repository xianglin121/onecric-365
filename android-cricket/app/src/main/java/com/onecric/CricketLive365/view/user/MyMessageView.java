package com.onecric.CricketLive365.view.user;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.MessageBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface MyMessageView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<MessageBean> list);
}
