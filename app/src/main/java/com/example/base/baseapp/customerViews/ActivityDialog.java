package com.example.base.baseapp.customerViews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.baseapp.R;
import com.example.base.baseapp.entity.ActivityBean;

/**
 * Created by chenyuelun on 2018/3/3.
 */

public class ActivityDialog extends AlertDialog {
    protected ActivityDialog(Context context) {
        super(context);
    }

    protected ActivityDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    /**
     * 创建活动弹窗
     * @param activity  //上下文 必须为Activity上下文
     * @param activityBean 存放活动数据的bean对象
     * @param listener 若要对点击后的事件进一步处理,传入接口监听
     */
    public static void create(Activity activity, final ActivityBean activityBean, final OnDialogClickListener listener){
        try {
            View view = View.inflate(activity, R.layout.activity_dialog,null);
            View close = view.findViewById(R.id.iBtnClose);
            ImageView choice = (ImageView)view.findViewById(R.id.ivActivity);
            if (activityBean != null){
                //此处使用图片加载框架加载图片
                String imageUrl = activityBean.getImageUrl();

            }
            Builder builder = new Builder(new ContextThemeWrapper(activity, R.style.myDialog));
            final AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
            dialog.getWindow().setContentView(view);
            //设置点击边缘消失
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
            //设置监听
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialog.cancel();
                    if (listener != null){
                        listener.onClose();
                    }
                }
            });

            choice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialog.cancel();
                    if (activityBean != null && !TextUtils.isEmpty(activityBean.getJumpUrl())){
                        //此处调用统跳方法跳转

                    }
                    if (listener != null){
                        listener.onPressImage();
                    }
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }



    }

    public interface OnDialogClickListener{
        void onPressImage();
        void onClose();
    }
}
