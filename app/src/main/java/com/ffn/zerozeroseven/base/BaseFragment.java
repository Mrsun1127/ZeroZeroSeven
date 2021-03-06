package com.ffn.zerozeroseven.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.view.WaitingDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.ImmersionFragment;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.leakcanary.RefWatcher;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * 碎片fragment基类
 *
 * @author L.A
 */
public abstract class BaseFragment extends ImmersionFragment {
    public View rootView;
    public Call call;
    private static final String TAG = "BaseFragment";
    public String userId;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible = true;
    protected Context bfCxt;
    private KProgressHUD hud;
    private WaitingDialog dialog;
    public String schoolIId;
    public UserInfo.DataBean userInfo;

    protected void showLoadProgress() {
        if (hud != null) {
            hud.setDetailsLabel("正在加载中")
                    .show();
        } else {
            hud = KProgressHUD.create(getContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setWindowColor(getResources().getColor(R.color.text_secondary_color))
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        }

    }

    protected void disLoadProgress() {
        if (hud != null) {
            hud.dismiss();
        } else {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bfCxt = context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hud = KProgressHUD.create(bfCxt)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setWindowColor(getResources().getColor(R.color.text_secondary_color))
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        userInfo = BaseAppApplication.getInstance().getLoginUser();
        if (userInfo != null) {
            userId = "" + userInfo.getId();
            schoolIId = userInfo.getSchoolId();
        } else {
            userId = "";
            schoolIId = "943478288";
        }
    }

    @Override
    protected void immersionInit() {
        ImmersionBar.with(getActivity())
                .statusBarDarkFont(false)
                .init();
    }

    public void initDate() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayout(), container,
                false);
        initView(rootView);
        initDate();
        doOther();
        return rootView;
    }

    public void doOther() {

    }

    protected abstract void initView(View view);

    protected abstract int setLayout();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
//        if (getUserVisibleHint()) {
//            lazyLoad();
//        } else {
//            onInvisible();
//        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private boolean isLoad = true;

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && isLoad) {
            lazyLoad();
            isLoad = false;
        }
    }

    /**
     * 可见
     */
    public void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    /**
     * 延迟加载 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        Window window = getActivity().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        window.setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public void httpPostJSON(Object obj) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //换成自己的ip就行
        String url = AppConfig.BaseUrl;
        OkHttpClient client = new OkHttpClient();//创建okhttp实例
        RequestBody body = RequestBody.create(JSON, JsonUtil.parseBeanToJson(obj));
        Request request = new Request.Builder()
                .addHeader("platform", "android")
                .url(url)
                .post(body)
                .build();
        call = client.newCall(request);


    }

    public void httpPostJSON(Object obj, boolean isToken) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //换成自己的ip就行
        String url = AppConfig.BaseUrl;
        OkHttpClient client = new OkHttpClient();//创建okhttp实例
        RequestBody body = RequestBody.create(JSON, JsonUtil.parseBeanToJson(obj));
        if (isToken) {
            String token = "";
            if (userInfo != null) {
                token = userInfo.getToken();
                if (TextUtils.isEmpty(token)) {
                    token = "";
                }
            }

            Request request = new Request.Builder()
                    .addHeader("platform", "android")
                    .addHeader("Authorization", "Bearer " + token)
                    .url(url)
                    .post(body)
                    .build();
            call = client.newCall(request);
        } else {
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            call = client.newCall(request);

        }


    }
}
