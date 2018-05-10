package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.QiangShowInfo;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


public class BitisDetils extends BaseActivity {
    @Bind(R.id.tv_user)
    TextView tv_user;
    @Bind(R.id.tv_content)
    TextView tv_content;
    private QiangShowInfo.DataBean.ItemsBean info;
    @Override
    protected int setLayout() {
        return R.layout.activity_bitis_detils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void doMain() {
        info = (QiangShowInfo.DataBean.ItemsBean) getIntent().getSerializableExtra("info");
        tv_user.setText(TextUtils.isEmpty(info.getUserName()) ? "该用户很懒，名字还没有取好" : info.getUserName());
        tv_content.setText(TextUtils.isEmpty(info.getContent()) ? "加载失败" : info.getContent());
    }
    public  void  showShare(String s) {
        OnekeyShare oks = new OnekeyShare();
        oks.setImageUrl(AppConfig.LOGOURL);
        oks.setTitleUrl(AppConfig.SHAREURL);
        oks.setText(info.getContent());
        oks.setTitle(info.getTitle());
        oks.setPlatform(s);
        oks.show(BitisDetils.this);
    }
    @OnClick({R.id.ib_qq, R.id.ib_qzone, R.id.ib_wechat, R.id.ib_center, R.id.ib_back})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.ib_qq:
                showShare(QQ.NAME);
                break;
            case R.id.ib_qzone:
                showShare(QZone.NAME);
                break;
            case R.id.ib_wechat:
                showShare(Wechat.NAME);
                break;
            case R.id.ib_center:
                showShare(WechatMoments.NAME);
                break;
            case R.id.ib_back:
                finish();
                break;

        }
    }
}
