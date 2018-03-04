package com.example.base.baseapp.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;

/**
 * Created by chenyuelun on 2018/3/1.
 * 底部导航菜单的itemBean
 */

public class HomeBottomMenuBean extends BaseObservable{
    private String text;//文本显示
    private String normalColor;//未选中时文字的颜色
    private String selectColor;//选中时文字的颜色
    private Bitmap normalBitmap;//未选中时显示的图片
    private Bitmap selectBitmap;//选中时显示的图片
    private boolean isSelect;//本条目是否被选中

    public HomeBottomMenuBean(String text, String normalColor, String selectColor, Bitmap normalBitmap, Bitmap selectBitmap, int positionTag) {
        this.text = text;
        this.normalColor = normalColor;
        this.selectColor = selectColor;
        this.normalBitmap = normalBitmap;
        this.selectBitmap = selectBitmap;
        this.positionTag = positionTag;
    }
    public HomeBottomMenuBean(){

    }
    private int positionTag;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(String normalColor) {
        this.normalColor = normalColor;
    }

    public String getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(String selectColor) {
        this.selectColor = selectColor;
    }

    public Bitmap getNormalBitmap() {
        return normalBitmap;
    }

    public void setNormalBitmap(Bitmap normalBitmap) {
        this.normalBitmap = normalBitmap;
    }

    public Bitmap getSelectBitmap() {
        return selectBitmap;
    }

    public void setSelectBitmap(Bitmap selectBitmap) {
        this.selectBitmap = selectBitmap;
    }

    public int getPositionTag() {
        return positionTag;
    }

    public void setPositionTag(int positionTag) {

        this.positionTag = positionTag;
    }

    @Bindable
    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
