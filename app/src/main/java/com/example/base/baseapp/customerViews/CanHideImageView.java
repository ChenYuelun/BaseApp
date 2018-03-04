package com.example.base.baseapp.customerViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.base.baseapp.R;

/**
 * Created by chenyuelun on 2018/3/3.
 */

public class CanHideImageView extends FrameLayout {
    private ImageView imageView;//显示活动图片
    private ImageButton ibtnClose;//关闭按钮

    public CanHideImageView(@NonNull Context context) {
        this(context,null);
    }

    public CanHideImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        if (isInEditMode()) {
            return;
        }
        imageView = new ImageView(getContext());
        imageView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ibtnClose = new ImageButton(getContext());
        LayoutParams lp = new LayoutParams(40, 40);
        lp.gravity = Gravity.TOP | Gravity.RIGHT;
        ibtnClose.setLayoutParams(lp);
        ibtnClose.setBackgroundResource(R.drawable.me_red_close);

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPressListener != null){
                    onPressListener.onClick();
                }
            }
        });

        ibtnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPressListener !=null){
                    onPressListener.onClose();
                }
            }
        });
    }



    private OnPressListener onPressListener;

    public OnPressListener getOnPressListener() {
        return onPressListener;
    }

    public void setOnPressListener(OnPressListener onPressListener) {
        this.onPressListener = onPressListener;
    }

    public interface OnPressListener{
        //当用户点击图片的时候
        void onClick();
        //当用户点击关闭按钮关闭的时候
        void onClose();
    }
}
