package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.view.TopView;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class ReleaseJumpShopActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    private ArrayList<String> imgList = new ArrayList<>();
    private static final int REQUEST_IMAGE = 2;

    @OnClick({R.id.tv_take_photo})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_take_photo:
                if (ContextCompat.checkSelfPermission(ReleaseJumpShopActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //动态的请求权限
                    ActivityCompat.requestPermissions(ReleaseJumpShopActivity.this, new String[]{Manifest.permission.CAMERA},
                            0x11);
                } else {
                    showTypeDialog();
                }
                break;

        }
    }

    /**
     * 拍照
     */
    private void pickCamera() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);//是否拍照
        selector.count(6);//数量
        selector.single();//单
        selector.origin(imgList);
        selector.start(ReleaseJumpShopActivity.this, REQUEST_IMAGE);
    }

    /**
     * 相册
     */
    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);
        selector.count(6);
        selector.multi();
        selector.origin(imgList);
        selector.start(ReleaseJumpShopActivity.this, REQUEST_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE://拍照
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        imgList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        for (int i = 0; i < imgList.size(); i++) {
                            LogUtils.D("imgpath",imgList.get(i));
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x11) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickCamera();//拍照
            } else {
                // Permission Denied
                Toast.makeText(this, "请求权限被拒绝", Toast.LENGTH_LONG);
            }
        }
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
                pickImage();
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                pickCamera();
                dialog.dismiss();
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
