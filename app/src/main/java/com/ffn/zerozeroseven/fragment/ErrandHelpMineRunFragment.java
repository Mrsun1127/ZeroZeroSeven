package com.ffn.zerozeroseven.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ErrandHelpAdapter;
import com.ffn.zerozeroseven.adapter.ErrandMineRunAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RunTypeInfo;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandHelpMineRunFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private static final String[] CHANNELS = new String[]{"快递", "文件", "美食", "电子产品", "服饰", "鲜花", "钥匙", "其他"};
    private static final int[] DRABABLES = new int[]{R.drawable.run_kuaidi, R.drawable.run_file, R.drawable.run_food, R.drawable.run_computer, R.drawable.run_yifu, R.drawable.run_flowers, R.drawable.run_key, R.drawable.run_other};
    private static final int[] DRABABLES_NOR = new int[]{R.drawable.run_kuaidi_nor, R.drawable.run_file_nor, R.drawable.run_food_nor, R.drawable.run_computer_nor, R.drawable.run_yifu_nor, R.drawable.run_flowers_nor, R.drawable.run_key_nor, R.drawable.run_other_nor};
    private RunTypeInfo runTypeInfo;
    private ErrandHelpAdapter errandHelpAdapter;

    public static ErrandHelpMineRunFragment newInstance() {
        return new ErrandHelpMineRunFragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

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

    @Override
    protected int setLayout() {
        return R.layout.errand_fragment_helpminerun;
    }


    @Override
    protected void lazyLoad() {

    }
}
