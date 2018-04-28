package com.ffn.zerozeroseven.view.mainscroll;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by dkzwm on 2018/2/27.
 * Triggered refresh action
 *
 * @author dkzwm
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef(
        {
                Constants.ACTION_NOTIFY,
                Constants.ACTION_AT_ONCE,
                Constants.ACTION_NOTHING
        }
)
public @interface Action {
}

