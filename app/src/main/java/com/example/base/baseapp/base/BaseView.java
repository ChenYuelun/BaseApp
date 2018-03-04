package com.example.base.baseapp.base;

import android.content.Context;

/**
 * Created by chenyuelun on 2018/3/1.
 */

public interface BaseView {
    //获取布局编号Id
    int getLayoutId();

    //初始化
    void initView();

    //获取 BR 参数
    int getBR();

    //获取Context
    Context getMyContext();

}
