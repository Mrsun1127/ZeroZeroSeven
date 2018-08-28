package com.ffn.zerozeroseven.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;


/**
 * Created by dell on 2017/3/13.
 */

public class StateLayout extends RelativeLayout {

    private final Button tipCallBt;
    private ImageView tipImg;
    private TextView tipTxt;
    public static final int netError = 0;
    public static final int noData = 1;
    public static final int noDianPu = 2;
    public static final int noJiLu = 3;
    public static final int noShangPin = 4;
    public static final int noTuPian = 4;

    protected int[] tipImgsId = {R.mipmap.icon_server_net_error, R.mipmap.icon_server_no_data, R.drawable.nodianpu, R.drawable.nojilu, R.drawable.noshangpin, R.drawable.notypian};
    protected int[] tipTxtsId = {R.string.state_tip_server_error, R.string.state_tip_no_data, R.string.state_tip_nodianpu, R.string.state_nojilu, R.string.state_noshangpin, R.string.state_notupian};

    public StateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_state_tip, this, true);
        tipImg = view.findViewById(R.id.layout_state_tip_img);
        tipTxt = view.findViewById(R.id.layout_state_tip_text);
        tipCallBt = view.findViewById(R.id.layout_state_tip_recall_bt);
        tipCallBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.reCall();
                }
            }
        });
    }


    public void showError(int type) {
        tipImg.setBackgroundResource(tipImgsId[type]);
        tipTxt.setText(tipTxtsId[type]);
    }

    public void showError(int type, boolean showBt) {
        tipImg.setBackgroundResource(tipImgsId[type]);
        tipTxt.setText(tipTxtsId[type]);
        if (!showBt) {
            tipCallBt.setVisibility(View.GONE);
        }
    }

    public interface OnStateLayoutCallListener {
        void reCall();
    }

    private OnStateLayoutCallListener listener;

    public void setOnStateCallListener(OnStateLayoutCallListener listener) {
        this.listener = listener;
    }

}
