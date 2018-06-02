package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.TypeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.FaTieInfo;
import com.ffn.zerozeroseven.bean.requsetbean.QiangTypeInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TitleView;
import com.ffn.zerozeroseven.view.TopView;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/23.
 */

public class MineWantGoQiangActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_topone;
    private TextView tv_bottom;
    private TextView tv_center;
    private CheckBox cb_niming;
    private EditText et_top;
    private EditText et_content;
    private EditText et_writer;
    private FaTieInfo.ParametersBean parametersBean;
    private FaTieInfo faTieInfo;
    private boolean isNiMing = false;
    private LinearLayout ll_niming;
    private String[] type;
    private TopView topView;
    private String showType;
    private RadioButton rb_love;
    private RadioButton rb_good;
    private RadioButton rb_find;
    private RadioButton rb_friend;
    @Override
    protected int setLayout() {
        return R.layout.activity_relaseactivited;
    }

    @Override
    protected void doMain() {
        String cache = MrsunAppCacheUtils.get(MineWantGoQiangActivity.this).getAsString("cache");
        if (TextUtils.isEmpty(cache)) {
            requestRype();
        } else {
            TypeInfo info = JSON.parseObject(cache, TypeInfo.class);
            type = new String[info.getData().getItems().size()];
            for (int i = 0; i < info.getData().getItems().size(); i++) {
                type[i] = info.getData().getItems().get(i).getDicKey();
            }
        }
        showType = getIntent().getStringExtra("showType");
        switch (showType) {
            case "01":
                break;
            case "02":
                rb_good.setChecked(true);
                sub_type = 1;
                tv_topone.setText("标题");
                et_top.setHint("输入您的标题");
                tv_center.setText("内容");
                et_content.setHint("输入您的话题内容");
                tv_bottom.setVisibility(View.GONE);
                et_writer.setVisibility(View.GONE);
                tv_bottom.setText("你的联系方式");
                et_writer.setHint("手机号码/QQ/WeChat");
                ll_niming.setVisibility(View.GONE);
                break;
            case "03":
                rb_find.setChecked(true);
                sub_type = 2;
                tv_topone.setText("标题");
                et_top.setHint("输入您的寻物求助标题");
                tv_center.setText("描述");
                et_content.setHint("输入您的物品描述");
                tv_bottom.setText("你的联系方式");
                et_writer.setHint("手机号码/QQ/WeChat");
                ll_niming.setVisibility(View.GONE);
                tv_bottom.setVisibility(View.GONE);
                et_writer.setVisibility(View.GONE);
                break;
            case "04":
                rb_friend.setChecked(true);
                sub_type = 3;
                tv_topone.setText("标题");
                et_top.setHint("输入您的标题");
                tv_center.setText("内容");
                et_content.setHint("输入您的话题内容");
                tv_bottom.setText("你的联系方式");
                et_writer.setHint("手机号码/QQ/WeChat");
                tv_bottom.setVisibility(View.GONE);
                et_writer.setVisibility(View.GONE);
                ll_niming.setVisibility(View.GONE);
                break;
        }
    }

    private void requestRype() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                showLoadProgress();
            }
        });

        QiangTypeInfo typeInfo = new QiangTypeInfo();
        typeInfo.setFunctionName("ListPostType");
        httpPostJSON(typeInfo);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
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
                TypeInfo info = JSON.parseObject(response.body().string(), TypeInfo.class);
                if (info.getCode() == 0) {
                    MrsunAppCacheUtils.get(MineWantGoQiangActivity.this).put("cache", JSON.toJSONString(info));
                    type = new String[info.getData().getItems().size()];
                    for (int i = 0; i < info.getData().getItems().size(); i++) {
                        type[i] = info.getData().getItems().get(i).getDicKey();
                    }
                }
            }
        });
    }

    private int sub_type;

    @Override
    public void initView() {
        BaseAppApplication.getInstance().addActivity(this);
        ll_niming = findViewById(R.id.ll_niming);
        tv_topone = findViewById(R.id.tv_topone);
        tv_bottom = findViewById(R.id.tv_bottom);
        tv_center = findViewById(R.id.tv_center);
        et_top = findViewById(R.id.et_top);
        et_content = findViewById(R.id.et_content);
        et_writer = findViewById(R.id.et_writer);
        cb_niming = findViewById(R.id.cb_niming);
        topView = findViewById(R.id.topView);
        cb_niming.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isNiMing = b;
            }
        });
        topView.setTvRightText("发表");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                releaseTalk();
            }

            @Override
            public void Back() {
                finish();
            }
        });

        rb_love=findViewById(R.id.rb_love);
        rb_love.setOnClickListener(this);
        rb_good=findViewById(R.id.rb_good);
        rb_good.setOnClickListener(this);
        rb_find=findViewById(R.id.rb_find);
        rb_find.setOnClickListener(this);
        rb_friend=findViewById(R.id.rb_friend);
        rb_friend.setOnClickListener(this);

    }

    public void requestDa(FaTieInfo faTieInfo) {
        showLoadProgress();
        httpPostJSON(faTieInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                disLoadProgress();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                String code = JsonUtil.getFieldValue(response.body().string(), "code");
                if (code.equals("0")) {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
//                            ZeroZeroSevenUtils.showCustonPop(MineWantGoQiangActivity.this,"正在审核中，通过之后将会第一时间展示您的帖子",tv_topone);
                            ToastUtils.showShort("正在审核中，通过之后将会第一时间展示您的帖子");
                            finish();

                        }
                    });
                } else {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShort("发帖失败，请稍后再试");
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_love:
                sub_type = 0;
                tv_topone.setText("标题");
                et_top.setHint("输入您的标题");
                tv_center.setText("内容");
                et_content.setHint("输入您的话题内容");
                tv_bottom.setVisibility(View.GONE);
                et_writer.setVisibility(View.GONE);
                ll_niming.setVisibility(View.GONE);
                break;
            case R.id.rb_good:
                sub_type = 1;
                tv_topone.setText("标题");
                et_top.setHint("输入您的标题");
                tv_center.setText("内容");
                et_content.setHint("输入您的话题内容");
                tv_bottom.setVisibility(View.GONE);
                et_writer.setVisibility(View.GONE);
                tv_bottom.setText("你的联系方式");
                et_writer.setHint("手机号码/QQ/WeChat");
                ll_niming.setVisibility(View.GONE);
                break;
            case R.id.rb_find:
                sub_type = 2;
                tv_topone.setText("标题");
                et_top.setHint("输入您的寻物求助标题");
                tv_center.setText("描述");
                et_content.setHint("输入您的物品描述");
                tv_bottom.setText("你的联系方式");
                et_writer.setHint("手机号码/QQ/WeChat");
                ll_niming.setVisibility(View.GONE);
                tv_bottom.setVisibility(View.GONE);
                et_writer.setVisibility(View.GONE);
                break;
            case R.id.rb_friend:
                sub_type = 3;
                tv_topone.setText("标题");
                et_top.setHint("输入您的标题");
                tv_center.setText("内容");
                et_content.setHint("输入您的话题内容");
                tv_bottom.setText("你的联系方式");
                et_writer.setHint("手机号码/QQ/WeChat");
                tv_bottom.setVisibility(View.GONE);
                et_writer.setVisibility(View.GONE);
                ll_niming.setVisibility(View.GONE);
                break;
        }
    }

    public void releaseTalk() {
        String title = et_top.getText().toString().trim();
        String content = et_content.getText().toString().trim();
        String bottom = et_writer.getText().toString().trim();
        if (!TextUtils.isEmpty(title)) {
            if (!TextUtils.isEmpty(content)) {

                faTieInfo = new FaTieInfo();
                faTieInfo.setFunctionName("UserPosting");
                parametersBean = new FaTieInfo.ParametersBean();
                parametersBean.setContent(content);
                parametersBean.setUserId(Integer.parseInt(userId));
                parametersBean.setTitle(title);
                if (!isNiMing) {
                    parametersBean.setIsAnonymity("1");
                } else {
                    parametersBean.setIsAnonymity("0");
                }

            } else {
                ToastUtils.showShort("请输入内容");
                return;
            }
        } else {
            ToastUtils.showShort("请输入标题");
            return;
        }


        switch (sub_type) {
            case 0:
                MobclickAgent.onEvent(this, "表白帖");
                parametersBean.setPostType(type[0]);
                faTieInfo.setParameters(parametersBean);
                requestDa(faTieInfo);
                break;
            case 1:
                MobclickAgent.onEvent(this, "技术帖");
                parametersBean.setPostType(type[1]);
                faTieInfo.setParameters(parametersBean);
                requestDa(faTieInfo);
                break;
            case 2:
                MobclickAgent.onEvent(this, "寻物帖");
                parametersBean.setPostType(type[2]);
                faTieInfo.setParameters(parametersBean);
                requestDa(faTieInfo);
                break;
            case 3:
                MobclickAgent.onEvent(this, "交友帖");
                parametersBean.setPostType(type[3]);
                faTieInfo.setParameters(parametersBean);
                requestDa(faTieInfo);
                break;
            default:
                MobclickAgent.onEvent(this, "表白帖");
                parametersBean.setPostType(type[0]);
                faTieInfo.setParameters(parametersBean);
                requestDa(faTieInfo);
                break;
        }
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
