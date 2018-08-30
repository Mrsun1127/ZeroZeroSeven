package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.ImageItemInfo;
import com.ffn.zerozeroseven.bean.UpdateIconInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.UpdateInfo;
import com.ffn.zerozeroseven.utlis.Constant;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;
import com.ffn.zerozeroseven.view.pop.UpdateMessagePopWindow;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by GT on 2017/11/22.
 */

public class PeopleMessAgeActivity extends BaseActivity implements View.OnClickListener {
    ImageView iv_photo;
    private Bitmap head;// 头像Bitmap
    private TextView tv_username;
    private TextView tv_userphone;
    private TextView tv_userbri;
    private TextView tv_userschool;
    private TextView tv_clazz;
    private LinearLayout ll_all;
    private UpdateMessagePopWindow popWindow;
    private DatePickerDialog datePickerDialog;
    private String name = "";
    private String phone = "";
    private String bri = "";
    private String college = "";
    private String clazz = "";

    @Override
    protected int setLayout() {
        return R.layout.activity_peoplemessage;
    }

    @Override
    protected void doMain() {
        if (userInfo != null) {
            name = userInfo.getRealName();
            phone = userInfo.getPhone();
            bri = userInfo.getBirthday();
            college = userInfo.getCollege();
            clazz = userInfo.getClazz();
            if (TextUtils.isEmpty(userInfo.getRealName())) {
                name = "";
            }
            if (TextUtils.isEmpty(userInfo.getPhone())) {
                phone = "";
            }
            if (TextUtils.isEmpty(userInfo.getBirthday())) {
                bri = "";
            }
            if (TextUtils.isEmpty(userInfo.getCollege())) {
                college = "";
            }
            if (TextUtils.isEmpty(userInfo.getClazz())) {
                clazz = "";
            }
            if (!TextUtils.isEmpty(MrsunAppCacheUtils.get(PeopleMessAgeActivity.this).getAsString("iconUrl"))) {
                Glide.with(PeopleMessAgeActivity.this)
                        .load(MrsunAppCacheUtils.get(PeopleMessAgeActivity.this).getAsString("iconUrl"))
                        .override(150, 150)
                        .into(iv_photo);
            } else {
                if (!TextUtils.isEmpty(userInfo.getAvatar())) {
                    Glide.with(PeopleMessAgeActivity.this)
                            .load(userInfo.getAvatar())
                            .override(150, 150)
                            .into(iv_photo);
                }
            }
        }

        tv_username.setText(name);
        tv_userphone.setText(phone);
        tv_userbri.setText(bri);
        tv_userschool.setText(college);
        tv_clazz.setText(clazz);

    }

    @Override
    public void initView() {
        popWindow = new UpdateMessagePopWindow(this);
        iv_photo = this.findViewById(R.id.iv_icon);
        iv_photo.setOnClickListener(this);
        TitleView titleView = findViewById(R.id.titleview);
        tv_username = findViewById(R.id.tv_username);
        tv_userphone = findViewById(R.id.tv_userphone);
        tv_userbri = findViewById(R.id.tv_userbri);
        tv_userschool = findViewById(R.id.tv_userschool);
        tv_clazz = findViewById(R.id.tv_clazz);
        ll_all = findViewById(R.id.ll_all);
        findViewById(R.id.tv_cl_name).setOnClickListener(this);
        findViewById(R.id.tv_cl_phone).setOnClickListener(this);
        findViewById(R.id.tv_cl_bri).setOnClickListener(this);
        findViewById(R.id.tv_cl_school).setOnClickListener(this);
        findViewById(R.id.tv_cl_clazz).setOnClickListener(this);
        titleView.setTopText("我的账号");
        titleView.setTvRightShow();
        titleView.setTvRightText("保存");
        titleView.setOnRightClickListener(new TitleView.OnRightClickListener() {
            @Override
            public void tvRight() {
                savePepMessage();
            }
        });
        titleView.setOnTitleListener(new TitleView.OnTitleClickListener() {
            @Override
            public void ivBack() {
                finish();
            }

            @Override
            public void ivDown() {

            }

            @Override
            public void ivMessAge() {

            }
        });

    }

    /**
     * private TextView tv_username;
     * private TextView tv_userphone;
     * private TextView tv_userbri;
     * private TextView tv_userschool;
     * private TextView tv_clazz;
     */
    private File iconFile;

    private void savePepMessage() {
        if (imgList.size() > 0) {
            iconFile = new File(imgList.get(0));
            Luban.with(this)
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
                            // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            showLoadProgress();
                        }

                        @Override
                        public void onSuccess(File file) {
                            // TODO 压缩成功后调用，返回压缩后的图片文件
                            disLoadProgress();
                            wifiGo(file);
                        }

                        @Override
                        public void onError(Throwable e) {
                            // TODO 当压缩过程出现问题时调用
                            ToastUtils.showShort("出现问题");
                        }
                    }).launch();

        } else {
            saveAndMessAge("", false);
        }

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
                        ZeroZeroSevenUtils.showCustonPop(PeopleMessAgeActivity.this, "图片太大，请选择尺寸合适的图片", iv_photo);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.D("PeopleMessAgeActivity", response);
                        UpdateIconInfo updateIconInfo = JSON.parseObject(response, UpdateIconInfo.class);
                        if (updateIconInfo.getCode() == 0) {
                            saveAndMessAge(updateIconInfo.getData().getUrl(), true);
                            MrsunAppCacheUtils.get(PeopleMessAgeActivity.this).put("iconUrl", updateIconInfo.getData().getUrl());
                        } else {
                            ZeroZeroSevenUtils.showCustonPop(PeopleMessAgeActivity.this, updateIconInfo.getMessage(), iv_photo);
                        }
                    }
                });
    }

    public void saveAndMessAge(String iconUrl, boolean needIcon) {
        String postName = tv_username.getText().toString();
        String postBri = tv_userbri.getText().toString();
        String postSchool = tv_userschool.getText().toString();
        String postClazz = tv_clazz.getText().toString();
        userInfo.setRealName(postName);
        userInfo.setBirthday(postBri);
        userInfo.setCollege(postSchool);
        userInfo.setClazz(postClazz);
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.setFunctionName("UpdateUserDetail");
        UpdateInfo.ParametersBean parametersBean = new UpdateInfo.ParametersBean();
        parametersBean.setBirthday(postBri);
        parametersBean.setRealName(postName);
        parametersBean.setClazz(postClazz);
        parametersBean.setCollege(postSchool);
        if (needIcon) {
            parametersBean.setAvatar(iconUrl);
            userInfo.setAvatar(iconUrl);
        }
        updateInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(PeopleMessAgeActivity.this);
        okGoUtils.httpPostJSON(updateInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final String code = JsonUtil.getFieldValue(response, "code");

                if (code.equals("0")) {
                    BaseAppApplication.getInstance().setLoginUser(userInfo);
                    SharePrefUtils.saveObject(PeopleMessAgeActivity.this, "userInfo", userInfo);
                    SharePrefUtils.setBoolean(PeopleMessAgeActivity.this, "bri", true);
                    Toast.makeText(PeopleMessAgeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    ToastUtils.showShort("服务器正忙 请稍后再试");
                }
            }
        });


    }

    private void showPop(final TextView view) {
        popWindow.showAtLocation(ll_all, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        lightOff();
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1.0f;
                getWindow().setAttributes(layoutParams);
            }
        });
        popWindow.setMlistener(new UpdateMessagePopWindow.OnButonClikListener() {

            @Override
            public void OnBtSub(String content) {
                view.setText(content);
                popWindow.dismiss();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
//                showTypeDialog();
                pickImage();
                break;
            case R.id.tv_cl_name:
                showPop(tv_username);
                break;
            case R.id.tv_cl_phone:
                showPop(tv_userphone);
                break;
            case R.id.tv_cl_bri:
//                setbri();
                setBri();
                break;
            case R.id.tv_cl_school:
                showPop(tv_userschool);
                break;
            case R.id.tv_cl_clazz:
                showPop(tv_clazz);
                break;
        }
    }

    //获取日期格式器对象
    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    private void setbri() {
        datePickerDialog = new DatePickerDialog(PeopleMessAgeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                if (month == 11) {
                    month = 12;
                } else {
                    month = month + 1;
                }
                tv_userbri.setText(year + "." + month + "." + day);
                datePickerDialog.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void setBri() {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
                //获取当前时间
                tv_userbri.setText(simpleDateFormat.format(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private ArrayList<String> imgList = new ArrayList<>();
    private static final int REQUEST_IMAGE = 2;

    /**
     * 相册
     */
    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);
        selector.count(1);
        selector.multi();
        selector.origin(imgList);
        selector.start(PeopleMessAgeActivity.this, REQUEST_IMAGE);

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
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();


            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PeopleMessAgeActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //动态的请求权限
                    ActivityCompat.requestPermissions(PeopleMessAgeActivity.this, new String[]{Manifest.permission.CAMERA},
                            0x11);
                    dialog.dismiss();
                } else {
                    Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    PeopleMessAgeActivity.this.startActivityForResult(openCameraIntent, 2);
                    dialog.dismiss();
                }


            }
        });
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE://拍照
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        imgList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        Glide.with(PeopleMessAgeActivity.this).load(imgList.get(0)).override(150, 150).into(iv_photo);
                    }
                }
                break;
        }
    }

}
