package com.onecric.CricketLive365.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.ArrayList;

public class AutoWrapTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Paint mPaint = null;
    private Paint.FontMetrics fm;
    private float offset;
    private String content = "";
    private float lineSpace = 0;
    private final Context mContext;

    public AutoWrapTextView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public AutoWrapTextView(Context context, AttributeSet set) {
        super(context, set, 0);
        this.mContext = context;
        init();
    }

    public AutoWrapTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mPaint = getPaint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getCurrentTextColor());
        initParams();
    }

    private void initParams() {
        fm = mPaint.getFontMetrics();
        if (lineSpace > 0) {
            fm.leading = lineSpace;
        } else {
            fm.leading = dip2px(1);
        }
        offset = fm.descent - fm.ascent + fm.leading;
    }

    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            int textWidth = (int) mPaint.measureText(content);
            width = Math.min(textWidth + getPaddingLeft() + getPaddingRight(), widthSize);
        } else {
            int textWidth = (int) mPaint.measureText(content);
            width = textWidth + getPaddingLeft() + getPaddingRight();
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            int lines = calculateLines(content, width - getPaddingLeft() - getPaddingRight()).size();
            int indeedHeight = getPaddingTop() + getPaddingBottom() + (int) offset * lines + (int) fm.bottom;
            height = Math.min(indeedHeight, heightSize);
        } else {
            int lines = calculateLines(content, width - getPaddingLeft() - getPaddingRight()).size();
            height = getPaddingTop() + getPaddingBottom() + (int) offset * lines + (int) fm.bottom;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = getPaddingLeft();
        float y = -fm.top + getPaddingTop();
        ArrayList<String> list = calculateLines(content, getWidth() - getPaddingLeft() - getPaddingRight());
        for (String text : list) {
            canvas.drawText(text, x, y, mPaint);
            y += offset;
        }
    }

    private final ArrayList<String> list = new ArrayList<>(0);

    private ArrayList<String> calculateLines(String content, int width) {
        list.clear();
        int length = content.length();
        float textWidth = mPaint.measureText(content);
        if (textWidth <= width) {
            list.add(content);
            return list;
        }
        int start = 0, end = 1;
        while (start < length) {
            if (mPaint.measureText(content, start, end) > width) {
                String lineText = content.substring(start, end - 1);
                list.add(lineText);
                start = end - 1;
            } else if (end < length) {
                end++;
            }
            if (end == length) {
                String lastLineText = content.subSequence(start, end).toString();
                list.add(lastLineText);
                break;
            }
        }
        return list;
    }

    public void setText(String text) {
        if (null == text || text.trim().length() == 0) {
            content = "";
        } else {
            content = text;
        }
        invalidate();
    }

    /**
     * @param textColor R.color.xx
     */
    @SuppressLint("ResourceType")
    public void setTextColor(int textColor) {
        mPaint.setColor(getResources().getColor(textColor));
        invalidate();
    }

    /**
     * @param textSize R.dimen.xx
     */
    public void setTextSize(int textSize) {
        mPaint.setTextSize(getResources().getDimension(textSize));
        initParams();
        invalidate();
    }

    /**
     * @param spacing R.dimen.xx
     */
    public void setLineSpacingExtra(int spacing) {
        this.lineSpace = getResources().getDimension(spacing);
        initParams();
        invalidate();
    }
}