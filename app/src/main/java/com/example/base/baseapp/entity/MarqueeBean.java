package com.example.base.baseapp.entity;

/**
 * Created by chenyuelun on 2018/3/3.
 * 跑马灯Bean
 */

public class MarqueeBean {
    //跑马灯展示的文本数据
    private String text;
    //通过scheme跳转的Url地址
    private String jumpUrl;

    public MarqueeBean() {
    }
    public MarqueeBean(String imageUrl, String jumpUrl) {
        this.text = imageUrl;
        this.jumpUrl = jumpUrl;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
