package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.DriverFourAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DriverDetilsThreeFragment extends BaseFragment {
    @Bind(R.id.webview)
    WebView webView;
    private String html;

    public static DriverDetilsThreeFragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putString("s", s);
        DriverDetilsThreeFragment fragment = new DriverDetilsThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        html = getArguments().getString("s");
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;color:#cccccc;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void initDate() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_driver_three;
    }

    @Override
    protected void lazyLoad() {
        webView.loadData(getHtmlData(html), "text/html; charset=UTF-8", null);
    }
}
