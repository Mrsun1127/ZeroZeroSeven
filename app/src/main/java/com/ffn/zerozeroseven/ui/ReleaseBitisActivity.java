package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.BitisAdapter;
import com.ffn.zerozeroseven.adapter.BitisScrollAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.AllItemDecoration;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class ReleaseBitisActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    private BitisScrollAdapter bitisScrollAdapter;

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
                ToastUtils.showShort("发布");
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
                tv_photo_count.setText(imgList.size()+"/3");
                bitisScrollAdapter.cleanDates();
                bitisScrollAdapter.addAll(imgList);
                ib_add.setVisibility(View.VISIBLE);
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
                            for (int i = 0; i < imgList.size(); i++) {
                                bitisScrollAdapter.cleanDates();
                                bitisScrollAdapter.addAll(imgList);
                            }
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
