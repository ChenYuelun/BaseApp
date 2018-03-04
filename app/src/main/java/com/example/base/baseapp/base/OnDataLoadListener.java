package com.example.base.baseapp.base;

import java.util.List;

/**
 * Created by chenyuelun on 2018/3/2.
 */

public interface OnDataLoadListener<T> {
    /**
     * 加载数据成功
     *
     * @param data
     */
    void loadSuccess(T data);

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);

    /**
     * 开始加载
     */
    void loadStart();

}
