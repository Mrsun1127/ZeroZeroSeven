package com.ffn.zerozeroseven.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.MyRunTaskdanAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.MyRunDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.MyrunnerInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RunActionInfo;
import com.ffn.zerozeroseven.utlis.ToastUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2018/1/25.
 */

@SuppressLint("ValidFragment")
public class TaskRunTypeFragment extends BaseReFreshFragment {
    int status;
    private MyRunTaskdanAdapter adapter;
    private MyRunDingDanInfo myRunDingDanInfo;

    public TaskRunTypeFragment(int status) {
        this.status = status;
    }

    @Override
    public void doMain() {
        adapter.setOnItemImgViewClick(new MyRunTaskdanAdapter.OnItemImgClick() {
            @Override
            public void onClick(View view, int position) {
                runAction(position,adapter.getItem(position).getStatus(),adapter.getItem(position).getId());
            }
        });

    }
    private void runAction(final int position, int status, int id) {
        showLoadDialog();
        RunActionInfo runActionInfo=new RunActionInfo();
        if (status == 1) {
            runActionInfo.setFunctionName("DeleteErrandOrder");
        } else if (status == 2) {
            runActionInfo.setFunctionName("SignInErrandOrder");
        } else if (status == 3) {
            runActionInfo.setFunctionName("RePublishErrandOrder");
        }
        RunActionInfo.ParametersBean parametersBean=new RunActionInfo.ParametersBean();
        parametersBean.setId(id+"");
        runActionInfo.setParameters(parametersBean);
        httpPostJSON(runActionInfo,true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dissMissLoad();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ErrorCodeInfo info= JSON.parseObject(response.body().string(),ErrorCodeInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dissMissLoad();
                        if(info.getCode()==0){
                            adapter.removeItem(adapter.getItem(position));
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            },1000);
                        }else{
                            ToastUtils.showShort(info.getMessage());
                        }
                    }
                });
            }
        });
    }
    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new MyRunTaskdanAdapter(getContext());
        return adapter;
    }

    @Override
    protected void readRespones(String response) {
        myRunDingDanInfo = JSON.parseObject(response,MyRunDingDanInfo.class);

    }

    @Override
    protected int setFlag() {
        return myRunDingDanInfo.getCode();
    }

    @Override
    protected int setSize() {
        return myRunDingDanInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(myRunDingDanInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        MyrunnerInfo myrunnerInfo=new MyrunnerInfo();
        myrunnerInfo.setFunctionName("ListPublisherErrandOrder");
        MyrunnerInfo.ParametersBean parametersBean=new MyrunnerInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        parametersBean.setStatus(status+"");
        myrunnerInfo.setParameters(parametersBean);
        return myrunnerInfo;
    }
}
