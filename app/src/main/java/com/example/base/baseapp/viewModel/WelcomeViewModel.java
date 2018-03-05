package com.example.base.baseapp.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.base.baseapp.R;
import com.example.base.baseapp.base.BaseModel;
import com.example.base.baseapp.base.BaseViewModel;
import com.example.base.baseapp.base.OnDataLoadListener;
import com.example.base.baseapp.entity.SplashBean;
import com.example.base.baseapp.model.WelcomeModel;
import com.example.base.baseapp.view.activity.WelcomeActivity;

/**
 * Created by chenyuelun on 2018/3/5.
 */

public class WelcomeViewModel extends BaseViewModel<WelcomeActivity, WelcomeModel> {

    public ObservableField<SplashBean> splashBean = new ObservableField<>();

    @Override
    protected WelcomeModel createModel() {
        return new WelcomeModel();
    }

    @Override
    public void fetch() {
        mModel.loadSplashData(mView.get(),new OnDataLoadListener<SplashBean>() {
            @Override
            public void loadSuccess(SplashBean data) {
                splashBean.set(data);
            }

            @Override
            public void loadFailure(String message) {

            }

            @Override
            public void loadStart() {

            }
        });

    }


}
