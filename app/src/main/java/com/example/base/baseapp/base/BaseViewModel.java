package com.example.base.baseapp.base;

import android.content.Context;
import android.databinding.BaseObservable;

import java.lang.ref.WeakReference;

/**
 * Created by chenyuelun on 2018/3/1.
 */

public abstract class BaseViewModel<V extends BaseView , M extends BaseModel> extends BaseObservable {

    protected M mModel;//model实例
    //弱引用持有View对象，使用前一定要判空，防止空指针
    protected WeakReference<V> mView;
    //viewModel与View建立关联
    public void atach(V v) {
        mView = new WeakReference<V>(v);
        mModel = createModel();
    }
    //子类集成 实例化model
    protected abstract M createModel();

    //viewModel与View解除
    public void detach() {
        mView.clear();
        mView = null;
    }

    //执行逻辑代码
    public abstract void fetch();

    //获取上下文
    public Context getContext() {
        if (mView != null && mView.get() != null){
            return mView.get().getMyContext();
        }
       return  null;
    }
}
