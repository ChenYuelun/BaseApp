package com.example.base.baseapp.bottomMenu;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.AttributeSet;
import android.widget.GridView;

import com.example.base.baseapp.entity.HomeBottomMenuBean;

import java.util.List;

/**
 * Created by chenyuelun on 2018/3/3.
 * 主界面底部导航控件
 */

public class HomeBottomMenuGridView extends GridView {
    private int selectePostion = -1;//当前选中的position
    private OnItemSelectedListener onItemSelectedListener;
    private List<HomeBottomMenuBean> data;//数据
    private HomeNavigationGvAdapter adapter;
    private OnNavigationReadyListener onNavigationReadyListener;

    public HomeBottomMenuGridView(Context context) {
        this(context,null);
    }

    public HomeBottomMenuGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //设置数据
    public void setNavigationData(final List<HomeBottomMenuBean>  data){
        if (data != null && data.size() > 0){
            this.data = data;
            setNumColumns(data.size());
            adapter = new HomeNavigationGvAdapter(getContext(),data);
            setAdapter(adapter);
            adapter.setOnItemClickListener(new HomeNavigationGvAdapter.OnItemClickListener() {

                @Override
                public void onItemClick( int position ) {
                    if (position == selectePostion){
                        return;
                    }
                    if (selectePostion >= 0){
                        data.get(selectePostion).setSelect(false);
                    }
                    data.get(position).setSelect(true);
                    selectePostion = position;
                    if (onItemSelectedListener != null){
                        onItemSelectedListener.onItemSelected(data.get(position).getPositionTag());
                    }
                    adapter.notifyDataSetChanged();
                }
            });

            //导航栏数据已设置 回调告诉上层
            if (onNavigationReadyListener != null){
                onNavigationReadyListener.onNavigationReady();
            }

        }
    }

    /**
     * 根据position设置选中的item
     */
    public void setSeletedByPosition(int position){
        //如果与当前选中的position相同>return
        if (position == selectePostion){
            return;
        }
        //如果数据为null或者适配器为null>return
        if (data == null || adapter == null){
            return;
        }
        //更改选中项
        if (selectePostion>= 0){
            data.get(selectePostion).setSelect(false);
        }
        data.get(position).setSelect(true);
        selectePostion = position;
        //监听回调当前tag
        if (onItemSelectedListener != null){
            onItemSelectedListener.onItemSelected(data.get(position).getPositionTag());
        }
        //刷新适配器
        adapter.notifyDataSetChanged();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setOnNavigationReadyListener(OnNavigationReadyListener onNavigationReadyListener) {
        this.onNavigationReadyListener = onNavigationReadyListener;
    }

    /**
     *  内部接口 1
     *  选中某item的监听  用于回调更改fragment显示
     */
    public interface OnItemSelectedListener{
        void onItemSelected(int postionTag);
    }

    /**
     *  内部接口2
     *  当数据准备设置完毕后的监听
     *  因为databing设置数据不是在数据更新后马上设置，所以用回调设置
     *  用于默认选项等设置
     */
    public interface OnNavigationReadyListener {
        void onNavigationReady();
    }

    /**
     * @ 用于databing直接绑定数据
     * @param gridView 本控件
     * @param datas
     */
    @BindingAdapter("android:navigation_data")
    public static void setNavigationData(HomeBottomMenuGridView gridView, List<HomeBottomMenuBean> datas){
        if (gridView != null && datas != null){
            gridView.setNavigationData(datas);
        }
    }

}
