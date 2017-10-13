package com.xuancao.dynamicskin.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.afollestad.appthemeengine.Config;
import com.xuancao.dynamicskin.R;
import com.xuancao.dynamicskin.base.SimpleBaseActivity;


/**
 * Created by xuancao on 2017/10/13.
 */

public class MainActivity extends SimpleBaseActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;

    private Class fragmentArray[] = {HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class};
    private int mImageViewArray[] = {R.drawable.tab_home_btn, R.drawable.tab_game_btn, R.drawable.tab_circle_btn, R.drawable.tab_find_btn, R.drawable.tab_my_btn};
    private int mTextviewArray[] = {R.string.tab_home, R.string.tab_game, R.string.tab_circle, R.string.tab_find, R.string.tab_my};
    private static int position = 0;



    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }


    protected void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        //实例化TabHost对象，得到TabHost
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //得到fragment的个数
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getResources().getString(mTextviewArray[i])).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            //  mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE); //去掉每个tab之间的分割线
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                position = mTabHost.getCurrentTab();
                upDateTab(mTabHost);  //tab切换时设置滤镜
            }
        });
        mTabHost.setCurrentTab(position);
        upDateTab(mTabHost); //初始化设置滤镜
    }

    @Override
    protected void initData() {
        super.initData();
    }

    private void upDateTab(FragmentTabHost mTabHost) {
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            ImageView imageView = mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.imageview);
            if (mTabHost.getCurrentTab() == i) {//选中
                imageView.setColorFilter(Config.getPrimaryColor() == 0 ? getResources().getColor(R.color.global_color) : Config.getPrimaryColor());
            } else {//不选中
                imageView.clearColorFilter();
            }
        }
    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        //  imageView.setColorFilter(getResources().getColor(R.color.global_color));
        TextView textView = view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        return view;
    }


}
