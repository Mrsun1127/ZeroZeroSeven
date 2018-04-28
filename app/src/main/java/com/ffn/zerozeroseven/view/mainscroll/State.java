package com.ffn.zerozeroseven.view.mainscroll;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by dkzwm1 on 2018/2/27.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef(
        {
                Constants.STATE_NONE,
                Constants.STATE_CONTENT,
                Constants.STATE_ERROR,
                Constants.STATE_EMPTY,
                Constants.STATE_CUSTOM
        }
)
public @interface State {
}