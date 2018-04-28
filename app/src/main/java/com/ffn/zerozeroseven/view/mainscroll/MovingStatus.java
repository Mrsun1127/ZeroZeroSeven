package com.ffn.zerozeroseven.view.mainscroll;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by dkzwm on 2018/3/5.
 *
 * @author dkzwm
 */

@IntDef({Constants.MOVING_CONTENT, Constants.MOVING_FOOTER, Constants.MOVING_HEADER})
@Retention(RetentionPolicy.SOURCE)
public @interface MovingStatus {
}