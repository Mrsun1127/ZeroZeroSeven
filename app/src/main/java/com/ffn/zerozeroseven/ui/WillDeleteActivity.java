package com.ffn.zerozeroseven.ui;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.ScreenUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WillDeleteActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.iv_photo)
    ImageView iv_photo;

    @Override
    protected int setLayout() {
        return R.layout.activity_image;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("跳蚤市场");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        Glide.with(this).load("http://m.qpic.cn/psb?/V11aBxEr3o07IR/a*f1qcIbZzi*6fNnIf5e0bO2P0UJxWeSmo6Qet8NyLk!/b/dDcBAAAAAAAA&bo=OASABwAAAAARB4s!&rf=viewer_4").override(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight()).into(iv_photo);
    }

    @Override
    protected void doMain() {

    }
}
