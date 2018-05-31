package com.ffn.zerozeroseven.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.QiangShowInfo;
import com.ffn.zerozeroseven.bean.TypeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DafenInfo;
import com.ffn.zerozeroseven.bean.requsetbean.QiangTypeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.XiaoYuanQiangInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;
import com.ffn.zerozeroseven.view.fingerswipe.SwipeFlingAdapterView;
import com.ffn.zerozeroseven.view.pop.SchoolPopWindow;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/12/3.
 */

public class SchoolnewCardActivity extends BaseActivity implements SwipeFlingAdapterView.onFlingListener
        , SwipeFlingAdapterView.OnItemClickListener, View.OnClickListener {
    private String classDetils[] = new String[20]; // 12个人名
    private String projectFrom[] = new String[20]; // 12个人名
    private int likecount[] = new int[20];
    private String time[] = new String[20];
    private String title[] = new String[20];
    private String content[] = new String[20];
    private String postType[] = new String[20];
    private int isLike[] = new int[20];
    private int[] id = new int[20];
    private TitleView titleView;
    private int cardWidth;
    private int cardHeight;
    private InnerAdapter adapter;
    private SwipeFlingAdapterView swipeView;
    private ArrayList<Talent> tabs = new ArrayList<>();
    private ArrayList<Talent> alltabs = new ArrayList<>();
    private ArrayList<Talent> liketabs = new ArrayList<>();
    private ArrayList<Talent> findtabs = new ArrayList<>();
    private ArrayList<Talent> goodtabs = new ArrayList<>();
    private ArrayList<Talent> friendtabs = new ArrayList<>();
    private SchoolPopWindow popWindow;
    private TextView tv_top;
    private RelativeLayout rl_root;
    private RelativeLayout rl_nodata;
    private Button bt_get;
    private RelativeLayout rl_bottom;
    private String[] type;

    @Override
    protected int setLayout() {
        return R.layout.activity_newcard;
    }

    @Override
    public void initView() {
        BaseAppApplication.getInstance().addActivity(this);
        String cache = MrsunAppCacheUtils.get(SchoolnewCardActivity.this).getAsString("cache");
        if (TextUtils.isEmpty(cache)) {
            requestRype();
        } else {
            TypeInfo info = JSON.parseObject(cache, TypeInfo.class);
            type = new String[info.getData().getItems().size()];
            for (int i = 0; i < info.getData().getItems().size(); i++) {
                type[i] = info.getData().getItems().get(i).getDicKey();
            }
        }
        initTop();
        initPop();
        tabs = alltabs;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 18 * density));
        cardHeight = (int) (dm.heightPixels - (338 * density));
        swipeView = findViewById(R.id.swipe_view);
        if (swipeView != null) {
            swipeView.setIsNeedSwipe(true);
            swipeView.setFlingListener(this);
            swipeView.setOnItemClickListener(this);

            adapter = new InnerAdapter();
            swipeView.setAdapter(adapter);
        }

        View v = findViewById(R.id.iv_like);
        if (v != null) {
            v.setOnClickListener(this);
        }
        v = findViewById(R.id.iv_dislike);
        if (v != null) {
            v.setOnClickListener(this);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initXiaoYuanQiang(tabs, "");
            }
        }, 500);

    }

    private void requestRype() {
        QiangTypeInfo typeInfo = new QiangTypeInfo();
        typeInfo.setFunctionName("ListPostType");
        httpPostJSON(typeInfo);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TypeInfo info = JSON.parseObject(response.body().string(), TypeInfo.class);
                if (info.getCode() == 0) {
                    type = new String[info.getData().getItems().size()];
                    for (int i = 0; i < info.getData().getItems().size(); i++) {
                        type[i] = info.getData().getItems().get(i).getDicKey();
                    }
                }
            }
        });
    }

    private void showPop(SchoolPopWindow popWindow) {
        popWindow.showPopupWindow(tv_top);
    }

    private void initTop() {
        titleView = findViewById(R.id.titleview);
        tv_top = findViewById(R.id.tv_top);
        rl_root = findViewById(R.id.rl_root);
        rl_nodata = findViewById(R.id.rl_nodata);
        rl_bottom = findViewById(R.id.rl_bottom);
        bt_get = findViewById(R.id.bt_get);
        bt_get.setOnClickListener(this);
        titleView.setTopText("全部");
        titleView.setTvRightShow();
        titleView.setDownShow();
        titleView.setOnTitleListener(new TitleView.OnTitleClickListener() {
            @Override
            public void ivBack() {
                finish();
            }

            @Override
            public void ivDown() {
                showPop(popWindow);
            }

            @Override
            public void ivMessAge() {

            }
        });
        titleView.setOnRightClickListener(new TitleView.OnRightClickListener() {
            @Override
            public void tvRight() {
                ZeroZeroSevenUtils.SwitchActivity(SchoolnewCardActivity.this, MineWantGoQiangActivity.class);
            }
        });
    }

    int curType = 0;//全部

    private void initPop() {
        popWindow = new SchoolPopWindow(this);
        popWindow.OnPopClickListener(new SchoolPopWindow.OnPopClickListener() {
            @Override
            public void All() {
                curType = 0;
                titleView.setTopText("全部");
                initXiaoYuanQiang(tabs, "");
                popWindow.dismiss();
            }

            @Override
            public void Love() {
                curType = 1;
                titleView.setTopText("表白帖");
                initXiaoYuanQiang(liketabs, type[0]);
                popWindow.dismiss();
            }

            @Override
            public void Good() {
                curType = 2;
                titleView.setTopText("技术贴");
                initXiaoYuanQiang(goodtabs, type[1]);
                popWindow.dismiss();
            }

            @Override
            public void Find() {
                curType = 3;
                titleView.setTopText("寻物贴");
                initXiaoYuanQiang(findtabs, type[2]);
                popWindow.dismiss();
            }

            @Override
            public void Friend() {
                curType = 4;
                titleView.setTopText("交友贴");
                initXiaoYuanQiang(friendtabs, type[3]);
                popWindow.dismiss();
            }
        });
    }
    boolean isLoding=true;
    private void initXiaoYuanQiang(final ArrayList<Talent> list, String type) {
        list.clear();
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (adapter != null) {
                    adapter.clear();
                }
                showPopupWindow();
            }
        });
        XiaoYuanQiangInfo qiangInfo = new XiaoYuanQiangInfo();
        qiangInfo.setFunctionName("ListPost");
        XiaoYuanQiangInfo.ParametersBean parametersBean = new XiaoYuanQiangInfo.ParametersBean();
        parametersBean.setPageIndex(0);
        parametersBean.setPageSize(20);
        parametersBean.setPostType(type);
        qiangInfo.setParameters(parametersBean);
        httpPostJSON(qiangInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        rl_nodata.setVisibility(View.VISIBLE);
                        swipeView.setVisibility(View.GONE);
                        rl_bottom.setVisibility(View.GONE);
                        dissMissPop();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                QiangShowInfo showInfo = JSON.parseObject(response.body().string(), QiangShowInfo.class);
                if (showInfo.getCode() == 0) {//现在输入法居然好了 果然皇天不负有心人啊啊！！
                    if(showInfo.getData().getItems().size()==1){
                        isLoding=false;
                    }
                    for (int i = 0; i < showInfo.getData().getItems().size(); i++) {
                        Talent dataItem = new Talent();
                        projectFrom[i] = showInfo.getData().getItems().get(i).getUserCollege();
                        classDetils[i] = showInfo.getData().getItems().get(i).getUserClazz();
                        likecount[i] = Integer.parseInt(showInfo.getData().getItems().get(i).getLikeCount());
                        isLike[i] = showInfo.getData().getItems().get(i).getIsLike();
                        String timer = showInfo.getData().getItems().get(i).getCreateTime();
                        time[i] = timer;
                        title[i] = showInfo.getData().getItems().get(i).getTitle();
                        content[i] = showInfo.getData().getItems().get(i).getContent();
                        id[i]=showInfo.getData().getItems().get(i).getId();
                        postType[i]=showInfo.getData().getItems().get(i).getPostType();
                        dataItem.projectFrom = projectFrom[i];
                        dataItem.projectClass = classDetils[i];
                        dataItem.count = likecount[i];
                        dataItem.time = time[i];
                        dataItem.title = title[i];
                        dataItem.content = content[i];
                        dataItem.id = id[i];
                        dataItem.isLike = isLike[i];
                        dataItem.postType=postType[i];
                        list.add(dataItem);
                    }
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addAll(list);
                            dissMissPop();
                            rl_nodata.setVisibility(View.GONE);
                            swipeView.setVisibility(View.VISIBLE);
                            rl_bottom.setVisibility(View.VISIBLE);
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void doMain() {

    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
        Talent talent = (Talent) dataObject;
        DafenInfo dafenInfo = new DafenInfo();
        dafenInfo.setFunctionName("UpdatePostLike");
        DafenInfo.ParametersBean parametersBean = new DafenInfo.ParametersBean();
        parametersBean.setPostId("" + talent.id);
//        parametersBean.setUserId("" + SharePrefUtils.getInt(SchoolnewCardActivity.this, "userId", 0));
        parametersBean.setEvent("DELETE");
        dafenInfo.setParameters(parametersBean);
        httpPostJSON(dafenInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.D("SchoolNewCardActivity", response.body().string());
            }
        });
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        Talent talent = (Talent) dataObject;
        if(talent.isLike==1){
            return;
        }
        DafenInfo dafenInfo = new DafenInfo();
        dafenInfo.setFunctionName("UpdatePostLike");
        DafenInfo.ParametersBean parametersBean = new DafenInfo.ParametersBean();
        parametersBean.setPostId("" + talent.id);
//        parametersBean.setUserId("" + SharePrefUtils.getInt(SchoolnewCardActivity.this, "userId", 0));
        parametersBean.setEvent("ADD");
        dafenInfo.setParameters(parametersBean);
        httpPostJSON(dafenInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.D("SchoolNewCardActivity", response.body().string());
            }
        });
    }

    ImageView iv_scanning;
    PopupWindow popupWindow1;

    //雷达扫面界面的显示方法
    private void showPopupWindow() {
        View popView = View.inflate(getApplicationContext(), R.layout.layout_pop, null);
        iv_scanning = popView.findViewById(R.id.iv_scanning);
        initAnimation();
        popupWindow1 = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow1.setBackgroundDrawable(new ColorDrawable(0xcc000000));
        popupWindow1.showAtLocation(rl_root, Gravity.CENTER, 0, 0);
    }

    private void dissMissPop() {
        if (popupWindow1 != null) {
            popupWindow1.dismiss();
        }
    }

    //雷达扫面界面的实现方法
    private void initAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1500);
        rotateAnimation.setRepeatCount(RotateAnimation.INFINITE);
        iv_scanning.startAnimation(rotateAnimation);

    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {

        if(itemsInAdapter==1){
            switch (curType) {
                case 0:
                    if(isLoding){
                        initXiaoYuanQiang(tabs, "");
                    }
                    break;
                case 1:
                    if(isLoding){
                        initXiaoYuanQiang(tabs, type[0]);
                    }
                    break;
                case 2:
                    if(isLoding){
                        initXiaoYuanQiang(tabs, type[1]);
                    }
                    break;
                case 3:
                    if(isLoding){
                        initXiaoYuanQiang(tabs, type[2]);
                    }
                    break;
                case 4:
                    if(isLoding){
                        initXiaoYuanQiang(tabs, type[3]);
                    }
                    break;
            }
            return;
        }
        if (itemsInAdapter == 0) {
            rl_nodata.setVisibility(View.VISIBLE);
            swipeView.setVisibility(View.GONE);
            rl_bottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {

    }

    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_dislike:
                swipeView.swipeLeft();
                break;
            case R.id.iv_like:
                swipeView.swipeRight();
                break;
            case R.id.bt_get:
                switch (curType) {
                    case 0:
                        initXiaoYuanQiang(tabs, "");
                        break;
                    case 1:
                        initXiaoYuanQiang(tabs, type[0]);
                        break;
                    case 2:
                        initXiaoYuanQiang(tabs, type[1]);
                        break;
                    case 3:
                        initXiaoYuanQiang(tabs, type[2]);
                        break;
                    case 4:
                        initXiaoYuanQiang(tabs, type[3]);
                        break;
                }
                break;
        }
    }

    private class InnerAdapter extends BaseAdapter {

        ArrayList<Talent> objs;

        public InnerAdapter() {
            objs = new ArrayList<>();
        }

        public void addAll(Collection<Talent> collection) {
            if (isEmpty()) {
                objs.addAll(collection);
                notifyDataSetChanged();
            } else {
                objs.addAll(collection);
            }
        }

        public void clear() {
            objs.clear();
            notifyDataSetChanged();
        }

        public boolean isEmpty() {
            return objs.isEmpty();
        }

        public void remove(int index) {
            if (index > -1 && index < objs.size()) {
                objs.remove(index);
                notifyDataSetChanged();
            }
        }


        @Override
        public int getCount() {
            return objs.size();
        }

        @Override
        public Talent getItem(int position) {
            if (objs == null || objs.size() == 0) return null;
            return objs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // TODO: getView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            final Talent talent = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_project.setText(talent.projectFrom);
            holder.tv_detils.setText(talent.projectClass);
            holder.tv_likecount.setText(talent.count + "");
            holder.tv_time.setText(talent.time);
            holder.tv_content.setText(talent.content);
            holder.tv_title.setText(talent.title);
            if (talent.isLike == 0) {
                Glide.with(SchoolnewCardActivity.this).load(R.drawable.heart_hollow).into(holder.iv_like);
            }else{
                Glide.with(SchoolnewCardActivity.this).load(R.drawable.heart_solid).into(holder.iv_like);
            }
//            if(talent.postType.equals("01")){
//                Glide.with(SchoolnewCardActivity.this).load(R.drawable.topic_romance_bg).into(holder.iv_type);
//            }else if("02".equals(talent.postType)){
//                Glide.with(SchoolnewCardActivity.this).load(R.drawable.topic_tech_bg).into(holder.iv_type);
//            }else if("03".equals(talent.postType)){
//                Glide.with(SchoolnewCardActivity.this).load(R.drawable.topic_lostnfound_bg).into(holder.iv_type);
//            }else if("04".equals(talent.postType)){
//                Glide.with(SchoolnewCardActivity.this).load(R.drawable.topic_friends_bg).into(holder.iv_type);
//
//            }
            holder.ll_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showShare(talent.title,talent.content);
                }
            });
            return convertView;
        }

    }
    private void showShare(String title,String content) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(AppConfig.SHAREURL);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(AppConfig.LOGOURL);//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(AppConfig.SHAREURL);
        // 启动分享GUI
        oks.show(SchoolnewCardActivity.this);
    }

    private static class ViewHolder {
        TextView tv_project;
        View maskView;
        TextView tv_detils;
        TextView tv_likecount;
        TextView tv_time;
        TextView tv_content;
        TextView tv_title;
        ImageView iv_like;
        LinearLayout ll_background;
        LinearLayout ll_share;
        ImageView iv_type;
        public ViewHolder(View view) {
            iv_type=view.findViewById(R.id.iv_type);
            tv_project = view.findViewById(R.id.tv_project);
            ll_background = view.findViewById(R.id.ll_background);
            ll_share = view.findViewById(R.id.ll_share);
            maskView = view.findViewById(R.id.maskView);
            tv_detils = view.findViewById(R.id.tv_detils);
            tv_likecount = view.findViewById(R.id.tv_likecount);
            tv_time = view.findViewById(R.id.tv_time);
            tv_content = view.findViewById(R.id.tv_content);
            tv_title = view.findViewById(R.id.tv_title);
            iv_like = view.findViewById(R.id.iv_like);
        }


    }

    public static class Talent {
        public String projectFrom;
        public String time;
        public String projectClass;
        public String title;
        public String content;
        public int count;
        public int id;
        public int isLike;
        public String postType;
    }
}
