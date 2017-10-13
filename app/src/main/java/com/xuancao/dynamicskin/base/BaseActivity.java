package com.xuancao.dynamicskin.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.afollestad.appthemeengine.ATE;
import com.xuancao.dynamicskin.Utils.LogUtil;

/**
 * Desc:所有Activity的基类，可在此类添加与业务无关的公用变量、方法，友盟统计，日志记录等
 */

public class BaseActivity extends FragmentActivity {
    protected String TAG = getClass().getSimpleName();
    protected Context context;

    private long updateTime = -1;

    @Nullable
    protected String getATEKey() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        ATE.preApply(this, getATEKey());
        super.onCreate(savedInstanceState);
        updateTime = System.currentTimeMillis();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i("BaseActivity", TAG + "->onStart");

        ATE.apply(this, getATEKey());
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("BaseActivity", TAG + "->onResume");

        if (ATE.didValuesChange(this, updateTime, getATEKey()))
            recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i("BaseActivity", TAG + "->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i("BaseActivity", TAG + "->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("BaseActivity", TAG + "->onDestroy");
    }

    /**
     * Activity跳转
     */
    public void startActivity(Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        startActivity(intent);
    }


}
