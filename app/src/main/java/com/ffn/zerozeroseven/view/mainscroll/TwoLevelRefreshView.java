package com.ffn.zerozeroseven.view.mainscroll;


/**
 * Created by dkzwm on 2017/6/12.
 *
 * @author dkzwm
 */
public interface TwoLevelRefreshView<T extends ITwoLevelIndicator> extends IRefreshView<T> {
    void onTwoLevelRefreshBegin(TwoLevelSmoothRefreshLayout layout, T twoLevelIndicator);
}
