package com.ffn.zerozeroseven.view.mainscroll;

/**
 * Created by dkzwm on 2017/6/12.
 *
 * @author dkzwm
 */
public interface ITwoLevelIndicatorSetter extends IIndicator {
    void onTwoLevelRefreshComplete();

    void setRatioOfHeaderToHintTwoLevel(float ratio);

    void setRatioOfHeaderToTwoLevel(float ratio);

    void setRatioToKeepTwoLevelHeader(float ratio);
}
