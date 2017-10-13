package com.xuancao.dynamicskin.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.xuancao.dynamicskin.R;
import com.xuancao.dynamicskin.app.Constant;
import com.xuancao.dynamicskin.view.TitleBar;

import java.lang.reflect.Field;

public abstract class BaseCustomFragment extends BaseFragment implements OnClickListener {
    private long clickTime = 0;
    private int tempViewId;
    private View mView;
    protected TitleBar titleBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //缓存view 防止fragment复用的时候重复执行onCreateView加载布局
        if (mView == null) {
            mView = onFragmentCreated(inflater, container, savedInstanceState);
            initTitleBar();
            initView();
            initData();
            setListener();
        }else {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        }
        return mView;
    }

    /**
     * Desc:加载布局
     */
    protected abstract View onFragmentCreated(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState);

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


    /**
     * Desc:findviewById
     */
    protected abstract void initView();

    /**
     * @MethodName:setListener
     * @Description: 设置监听
     */
    protected abstract void setListener();

    /**
     * @param v void
     * @MethodName:onClickEvent
     * @Description: 点击事件的封装
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

    /**
     * @param id
     * @return View
     * @MethodName:findViewById
     * @Description: findViewById的抽取
     */
    protected View findViewById(int id) {
        return mView.findViewById(id);
    }

    /**
     * This may fix the bug
     * java.lang.RuntimeException: java.lang.IllegalStateException: Activity has been destroyed
     * http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception
     * -activity-has-been-destroyed
     */
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected void onLeftClick() {
        getActivity().onBackPressed();
    }

    protected void onRightClick() {

    }

    protected void onSubRightClick() {

    }


    protected void onSubLeftClick() {

    }


}
