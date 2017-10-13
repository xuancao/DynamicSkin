package com.xuancao.dynamicskin.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xuancao on 2017/7/23.
 */

public class SkinHelper {

    static String DEF_SHARE_NAME= Constant.SHARE_NAME;
    private static int MODE = Context.MODE_PRIVATE;

    public static final String SKIN_TYPE = "skin_type"; //皮肤类型 1.红  2.橙 3.黑 4.绿 5.青 6.蓝 7.紫色  8.自选

    public static void setSkinType(int skin_type) {
        putInt(SKIN_TYPE, skin_type);
    }

    public static int getSkinType() {
        return getInt(SKIN_TYPE, 1); //默认官方红
    }

    public static void putInt(String key, int value) {
        putInt(DEF_SHARE_NAME, key, value);
    }
    public static void putInt(String shareName, String key, int value) {
        SharedPreferences share = getSharePrefer(shareName);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private static SharedPreferences getSharePrefer(String shareName) {
        return MyApp.getInstance().getSharedPreferences(shareName, MODE);
    }

    public static int getInt(String key, int defValue) {
        return getInt(DEF_SHARE_NAME, key, defValue);
    }

    public static int getInt(String shareName, String key, int defValue) {
        SharedPreferences share = getSharePrefer(shareName);
        return share.getInt(key, defValue);
    }
}
