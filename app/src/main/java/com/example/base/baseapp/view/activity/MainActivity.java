package com.example.base.baseapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import com.example.base.baseapp.BR;
import com.example.base.baseapp.base.BaseFragment;
import com.example.base.baseapp.bottomMenu.HomeBottomMenuGridView;
import com.example.base.baseapp.R;
import com.example.base.baseapp.base.BaseActivity;
import com.example.base.baseapp.databinding.ActivityMainBinding;
import com.example.base.baseapp.view.fragment.HomeFragment;
import com.example.base.baseapp.view.fragment.MeFragment;
import com.example.base.baseapp.viewModel.MainViewModel;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> {

    private Map<Integer,BaseFragment> fragments;
    private BaseFragment preFragment;

    //返回布局Id
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 启动本Antivity的方法
     * @param context 上下文
     * @param positionTag 显示哪个fragment
     */
    public static void start(Context context,int positionTag){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("positionTag",positionTag);
        context.startActivity(intent);
    }

    //初始化
    @Override
    public void initView() {
        mViewModel.fetch();
        //初始化所有fragment
        initFragment();
        //底部导航设置item点击监听
        mDataBinding.gvMainNavigation.setOnItemSelectedListener(new HomeBottomMenuGridView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int postionTag) {
                channgeShowFragmentByTag(postionTag);
            }
        });
        //底部导航栏数据加载完毕的监听
        mDataBinding.gvMainNavigation.setOnNavigationReadyListener(new HomeBottomMenuGridView.OnNavigationReadyListener() {
            @Override
            public void onNavigationReady() {
                mDataBinding.gvMainNavigation.setSeletedByPosition(0);
            }
        });
    }

    //初始化fragment实例
    private void initFragment() {
        HomeFragment fragmentOne = new HomeFragment();
        MeFragment fragmentTwo = new MeFragment();
        fragments = new HashMap<>();
        fragments.put(1,fragmentOne);
        fragments.put(2,fragmentTwo);
    }


    //返回BR 用于绑定数据和布局
    @Override
    public int getBR() {
        return BR.mMainViewModel;
    }



    /**
     * 根据tag切换前台显示的fragment
     * @param postionTag
     */
    private void channgeShowFragmentByTag(int postionTag) {
        //先从存放所有fragment的集合通过 tag 取出想要显示的fragment
        BaseFragment fragment = fragments.get(postionTag);
        //如果想要显示的fragment与当前显示的fragment相同 return，不做操作
        if (fragment == preFragment){
            return;
        }
        //开启事务
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (preFragment != null){
            //隐藏当前显示非fragment
            fragmentTransaction.hide(preFragment);
        }
        //如果想要展示的fragment已经添加到布局，直接显示，否则添加
        if (fragment.isAdded()){
            fragmentTransaction.show(fragment);
        }else {
            fragmentTransaction.add(R.id.fl_main,fragment);
        }
        //更新当前显示的fragment
        preFragment = fragment;
        //提交事务
        fragmentTransaction.commit();
    }

    //实例化ViewModel对象
    @Override
    protected MainViewModel createViewModel() {
        return new MainViewModel();
    }
}
