package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ImageItemInfo;
import com.ffn.zerozeroseven.utlis.Constant;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReleaseJumpShopActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @OnClick({R.id.tv_take_photo})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_take_photo:
                showTypeDialog();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {

                dialog.dismiss();


            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_rlease_jump_shop;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("发布宝贝");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
    }

    @Override
    protected void doMain() {

    }
}
