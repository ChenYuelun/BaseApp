package com.example.base.baseapp.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chenyuelun on 2018/3/1.
 */

public abstract class BaseActivity<VDB extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity implements BaseView{

    protected VDB mDataBinding;
    protected VM mViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化dataBinding
        mDataBinding = DataBindingUtil.setContentView(this,getLayoutId());
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
    }

    /**
     * 子类必须重写 用于创建ViewModel实例
     * @return
     */
    protected abstract VM createViewModel();


    /**
     * 获取Intent携带的数据  选择性重写
     */
    private void initIntentData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //viewModel解绑
        mViewModel.detach();
    }

    /**
     * 用于View获取上下文
     * @return
     */
    @Override
    public Context getMyContext() {
        return this;
    }
}
