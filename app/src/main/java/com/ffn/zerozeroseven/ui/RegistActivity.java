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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppManger;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseCaheActivity;
import com.ffn.zerozeroseven.bean.CodeInfo;
import com.ffn.zerozeroseven.bean.OkInfo;
import com.ffn.zerozeroseven.bean.requsetbean.JiaoYanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SendCodeInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MyCountTimer;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.MyTextWatcher;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/15.
 */

public class RegistActivity extends BaseCaheActivity implements View.OnClickListener {
    ImageView iv_back;
    Button bt_reg;
    Button bt_send;
    TextView tv_bottom;
    ImageView iv_photo;
    EditText et_phoneNumber;
    EditText et_code;
    private Bitmap head;// 头像Bitmap
    private String regCode;
    private MyCountTimer timer;
    @Bind(R.id.et_yaoqing)
    EditText et_yaoqing;
    @Override
    protected void runMainThread() {
        initView();
        ZeroZeroSevenUtils.setUnderline(RegistActivity.this,tv_bottom,"已有账号？点击这里登录");
    }

    private void initView() {
        ButterKnife.bind(this);
        AppManger.getAppManager().addActivity(this);
        regCode="";
        iv_back = this.findViewById(R.id.iv_back);
        bt_reg = this.findViewById(R.id.bt_reg);
        bt_send = this.findViewById(R.id.bt_send);
        tv_bottom = this.findViewById(R.id.tv_bottom);
        iv_photo = this.findViewById(R.id.iv_icon);
        et_phoneNumber = this.findViewById(R.id.et_phoneNumber);
        et_code = this.findViewById(R.id.et_code);
        iv_back.setOnClickListener(this);
        bt_reg.setOnClickListener(this);
        tv_bottom.setOnClickListener(this);
        iv_photo.setOnClickListener(this);
        bt_send.setOnClickListener(this);
        timer = new MyCountTimer(bt_send);
        List<EditText> list = new ArrayList<>();
        list.add(et_phoneNumber);
        list.add(et_code);
        et_phoneNumber.addTextChangedListener(new MyTextWatcher(list, bt_reg));
        et_code.addTextChangedListener((new MyTextWatcher(list, bt_reg)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_reg:
                if(TextUtils.isEmpty(et_code.getText().toString().trim())){
                    ToastUtils.showShort("请输入验证码");
                    return;
                }
                Yanzheng();
                break;
            case R.id.tv_bottom:
                ZeroZeroSevenUtils.SwitchActivity(RegistActivity.this,LoginActivity.class,null);
                finish();
                break;
            case R.id.iv_icon:
                showTypeDialog();
                break;
            case R.id.bt_send:
                SendCodeInfo codeInfo=new SendCodeInfo();
                codeInfo.setFunctionName("SendAuthcode");
                codeInfo.setId("1");
                SendCodeInfo.ParametersBean parametersBean=new SendCodeInfo.ParametersBean();
                parametersBean.setEvent("REGISTER");
                parametersBean.setPhone(et_phoneNumber.getText().toString().trim());
                codeInfo.setParameters(parametersBean);
                httpPostJSON(codeInfo);
                call.enqueue(new Callback() {
                    //请求失败时调用
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    //请求成功时调用
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    CodeInfo info= JsonUtil.parseJsonToBean(response.body().string(),CodeInfo.class);
                                    if(info.getCode()==0){
                                        regCode = info.getData().getAuthcode();
                                        ToastUtils.showShort("发送成功!");
                                        timer.start();
                                        LogUtils.D("RegActivity",regCode);
                                    }else{
                                        ToastUtils.showShort(info.getMessage());
                                    }
                                }catch (Exception e){
                                    ToastUtils.showShort("服务器异常，请稍后再试");
                                }
                            }
                        });
                    }
                });
                break;
        }
    }

    private void Yanzheng() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                showLoadProgress();
            }
        });
        JiaoYanInfo jiaoYanInfo=new JiaoYanInfo();
        jiaoYanInfo.setFunctionName("ValidateAuthcode");
        JiaoYanInfo.ParametersBean parametersBean=new JiaoYanInfo.ParametersBean();
        parametersBean.setAuthcode(et_code.getText().toString().trim());
        parametersBean.setEvent("REGISTER");
        parametersBean.setPhone(et_phoneNumber.getText().toString().trim());
        jiaoYanInfo.setParameters(parametersBean);
        httpPostJSON(jiaoYanInfo);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        ZeroZeroSevenUtils.showCustonPop(RegistActivity.this,"服务器异常",et_code);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                    }
                });
                final OkInfo okInfo= JSON.parseObject(response.body().string(),OkInfo.class);
                if(okInfo.getCode()==0){
                    MobclickAgent.onEvent(RegistActivity.this, "注册");
                    if(okInfo.getData().isCorrect()){
                        Bundle bundle=new Bundle();
                        bundle.putString("rgCode",et_code.getText().toString().trim());
                        bundle.putString("phone",et_phoneNumber.getText().toString().trim());
                        bundle.putString("yaoqing",et_yaoqing.getText().toString().trim());
                        ZeroZeroSevenUtils.SwitchActivity(RegistActivity.this,SetPassWordActivity.class,bundle);
                        finish();
                    }else{
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                ZeroZeroSevenUtils.showCustonPop(RegistActivity.this,"验证码错误",et_code);
                            }
                        });
                    }

                }else{
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ZeroZeroSevenUtils.showCustonPop(RegistActivity.this,okInfo.getMessage(),et_code);
                        }
                    });
                }
            }
        });
    }


    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
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
                if (ContextCompat.checkSelfPermission(RegistActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //动态的请求权限
                    ActivityCompat.requestPermissions(RegistActivity.this, new String[]{Manifest.permission.CAMERA},
                            0x11);
                    dialog.dismiss();
                }else{
                    Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    RegistActivity.this.startActivityForResult(openCameraIntent, 2);
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
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        iv_photo.setImageBitmap(head);// 用ImageView显示出来
                    }
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        iv_photo.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void resoluTion(String cache) {

    }

    @Override
    protected int setCacheTime() {
        return 0;
    }

    @Override
    protected String setCacheKey() {
        return null;
    }

    @Override
    protected HashMap<String, String> setUrl() {
        return null;
    }

    @Override
    protected HashMap<String, String> setRequstMap() {
        return null;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_regist;
    }

    @Override
    protected void doSuccess(String response) {

    }

    @Override
    protected void doError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManger.getAppManager().finishActivity(this);
        timer.onFinish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
