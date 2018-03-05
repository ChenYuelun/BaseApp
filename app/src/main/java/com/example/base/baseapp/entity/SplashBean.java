package com.example.base.baseapp.entity;

import android.graphics.Bitmap;

/**
 * Created by chenyuelun on 2018/3/5.
 */

public class SplashBean {


    private Bitmap bitmap;//闪屏图片，为了优化加载速度，直接缓存bitmap而不是图片地址
    private int countDownTime;//闪屏倒计时时间
    private String jumpUrl;//闪屏活动scheme跳转地址

    public SplashBean() {}

    public SplashBean(Bitmap bitmap, int countDownTime, String jumpUrl) {
        this.bitmap = bitmap;
        this.countDownTime = countDownTime;
        this.jumpUrl = jumpUrl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getCountDownTime() {
        return countDownTime;
    }

    public void setCountDownTime(int countDownTime) {
        this.countDownTime = countDownTime;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
