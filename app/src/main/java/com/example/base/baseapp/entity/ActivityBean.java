package com.example.base.baseapp.entity;

/**
 * Created by chenyuelun on 2018/3/3.
 * 说明：活动弹窗Bean
 */

public class ActivityBean {

    //活动图片地址
    private String imageUrl;
    //通过scheme跳转的Url地址
    private String jumpUrl;

    public ActivityBean() {
    }
    public ActivityBean(String imageUrl, String jumpUrl) {
        this.imageUrl = imageUrl;
        this.jumpUrl = jumpUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
