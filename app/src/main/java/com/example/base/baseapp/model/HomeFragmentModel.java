package com.example.base.baseapp.model;

import com.example.base.baseapp.base.BaseModel;
import com.example.base.baseapp.base.OnDataLoadListener;
import com.example.base.baseapp.entity.BannerBean;
import com.example.base.baseapp.entity.MarqueeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuelun on 2018/3/2.
 */

public class HomeFragmentModel extends BaseModel {

    public void loadBannerData(OnDataLoadListener<List<BannerBean>> listener){
        listener.loadStart();
        List<BannerBean> bannerIamgeList = new ArrayList<>();
        String image = "http://7vzspj.com2.z0.glb.clouddn.com/image/focus/default_focus.png";
        bannerIamgeList.add(new BannerBean(image,""));
        bannerIamgeList.add(new BannerBean(image,""));
        bannerIamgeList.add(new BannerBean(image,""));
        listener.loadSuccess(bannerIamgeList);
    }
    public void loadMarqueeData(OnDataLoadListener<List<MarqueeBean>> listener){
        listener.loadStart();
        List<MarqueeBean> textList = new ArrayList<>();
        textList.add(new MarqueeBean("0000000",""));
        textList.add(new MarqueeBean("1111111",""));
        textList.add(new MarqueeBean("2222222",""));
        listener.loadSuccess(textList);
    }


}
