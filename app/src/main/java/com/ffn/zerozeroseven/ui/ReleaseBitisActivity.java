package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.BitisAdapter;
import com.ffn.zerozeroseven.adapter.BitisScrollAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.UrlInfo;
import com.ffn.zerozeroseven.bean.requsetbean.FaTieInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.AllItemDecoration;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ReleaseBitisActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    @Bind(R.id.et_talk)
    EditText et_talk;
    private BitisScrollAdapter bitisScrollAdapter;
    private String text;

    @Override
    protected int setLayout() {
        return R.layout.activity_release_bitis;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("我要上墙");
        topView.setTvRightText("发布");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                text = et_talk.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    return;
                }
                if (imgList != null && imgList.size() > 0) {
                    imgSub(imgList);
                } else {
                    textSub();
                }
            }

            @Override
            public void Back() {
                finish();
            }
        });
        bitisScrollAdapter = new BitisScrollAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new AllItemDecoration(20, 20));
        recyclerView.setAdapter(bitisScrollAdapter);
        bitisScrollAdapter.setOnItemCloseViewClick(new BitisScrollAdapter.OnItemCloseClick() {
            @Override
            public void onClick(View view, int position) {
                imgList.remove(position);
                tv_photo_count.setTextColor(getResources().getColor(R.color.line6));
                tv_photo_count.setText(imgList.size() + "/3");
                bitisScrollAdapter.cleanDates();
                bitisScrollAdapter.addAll(imgList);
                ib_add.setVisibility(View.VISIBLE);
            }
        });
    }

    private void textSub() {
        finalImgSub(null);
    }

    private ArrayList<File> files;

    private void imgSub(final ArrayList<String> imgList) {
        for (int i = 0; i < imgList.size(); i++) {
            final int finalI = i;
            Luban.with(ReleaseBitisActivity.this)
                    .load(imgList.get(i))
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
                            if (files == null) {
                                files = new ArrayList<>();
                            }
                            files.add(file);
                            if (finalI == imgList.size() - 1) {
                                saveImg(files);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtils.showShort("出现问题");
                        }
                    }).launch();
        }

    }

    private void saveImg(ArrayList<File> files) {
        Map<String, File> fileMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            fileMap.put("files", files.get(i));
        }
        PostFormBuilder post = OkHttpUtils.post();
        post
                .url("https://api.lingling7.com/lingling7-server/upload/multiple")
                .addHeader("Authorization", "Bearer " + userInfo.getToken())
                .addParams("uploadType", "IMAGE");
        for (int i = 0; i < files.size(); i++) {
            post.addFile("files", files.get(i).getName(), files.get(i));
        }
        post.build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ZeroZeroSevenUtils.showCustonPop(ReleaseBitisActivity.this, "上传的图片过大", et_talk);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.D("response", response);
                        UrlInfo urlInfo = JSON.parseObject(response, UrlInfo.class);
                        if (urlInfo.getCode() == 0) {
                            finalImgSub(urlInfo.getData().getUrls());
                        } else {
                            ZeroZeroSevenUtils.showCustonPop(ReleaseBitisActivity.this, urlInfo.getMessage(), et_talk);
                        }
                    }
                });
    }

    String[] strings;

    private void finalImgSub(List<String> urls) {
        FaTieInfo faTieInfo = new FaTieInfo();
        faTieInfo.setFunctionName("UserPosting");
        FaTieInfo.ParametersBean parametersBean = new FaTieInfo.ParametersBean();
        parametersBean.setContent(text);
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        if (urls != null && urls.size() > 0) {
            strings = new String[urls.size()];
            for (int i = 0; i < urls.size(); i++) {
                strings[i] = urls.get(i);
            }
            parametersBean.setImages(strings);
        }
        faTieInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ReleaseBitisActivity.this);
        okGoUtils.httpPostJSON(faTieInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    ZeroZeroSevenUtils.showCustonPop(ReleaseBitisActivity.this, "您的帖子发布成功，工作人员正在审核", et_talk, ReleaseBitisActivity.this);
                } else {
                    ZeroZeroSevenUtils.showCustonPop(ReleaseBitisActivity.this, errorCodeInfo.getMessage(), et_talk);
                }
            }
        });
    }

    @Override
    protected void doMain() {

    }

    private ArrayList<String> imgList = new ArrayList<>();
    private static final int REQUEST_IMAGE = 2;

    /**
     * 相册
     */
    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);
        selector.count(3);
        selector.multi();
        selector.origin(imgList);
        selector.start(ReleaseBitisActivity.this, REQUEST_IMAGE);

    }

    @Bind(R.id.ib_add)
    ImageButton ib_add;

    @OnClick({R.id.ib_add})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.ib_add:
                if (ContextCompat.checkSelfPermission(ReleaseBitisActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //动态的请求权限
                    ActivityCompat.requestPermissions(ReleaseBitisActivity.this, new String[]{Manifest.permission.CAMERA},
                            0x11);
                } else {
                    pickImage();
                }
                break;

        }
    }

    @Bind(R.id.tv_photo_count)
    TextView tv_photo_count;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE://拍照
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        imgList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        if (imgList != null && imgList.size() > 0) {
                            tv_photo_count.setText(imgList.size() + "/3");
                            if (imgList.size() == 3) {
                                ib_add.setVisibility(View.GONE);
                                tv_photo_count.setTextColor(getResources().getColor(R.color.money));
                            } else {
                                ib_add.setVisibility(View.VISIBLE);
                                tv_photo_count.setTextColor(getResources().getColor(R.color.line6));
                            }
                            bitisScrollAdapter.cleanDates();
                            bitisScrollAdapter.addAll(imgList);
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
                pickImage();//拍照
            } else {
                // Permission Denied
                Toast.makeText(this, "请求权限被拒绝", Toast.LENGTH_LONG);
            }
        }
    }
}
