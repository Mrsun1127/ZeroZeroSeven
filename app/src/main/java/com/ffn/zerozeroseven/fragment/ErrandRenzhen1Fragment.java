package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.ReleaseJumpShopActivity;
import com.ffn.zerozeroseven.ui.SeachSchoolListActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.app.Activity.RESULT_OK;

public class ErrandRenzhen1Fragment extends BaseFragment {

    private String school;
    private String id;

    public static ErrandRenzhen1Fragment newInstance() {
        return new ErrandRenzhen1Fragment();
    }
    private ErrandRenzhen1Fragment fragment;
    private ArrayList<String> imgList = new ArrayList<>();
    @Bind(R.id.tv_school)
    TextView tv_school;

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

    @OnClick({R.id.iv_icon, R.id.bt_next, R.id.ll_school})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.ll_school:
                Bundle bundle = new Bundle();
                bundle.putString("errand", "yes");
                ZeroZeroSevenUtils.FragmentSwitchActivity(bfCxt, SeachSchoolListActivity.class, fragment,bundle, 3);
                break;
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
    private static final int REQUEST_SCHOOL = 3;

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
            case REQUEST_SCHOOL:
                if (data != null) {
                    school = data.getStringExtra("school");
                    id = data.getStringExtra("id");
                    if (!TextUtils.isEmpty(school)) {
                        tv_school.setText(school);
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
