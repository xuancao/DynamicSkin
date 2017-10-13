package com.xuancao.dynamicskin.ui;

public class CustomData {
    private int mBackgroundColor;
    private int mBackgroundResId;
    public CustomData(int backdroundResId, int backgroundColor) {
        mBackgroundColor = backgroundColor;
        mBackgroundResId=backdroundResId;

    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public int getmBackgroundResId() {
        return mBackgroundResId;
    }

}
