package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.BasketballDetailBean;
import com.onecric.CricketLive365.model.ReportBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface BasketballMatchDetailView extends BaseView<BasketballDetailBean> {
    void getReportListSuccess(List<ReportBean> list);

    void doReportSuccess();
}
