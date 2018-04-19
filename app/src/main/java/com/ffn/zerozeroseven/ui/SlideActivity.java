package com.ffn.zerozeroseven.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.ffn.zerozeroseven.fragment.SlideOne;
import com.ffn.zerozeroseven.fragment.SlideThree;
import com.ffn.zerozeroseven.fragment.SlideTwo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by GT on 2017/12/6.
 */

public class SlideActivity extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new SlideOne(),getApplicationContext());
        addSlide(new SlideTwo(),getApplicationContext());
        addSlide(new SlideThree(),getApplicationContext());
        setBarColor(Color.parseColor("#fbb03b"));
        setSeparatorColor(Color.parseColor("#fbb03b"));
        // You can also hide Skip button
        showSkipButton(true);
    }

    @Override
    public void onSkipPressed() {
        SharedPreferences shared = getSharedPreferences("lead", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("First", false);
        editor.commit();
        ZeroZeroSevenUtils.SwitchActivity(SlideActivity.this,LoginActivity.class);
        finish();
    }

    @Override
    public void onDonePressed() {
        SharedPreferences shared = getSharedPreferences("lead", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("First", false);
        editor.commit();
        ZeroZeroSevenUtils.SwitchActivity(SlideActivity.this,LoginActivity.class);
        finish();
    }
}
