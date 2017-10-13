package com.xuancao.dynamicskin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuancao.dynamicskin.R;
import com.xuancao.dynamicskin.Utils.ViewUtil;
import com.xuancao.dynamicskin.app.Constant;

/**
 * @version V1.0
 * @ClassName: TitleBar
 * @Description: 自定义标题导航栏
 */
public class TitleBar extends RelativeLayout implements OnClickListener {
    private static final String TAG = "TitleBar";
    private long clickTime = 0;
    private int tempViewId;
    private OnTitleClickListener listener;
    private TextView mTvTitle;
    private TextView mTvLeft;
    private TextView mTvRight;
    private TextView mTvSubRight;
    private TextView mTvSubLeft;
    private RelativeLayout mBgLayout;

    public TextView getmTvTitle() {
        return mTvTitle;
    }

    public TextView getmTvLeft() {
        return mTvLeft;
    }

    public TextView getmTvRight() {
        return mTvRight;
    }

    public TextView getmTvSubRight(){
        return mTvSubRight;
    }

    public TextView getmTvSubLeft(){
        return mTvSubLeft;
    }

    public RelativeLayout getmBgLayout() {
        return mBgLayout;
    }

    public void setOnTitleClickListener(OnTitleClickListener listener) {
        this.listener = listener;
    }


    public interface OnTitleClickListener {
        void onTitleLeftClick();
        void onTitleRightClick();
        void onTitleSubRightClick();
        void onTitleSubLeftClick();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.layout_public_titlebar, this);
        TypedArray typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);

        int titleText = typeArray.getResourceId(R.styleable.TitleBar_titleText, 0);
        int titleIcon = typeArray.getResourceId(R.styleable.TitleBar_titleIcon,0);
        int leftIcon = typeArray.getResourceId(R.styleable.TitleBar_leftIcon, 0);

        int leftText = typeArray.getResourceId(R.styleable.TitleBar_leftText, 0);
        int rightIcon = typeArray.getResourceId(R.styleable.TitleBar_rightIcon, 0);
        int rightText = typeArray.getResourceId(R.styleable.TitleBar_rightText, 0);
        int rightSubIcon = typeArray.getResourceId(R.styleable.TitleBar_rightSubIcon, 0);
        int rightSubText = typeArray.getResourceId(R.styleable.TitleBar_rightSubText, 0);
        int leftSubIcon = typeArray.getResourceId(R.styleable.TitleBar_leftSubIcon, 0);
        int leftSubText = typeArray.getResourceId(R.styleable.TitleBar_leftSubText, 0);
        int bgColor = typeArray.getResourceId(R.styleable.TitleBar_bgColor, 0);
        mBgLayout = (RelativeLayout) findViewById(R.id.mBgLayout);

        //为了一键换肤注释掉手动设置背景色
//        if (bgColor > 0) {
//            mBgLayout.setBackgroundColor(getResources().getColor(bgColor));
//        } else {
//            mBgLayout.setBackgroundColor(getResources().getColor(R.color.bg_title_bar));
//        }
        mTvLeft = (TextView) findViewById(R.id.mTvLeft);
        if (leftIcon > 0) {
            mTvLeft.setVisibility(View.VISIBLE);
            ViewUtil.addDrawableLeft(getContext(), mTvLeft, leftIcon);
        } else if (leftText > 0) {
            mTvLeft.setVisibility(View.VISIBLE);
            mTvLeft.setText(leftText);
        } else {
            mTvLeft.setVisibility(View.GONE);
        }

        // 中间标题
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        if (titleIcon > 0){
            mTvTitle.setVisibility(View.VISIBLE);
            ViewUtil.addDrawableCenter(getContext(), mTvTitle, titleIcon);
        }else if (titleText > 0) {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(titleText);
        }

        // 右边图片按钮
        mTvRight = (TextView) findViewById(R.id.mTvRight);
        if (rightIcon > 0) {
            mTvRight.setVisibility(View.VISIBLE);
            ViewUtil.addDrawableRight(getContext(), mTvRight, rightIcon);
        } else if (rightText > 0) {
            mTvRight.setVisibility(View.VISIBLE);
            mTvRight.setText(rightText);
            mTvRight.setTextColor(getResources().getColor(R.color.text_white));
        } else {
            mTvRight.setVisibility(View.GONE);
        }
        // 右边次图片按钮
        mTvSubRight = (TextView) findViewById(R.id.mTvSubRight);
        mTvSubLeft = (TextView) findViewById(R.id.mTvSubLeft);
        if (rightSubIcon > 0) {
            mTvSubRight.setVisibility(View.VISIBLE);
            ViewUtil.addDrawableRight(getContext(), mTvSubRight, rightSubIcon);
        } else if (rightSubText > 0) {
            mTvSubRight.setVisibility(View.VISIBLE);
            mTvSubRight.setText(rightSubText);
            mTvSubRight.setTextColor(getResources().getColor(R.color.text_gray));
        } else {
            mTvSubRight.setVisibility(View.GONE);
        }
        if (leftSubIcon > 0) {
            mTvSubLeft.setVisibility(View.VISIBLE);
            ViewUtil.addDrawableLeft(getContext(), mTvSubLeft, leftSubIcon);
        } else if (leftSubText > 0) {
            mTvSubLeft.setVisibility(View.VISIBLE);
            mTvSubLeft.setText(leftSubText);
            mTvSubLeft.setTextColor(getResources().getColor(R.color.text_white));
        } else {
            mTvSubLeft.setVisibility(View.GONE);
        }
        mTvLeft.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
        mTvSubRight.setOnClickListener(this);
        mTvSubLeft.setOnClickListener(this);
        typeArray.recycle();

    }

    public void setTitle(CharSequence titleText) {
        mTvTitle.setText(titleText);
    }

    public void setTitle(int resid) {
        mTvTitle.setText(resid);
    }

    public void setRightText(int resid) {
        mTvRight.setText(resid);
        mTvRight.setVisibility(View.VISIBLE);
    }

    public void setRightText(CharSequence rightText) {
        mTvRight.setText(rightText);
        mTvRight.setVisibility(View.VISIBLE);
    }

    public void setRightIcon(int resid) {
        mTvRight.setVisibility(View.VISIBLE);
        ViewUtil.addDrawableRight(getContext(), mTvRight, resid);
    }

    public void setLeftIcon(int resid) {
        mTvLeft.setVisibility(View.VISIBLE);
        ViewUtil.addDrawableLeft(getContext(), mTvLeft, resid);
    }

    public void setCenterIcon(int resid) {
        mTvTitle.setVisibility(View.VISIBLE);
        ViewUtil.addDrawableCenter(getContext(), mTvTitle, resid);
    }

    public void setBgColorRes(int resid) {
        mBgLayout.setBackgroundColor(getResources().getColor(resid));
    }

    public void setBgColor(int color) {
        mBgLayout.setBackgroundColor(color);
    }

    @Override
    public void onClick(View v) {
        if ((System.currentTimeMillis() - clickTime) > Constant.CLICK_INTERVAL) {
            clickTime = System.currentTimeMillis();
        } else {
            if (tempViewId == v.getId()) {
                return;
            }
        }
        tempViewId = v.getId();
        switch (v.getId()) {
            case R.id.mTvLeft:
                if (listener != null) {
                    listener.onTitleLeftClick();
                }
                break;
            case R.id.mTvRight:
                if (listener != null && mTvRight.getVisibility() == View.VISIBLE) {
                    listener.onTitleRightClick();
                }
                break;
            case R.id.mTvSubRight:
                if (listener != null && mTvSubRight.getVisibility() == View.VISIBLE) {
                    listener.onTitleSubRightClick();
                }
                break;
            case R.id.mTvSubLeft:
                if (listener != null && mTvSubLeft.getVisibility() == View.VISIBLE) {
                    listener.onTitleSubLeftClick();
                }
                break;
            default:
                break;
        }
    }

}
