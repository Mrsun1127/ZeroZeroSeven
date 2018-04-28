package com.ffn.zerozeroseven.view.mainscroll;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.SOURCE)
@IntDef(
        {
                Constants.MODE_DEFAULT,
                Constants.MODE_SCALE
        }
)
public @interface Mode {
}
