package com.onecric.CricketLive365.socket;

import com.onecric.CricketLive365.model.MatchSocketBean;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2020/11/24
 */

public interface WebSocketMsgListener {
    /**
     * socket重连
     */
    void onReconnection();
    /**
     * 信息更新
     */
    void onMessage(MatchSocketBean dataBean);
}
