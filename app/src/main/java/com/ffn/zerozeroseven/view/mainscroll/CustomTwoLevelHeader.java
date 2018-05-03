package com.ffn.zerozeroseven.view.mainscroll;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.fragment.MainFragment;
import com.ffn.zerozeroseven.ui.HomeActivity;
import com.ffn.zerozeroseven.ui.MrsunWebActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.just.library.AgentWeb;
import com.just.library.AgentWebView;


/**
 * Created by dkzwm on 2017/6/12.
 *
 * @author dkzwm
 */

public class CustomTwoLevelHeader extends FrameLayout implements TwoLevelRefreshView<ITwoLevelIndicator> {
    private static final byte STATUS_PULL_DOWN = 1;
    private static final byte STATUS_RELEASE_TO_REFRESH = 2;
    private static final byte STATUS_TWO_LEVEL_REFRESH_HINT = 4;
    private static final byte STATUS_TWO_LEVEL_RELEASE_TO_REFRESH = 5;
    private byte mStatus = STATUS_PULL_DOWN;
    private TextView mTextViewTitle;
    public CustomTwoLevelHeader(Context context) {
        this(context, null);
    }

    public CustomTwoLevelHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTwoLevelHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View header = LayoutInflater.from(context).inflate(R.layout.layout_custom_two_level_header, this);
        mTextViewTitle=header.findViewById(R.id.tv_up);
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

    @Override
    public int getStyle() {
        return STYLE_DEFAULT;
    }

    @Override
    public int getCustomHeight() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onReset(SmoothRefreshLayout frame) {
        if (frame.isEnabledPullToRefresh()) {
            mTextViewTitle.setText("下拉刷新");
        } else {
            mTextViewTitle.setText("下拉");
        }
    }

    @Override
    public void onRefreshPrepare(SmoothRefreshLayout frame) {
        if (frame.isEnabledPullToRefresh()) {
            mTextViewTitle.setText("下拉刷新");
        } else {
            mTextViewTitle.setText("下拉");
        }
    }

    @Override
    public void onFingerUp(SmoothRefreshLayout layout, ITwoLevelIndicator indicator) {
    }

    @Override
    public void onRefreshBegin(SmoothRefreshLayout frame, ITwoLevelIndicator indicator) {
        mTextViewTitle.setText("正在刷新");
    }


    @Override
    public void onRefreshComplete(SmoothRefreshLayout frame, boolean isSuccessful) {
        mTextViewTitle.setText("更新完成");
    }

    @Override
    public void onRefreshPositionChanged(SmoothRefreshLayout layout, byte status, ITwoLevelIndicator indicator) {
        final int currentPos = indicator.getCurrentPos();
        if (layout instanceof TwoLevelSmoothRefreshLayout) {
            TwoLevelSmoothRefreshLayout refreshLayout = (TwoLevelSmoothRefreshLayout) layout;
            if (!refreshLayout.isDisabledTwoLevelRefresh() && status == SmoothRefreshLayout.SR_STATUS_PREPARE) {
                final int offSetToTwoLevelRefresh = indicator.getOffsetToTwoLevelRefresh();
                if (currentPos < offSetToTwoLevelRefresh && indicator.crossTwoLevelHintLine()) {
                    if (mStatus != STATUS_TWO_LEVEL_REFRESH_HINT) {
                        mTextViewTitle.setText("继续下拉有惊喜");
                        mStatus = STATUS_TWO_LEVEL_REFRESH_HINT;
                    }
                    return;
                } else if (currentPos > offSetToTwoLevelRefresh) {
                    if (mStatus != STATUS_TWO_LEVEL_RELEASE_TO_REFRESH) {
                        mStatus = STATUS_TWO_LEVEL_RELEASE_TO_REFRESH;
                        if (!layout.isEnabledPullToRefresh()) {
                            mTextViewTitle.setText("松手得惊喜");
                        }
                    }
                    return;
                }
            }
        }
        final int mOffsetToRefresh = indicator.getOffsetToRefresh();
        if (currentPos < mOffsetToRefresh && mStatus != STATUS_PULL_DOWN
                && status == SmoothRefreshLayout.SR_STATUS_PREPARE) {
            mStatus = STATUS_PULL_DOWN;
            if (layout.isEnabledPullToRefresh()) {
                mTextViewTitle.setText("下拉刷新");
            } else {
                mTextViewTitle.setText("下拉");
            }
        } else if (currentPos > mOffsetToRefresh && mStatus != STATUS_RELEASE_TO_REFRESH
                && indicator.hasTouched() && status == SmoothRefreshLayout.SR_STATUS_PREPARE) {
            mStatus = STATUS_RELEASE_TO_REFRESH;
            if (!layout.isEnabledPullToRefresh()) {
                mTextViewTitle.setText("释放刷新");
            }
        }
    }

    @Override
    public void onPureScrollPositionChanged(SmoothRefreshLayout layout, byte status, ITwoLevelIndicator indicator) {

    }

    @Override
    public void onTwoLevelRefreshBegin(TwoLevelSmoothRefreshLayout layout, ITwoLevelIndicator twoLevelIndicator) {
        BaseAppApplication.mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle=new Bundle();
                bundle.putString("url","http://www.baidu.com");
                bundle.putString("title","百度一下");
                ZeroZeroSevenUtils.SwitchActivity(HomeActivity.getmInstance().get(),MrsunWebActivity.class,bundle);
            }
        },500);
    }
}
