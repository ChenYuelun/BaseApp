package com.example.base.baseapp.customerViews;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.baseapp.AppJumpUtils;
import com.example.base.baseapp.R;
import com.example.base.baseapp.entity.SplashBean;

/**
 * Created by chenyuelun on 2018/3/5.
 * 闪屏控件
 */

public class SplashView extends FrameLayout {
    private SplashBean splashBean;//存放闪屏数据
    private OnSplashFinishListener onSplashFinishListener;//闪屏结束的监听
    private ImageView iv_splash;//闪屏图片
    private TextView tv_time_splash;//倒计时显示
    private CountDownTimer countDownTimer;
    private int defaultTime = 2000;//默认闪屏时间2秒

    public SplashView(@NonNull Context context) {
        this(context, null);
    }

    public SplashView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.splash_view, this);
        iv_splash = findViewById(R.id.iv_splash);
        tv_time_splash = findViewById(R.id.tv_time_splash);
    }

    public void setSplashData(final SplashBean bean) {
        if (bean == null) {
            iv_splash.setVisibility(GONE);
            tv_time_splash.setVisibility(GONE);
            return;
        }
        splashBean = bean;
        startCountDown();
    }

    private void startCountDown() {
        if (splashBean == null) {
            return;
        }
        //延迟一秒展示闪屏
        postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_splash.setVisibility(VISIBLE);
                tv_time_splash.setVisibility(VISIBLE);
                iv_splash.setImageBitmap(splashBean.getBitmap());
                //取出闪屏时间，若小于2秒，设置成2秒
                int downTime = splashBean.getCountDownTime();
                if (downTime < defaultTime) {
                    downTime = defaultTime;
                }
                countDownTimer = new CountDownTimer(downTime, 100) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        //倒计时文本更新
                        long l = millisUntilFinished / 1000;
                        String timeFormat = getResources().getString(R.string.splash_time);
                        String time = String.format(timeFormat, l);
                        tv_time_splash.setText(time);
                    }

                    @Override
                    public void onFinish() {
                        if (onSplashFinishListener != null) {
                            onSplashFinishListener.onFinsh();
                        }
                    }
                };
                //设置倒计时点击跳过，直接结束闪屏播放
                tv_time_splash.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopCountDown();
                        if (onSplashFinishListener != null) {
                            onSplashFinishListener.onFinsh();
                        }
                    }
                });


                //设置图片点击跳转
                iv_splash.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopCountDown();
                        //点击闪屏图片 scheme跳转
                        String jumpUrl = splashBean.getJumpUrl();
                        if (!TextUtils.isEmpty(jumpUrl))
                        AppJumpUtils.allKindsOfJump(getContext(), jumpUrl);
                    }
                });
                countDownTimer.start();
            }
        }, 1000);
    }

    /**
     * 结束倒计时 在倒计时时当前activity被切换到后台或者杀死，要及时停止倒计时
     */
    public void stopCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    public OnSplashFinishListener getOnSplashFinishListener() {
        return onSplashFinishListener;
    }

    public void setOnSplashFinishListener(OnSplashFinishListener onSplashFinishListener) {
        this.onSplashFinishListener = onSplashFinishListener;
    }

    public interface OnSplashFinishListener {
        void onFinsh();
    }

    /**
     * 用于databinding 在布局文件绑定数据
     *
     * @param splashView
     * @param bean
     */
    @BindingAdapter("android:splash_data")
    public static void splash_data(SplashView splashView, SplashBean bean) {
        if (splashView != null && bean != null) {
            splashView.setSplashData(bean);
        }
    }
}
