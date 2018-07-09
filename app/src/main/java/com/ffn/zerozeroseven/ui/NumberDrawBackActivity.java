package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.NumberTuiKuanAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SelectBean;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberDrawBackActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.rl_pop)
    RelativeLayout rl_pop;
    @Bind(R.id.tv_select_reason)
    TextView tv_select_reason;
    @Bind(R.id.rc_reason)
    RecyclerView rc_reason;
    private List<SelectBean> list;
    private NumberTuiKuanAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_drawback;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("申请退款");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
    }

    String showReason = "";

    @OnClick({R.id.rl_drawback_reason, R.id.tv_close, R.id.bt_sure})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_drawback_reason:
                rl_pop.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_close:
                rl_pop.setVisibility(View.GONE);
                break;
            case R.id.bt_sure:
                rl_pop.setVisibility(View.GONE);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isCheck()) {
                        showReason = showReason + list.get(i).getName() + " ";
                    }
                }
                if (!TextUtils.isEmpty(showReason)) {
                    tv_select_reason.setText(showReason+">");
                } else {
                    tv_select_reason.setText("please checked >");
                }
                break;
        }
    }

    private List<String> reasonList = new ArrayList<>();
    private int curPosition;

    @Override
    protected void doMain() {
        adapter = new NumberTuiKuanAdapter(this);
        rc_reason.setLayoutManager(new LinearLayoutManager(this));
        rc_reason.setAdapter(adapter);
        list = new ArrayList<>();
        reasonList.add("拍错");
        reasonList.add("不想要");
        reasonList.add("多拍");
        reasonList.add("未按约定时间发货");
        for (int i = 0; i < reasonList.size(); i++) {
            SelectBean selectBean = new SelectBean();
            selectBean.setName(reasonList.get(i));
            list.add(selectBean);
        }
        adapter.addAll(list);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                curPosition = position;
                showReason="";
                if (list.get(position).isCheck()) {
                    list.get(position).setCheck(false);
                    if (!TextUtils.isEmpty(showReason)) {
                        showReason.replaceAll(list.get(position).getName(), "");
                    }
                } else {
                    list.get(position).setCheck(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


}
