package com.xuancao.dynamicskin.app;

import android.app.Application;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by xuancao on 2017/10/13.
 */

public class MyApp extends Application {

    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }

    @Nullable
    public static String getATEKey() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).getBoolean("dark_theme", false) ? "dark_theme" : "light_theme";
    }
}

