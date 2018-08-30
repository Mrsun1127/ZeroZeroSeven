package com.ffn.zerozeroseven.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ErrandHelpAdapter;
import com.ffn.zerozeroseven.adapter.ErrandMineRunAdapter;
import com.ffn.zerozeroseven.adapter.PopWeightAdapter;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RrunnerPayInfo;
import com.ffn.zerozeroseven.bean.RunTypeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RAddRunAdrInfo;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.PayMoneyNewActivity;
import com.ffn.zerozeroseven.ui.RunAdrListActivity;
import com.ffn.zerozeroseven.ui.WebViewActivity;
import com.ffn.zerozeroseven.utlis.ScreenUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandHelpMineRunFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.ll_all)
    LinearLayout ll_all;
    @Bind(R.id.ll_bot)
    LinearLayout ll_bot;
    @Bind(R.id.tv_weight)
    TextView tv_weight;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_runnerlevel)
    TextView tv_runnerlevel;
    private EasyPopup mCirclePop;
    private static final String[] CHANNELS = new String[]{"快递", "文件", "美食", "电子产品", "服饰", "鲜花", "钥匙", "其他"};
    private static final int[] DRABABLES = new int[]{R.drawable.run_kuaidi, R.drawable.run_file, R.drawable.run_food, R.drawable.run_computer, R.drawable.run_yifu, R.drawable.run_flowers, R.drawable.run_key, R.drawable.run_other};
    private static final int[] DRABABLES_NOR = new int[]{R.drawable.run_kuaidi_nor, R.drawable.run_file_nor, R.drawable.run_food_nor, R.drawable.run_computer_nor, R.drawable.run_yifu_nor, R.drawable.run_flowers_nor, R.drawable.run_key_nor, R.drawable.run_other_nor};
    private RunTypeInfo runTypeInfo;
    private ErrandHelpAdapter errandHelpAdapter;
    private List<String> demoList = new ArrayList<>();
    private List<String> levelList = new ArrayList<>();
    private PopWeightAdapter weightAdapter;
    private TextView tv_center;
    private int clickSwitch = -1;
    @Bind(R.id.tv_showmoney)
    TextView tv_showmoney;
    private ErrandHelpMineRunFragment fragment;
    private RAddRunAdrInfo rAddRunAdrInfo;
    private RAddRunAdrInfo rAddRunAdrInfo1;
    private RAddRunAdrInfo rAddRunAdrInfo2;

    public static ErrandHelpMineRunFragment newInstance() {
        return new ErrandHelpMineRunFragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        fragment = this;
        initWeightPop();
        tv_rmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_showmoney.setText(tv_rmoney.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    String weight1 = "1-10Kg";

    private void initWeightPop() {
        mCirclePop = EasyPopup.create()
                .setContentView(bfCxt, R.layout.pop_run_weight)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                .setWidth(ScreenUtils.getScreenWidth())
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                .apply();
        RelativeLayout rl_left = mCirclePop.findViewById(R.id.rl_left);
        tv_center = mCirclePop.findViewById(R.id.tv_center);
        RelativeLayout rl_right = mCirclePop.findViewById(R.id.rl_right);
        RecyclerView recycleview = mCirclePop.findViewById(R.id.recycleview);
        recycleview.setLayoutManager(new LinearLayoutManager(bfCxt));
        weightAdapter = new PopWeightAdapter(bfCxt);
        recycleview.setAdapter(weightAdapter);
        weightAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                weightAdapter.setClickPosition(position);
            }
        });
        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCirclePop.dismiss();
            }
        });
        rl_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCirclePop.dismiss();
                if (weightAdapter.getClickPosition() != -1) {
                    switch (clickSwitch) {
                        case 0:
                            tv_weight.setText(weightAdapter.getItem(weightAdapter.getClickPosition()));
                            switch (weightAdapter.getClickPosition()) {
                                case 0:
                                    weight1 = "1-10kg";
                                    break;
                                case 1:
                                    weight1 = "10-20kg";
                                    break;
                                case 2:
                                    weight1 = "20-30kg";
                                    break;
                                case 3:
                                    weight1 = "30kg以上";
                                    break;
                            }
                            break;
                        case 1:
                            tv_runnerlevel.setText(weightAdapter.getItem(weightAdapter.getClickPosition()));
                            break;
                    }
                }
            }
        });
        demoList.add("10kg/10元");
        demoList.add("10-20kg/20元");
        demoList.add("20-30kg/30元");
        demoList.add("30kg以上/40元");
        levelList.add("5星跑腿");
        levelList.add("4星跑腿");
        levelList.add("3星跑腿");
        levelList.add("2星跑腿");
        levelList.add("1星跑腿");
    }

    @Override
    public void initDate() {
        runTypeInfo = new RunTypeInfo();
        List<RunTypeInfo.PlacesBean> places = new ArrayList<>();
        for (int i = 0; i < CHANNELS.length; i++) {
            RunTypeInfo.PlacesBean placesBean = new RunTypeInfo.PlacesBean();
            placesBean.setDrawable(DRABABLES[i]);
            placesBean.setDrawablenor(DRABABLES_NOR[i]);
            placesBean.setTitle(CHANNELS[i]);
            places.add(placesBean);
        }
        runTypeInfo.setPlaces(places);
        errandHelpAdapter = new ErrandHelpAdapter(bfCxt);
        recycleview.setLayoutManager(new GridLayoutManager(bfCxt, 4));
        recycleview.setAdapter(errandHelpAdapter);
        errandHelpAdapter.addAll(runTypeInfo.getPlaces());
        errandHelpAdapter.setClickPosition(0);
        errandHelpAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                errandHelpAdapter.setClickPosition(position);
            }
        });
    }

    private void setBri() {
        TimePickerView pvTime = new TimePickerView.Builder(bfCxt, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");// HH:mm:ss
                //获取当前时间
                tv_time.setText(simpleDateFormat.format(date) + ":00左右");
            }
        }).setType(new boolean[]{false, false, false, true, false, false})// 默认全部显示
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    @OnClick({R.id.tv_web, R.id.ll_f, R.id.ll_s, R.id.tv_selectSadr, R.id.tv_selectFadr, R.id.ll_weight, R.id.ll_level, R.id.ll_lettime, R.id.bt_pay})
    void setOnClicks(View v) {

        switch (v.getId()) {
            case R.id.tv_web:
                Bundle bundle9 = new Bundle();
                bundle9.putString("title", "发布协议");
                bundle9.putString("url", AppConfig.WEBFABUCONTENT);
                ZeroZeroSevenUtils.SwitchActivity(getContext(), WebViewActivity.class, bundle9);
                break;
            case R.id.tv_selectSadr:
                Bundle bundle = new Bundle();
                bundle.putString("type", "s");
                ZeroZeroSevenUtils.FragmentSwitchActivity(bfCxt, RunAdrListActivity.class, fragment, bundle, 2);
                break;
            case R.id.tv_selectFadr:
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "f");
                ZeroZeroSevenUtils.FragmentSwitchActivity(bfCxt, RunAdrListActivity.class, fragment, bundle1, 2);
                break;
            case R.id.ll_s:
                Bundle bundle5 = new Bundle();
                bundle5.putString("type", "s");
                ZeroZeroSevenUtils.FragmentSwitchActivity(bfCxt, RunAdrListActivity.class, fragment, bundle5, 2);
                break;
            case R.id.ll_f:
                Bundle bundle4 = new Bundle();
                bundle4.putString("type", "f");
                ZeroZeroSevenUtils.FragmentSwitchActivity(bfCxt, RunAdrListActivity.class, fragment, bundle4, 2);
                break;
            case R.id.ll_weight:
                clickSwitch = 0;
                weightAdapter.setClickPosition(-1);
                tv_center.setText("请选择物品重量");
                weightAdapter.cleanDates();
                weightAdapter.addAll(demoList);
                mCirclePop.showAtLocation(ll_bot, YGravity.ALIGN_BOTTOM, 0, 0);
                break;
            case R.id.ll_level:
                clickSwitch = 1;
                weightAdapter.setClickPosition(-1);
                tv_center.setText("请选择星级跑腿");
                weightAdapter.cleanDates();
                weightAdapter.addAll(levelList);
                mCirclePop.showAtLocation(ll_bot, YGravity.ALIGN_BOTTOM, 0, 0);
                break;
            case R.id.ll_lettime:
                setBri();
                break;
            case R.id.bt_pay:
                String mm = tv_rmoney.getText().toString().trim();
                if (TextUtils.isEmpty(mm) || "输入配送配".equals(mm) || "0".equals(mm) || "0.0".equals(mm)) {
                    ToastUtils.showShort("请输入>0元的跑腿费");
                    return;
                }
                RrunnerPayInfo rrunnerPayInfo = checkSome();
                if (rrunnerPayInfo != null) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("money", mm);
                    bundle3.putString("pay", "run");
                    bundle3.putSerializable("runInfo", rrunnerPayInfo);
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, PayMoneyNewActivity.class, bundle3);
                }
                break;
        }
    }

    @Bind(R.id.tv_selectSadr)
    TextView tv_selectSadr;
    @Bind(R.id.tv_selectFadr)
    TextView tv_selectFadr;

    @Bind(R.id.ll_s)
    LinearLayout ll_s;
    @Bind(R.id.ll_f)
    LinearLayout ll_f;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                try {
                    rAddRunAdrInfo = (RAddRunAdrInfo) data.getSerializableExtra("adrInfo");
                    if (rAddRunAdrInfo.getParameters().getType().equals("SEND")) {//发
                        rAddRunAdrInfo1 = rAddRunAdrInfo;
                        tv_selectFadr.setVisibility(View.GONE);
                        ll_f.setVisibility(View.VISIBLE);
                        tv_fahuo.setText(rAddRunAdrInfo.getParameters().getAddress());
                        tv_info.setText(rAddRunAdrInfo.getParameters().getName() + "   " + rAddRunAdrInfo.getParameters().getPhone());
                    } else {
                        rAddRunAdrInfo2 = rAddRunAdrInfo;
                        tv_selectSadr.setVisibility(View.GONE);
                        ll_s.setVisibility(View.VISIBLE);
                        tv_shouhuo.setText(rAddRunAdrInfo.getParameters().getAddress());
                        tv_shouinfo.setText(rAddRunAdrInfo.getParameters().getName() + "   " + rAddRunAdrInfo.getParameters().getPhone());
                    }
                } catch (Exception e) {
                }
                break;
        }
    }

    @Bind(R.id.et_remark)
    EditText et_remark;
    @Bind(R.id.tv_rmoney)
    EditText tv_rmoney;
    @Bind(R.id.cb_check)
    CheckBox cb_check;
    @Bind(R.id.tv_letadr)
    TextView tv_fahuo;
    @Bind(R.id.tv_letinfo)
    TextView tv_info;
    @Bind(R.id.tv_getadr)
    TextView tv_shouhuo;
    @Bind(R.id.tv_getinfo)
    TextView tv_shouinfo;

    private RrunnerPayInfo checkSome() {
        String type = errandHelpAdapter.getItem(errandHelpAdapter.clickPosition).getTitle();
        String letTime = tv_time.getText().toString();
        String remark = et_remark.getText().toString().trim();
        String level = tv_runnerlevel.getText().toString();
        String rmoney = tv_rmoney.getText().toString().replaceAll("元", "");
        boolean isSelect = cb_check.isChecked();
        if (!TextUtils.isEmpty(letTime)) {
            if (!TextUtils.isEmpty(level)) {
                if (!TextUtils.isEmpty(tv_fahuo.getText().toString()) && !TextUtils.isEmpty(tv_info.getText().toString()) && !TextUtils.isEmpty(tv_shouhuo.getText().toString()) && !TextUtils.isEmpty(tv_shouinfo.getText().toString())) {
                    if (isSelect) {
                        RrunnerPayInfo rrunnerPayInfo = new RrunnerPayInfo();
                        rrunnerPayInfo.setFunctionName("AddErrandOrder");
                        RrunnerPayInfo.ParametersBean parametersBean = new RrunnerPayInfo.ParametersBean();
                        parametersBean.setGoodsType(type);
                        parametersBean.setUserId(userId);
                        parametersBean.setDeliveryAddress(rAddRunAdrInfo1.getParameters().getAddress());
                        parametersBean.setDeliveryName(rAddRunAdrInfo1.getParameters().getName());
                        parametersBean.setDeliveryPhone(rAddRunAdrInfo1.getParameters().getPhone());
                        parametersBean.setReceiverAddress(rAddRunAdrInfo2.getParameters().getAddress());
                        parametersBean.setReceiverName(rAddRunAdrInfo2.getParameters().getName());
                        parametersBean.setReceiverPhone(rAddRunAdrInfo2.getParameters().getPhone());
                        parametersBean.setShippingFee(rmoney);
                        if (!TextUtils.isEmpty(remark)) {
                            parametersBean.setRemark(remark);
                        }
                        if ("没要求".equals(level)) {
                            parametersBean.setErrandLevel("5");
                        } else {
                            parametersBean.setErrandLevel(level.replaceAll("星跑腿", ""));
                        }
                        parametersBean.setPickupTime(letTime);
                        rrunnerPayInfo.setParameters(parametersBean);
                        return rrunnerPayInfo;
                    } else {
                        ToastUtils.showShort("请同意跑腿条款");
                    }
                } else {
                    ToastUtils.showShort("请完善地址");
                }
            } else {
                ToastUtils.showShort("请选择跑腿等级");
            }
        } else {
            ToastUtils.showShort("请选择取件时间");
        }

        return null;
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_fragment_helpminerun;
    }


    @Override
    protected void lazyLoad() {

    }
}
