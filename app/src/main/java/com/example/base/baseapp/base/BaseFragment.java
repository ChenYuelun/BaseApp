package com.example.base.baseapp.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenyuelun on 2018/3/1.
 */

public abstract class BaseFragment<VDB extends ViewDataBinding,VM extends BaseViewModel> extends Fragment implements BaseView {

    protected VDB mDataBinding;
    protected VM mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        //初始化ViewModel
        mViewModel = createViewModel();
        //viewModel关联View
        mViewModel.atach(this);
        //DataBinding绑定ViewModel
        mDataBinding.setVariable(getBR(),mViewModel);
        //获取intent携带的数据 选择新重写
        initIntentData();
        //用于执行一些优先级较高的逻辑操作，如获取控件、数据、设置监听
        initView();
        return mDataBinding.getRoot();
    }

    /**
     * 子类必须重写 用于创建ViewModel实例
     * @return
     */
    protected abstract VM createViewModel();

    /**
     * 获取intent携带的数据 选择新重写
     */
    private void initIntentData() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.detach();
    }

    /**
     * 用于View获取上下文
     * @return
     */
    @Override
    public Context getMyContext() {
        return getContext();
    }

}
