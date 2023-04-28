package com.onecric.CricketLive365.view.cricket;

import com.onecric.CricketLive365.model.CricketLiveBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/26
 */
public interface CricketLiveView extends BaseView<List<CricketLiveBean>> {
    void loadMoreDataSuccess(List<CricketLiveBean> list);

    void loadMoreDataFailed();
}
