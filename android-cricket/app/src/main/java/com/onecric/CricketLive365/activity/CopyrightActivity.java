package com.onecric.CricketLive365.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.onecric.CricketLive365.R;
import com.onecric.CricketLive365.view.BaseActivity;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2022/1/4
 */
public class CopyrightActivity extends BaseActivity implements View.OnClickListener {
    public static void forward(Context context) {
        Intent intent = new Intent(context, CopyrightActivity.class);
        context.startActivity(intent);
    }

    private TextView tv_content;

    @Override
    public boolean getStatusBarTextColor() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_copy_right;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.copyright_title));
        tv_content = findViewById(R.id.tv_content);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
