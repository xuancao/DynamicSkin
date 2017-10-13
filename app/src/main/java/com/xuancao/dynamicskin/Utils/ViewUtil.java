package com.xuancao.dynamicskin.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by xuancao on 2017/7/04.
 */

public class ViewUtil {

    public static void addDrawableRight(Context context, TextView textView, int resId) {
        Drawable arrow = context.getResources().getDrawable(resId);
        arrow.setBounds(0, 0, arrow.getIntrinsicWidth(), arrow.getIntrinsicHeight());
        textView.setCompoundDrawables(null, null, arrow, null);
    }

    public static void addDrawableCenter(Context context, TextView textView, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void addDrawableLeft(Context context, TextView textView, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }
}
