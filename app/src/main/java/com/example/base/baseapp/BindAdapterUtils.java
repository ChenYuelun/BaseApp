package com.example.base.baseapp;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.baseapp.customerViews.MarqueeTextView;
import com.youth.banner.Banner;

import java.util.List;


/**
 * Created by chenyuelun on 2018/3/2.
 *
 * databing自定义属性
 */

public class BindAdapterUtils {

    //直接设置类似于"#ffffff"的颜色为text文本颜色
    @BindingAdapter("android:string_color")
    public static void setTextColor(TextView textView,String color){
        Log.e("mmmmmm",color);
        if (textView != null && !TextUtils.isEmpty(color)){
            Log.e("mmmmmm",color);
            textView.setTextColor(Color.parseColor(color));
        }
    }

    //将bitmap直接设置在iamgeView上
    @BindingAdapter("android:bitmap")
    public static void setBitmap(ImageView imageView, Bitmap bitmap){
        if (imageView != null && bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
    }




}
