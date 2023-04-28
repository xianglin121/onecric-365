package com.onecric.CricketLive365.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.adapter.PreViewPagerAdapter;
import com.onecric.CricketLive365.fragment.PreViewContentFragment;
import com.zhihu.matisse.internal.utils.Platform;

import java.util.List;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2020/11/24
 */

public class PreViewActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    protected ViewPager mPager;
    private PreViewPagerAdapter mAdapter;
    public static final String IMAGE = "image";
    public static final String POSITION = "position";
    private TextView mTvCount;

    private int mCurrentPosition;
    private int maxCount;
    private int mPreviousPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_view);
        if (Platform.hasKitKat()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        mPager = findViewById(R.id.pager);
        mTvCount = findViewById(R.id.tv_count);
        Intent intent = getIntent();
        if (intent == null) return;
        List<String> imageList = intent.getStringArrayListExtra(IMAGE);
        mCurrentPosition = intent.getIntExtra(POSITION, 0);
        maxCount = imageList.size();
        mAdapter = new PreViewPagerAdapter(getSupportFragmentManager(), imageList);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);
        mPager.setCurrentItem(mCurrentPosition);
        changePositionText(mCurrentPosition);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (mPreviousPos != -1 && mPreviousPos != i) {

            ((PreViewContentFragment) mAdapter.instantiateItem(mPager, mPreviousPos)).resetView();
        }
        mPreviousPos = i;
        changePositionText(i);
    }


    @Override
    public void onPageScrollStateChanged(int i) {

    }


    private void changePositionText(int position) {
        mCurrentPosition = position + 1;
        String currentPositionStr = mCurrentPosition + "";
        String maxPositionStr = maxCount + "";
        SpannableString spannableString = new SpannableString(currentPositionStr + " / " +
                maxPositionStr);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, currentPositionStr.length
                (), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(2f), 0, currentPositionStr.length(),
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
                currentPositionStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvCount.setText(spannableString);

    }


}
