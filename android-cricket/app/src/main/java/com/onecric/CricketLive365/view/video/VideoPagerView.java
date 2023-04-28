package com.onecric.CricketLive365.view.video;

import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.ReportBean;
import com.onecric.CricketLive365.model.ShortVideoBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

public interface VideoPagerView extends BaseView<JsonBean> {
    void getDataSuccess(boolean isRefresh, List<ShortVideoBean> list);

    void getReportListSuccess(List<ReportBean> list);

    void doCommentSuccess(Integer cid);

    void doReportSuccess();
}
