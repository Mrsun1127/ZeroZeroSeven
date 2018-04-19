package com.ffn.zerozeroseven.fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.LookMoreAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.QiangDanOkInfo;
import com.ffn.zerozeroseven.bean.RunListRquestInfo;
import com.ffn.zerozeroseven.bean.requsetbean.QiangRunRequsetInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RunListInfo;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2018/2/1.
 */

public class LookMoreRunFragment extends BaseReFreshFragment {

    private LookMoreAdapter adapter;
    private RunListRquestInfo info;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new LookMoreAdapter(getContext());
        return adapter;
    }

    @Override
    protected void readRespones(String response) {
        info = JSON.parseObject(response, RunListRquestInfo.class);
    }

    @Override
    public void doMain() {
        adapter.setOnItemImgViewClick(new LookMoreAdapter.OnItemImgClick() {
            @Override
            public void onClick(View view, int position) {
                qiangDan(position);
            }
        });
    }

    @Override
    protected int setFlag() {
        return info.getCode();
    }

    @Override
    protected int setSize() {
        return info.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(info.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        RunListInfo runListInfo = new RunListInfo();
        runListInfo.setFunctionName("ListSchoolErrandOrder");
        RunListInfo.ParametersBean parametersBean = new RunListInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        runListInfo.setParameters(parametersBean);
        return runListInfo;
    }

    private void qiangDan(final int position) {
        showLoadProgress();
        QiangRunRequsetInfo qiangRunRequsetInfo = new QiangRunRequsetInfo();
        qiangRunRequsetInfo.setFunctionName("GrabErrandOrder");
        QiangRunRequsetInfo.ParametersBean parametersBean = new QiangRunRequsetInfo.ParametersBean();
        parametersBean.setId(String.valueOf(adapter.getItem(position).getId()));
        qiangRunRequsetInfo.setParameters(parametersBean);
        httpPostJSON(qiangRunRequsetInfo, true);
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
                final QiangDanOkInfo qiangDanOkInfo = JSON.parseObject(response.body().string(), QiangDanOkInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (qiangDanOkInfo.getCode() == 0) {
                            ZeroZeroSevenUtils.showCustonPop(getContext(), "抢单成功", recyclerView);
                            adapter.removeItem(position);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            }, 800);
                        } else {
                            ZeroZeroSevenUtils.showCustonPop(getContext(), qiangDanOkInfo.getMessage(), recyclerView);
                        }
                    }
                });

            }
        });
    }
}
