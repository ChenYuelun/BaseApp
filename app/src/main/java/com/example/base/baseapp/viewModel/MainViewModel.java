package com.example.base.baseapp.viewModel;

import android.databinding.ObservableField;
import android.util.Log;


import com.example.base.baseapp.view.activity.MainActivity;
import com.example.base.baseapp.base.BaseViewModel;
import com.example.base.baseapp.base.OnDataLoadListener;
import com.example.base.baseapp.entity.HomeBottomMenuBean;
import com.example.base.baseapp.model.MainModel;

import java.util.List;

/**
 * Created by chenyuelun on 2018/3/1.
 */

public class MainViewModel extends BaseViewModel<MainActivity, MainModel> {

    public ObservableField<List<HomeBottomMenuBean>> navigationData = new ObservableField<>();


    @Override
    protected MainModel createModel() {
        return new MainModel();
    }

    @Override
    public void fetch() {
        mModel.loadNavigationData(mView.get(), new OnDataLoadListener<List<HomeBottomMenuBean>>() {
            @Override
            public void loadSuccess(List<HomeBottomMenuBean> data) {
                if (mView != null){
                    navigationData.set(data);
                }
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
