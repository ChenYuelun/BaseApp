package com.example.base.baseapp.view.fragment;

import com.example.base.baseapp.BR;
import com.example.base.baseapp.R;
import com.example.base.baseapp.base.BaseFragment;
import com.example.base.baseapp.base.BaseViewModel;
import com.example.base.baseapp.viewModel.FragmentTwoViewModel;

/**
 * Created by chenyuelun on 2018/3/2.
 */

public class MeFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.moudle_fragment_two;
    }

    @Override
    public void initView() {

    }

    @Override
    public int getBR() {
        return BR.TwoViewModel;
    }


    @Override
    protected BaseViewModel createViewModel() {
        return new FragmentTwoViewModel();
    }
}
