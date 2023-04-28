package com.onecric.CricketLive365.view.cricket;

import com.onecric.CricketLive365.model.CricketInfoBean;
import com.onecric.CricketLive365.model.CricketPointsBean;
import com.onecric.CricketLive365.view.BaseView;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/8/26
 */
public interface CricketInfoView extends BaseView<CricketInfoBean> {
    void getPointsDataSuccess(List<CricketPointsBean> list);
}
