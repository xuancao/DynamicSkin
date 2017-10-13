package com.xuancao.dynamicskin.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.appthemeengine.Config;
import com.xuancao.dynamicskin.R;
import com.xuancao.dynamicskin.base.SimpleBaseFragment;

/**
 * Created by xuancao on 2017/10/13.
 */

public class HomeFragment extends SimpleBaseFragment{

    private View view;
    private ImageView imageView;

    @Override
    protected View onFragmentCreated(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        return view;
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.iv_change);
    }

    @Override
    protected void onRightClick() {
        Intent intent = new Intent(context,ChoiceColorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initData() {
        imageView.setBackground(new ColorDrawable(Config.getPrimaryColor() == 0 ? getResources().getColor(R.color.global_color) : Config.getPrimaryColor()));
    }
}
