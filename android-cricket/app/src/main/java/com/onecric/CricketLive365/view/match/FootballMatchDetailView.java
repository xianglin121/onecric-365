package com.onecric.CricketLive365.view.match;

import com.onecric.CricketLive365.model.FootballDetailBean;
import com.onecric.CricketLive365.model.ReportBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface FootballMatchDetailView extends BaseView<FootballDetailBean> {
    void getReportListSuccess(List<ReportBean> list);

    void doReportSuccess();
}
