package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;

import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class SerachShopActivity extends BaseActivity {

    private SearchView searchView;
    private RelativeLayout rl_back;

    @Override
    protected int setLayout() {
        return R.layout.activity_seachshop;
    }

    @Override
    public void initView() {
        super.initView();
        searchView = findViewById(R.id.search_view);
        rl_back = findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void doMain() {
        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("name", string);
                setResult(2, intent);
                finish();
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });

    }
}
