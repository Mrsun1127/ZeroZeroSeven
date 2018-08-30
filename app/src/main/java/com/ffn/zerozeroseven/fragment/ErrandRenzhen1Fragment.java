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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.bean.CodeInfo;
import com.ffn.zerozeroseven.bean.OkInfo;
import com.ffn.zerozeroseven.bean.RrenzhengInfo;
import com.ffn.zerozeroseven.bean.UpdateIconInfo;
import com.ffn.zerozeroseven.bean.requsetbean.JiaoYanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SendCodeInfo;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.LoginActivity;
import com.ffn.zerozeroseven.ui.PeopleMessAgeActivity;
import com.ffn.zerozeroseven.ui.ReleaseJumpShopActivity;
import com.ffn.zerozeroseven.ui.SeachSchoolListActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

public class ErrandRenzhen1Fragment extends BaseFragment implements ActionSheet.ActionSheetListener {

    private String school;
    private String id;

    public static ErrandRenzhen1Fragment newInstance() {
        return new ErrandRenzhen1Fragment();
    }

    private ErrandRenzhen1Fragment fragment;
    private ArrayList<String> imgList = new ArrayList<>();
    @Bind(R.id.tv_school)
    TextView tv_school;
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_card)
    EditText et_card;
    @Bind(R.id.tv_phone)
    TextView tv_phone;

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        fragment = this;
        tv_phone.setText(userInfo.getPhone());
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_renzheng1;
    }

    @Bind(R.id.iv_icon)
    CircleImageView iv_icon;
    String[] items = new String[]{"男", "女"};

    @OnClick({R.id.iv_icon, R.id.bt_next, R.id.ll_school, R.id.ll_sex})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.ll_sex:
                ActionSheet.createBuilder(bfCxt, getFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setOtherButtonTitles(items[0], items[1])
                        .setCancelableOnTouchOutside(true)
                        .setListener(this).show();
                break;
            case R.id.ll_school:
                Bundle bundle = new Bundle();
                bundle.putString("errand", "yes");
                ZeroZeroSevenUtils.FragmentSwitchActivity(bfCxt, SeachSchoolListActivity.class, fragment, bundle, 3);
                break;
            case R.id.bt_next:
                userCheck();
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


    private void userCheck() {
        String name = et_name.getText().toString().trim();
        String school = tv_school.getText().toString().trim();
        String idCard = et_card.getText().toString().trim();
        String phone = tv_phone.getText().toString().trim();
        String sex = tv_sex.getText().toString().trim();
        if (imgList.size() > 0) {
            if (!">".equals(school)) {
                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(idCard)) {
                        if (!">".equals(sex)) {
                            vetifyCode();
                        } else {
                            ToastUtils.showShort("请选择性别");
                        }
                    } else {
                        ToastUtils.showShort("请输入身份证号码");
                    }
                } else {
                    ToastUtils.showShort("请输入姓名");
                }
            } else {
                ToastUtils.showShort("请选择学校");
            }
        } else {
            ToastUtils.showShort("请选择头像");
        }

    }

    private void vetifyCode() {
        saveIcon();
    }

    private File iconFile;

    private void saveIcon() {
        iconFile = new File(imgList.get(0));
        Luban.with(bfCxt)
                .load(iconFile)
                .ignoreBy(100)
                .setTargetDir(BaseAppApplication.context.getExternalCacheDir().getAbsolutePath())
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        showLoadProgress();
                    }

                    @Override
                    public void onSuccess(File file) {
                        disLoadProgress();
                        wifiGo(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("出现问题");
                    }
                }).launch();
    }

    private void wifiGo(File file) {
        OkHttpUtils.post()
                .url("https://api.lingling7.com/lingling7-server/upload")
                .addHeader("Authorization", "Bearer " + userInfo.getToken())
                .addParams("uploadType", "IMAGE")
                .addFile("file", file.getName(), file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("图片太大，请选择尺寸合适的图片");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        UpdateIconInfo updateIconInfo = JSON.parseObject(response, UpdateIconInfo.class);
                        if (updateIconInfo.getCode() == 0) {
                            saveAndMessAge(updateIconInfo.getData().getUrl());
                        } else {
                            ToastUtils.showShort(updateIconInfo.getMessage());
                        }
                    }
                });
    }

    private void saveAndMessAge(String url) {
        RrenzhengInfo rrenzhengInfo = ErrandAuitActivity.mInstance.get().getRrenzhengInfo();
        rrenzhengInfo.setFunctionName("AddOrUpdateErrandUserCheck");
        RrenzhengInfo.ParametersBean parametersBean1 = new RrenzhengInfo.ParametersBean();
        parametersBean1.setAvatar(url);
        parametersBean1.setIdCard(et_card.getText().toString().trim());
        parametersBean1.setRealName(et_name.getText().toString().trim());
        parametersBean1.setSchoolId(id);
        if ("男".equals(tv_sex.getText().toString().trim())) {
            parametersBean1.setSex("1");
        } else {
            parametersBean1.setSex("0");
        }
        parametersBean1.setUserPhone(userInfo.getPhone());
        rrenzhengInfo.setParameters(parametersBean1);
        ErrandAuitActivity.mInstance.get().setRrenzhengInfo(rrenzhengInfo);
        ErrandAuitActivity.mInstance.get().goVp(1);
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

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Bind(R.id.tv_sex)
    TextView tv_sex;

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        actionSheet.dismiss();
        tv_sex.setText(items[index]);
    }
}
