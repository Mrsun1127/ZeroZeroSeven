package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
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
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.ui.AdrMannGerActivity;
import com.ffn.zerozeroseven.ui.CourierActivity;
import com.ffn.zerozeroseven.ui.HistoryTalkActivity;
import com.ffn.zerozeroseven.ui.KuaiDiYuanRenZhengActivity;
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
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

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
        TextView tv_top = view.findViewById(R.id.tv_top);
        tv_username = view.findViewById(R.id.tv_username);
        tv_username.setOnClickListener(this);
        rl_message.setVisibility(View.VISIBLE);
        iv_back.setVisibility(View.GONE);
        tv_top.setText("个人中心");
        rl_message.setOnClickListener(this);
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

    @Override
    public void initDate() {

    }

    @Bind(R.id.iv_vip)
    ImageView iv_vip;

    @Override
    public void onResume() {
        super.onResume();
        UserInfo.DataBean userInfo= BaseAppApplication.getInstance().getLoginUser();
        schoolIId=userInfo.getSchoolId();
        if (userInfo != null) {
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
                        .into(iv_usericon);
            }
            switch (userInfo.getHonerLevel()) {
                case 1:
                    iv_level.setBackgroundResource(R.drawable.level1);
                    break;
                case 2:
                    iv_level.setBackgroundResource(R.drawable.level2);
                    break;
                case 3:
                    iv_level.setBackgroundResource(R.drawable.level3);
                    break;
                case 4:
                    iv_level.setBackgroundResource(R.drawable.level4);
                    break;
                case 5:
                    iv_level.setBackgroundResource(R.drawable.level5);
                    break;
                case 6:
                    iv_level.setBackgroundResource(R.drawable.level6);
                    break;
            }
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
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MyDingDanActivity.class, null);
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
                    String json = MrsunAppCacheUtils.get(bfCxt).getAsString("curInfo");
                    if (json != null) {
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
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MyRunActivity.class, null);
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
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, HistoryTalkActivity.class, null);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
        }
    }

    @OnClick({R.id.rl_vip, R.id.rl_yaoqing, R.id.rl_shouyi})
    void setOnClicks(View v) {
        switch (v.getId()) {
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
        }
    }


}
