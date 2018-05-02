package com.ffn.zerozeroseven.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;


public class ZQImageViewRoundOval extends ImageView {
    private int cornerSize;//圆角大小
    private Context context;
    public ZQImageViewRoundOval(Context context){
        this(context,null);
    }

    public ZQImageViewRoundOval(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }

    public ZQImageViewRoundOval(Context context, AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        this.context=context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView,defStyle,0);
        cornerSize = a.getInt(R.styleable.RoundCornerImageView_corner_size,5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = getWidth();
        int h = getHeight();
        //这里对path添加一个圆角区域，这里一般需要将dp转换为pixel
        path.addRoundRect(new RectF(0,0,w,h), ZeroZeroSevenUtils.dp2px(context,cornerSize),ZeroZeroSevenUtils.dp2px(context,cornerSize), Path.Direction.CW);
        canvas.clipPath(path);//将Canvas按照上面的圆角区域截取
        super.onDraw(canvas);
    }

    /**
     * 设置圆角的大小
     * @param size
     */
    public void setCornerSize(int size){
        cornerSize = size;
    }
}
