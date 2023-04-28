package com.onecric.CricketLive365.common;

import android.content.res.Resources;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.bumptech.glide.util.Util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * Copyright (C) 2018
 * 版权所有
 *
 * 功能描述：glide圆角
 * 作者：东莞市梦幻科技有限公司
 * 创建时间：2018/10/29
 *
 * 修改人：
 * 修改描述：
 * 修改日期
 */
public class GlideRoundTransform extends BitmapTransformation {

    public GlideRoundTransform(int dp) {
        super();
        roundingRadius = (int) (Resources.getSystem().getDisplayMetrics().density * dp);
    }

    private static final String ID = "com.onecric.live.common.GlideRoundTransform";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private int roundingRadius;

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (roundingRadius == 0)
            return toTransform;
        return TransformationUtils.roundedCorners(pool, toTransform, roundingRadius);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GlideRoundTransform) {
            GlideRoundTransform other = (GlideRoundTransform) o;
            return roundingRadius == other.roundingRadius;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Util.hashCode(ID.hashCode(), Util.hashCode(roundingRadius));
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        byte[] radiusData = ByteBuffer.allocate(4).putInt(roundingRadius).array();
        messageDigest.update(radiusData);
    }
}