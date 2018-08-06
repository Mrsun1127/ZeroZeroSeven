package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ErrandMineRunAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.PeopleMessAgeActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandMineRunFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    public static ErrandMineRunFragment newInstance() {
        return new ErrandMineRunFragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        recycleview.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        ErrandMineRunAdapter adapter = new ErrandMineRunAdapter(bfCxt);
        recycleview.setAdapter(adapter);
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        adapter.addAll(strings);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                showTypeDialog();
            }
        });
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(bfCxt);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(bfCxt, R.layout.pop_qiangdan, null);
        Button bt_left = view.findViewById(R.id.bt_left);
        Button bt_right = view.findViewById(R.id.bt_right);
        bt_left.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_fragment_minerun;
    }

    @OnClick({R.id.iv_audit})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.iv_audit:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, ErrandAuitActivity.class);
                break;

        }
    }

    @Override
    protected void lazyLoad() {

    }
}
