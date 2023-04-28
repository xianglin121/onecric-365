package com.onecric.CricketLive365.presenter.cricket;

import com.onecric.CricketLive365.presenter.BasePresenter;
import com.onecric.CricketLive365.view.cricket.PlayerStatsView;

public class PlayerStatsPresenter extends BasePresenter<PlayerStatsView> {
    public PlayerStatsPresenter(PlayerStatsView view) {
        attachView(view);
    }
}
