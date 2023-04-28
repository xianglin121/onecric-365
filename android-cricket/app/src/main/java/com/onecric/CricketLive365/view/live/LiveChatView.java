package com.onecric.CricketLive365.view.live;

import com.onecric.CricketLive365.model.BoxBean;
import com.onecric.CricketLive365.model.GiftBean;
import com.onecric.CricketLive365.model.HistoryMsgBean;
import com.onecric.CricketLive365.model.JsonBean;
import com.onecric.CricketLive365.model.NobelBean;
import com.onecric.CricketLive365.model.RedEnvelopeBean;
import com.onecric.CricketLive365.view.BaseView;
import com.tencent.qcloud.tuikit.tuichat.bean.MessageInfo;

import java.util.List;

public interface LiveChatView extends BaseView<JsonBean> {
    void getNobelDataSuccess(NobelBean nobelBean);

    void getGiftListSuccess(List<GiftBean> list);

    void getBackpackGiftListSuccess(List<GiftBean> list);

    void sendGiftSuccess(GiftBean giftBean, String msg);

    void onGroupForceExit(String groupId);

    void exitGroupChat(String chatId);

    void onApplied(int unHandledSize);

    void handleRevoke(String msgId);

    void onRecvNewMessage(MessageInfo messageInfo);

    void onGroupNameChanged(String groupId, String newName);

    void getBackgroundDanmuListSuccess(List<GiftBean> list);

    void getBoxListSuccess(List<BoxBean> list);

    void doBoxTimeOverSuccess();

    void openBoxSuccess(BoxBean boxBean);

    void getRedEnvelopeListSuccess(List<RedEnvelopeBean> list);

    void receiveRedEnvelope(String amount);

    void addRedEnvelopeSuccess();

    void getHistoryMsgListSuccess(List<HistoryMsgBean.RspMsgListDTO> list);


}
