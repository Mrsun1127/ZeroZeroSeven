package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.ReleaseJumpShopActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.app.Activity.RESULT_OK;

public class ErrandRenzhen1Fragment extends BaseFragment {
    public static ErrandRenzhen1Fragment newInstance() {
        return new ErrandRenzhen1Fragment();
    }

    private ErrandRenzhen1Fragment fragment;
    private ArrayList<String> imgList = new ArrayList<>();

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        fragment = this;
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_renzheng1;
    }

    @Bind(R.id.iv_icon)
    CircleImageView iv_icon;

    @OnClick({R.id.iv_icon, R.id.bt_next})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                ErrandAuitActivity.mInstance.get().goVp(1);
                break;
            case R.id.iv_icon:
                if (ContextCompat.checkSelfPermission(bfCxt, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //动态的请求权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                            0x11);
                } else {
                    pickImage();
                }
                break;
        }
    }

    private static final int REQUEST_IMAGE = 2;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE://拍照
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        imgList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        for (int i = 0; i < imgList.size(); i++) {
//                            LogUtils.D("imgpath", imgList.get(i));
                            Glide.with(bfCxt).load(imgList.get(i)).into(iv_icon);
                        }
                    }
                }
                break;
        }
    }

    /**
     * 相册
     */
    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);
        selector.count(1);
        selector.multi();
        selector.origin(imgList);
        selector.start(fragment, REQUEST_IMAGE);

    }

    @Override
    protected void lazyLoad() {

    }
}
