package com.ffn.zerozeroseven.utlis;

import android.content.Context;
import android.content.SharedPreferences;

//获得软件的全局配置文件
public class SharedConfig {
    Context context;
    SharedPreferences shared;

    public SharedConfig(Context context) {
        this.context = context;
        shared = context.getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    public SharedPreferences GetConfig() {
        return shared;
    }

    public void ClearConfig() {
        shared.edit().clear().commit();
    }
}
