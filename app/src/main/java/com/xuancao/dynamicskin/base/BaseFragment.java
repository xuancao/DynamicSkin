package com.xuancao.dynamicskin.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.appthemeengine.ATE;
import com.xuancao.dynamicskin.Utils.LogUtil;
import com.xuancao.dynamicskin.app.MyApp;

/**
 * Created by xuancao on 2017/4/19.
 */

public abstract class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    protected Activity context;
    protected LayoutInflater inflater;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        LogUtil.i("BaseFragment", TAG + "->onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ATE.preApply(getActivity(), MyApp.getATEKey());
        super.onCreate(savedInstanceState);
        LogUtil.i("BaseFragment", TAG + "->onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i("BaseFragment", TAG + "->onCreateView");
        this.inflater = inflater;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i("BaseFragment", TAG + "->onActivityCreated");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.i("BaseFragment", TAG + "->onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        ATE.apply(this, MyApp.getATEKey());
        LogUtil.i("BaseFragment", TAG + "->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("BaseFragment", TAG + "->onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i("BaseFragment", TAG + "->onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i("BaseFragment", TAG + "->onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i("BaseFragment", TAG + "->onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("BaseFragment", TAG + "->onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.i("BaseFragment", TAG + "->onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            LogUtil.i("BaseFragment", TAG + "->setUserVisibleHint");
        }
    }

    /**
     * @param clazz void
     * @MethodName:goActivity
     * @Description: 跳转到Activity
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        startActivity(intent);
    }



}