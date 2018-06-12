package com.ffn.zerozeroseven.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.AllAdrAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.AllAdrInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DeleteAdrInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.PullSwipeMenuListView;
import com.ffn.zerozeroseven.view.StateLayout;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/27.
 */

public class AdrMannGerActivity extends BaseActivity implements View.OnClickListener {
    PullSwipeMenuListView msgPullLv;
    private AllAdrAdapter allAdrAdapter;
    private ShouHuoInfo shouHuoInfo;
    private StateLayout commonStateLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_adrmanger;
    }

    @Override
    protected void doMain() {
//        GetAllAdr();
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(AdrMannGerActivity.this);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#299bef")));
                // set item width
                deleteItem.setWidth(ZeroZeroSevenUtils.dp2px(AdrMannGerActivity.this, 90));
                // set a icon
                deleteItem.setIcon(R.mipmap.lisi_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        msgPullLv.setMenuCreator(creator);

        msgPullLv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            /**
             *
             * @param position listview item 下标
             * @param menu
             * @param index menuitem 下标
             * @return
             */
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        final ConfirmDialog confirmDialog = new ConfirmDialog(AdrMannGerActivity.this);
                        confirmDialog.setTitles("提示");
                        confirmDialog.setMessages("确定删除此地址？");
                        confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                            @Override
                            public void doConfirm() {
                                confirmDialog.dismiss();
                                deleteAdr(position);

                            }

                            @Override
                            public void doCancel() {
                                confirmDialog.dismiss();
                            }
                        });
                        break;
                }
                return false;
            }


        });
        msgPullLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",shouHuoInfo.getData().getAddresses().get(i).getId());
                bundle.putString("name",shouHuoInfo.getData().getAddresses().get(i).getContactName());
                bundle.putString("adr",shouHuoInfo.getData().getAddresses().get(i).getContactSchool());
                bundle.putString("phone",shouHuoInfo.getData().getAddresses().get(i).getContactPhone());
                bundle.putString("dong",shouHuoInfo.getData().getAddresses().get(i).getContactBuilding());
                bundle.putString("men",shouHuoInfo.getData().getAddresses().get(i).getContactDorm());
                ZeroZeroSevenUtils.SwitchActivity(AdrMannGerActivity.this,UpDateAdrActivity.class,bundle);
            }
        });
    }

    private void deleteAdr(int position) {
        showLoadProgress();
        DeleteAdrInfo deleteAdrInfo = new DeleteAdrInfo();
        deleteAdrInfo.setFunctionName("DeleteUserAddress");
        DeleteAdrInfo.ParametersBean parametersBean = new DeleteAdrInfo.ParametersBean();
        parametersBean.setId(shouHuoInfo.getData().getAddresses().get(position).getId());
        deleteAdrInfo.setParameters(parametersBean);
        httpPostJSON(deleteAdrInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                String code = JsonUtil.getFieldValue(response.body().string(), "code");
                if ("0".equals(code)) {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShort("删除成功");
                            ZeroZeroSevenUtils.SwitchActivity(AdrMannGerActivity.this,AdrMannGerActivity.class);
                            finish();
                        }
                    });
                }else{
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShort("服务器正忙，请稍后再试");
                        }
                    });
                }
            }
        });
    }
    private void showErrorLayout(int errType) {
        commonStateLayout.setVisibility(View.VISIBLE);
        commonStateLayout.showError(errType);
    }
    private void GetAllAdr(final boolean refrsh) {
        showLoadProgress();
        AllAdrInfo allAdrInfo = new AllAdrInfo();
        allAdrInfo.setFunctionName("ListUserAddress");
        httpPostJSON(allAdrInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                shouHuoInfo = JSON.parseObject(response.body().string(), ShouHuoInfo.class);
                if (shouHuoInfo.getCode() == 0) {//成功
                    if (shouHuoInfo.getData().getAddresses().size() > 0) {
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                msgPullLv.setVisibility(View.VISIBLE);
                                commonStateLayout.setVisibility(View.GONE);
                                if (allAdrAdapter != null) {
                                    allAdrAdapter.setList(shouHuoInfo.getData().getAddresses());
                                } else {
                                    allAdrAdapter = new AllAdrAdapter(shouHuoInfo.getData().getAddresses(), AdrMannGerActivity.this);
                                    msgPullLv.setAdapter(allAdrAdapter);
                                }
                            }
                        });
                    }else{
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                msgPullLv.setVisibility(View.GONE);
                                showErrorLayout(StateLayout.noData);
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void initView() {
        commonStateLayout = findViewById(R.id.common_stateLayout);
        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GetAllAdr(false);
                    }
                },500);
            }
        });
        msgPullLv = findViewById(R.id.msg_pull_lv);
        Button bt_addadr = findViewById(R.id.bt_addadr);
        bt_addadr.setOnClickListener(this);
        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("地址管理");
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_addadr:
                ZeroZeroSevenUtils.SwitchActivity(AdrMannGerActivity.this, AddNewAdrActivity.class);
                break;
            default:
                break;
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        GetAllAdr(false);
    }
}
