package com.example.base.baseapp.customerViews;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.base.baseapp.AppJumpUtils;
import com.example.base.baseapp.R;
import com.example.base.baseapp.entity.MarqueeBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by chenyuelun on 2018/3/3.
 * <p>
 * 跑马灯控件
 */

public class MarqueeTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {

    private List<MarqueeBean> datas;
    private int interval = 2;//滚动间隔时间，默认5秒
    private Disposable observable;
    private int count;//第几次滚动
    private int position;//当前显示条目position
    private boolean isStarting;

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setFactory(this);
        setDefaultAnimation();

    }

    //设置默认从下往上滚动
    public void setDefaultAnimation() {
        setInAnimation(getContext(), R.anim.marquee_bottom_in);
        setOutAnimation(getContext(), R.anim.marquee_top_out);
    }

    //创建滚动的textView 并设置一些基础属性
    @Override
    public View makeView() {
        TextView textView = new TextView(getContext());
        //设置垂直居中对齐
        textView.setGravity(Gravity.CENTER_VERTICAL);
        //设置字体大小
        textView.setTextSize(13f);
        //设置只显示一行
        textView.setMaxLines(1);
        //设置内边距
        textView.setPadding(0, 12, 0, 12);
        //设置字体颜色
        textView.setTextColor(Color.parseColor("#00ff00"));
        //设置末尾省略
        textView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        textView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击事件跳转
                if (datas != null && datas.size() > position) {
                    MarqueeBean marqueeBean = datas.get(position);
                    if (marqueeBean != null) {
                        String jumpUrl = marqueeBean.getJumpUrl();
                        if (TextUtils.isEmpty(jumpUrl)) {
                            //此处调用统跳方法
                            AppJumpUtils.allKindsOfJump(getContext(), jumpUrl);
                        }
                    }
                    if (listener != null){
                        listener.onItemtClick(position);
                    }

                }
            }
        });
        return textView;
    }

    //设置滚动的Text数据
    public MarqueeTextView setContent(List<MarqueeBean> datas) {
        //初始化数据
        //直接将新数据集合赋值
        this.datas = datas;
        if (this.datas != null && this.datas.size()>0){
            start();
        }
        return this;
    }

    public MarqueeTextView setInterval(int interval) {
        this.interval = interval;
        stop();
        start();
        return this;
    }

    //开始滚动
    public void start() {
        //如果无数据 或者正在轮播，直接返回
        if (datas == null || datas.isEmpty() ||isStarting ) {
            return;
        }
        isStarting = true;
        count = 0;
        position = 0;
        observable = Observable.interval(0, interval, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        position = count % datas.size();
                        MarqueeBean marqueeBean = datas.get(position);
                        if (marqueeBean == null ) {
                            marqueeBean= new MarqueeBean("今天心情美美哒","");
                        }
                        if (TextUtils.isEmpty(marqueeBean.getText())){
                            marqueeBean.setText("今天心情美美哒");
                        }
                        setText(marqueeBean.getText());
                        count++;
                    }
                });
    }

    //停止滚动  释放资源
    public void stop() {
        if (observable != null) {
            isStarting = false;
            observable.dispose();
            observable = null;
        }
    }

    //返回当前显示条目的position
    public int getVisibleItemPosition() {
        return position;
    }


    //点击监听
    private onItemClickListener listener;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public onItemClickListener getListener() {
        return listener;
    }


    public interface onItemClickListener {
        //跑马灯item点击监听
        void onItemtClick(int position);
    }

    /**
     * 用于databinding直接绑定设置数据
     *
     * @android:marquee_content 直接在布局文件中使用的属性名
     * @MarqueeTextView marquee 控件
     * @
     */
    @BindingAdapter("android:marquee_datas")
    public static void setMarqueeImage(MarqueeTextView marquee, List<MarqueeBean> datas) {
        if (marquee != null && datas != null) {
            marquee.setContent(datas);
        }
    }
}
