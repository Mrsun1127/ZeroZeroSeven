package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RrunnerPayInfo;
import com.ffn.zerozeroseven.bean.RunTypeInfo;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.PayMoneyNewActivity;
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

    public static ErrandHelpMineRunFragment newInstance() {
        return new ErrandHelpMineRunFragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        initWeightPop();
    }


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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");// HH:mm:ss
                //获取当前时间
                tv_time.setText(simpleDateFormat.format(date));
            }
        }).setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    @OnClick({R.id.ll_weight, R.id.ll_level, R.id.ll_lettime, R.id.bt_pay})
    void setOnClicks(View v) {
        switch (v.getId()) {
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
                RrunnerPayInfo rrunnerPayInfo = checkSome();
                if (rrunnerPayInfo != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("money", "10");
                    bundle.putString("pay", "run");
                    bundle.putSerializable("runInfo", rrunnerPayInfo);
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, PayMoneyNewActivity.class, bundle);
                }
                break;
        }
    }

    @Bind(R.id.et_remark)
    EditText et_remark;
    @Bind(R.id.tv_rmoney)
    TextView tv_rmoney;
    @Bind(R.id.cb_check)
    CheckBox cb_check;

    private RrunnerPayInfo checkSome() {
        String type = errandHelpAdapter.getItem(errandHelpAdapter.clickPosition).getTitle();
        String weight = tv_weight.getText().toString();
        String letTime = tv_time.getText().toString();
        String remark = et_remark.getText().toString().trim();
        String level = tv_runnerlevel.getText().toString();
        String rmoney = tv_rmoney.getText().toString();
        boolean isSelect = cb_check.isChecked();
        if (!TextUtils.isEmpty(weight)) {
            if (!TextUtils.isEmpty(letTime)) {
                if (!TextUtils.isEmpty(level)) {
                    if (isSelect) {
                        RrunnerPayInfo rrunnerPayInfo = new RrunnerPayInfo();
                        rrunnerPayInfo.setFunctionName("AddErrandOrder");
                        RrunnerPayInfo.ParametersBean parametersBean = new RrunnerPayInfo.ParametersBean();
                        parametersBean.setGoodsType(type);
                        parametersBean.setUserId(userId);
                        parametersBean.setGoodsWeight(weight);
                        parametersBean.setDeliveryAddress("xxx");
                        parametersBean.setDeliveryName("mrsun");
                        parametersBean.setDeliveryPhone("18888888888");
                        parametersBean.setReceiverAddress("xxx");
                        parametersBean.setReceiverName("mrqin");
                        parametersBean.setReceiverPhone("1666666666");
                        parametersBean.setShippingFee(rmoney);
                        if (!TextUtils.isEmpty(remark)) {
                            parametersBean.setRemark(remark);
                        }
                        parametersBean.setErrandLevel(level);
                        parametersBean.setPickupTime(letTime);
                        rrunnerPayInfo.setParameters(parametersBean);
                        return rrunnerPayInfo;
                    } else {
                        ToastUtils.showShort("请同意跑腿条款");
                    }
                } else {
                    ToastUtils.showShort("请选择跑腿等级");
                }
            } else {
                ToastUtils.showShort("请选择取件时间");
            }
        } else {
            ToastUtils.showShort("请选择物品重量");
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
