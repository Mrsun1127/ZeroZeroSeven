package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.bean.JiInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.JifenInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.ui.AdrMannGerActivity;
import com.ffn.zerozeroseven.ui.AllDingDanActivity;
import com.ffn.zerozeroseven.ui.BitisHistoryActivity;
import com.ffn.zerozeroseven.ui.CourierActivity;
import com.ffn.zerozeroseven.ui.DingDanBobyActivity;
import com.ffn.zerozeroseven.ui.ErrandDingdanActivity;
import com.ffn.zerozeroseven.ui.HistoryTalkActivity;
import com.ffn.zerozeroseven.ui.InteralDetilsActivity;
import com.ffn.zerozeroseven.ui.KuaiDiYuanRenZhengActivity;
import com.ffn.zerozeroseven.ui.LevelActivity;
import com.ffn.zerozeroseven.ui.LoginActivity;
import com.ffn.zerozeroseven.ui.MessAgeActivity;
import com.ffn.zerozeroseven.ui.MyDingDanActivity;
import com.ffn.zerozeroseven.ui.MyRunActivity;
import com.ffn.zerozeroseven.ui.PeopleMessAgeActivity;
import com.ffn.zerozeroseven.ui.SetActivity;
import com.ffn.zerozeroseven.ui.ShouyiActivity;
import com.ffn.zerozeroseven.ui.SugActivity;
import com.ffn.zerozeroseven.ui.ToBeAGoodPeople;
import com.ffn.zerozeroseven.ui.VipActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by GT on 2017/11/15.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {


    private TextView tv_username;
    private TextView tv_schoolInfo;
    private ImageView iv_usericon;
    @Bind(R.id.iv_level)
    ImageView iv_level;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        FindView(view);
    }

    private void FindView(View view) {
        RelativeLayout rl_set = view.findViewById(R.id.rl_set);
        RelativeLayout rl_sug = view.findViewById(R.id.rl_sug);
        RelativeLayout rl_history = view.findViewById(R.id.rl_history);
        ImageView iv_dingdan = view.findViewById(R.id.iv_dingdan);
        RelativeLayout iv_back = view.findViewById(R.id.iv_back);
        tv_schoolInfo = view.findViewById(R.id.tv_schoolInfo);
        iv_usericon = view.findViewById(R.id.iv_usericon);
        ImageView iv_run = view.findViewById(R.id.iv_run);
        ImageView iv_adr = view.findViewById(R.id.iv_adr);
        RelativeLayout rl_message = view.findViewById(R.id.rl_message);
        tv_username = view.findViewById(R.id.tv_username);
        tv_username.setOnClickListener(this);
        iv_dingdan.setOnClickListener(this);
        rl_set.setOnClickListener(this);
        rl_sug.setOnClickListener(this);
        rl_history.setOnClickListener(this);
        iv_usericon.setOnClickListener(this);
        iv_run.setOnClickListener(this);
        iv_adr.setOnClickListener(this);
        view.findViewById(R.id.rl_tobe).setOnClickListener(this);
        view.findViewById(R.id.rl_kuaidi).setOnClickListener(this);
    }

    @Bind(R.id.tv_jifen)
    TextView tv_jifen;

    @Override
    public void initDate() {
        requestJifen();
    }

    private void requestJifen() {
        JifenInfo jifenInfo = new JifenInfo();
        jifenInfo.setFunctionName("QueryUserHonerPoint");
        JifenInfo.ParametersBean parametersBean = new JifenInfo.ParametersBean();
        try {
            parametersBean.setUserId(Integer.parseInt(userId));
        } catch (Exception e) {
        }
        jifenInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(jifenInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final JiInfo jiInfo = JSON.parseObject(response, JiInfo.class);

                if (jiInfo.getCode() == 0) {
                    tv_jifen.setText("当前积分：" + jiInfo.getData().getHonerPoint());
                }
            }
        });
    }

    @Bind(R.id.iv_vip)
    ImageView iv_vip;
    @Bind(R.id.rl_tel)
    RelativeLayout rl_tel;
    @Bind(R.id.tv_tel)
    TextView tv_tel;

    @Override
    public void onResume() {
        super.onResume();
        UserInfo.DataBean userInfo = BaseAppApplication.getInstance().getLoginUser();
        if (userInfo != null) {
            schoolIId = userInfo.getSchoolId();
            tv_username.setText(userInfo.getRealName());
            if (userInfo.getCollege() != null && userInfo.getClazz() != null) {
                tv_schoolInfo.setText(userInfo.getCollege() + "  " + userInfo.getClazz());
            }
            if (userInfo.getIsMember() == 1) {
                iv_vip.setVisibility(View.VISIBLE);
            }
            if (!TextUtils.isEmpty(userInfo.getAvatar())) {
                Glide.with(bfCxt)
                        .load(userInfo.getAvatar())
                        .override(150, 150)
                        .into(iv_usericon);
            }
            if (!TextUtils.isEmpty(userInfo.getServicePhone())) {
                rl_tel.setVisibility(View.VISIBLE);
                tv_tel.setText("客服电话: " + userInfo.getServicePhone());
            } else {
                rl_tel.setVisibility(View.GONE);
            }

            switch (userInfo.getHonerLevel()) {
                case 1:
                    Glide.with(bfCxt).load(R.drawable.level1).into(iv_level);
                    break;
                case 2:
                    Glide.with(bfCxt).load(R.drawable.level2).into(iv_level);
                    break;
                case 3:
                    Glide.with(bfCxt).load(R.drawable.level3).into(iv_level);
                    break;
                case 4:
                    Glide.with(bfCxt).load(R.drawable.level4).into(iv_level);
                    break;
                case 5:
                    Glide.with(bfCxt).load(R.drawable.level5).into(iv_level);
                    break;
                case 6:
                    Glide.with(bfCxt).load(R.drawable.level6).into(iv_level);
                    break;
            }
        }

    }

    private final int REQUEST_CODE = 0x1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && PermissionChecker.checkSelfPermission(bfCxt, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showShort("授权成功,请重新拨打电话");
        }
    }

    private void callPhone(String phoneNum) {
        //直接拨号
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        //此处不判断就会报错
        if (ActivityCompat.checkSelfPermission(bfCxt, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void lazyLoad() {
        LogUtils.D("lazyLoad", "lazyLoad");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_set:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, SetActivity.class, null);
                break;
            case R.id.rl_sug:
                if (userInfo != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("sugType", "app");
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, SugActivity.class, bundle);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.iv_dingdan:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, AllDingDanActivity.class, null);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.rl_message:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MessAgeActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.rl_history:
                if (userInfo != null) {
                    if (userInfo.isLoginCouris()) {
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, CourierActivity.class, null);
                    } else {
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, KuaiDiYuanRenZhengActivity.class, null);
                    }
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.iv_usericon:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, PeopleMessAgeActivity.class, null);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }

                break;
            case R.id.iv_run:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, ErrandDingdanActivity.class, null);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.iv_adr:
                if (userInfo != null) {
                    if ("943478288".equals(schoolIId)) {
                        ToastUtils.showShort("请先选择学校");
                    } else {
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, AdrMannGerActivity.class, null);
                    }
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }

                break;
            case R.id.rl_kuaidi:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, ToBeAGoodPeople.class);

                break;
            case R.id.tv_username:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, PeopleMessAgeActivity.class, null);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.rl_tobe:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, BitisHistoryActivity.class, null);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
        }
    }

    @OnClick({R.id.rl_message, R.id.rl_tel, R.id.rl_vip, R.id.rl_yaoqing, R.id.rl_shouyi, R.id.iv_level})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_message:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MessAgeActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.rl_tel:
                ZeroZeroSevenUtils.requestCallMainifest(getActivity());
                ZeroZeroSevenUtils.MakingCalls(bfCxt, userInfo.getServicePhone());
                break;
            case R.id.rl_vip:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, VipActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.rl_yaoqing:
                try {
                    if (userInfo != null) {
                        ZeroZeroSevenUtils.showCustonPopToShare(bfCxt, "你的邀请码是" + userInfo.getInviteCode(), tv_username, userInfo);
                    } else {
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                    }
                } catch (Exception e) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }

                break;
            case R.id.rl_shouyi:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, ShouyiActivity.class, null);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.iv_level:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, LevelActivity.class);
                break;
        }
    }


}
