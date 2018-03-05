package com.example.base.baseapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.base.baseapp.BR;
import com.example.base.baseapp.R;
import com.example.base.baseapp.base.BaseActivity;
import com.example.base.baseapp.base.BaseViewModel;
import com.example.base.baseapp.customerViews.SplashView;
import com.example.base.baseapp.databinding.ActivityWelcomeBinding;
import com.example.base.baseapp.viewModel.WelcomeViewModel;

public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding,WelcomeViewModel> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        mViewModel.fetch();
        //闪屏结束的监听
        mDataBinding.flSplash.setOnSplashFinishListener(new SplashView.OnSplashFinishListener() {
            @Override
            public void onFinsh() {
                MainActivity.start(WelcomeActivity.this,1);
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDataBinding.flSplash.stopCountDown();
    }

    @Override
    public int getBR() {
        return BR.mWelcomeViewModel;
    }

    @Override
    protected WelcomeViewModel createViewModel() {
        return new WelcomeViewModel();
    }
}
