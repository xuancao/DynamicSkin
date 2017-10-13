package com.xuancao.dynamicskin.base;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;

import com.xuancao.dynamicskin.R;
import com.xuancao.dynamicskin.app.Constant;
import com.xuancao.dynamicskin.view.TitleBar;


public abstract class BaseCustomActivity extends BaseActivity implements OnClickListener {
    private long clickTime = 0;
    private int tempViewId;
    protected TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActivityCreated(savedInstanceState);
        initTitleBar();
        initView();
        initData();
        setListener();
    }


    /**
     * @MethodName:onActivityCreated
     * @Description: 相当于onCreate, 为了不让子类显示super.onCreate()而诞生的方法
     */
    protected abstract void onActivityCreated(Bundle savedInstanceState);

    /**
     * @MethodName:initTitleBar
     * @Description: 注册标题TitleBar
     */
    protected void initTitleBar() {
        titleBar = (TitleBar) findViewById(R.id.titleBar);
        if (titleBar != null) {
            titleBar.setOnTitleClickListener(new TitleBar.OnTitleClickListener() {

                @Override
                public void onTitleLeftClick() {
                    onLeftClick();
                }

                @Override
                public void onTitleRightClick() {
                    onRightClick();
                }

                @Override
                public void onTitleSubRightClick() {
                    onSubRightClick();
                }

                @Override
                public void onTitleSubLeftClick() {
                    onSubLeftClick();
                }
            });
        }
    }

    protected abstract void initView();

    /**
     * @MethodName:setListener
     * @Description: 设置监听 已在oncreate中调用
     */
    protected abstract void setListener();

    /**
     * @param v void
     * @MethodName:onClickEvent
     * @Description: 点击事件包装
     */
    protected abstract void onClickEvent(View v);

    /**
     * @MethodName:initData
     * @Description: 设置数据
     */
    protected abstract void initData();

    @Override
    public void onClick(View v) {
        if ((System.currentTimeMillis() - clickTime) >  Constant.CLICK_INTERVAL) {
            clickTime = System.currentTimeMillis();
        } else {
            if (tempViewId == v.getId()) {
                return;
            }
        }
        tempViewId = v.getId();
        onClickEvent(v);
    }

    @Nullable
    @Override
    protected final String getATEKey() {
        return PreferenceManager.getDefaultSharedPreferences(this).getBoolean("dark_theme", false) ?
                "dark_theme" : "light_theme";
    }


    protected void onLeftClick() {
        onBackPressed();
    }

    protected void onRightClick() {

    }

    protected void onSubRightClick() {

    }

    protected void onSubLeftClick() {

    }



}
