package com.erkuai.expandabletextview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/5/20.
 */

public class ExpandableTextView extends LinearLayout {

    //内容文本
    private TextView mContextTextView;

    //全文/收起  按钮
    private TextView mExpansionButton;

    //最大显示行数
    private int mMaxLine = 3;

    //显示的内容
    private CharSequence mContent;

    //是否需要显示“全文”
    private boolean mIsNeedExpansion;


    public ExpandableTextView(Context context) {
        super(context);
        init(context);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.expandable_textview, this);
        mContextTextView = (TextView) findViewById(R.id.tv_content);
        mExpansionButton = (TextView) findViewById(R.id.tv_expansion);

        mContextTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mContextTextView.getWidth() == 0) {
                    return;
                }
                mContextTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                setText(mContent);
            }
        });

        mExpansionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpansionStatus();
            }
        });


    }

    private void toggleExpansionStatus() {

        // 更新内容和切换按钮的显示
        if (mIsNeedExpansion) {
            mIsNeedExpansion = false;
            mExpansionButton.setText("收起");                       // 全文状态, 按钮显示 "收起"
            mContextTextView.setMaxLines(Integer.MAX_VALUE);        // 全文状态, 行数设置为最大
        } else {
            mIsNeedExpansion = true;
            mExpansionButton.setText("全文");                       // 收起状态, 按钮显示 "全文"
            mContextTextView.setMaxLines(mMaxLine);
            mContextTextView.setEllipsize(TextUtils.TruncateAt.END);// 收起状态, 最大显示指定的行数
        }

    }

    public void setText(CharSequence text) {
        mContent = text;
        //文本控件有宽度时（绘制成功后）才能获取到文本显示所需要的行数
        //如果控件还没有绘制，等监听到绘制成功后再设置文本
        if (mContextTextView.getWidth() == 0) {
            return;
        }
        //默认先设置最大行数为最大值
        mContextTextView.setMaxLines(Integer.MAX_VALUE);
        //设置文本
        mContextTextView.setText(text);
        //设置文本完成后，获取显示该文本所需要的行数
        int lineCount = mContextTextView.getLineCount();
        if (lineCount > mMaxLine) {
            mIsNeedExpansion = true;
            mExpansionButton.setVisibility(VISIBLE);

            mContextTextView.setMaxLines(mMaxLine);
            mContextTextView.setEllipsize(TextUtils.TruncateAt.END);

        } else {
            mIsNeedExpansion = false;
            mExpansionButton.setVisibility(GONE);
        }
    }
}
