package com.onecric.CricketLive365.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.activity.RankingActivity;

import java.util.ArrayList;
import java.util.List;

public class RichRankingFragment extends Fragment implements View.OnClickListener {
    public static RichRankingFragment newInstance() {
        RichRankingFragment fragment = new RichRankingFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    protected View rootView;
    private TextView tv_week_list, tv_month_list, tv_total_list;
    private ViewPager vp_rich;
    private List<Fragment> mViewList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rich_ranking, null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        initData();
    }

    protected void initUI() {
        tv_week_list = rootView.findViewById(R.id.tv_week_list);
        tv_month_list = rootView.findViewById(R.id.tv_month_list);
        tv_total_list = rootView.findViewById(R.id.tv_total_list);
        vp_rich = rootView.findViewById(R.id.vp_rich);

        tv_week_list.setOnClickListener(this);
        tv_month_list.setOnClickListener(this);
        tv_total_list.setOnClickListener(this);
    }

    protected void initData() {
        tv_week_list.setSelected(true);

        mViewList = new ArrayList<>();
        mViewList.add(RichRankingInnerFragment.newInstance(RankingActivity.TYPE_WEEK));
        mViewList.add(RichRankingInnerFragment.newInstance(RankingActivity.TYPE_MONTH));
        mViewList.add(RichRankingInnerFragment.newInstance(RankingActivity.TYPE_TOTAL));
        initViewPager();
    }

    private void initViewPager() {
        //初始化viewpager
        vp_rich.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        vp_rich.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mViewList.get(i);
            }

            @Override
            public int getCount() {
                return mViewList.size();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_week_list:
                if (tv_week_list.isSelected()) {
                    return;
                }
                tv_week_list.setSelected(true);
                tv_month_list.setSelected(false);
                tv_total_list.setSelected(false);
                vp_rich.setCurrentItem(0);
                break;
            case R.id.tv_month_list:
                if (tv_month_list.isSelected()) {
                    return;
                }
                tv_week_list.setSelected(false);
                tv_month_list.setSelected(true);
                tv_total_list.setSelected(false);
                vp_rich.setCurrentItem(1);
                break;
            case R.id.tv_total_list:
                if (tv_total_list.isSelected()) {
                    return;
                }
                tv_week_list.setSelected(false);
                tv_month_list.setSelected(false);
                tv_total_list.setSelected(true);
                vp_rich.setCurrentItem(2);
                break;
        }
    }
}
