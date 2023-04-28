//package com.onecric.CricketLive365.fragment;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.google.android.material.tabs.TabLayout;
//import com.onecric.CricketLive365.R;
//import com.onecric.CricketLive365.activity.LiveDetailActivity;
//import com.onecric.CricketLive365.custom.CustomPagerTitleView;
//import com.onecric.CricketLive365.fragment.dialog.LoginDialog;
//import com.onecric.CricketLive365.model.CricketMatchBean;
//import com.onecric.CricketLive365.model.LiveRoomBean;
//import com.onecric.CricketLive365.model.LiveUserBean;
//import com.onecric.CricketLive365.util.DpUtil;
//import com.tencent.qcloud.tuikit.tuichat.bean.MessageInfo;
//
//import net.lucode.hackware.magicindicator.buildins.UIUtil;
//import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
//import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
//import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
//import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
//import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 开发公司：东莞市梦幻科技有限公司
// * 时间：2021/10/25
// */
//public class LiveDetailMainFragment extends Fragment {
//    public boolean isNotStart = false;
//    public boolean isHistory = false;
//    public static LiveDetailMainFragment newInstance(String groupId, int anchorId,int matchId) {
//        LiveDetailMainFragment fragment = new LiveDetailMainFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("groupId", groupId);
//        bundle.putInt("anchorId", anchorId);
//        bundle.putInt("matchId", matchId);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    protected View rootView;
////    private RecyclerView rv_indicator;
////    private LiveIndicatorAdapter mIndicatorAdapter;
////    private int mCurrPosition;//当前指示器位置
////    private MagicIndicator magicIndicator;
//    private TabLayout tab_layout;
//    private List<String> mTitles;
//    public ViewPager vp_live;
//    private List<Fragment> mViewList;
////    private ConstraintLayout cl_follow;
////    private ImageView iv_icon;
////    private TextView tv_follow;
////    private TextView tv_attention_count;
//
//    public LiveUserBean mUserBean;
//
//    private LoginDialog loginDialog;
//    private int mMatchId;
//    public void setLoginDialog(LoginDialog dialog){
//        loginDialog = dialog;
//    }
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout.fragment_live_detail_main, null);
//        return rootView;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        initUI();
//        initData();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(mMatchId!=0 && !isNotStart){
//            ((LiveDetailActivity)getActivity()).getMatchDetail();
//        }
//    }
//
//    private void initUI() {
//        mTitles = new ArrayList<>();
//        mViewList = new ArrayList<>();
////        rv_indicator = rootView.findViewById(R.id.rv_indicator);
////        magicIndicator = rootView.findViewById(R.id.magicIndicator);
//        tab_layout = rootView.findViewById(R.id.tab_layout);
//        vp_live = rootView.findViewById(R.id.vp_live);
///*        cl_follow = rootView.findViewById(R.id.cl_follow);
//        iv_icon = rootView.findViewById(R.id.iv_icon);
//        tv_follow = rootView.findViewById(R.id.tv_follow);
//        tv_attention_count = rootView.findViewById(R.id.tv_attention_count);
//
//        cl_follow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!TextUtils.isEmpty(CommonAppConfig.getInstance().getToken())) {
//                    if (mUserBean != null && mUserBean.getIs_attention() == 0) {
//                        ((LiveDetailActivity)getActivity()).doFollow();
//                    }
//                }else {
//                    if(loginDialog!=null){
//                        loginDialog.show();
//                    }else{
//                        LoginActivity.forward(getContext());
//                    }
//                }
//            }
//        });*/
//    }
//
//    private void initData() {
////        updateFollowData();
///*        List<IndicatorBean> list = new ArrayList<>();
//        list.add(new IndicatorBean(getActivity().getString(R.string.live_chat), true));
//        list.add(new IndicatorBean(getActivity().getString(R.string.live_anchor), false));
//        list.add(new IndicatorBean(getActivity().getString(R.string.live_ranking), false));
//        list.add(new IndicatorBean(getActivity().getString(R.string.live_about_video), false));
//        mIndicatorAdapter = new LiveIndicatorAdapter(R.layout.item_live_indicator, list);
//        mIndicatorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (mCurrPosition != position) {
//                    mIndicatorAdapter.getItem(mCurrPosition).setSelected(false);
//                    mIndicatorAdapter.getItem(position).setSelected(true);
//                    mCurrPosition = position;
//                    mIndicatorAdapter.notifyDataSetChanged();
//                    vp_live.setCurrentItem(mCurrPosition);
//                }
//            }
//        });
//        rv_indicator.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        rv_indicator.setAdapter(mIndicatorAdapter);*/
//
////        initViewPager();
//        initTabViewPager();
//
//        /*if (!TextUtils.isEmpty(CommonAppConfig.getInstance().getUid())) {
//            if (CommonAppConfig.getInstance().getUid().equals(String.valueOf(getArguments().getInt("anchorId")))) {
//                cl_follow.setVisibility(View.GONE);
//            }else {
//                cl_follow.setVisibility(View.VISIBLE);
//            }
//        }else {
//            cl_follow.setVisibility(View.VISIBLE);
//        }*/
//
//    }
//
//    public void updateFollowData(LiveRoomBean bean) {
//        if (bean != null) {
//            mUserBean = bean.getUserData();
//        }
//        if (mUserBean != null) {
///*            if (mUserBean.getIs_attention() == 1) {
//                cl_follow.setBackgroundColor(getContext().getResources().getColor(R.color.c_D5D5D5));
//                tv_follow.setText(getActivity().getString(R.string.followed));
//                iv_icon.setVisibility(View.GONE);
//            }else {
//                cl_follow.setBackgroundResource(R.mipmap.bg_live_follow);
//                tv_follow.setText(getActivity().getString(R.string.follow));
//                iv_icon.setVisibility(View.VISIBLE);
//            }
//            tv_attention_count.setText(String.valueOf(mUserBean.getAttention()));*/
////            ((LiveAnchorFragment)mViewList.get(1)).updateFollowData();
//            ((LiveChatFragment)mViewList.get(1)).updateLoginData();
//        }
//    }
//
//    public void updateData() {
//        ((LiveChatFragment)mViewList.get(1)).updateData();
//    }
//
//    private void initViewPager() {
//        mMatchId = getArguments().getInt("matchId");
//        mTitles.add(getActivity().getString(R.string.list));
//        mTitles.add(getActivity().getString(R.string.live_chat));
//        mTitles.add(getString(R.string.live));
//        mTitles.add(getString(R.string.info));
//        mTitles.add(getString(R.string.scorecard));
//        mTitles.add(getString(R.string.squad));
////        mTitles.add(getActivity().getString(R.string.live_anchor));
////        mTitles.add(getActivity().getString(R.string.live_ranking));
//
//        LiveChatFragment chatFragment = LiveChatFragment.newInstance(getArguments().getString("groupId"), getArguments().getInt("anchorId"));
//        chatFragment.setLoginDialog(loginDialog);
//        chatFragment.mainFragment = this;
//        LiveMoreVideoFragment moreVideoFragment = LiveMoreVideoFragment.newInstance();
//        moreVideoFragment.setLoginDialog(loginDialog);
//        mViewList.add(moreVideoFragment);
//        mViewList.add(chatFragment);
//        mViewList.add(CricketLiveFragment.newInstance(mMatchId));
//        mViewList.add(CricketInfoFragment.newInstance(mMatchId));
//        mViewList.add(CricketScorecardFragment.newInstance());
//        mViewList.add(CricketSquadFragment.newInstance());
//
////        LiveAnchorFragment anchorFragment = LiveAnchorFragment.newInstance(getArguments().getInt("anchorId"));
////        anchorFragment.setLoginDialog(loginDialog);
////        mViewList.add(anchorFragment);
////        mViewList.add(LiveRankingFragment.newInstance(getArguments().getInt("anchorId")));
//
//
//        //初始化viewpager
//        //初始化指示器
//        CommonNavigator commonNavigator = new CommonNavigator(getContext());
//        commonNavigator.setForegroundGravity(Gravity.CENTER);
//        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
//            @Override
//            public int getCount() {
//                return mTitles.size();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, int index) {
//                CustomPagerTitleView titleView = new CustomPagerTitleView(context);
//                titleView.setNormalColor(getResources().getColor(R.color.c_333333));
//                titleView.setSelectedColor(getResources().getColor(R.color.c_DC3C23));
//                titleView.setText(mTitles.get(index));
//                titleView.setTextSize(13);
////                titleView.getPaint().setFakeBoldText(true);
//                titleView.setOnPagerTitleChangeListener(new CustomPagerTitleView.OnPagerTitleChangeListener() {
//                    @Override
//                    public void onSelected(int index, int totalCount) {
//                    }
//
//                    @Override
//                    public void onDeselected(int index, int totalCount) {
//                    }
//
//                    @Override
//                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
//
//                    }
//
//                    @Override
//                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
//
//                    }
//                });
//                titleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (vp_live != null) {
//                            vp_live.setCurrentItem(index);
//                        }
//                    }
//                });
//                return titleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
//                linePagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
//                linePagerIndicator.setLineWidth(DpUtil.dp2px(25));
//                linePagerIndicator.setLineHeight(DpUtil.dp2px(3));
//                linePagerIndicator.setXOffset(DpUtil.dp2px(0));
//                linePagerIndicator.setYOffset(DpUtil.dp2px(1));
//                linePagerIndicator.setRoundRadius(DpUtil.dp2px(2));
//                linePagerIndicator.setColors(getResources().getColor(R.color.c_DC3C23));
//                return linePagerIndicator;
//            }
//        });
////        commonNavigator.setAdjustMode(true);
//        vp_live.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
////                if (mCurrPosition != position) {
////                    mIndicatorAdapter.getItem(mCurrPosition).setSelected(false);
////                    mIndicatorAdapter.getItem(position).setSelected(true);
////                    mCurrPosition = position;
////                    mIndicatorAdapter.notifyDataSetChanged();
////                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//        vp_live.setOffscreenPageLimit( (mMatchId!=0 && !isNotStart) ?7:2);
//        vp_live.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//            @Override
//            public Fragment getItem(int i) {
//                return mViewList.get(i);
//            }
//
//            @Override
//            public int getCount() {
//                return mViewList.size();
//            }
//        });
//
////        magicIndicator.setNavigator(commonNavigator);
////        ViewPagerHelper.bind(magicIndicator, vp_live);
//
//    }
//
//    private void initTabViewPager(){
//        mMatchId = getArguments().getInt("matchId");
//
//        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.list)));
//        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.live_chat)));
//        if(mMatchId != 0 && !isNotStart){
//            tab_layout.addTab(tab_layout.newTab().setText("Animation"));
//            tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.live)));
//            tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.info)));
//            tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.scorecard)));
//            tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.squad)));
//        }
//
//        LiveChatFragment chatFragment = LiveChatFragment.newInstance(getArguments().getString("groupId"), getArguments().getInt("anchorId"));
//        chatFragment.setLoginDialog(loginDialog);
//        if(isHistory){
//            LiveHistoryFragment historyFragment = LiveHistoryFragment.newInstance();
//            historyFragment.setLoginDialog(loginDialog);
//            mViewList.add(historyFragment);
//        }else{
//            LiveMoreVideoFragment moreVideoFragment = LiveMoreVideoFragment.newInstance();
//            moreVideoFragment.setLoginDialog(loginDialog);
//            mViewList.add(moreVideoFragment);
//        }
//
//        mViewList.add(chatFragment);
//        if(mMatchId != 0 && !isNotStart){
//            mViewList.add(AnimationLiveFragment.newInstance());
//            mViewList.add(CricketLiveFragment.newInstance(mMatchId));
//            CricketInfoFragment info = CricketInfoFragment.newInstance(mMatchId);
//            info.fragment = this;
//            mViewList.add(info);
//            mViewList.add(CricketScorecardFragment.newInstance());
//            mViewList.add(CricketSquadFragment.newInstance());
//        }
//
///*        for(int i=0; i<tab_layout.getTabCount(); i++){
//            TabLayout.Tab tab1 =tab_layout.getTabAt(i);
//            tab1.view.setLongClickable(false);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                tab1.view.setTooltipText(null);
//            }
//        }*/
//        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                tab.view.setPadding(0,0,0,UIUtil.dip2px(getContext(),2));
//                vp_live.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.view.setPadding(0,0,0,0);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
////        ((FrameLayout)tab_layout).setForeground(getResources().getDrawable(R.drawable.selector_foreground_white));
//
//        //初始化viewpager
//        vp_live.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                tab_layout.getTabAt(i).select();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//        vp_live.setOffscreenPageLimit(mViewList.size());
//        vp_live.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//            @Override
//            public Fragment getItem(int i) {
//                return mViewList.get(i);
//            }
//
//            @Override
//            public int getCount() {
//                return mViewList.size();
//            }
//        });
//
//        vp_live.setCurrentItem(1);
//    }
//
//    public void sendMessage(String nobleIcon, String expIcon, MessageInfo messageInfo) {
////        ((LiveChatFragment)mViewList.get(0)).updateAdapter(nobleIcon, expIcon, messageInfo);
//        ((LiveChatFragment)mViewList.get(1)).updateAdapter(messageInfo);
//    }
//
//    public void showRedEnvelopeDialog() {
//        ((LiveChatFragment)mViewList.get(1)).showRedEnvelopeDialog();
//    }
//
//    public void setMatchData(CricketMatchBean model){
//        if(mViewList.size()>2){
//            ((AnimationLiveFragment) mViewList.get(2)).setLivePath(model.getLive_path());
//            ((CricketLiveFragment) mViewList.get(3)).getData(model.getMatch_id());
//            if (!TextUtils.isEmpty(model.getTournament_id())) {
//                ((CricketInfoFragment) mViewList.get(4)).getList(model.getHome_id(), model.getAway_id(), Integer.valueOf(model.getTournament_id()));
//            }
//            ((CricketScorecardFragment) mViewList.get(5)).getData(model);
//            ((CricketSquadFragment) mViewList.get(6)).getList(model.getMatch_id(), model.getHome_name(), model.getHome_logo(), model.getAway_name(), model.getAway_logo());
//        }
//    }
//
//}
