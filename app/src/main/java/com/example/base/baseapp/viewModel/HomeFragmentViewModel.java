package com.example.base.baseapp.viewModel;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.base.baseapp.entity.BannerBean;
import com.example.base.baseapp.entity.MarqueeBean;
import com.example.base.baseapp.view.fragment.HomeFragment;
import com.example.base.baseapp.model.HomeFragmentModel;
import com.example.base.baseapp.base.BaseViewModel;
import com.example.base.baseapp.base.OnDataLoadListener;

import java.util.List;

/**
 * Created by chenyuelun on 2018/3/2.
 */

public class HomeFragmentViewModel extends BaseViewModel<HomeFragment,HomeFragmentModel> {

    public ObservableField<List<BannerBean>> bannerData = new ObservableField();
    public ObservableField<List<MarqueeBean>> marqueeData = new ObservableField();

    @Override
    protected HomeFragmentModel createModel() {
        return new HomeFragmentModel();
    }

    @Override
    public void fetch() {
        mModel.loadBannerData(new OnDataLoadListener<List<BannerBean>>() {

            @Override
            public void loadSuccess(List<BannerBean> data) {
                bannerData.set(data);
            }

            @Override
            public void loadFailure(String message) {

            }

            @Override
            public void loadStart() {

            }
        });

        mModel.loadMarqueeData(new OnDataLoadListener<List<MarqueeBean>>() {
            @Override
            public void loadSuccess(List<MarqueeBean> data) {
                marqueeData.set(data);
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
