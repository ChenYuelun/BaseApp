package com.example.base.baseapp.customerViews;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.base.baseapp.AppJumpUtils;
import com.example.base.baseapp.entity.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuelun on 2018/3/3.
 */

public class MyBanner extends Banner {

    private List<BannerBean> datas;
    private List<String> images;

    public MyBanner(Context context) {
        this(context, null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //设置Banner数据 并做对播放属性进行设置
    public void setData(final List<BannerBean> datas) {
        this.datas = datas;
        //存放图片字符串的集合初始化
        if (this.images == null) {
            this.images = new ArrayList<>();
        } else {
            images.clear();
        }
        //图片集合赋值
        for (BannerBean bean : datas) {
            images.add(bean.getImageUrl());
        }
        //没有图片 数据异常  放一张默认的
        if (images.size() == 0) {
            images.add("http://7vzspj.com2.z0.glb.clouddn.com/image/focus/default_focus.png");
        }
        //设置图片集合
        setImages(images);
        //设置图片加载器
        setImageLoader(new BannerImageLoader());
        //设置点击事件
        setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
                if (datas.get(position) != null) {
                    String jumpUrl = datas.get(position).getJumpUrl();
                    if (!TextUtils.isEmpty(jumpUrl)) {
                        //此处调用统跳方法跳转
                        AppJumpUtils.allKindsOfJump(getContext(),jumpUrl);
                    }
                }
            }
        });
        //开始轮播
        start();
    }

    //用于DataBinding 直接绑定banner设置数据
    @BindingAdapter("android:banner_datas")
    public static void setBannerImage(MyBanner banner, List<BannerBean> datas) {
        if (banner != null && datas != null) {
            banner.setData(datas);
        }
    }

    /**
     * 内部类
     * banner图片加载器
     */
    public class BannerImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
