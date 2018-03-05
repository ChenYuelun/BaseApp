package com.example.base.baseapp.view.fragment;

import android.view.View;
import android.widget.Toast;

import com.example.base.baseapp.BR;
import com.example.base.baseapp.customerViews.ActivityDialog;
import com.example.base.baseapp.R;
import com.example.base.baseapp.base.BaseFragment;
import com.example.base.baseapp.databinding.MoudleFramentHomeBinding;
import com.example.base.baseapp.entity.ActivityBean;
import com.example.base.baseapp.viewModel.HomeFragmentViewModel;

/**
 * Created by chenyuelun on 2018/3/2.
 */

public class HomeFragment extends BaseFragment<MoudleFramentHomeBinding, HomeFragmentViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.moudle_frament_home;
    }

    @Override
    public void initView() {
        mViewModel.fetch();
        mDataBinding.btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();

            }
        });
    }

    @Override
    public int getBR() {
        return BR.OneViewModel;
    }


    @Override
    protected HomeFragmentViewModel createViewModel() {
        return new HomeFragmentViewModel();
    }

    public void showDialog(){
        Toast.makeText(getContext(), "弹个窗", Toast.LENGTH_SHORT).show();
        ActivityDialog.create(getActivity(), new ActivityBean(), new ActivityDialog.OnDialogClickListener() {
            @Override
            public void onPressImage() {
                Toast.makeText(getContext(), "点击了图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {
                Toast.makeText(getContext(), "点击了关闭按钮", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
