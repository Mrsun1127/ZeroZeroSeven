package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppManger;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;

/**
 * Created by GT on 2017/11/19.
 */

public class SetActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_exit;
    @Override
    protected int setLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void doMain() {

        Button bt_exit = findViewById(R.id.bt_exit);
        if (userInfo!=null) {
            bt_exit.setVisibility(View.VISIBLE);
        }else {
            bt_exit.setVisibility(View.GONE);
        }
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.rl_clean).setOnClickListener(this);
        findViewById(R.id.rl_zzs).setOnClickListener(this);
        findViewById(R.id.bt_exit).setOnClickListener(this);
        findViewById(R.id.rl_pwd).setOnClickListener(this);
        findViewById(R.id.rl_zfb).setOnClickListener(this);
        TextView textView = findViewById(R.id.tv_top);
        textView.setText("设置");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_clean:
                final ConfirmDialog dialog = new ConfirmDialog(SetActivity.this);
                dialog.setTitles("提示");
                dialog.setMessages("确认清除缓存？");
                dialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        dialog.dismiss();
                        ToastUtils.showShort("清除成功", SetActivity.this);
                    }

                    @Override
                    public void doCancel() {
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.rl_zzs:
                ZeroZeroSevenUtils.SwitchActivity(SetActivity.this, AboutUsActivity.class);
                break;
            case R.id.bt_exit:
                final ConfirmDialog confirmDialog = new ConfirmDialog(SetActivity.this);
                confirmDialog.setTitles("提示");
                confirmDialog.setMessages("确认退出007吗?");
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        MrsunAppCacheUtils.get(SetActivity.this).clear();
                        SharePrefUtils.clearData(SetActivity.this);
                        Bundle bundle =new Bundle();
                        bundle.putString("exit","set");
                        ZeroZeroSevenUtils.SwitchActivity(SetActivity.this, LoginActivity.class,bundle);
                        BaseAppApplication.getInstance().setLoginUser(null);
                        BaseAppApplication.getInstance().exit();
                        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                    }
                });
                break;
            case R.id.rl_pwd:
                ZeroZeroSevenUtils.SwitchActivity(SetActivity.this,ForgetPassWordActivity.class);
                break;
            case R.id.rl_zfb:
                ZeroZeroSevenUtils.SwitchActivity(SetActivity.this,BindZFbPayActivity.class);
                break;
        }
    }
}
