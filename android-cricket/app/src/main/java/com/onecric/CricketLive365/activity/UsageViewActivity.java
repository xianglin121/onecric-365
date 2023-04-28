package com.onecric.CricketLive365.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.fragment.UsagePreViewContentFragment;
import com.onecric.CricketLive365.util.SpUtil;
import com.onecric.CricketLive365.view.BaseActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2021/6/17
 */
public class UsageViewActivity extends BaseActivity {
    public static void forward(Context context) {
        Intent intent = new Intent(context, UsageViewActivity.class);
        context.startActivity(intent);
    }

    private ViewPager mPager;
    private MagicIndicator indicator;
    private Handler handler = new Handler();
    private List<UsagePreViewContentFragment> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_usage_view;
    }

    @Override
    protected void initView() {
        mPager = findViewById(R.id.view_pager);
        indicator = findViewById(R.id.guide_indicator);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add(UsagePreViewContentFragment.newInstant(1));
        list.add(UsagePreViewContentFragment.newInstant(2));
        list.add(UsagePreViewContentFragment.newInstant(3));
        list.add(UsagePreViewContentFragment.newInstant(4));
        //初始化viewpager
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if(i+1 == list.size()){
                    //计时三秒到主页
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (!SpUtil.getInstance().getBooleanValue(SpUtil.HIDE_USAGE)) {
                                SpUtil.getInstance().setBooleanValue(SpUtil.HIDE_USAGE, true);
                            }
                            MainActivity.forward(UsageViewActivity.this);
                            finish();
                        }
                    },3000);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        initIndicator();
        SpUtil.getInstance().setBooleanValue(SpUtil.RED_AND_YELLOW_CARD, true);
        SpUtil.getInstance().setBooleanValue(SpUtil.GOAL_SOUND, true);
        SpUtil.getInstance().setBooleanValue(SpUtil.GOAL_VIBRATOR, true);
        SpUtil.getInstance().setBooleanValue(SpUtil.RED_CARD_SOUND, true);
        SpUtil.getInstance().setBooleanValue(SpUtil.RED_CARD_VIBRATOR, true);
    }

    private void initIndicator(){
        //指示器
        CircleNavigator circleNavigator = new CircleNavigator(this);
        circleNavigator.setCircleCount(list.size());
        circleNavigator.setCircleColor(getResources().getColor(R.color.white));
        indicator.setNavigator(circleNavigator);

        mPager.setBackgroundColor(Color.TRANSPARENT);
        ViewPagerHelper.bind(indicator, mPager);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int cur_item = mPager.getCurrentItem();
                cur_item = (cur_item + 1) % list.size();
                mPager.setCurrentItem(cur_item);
                //循环
                if(cur_item + 1 != list.size()){
                    handler.postDelayed(this, 3000);
                }
            }
        }, 3000);
    }

}
