package com.example.base.baseapp.bottomMenu;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.base.baseapp.R;
import com.example.base.baseapp.databinding.ItemMainNavigationBinding;
import com.example.base.baseapp.entity.HomeBottomMenuBean;

import java.util.List;

/**
 * Created by chenyuelun on 2018/3/1.
 */

public class HomeNavigationGvAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HomeBottomMenuBean> data;
    private ItemMainNavigationBinding binding;
    public HomeNavigationGvAdapter(Context context, List<HomeBottomMenuBean> navigationData) {
        mContext = context;
        data = navigationData;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public HomeBottomMenuBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil
                    .inflate(LayoutInflater.from(mContext),
                            R.layout.item_main_navigation,
                            parent,
                            false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemMainNavigationBinding) convertView.getTag();
        }
        binding.setVariable(com.example.base.baseapp.BR.HomeMenuBean, data.get(position));
        binding.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        binding.executePendingBindings();
        return convertView;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;


    //grid原生的item点击事件无效 暂不知原因 ，故暂时自定义内部接口回调
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

}
