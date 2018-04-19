package com.ffn.zerozeroseven.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;

import com.baoyz.swipemenulistview.SwipeMenuListView;

/*
* PullSwipeMenuListView  左滑删除 带刷新
* @author archerlee
* @time 16/6/1 09:16
*/
public class PullSwipeMenuListView extends SwipeMenuListView implements PullToRefreshLayout.Pullable {
    public PullSwipeMenuListView(Context context) {
        this(context, null);
    }

    public PullSwipeMenuListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullSwipeMenuListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean canPullDown() {
        return  !ViewCompat.canScrollVertically(this, -1);
    }

    @Override
    public boolean canPullUp() {
        return false;
    }
}
