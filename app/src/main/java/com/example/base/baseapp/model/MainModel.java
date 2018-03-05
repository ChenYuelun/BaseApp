package com.example.base.baseapp.model;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.base.baseapp.R;
import com.example.base.baseapp.base.BaseModel;
import com.example.base.baseapp.base.OnDataLoadListener;
import com.example.base.baseapp.entity.HomeBottomMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuelun on 2018/3/1.
 */

public class MainModel extends BaseModel {

    //底部导航的默认颜色
    public static final String NORMAL_COLOR = "#BCBBBB";
    public static final String PRESS_COLOR = "#5494ff";


    public void loadNavigationData(Context context, OnDataLoadListener<List<HomeBottomMenuBean>> listener){
        if (context == null || listener == null){
            throw new RuntimeException("Context or OnDataLoadListener can not be null");
        }
        listener.loadStart();
        List<HomeBottomMenuBean> data = new ArrayList();
        data.add(new HomeBottomMenuBean("首页",NORMAL_COLOR,PRESS_COLOR,idToBitmap(context, R.drawable.analyse_normal),idToBitmap(context,R.drawable.analyse_press),1));
        data.add(new HomeBottomMenuBean("我",NORMAL_COLOR,PRESS_COLOR,idToBitmap(context,R.drawable.me_normal),idToBitmap(context,R.drawable.me_press),2));
        if (listener != null){
            listener.loadSuccess(data);
        }
    }



    //测试造数据代码  正式项目此代码删除或者抽取到工具类
    public Bitmap idToBitmap(Context context, int id) {
        return BitmapFactory.decodeResource(context.getResources(), id);
    }

}
