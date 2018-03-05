package com.example.base.baseapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.base.baseapp.R;
import com.example.base.baseapp.base.BaseModel;
import com.example.base.baseapp.base.OnDataLoadListener;
import com.example.base.baseapp.entity.SplashBean;

/**
 * Created by chenyuelun on 2018/3/5.
 */

public class WelcomeModel extends BaseModel {

    public void loadSplashData(Context context,OnDataLoadListener<SplashBean> listener){
        listener.loadSuccess(new SplashBean(idToBitmap(context, R.drawable.shan), 4100, ""));


    }
    //测试造数据代码  正式项目此代码删除或者抽取到工具类
    public Bitmap idToBitmap(Context context, int id) {
        return BitmapFactory.decodeResource(context.getResources(), id);
    }
}
