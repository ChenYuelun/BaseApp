package com.example.base.baseapp.viewModel;

import com.example.base.baseapp.base.BaseViewModel;
import com.example.base.baseapp.model.HomeFragmentModel;
import com.example.base.baseapp.view.fragment.HomeFragment;

/**
 * Created by chenyuelun on 2018/3/2.
 */

public class FragmentTwoViewModel extends BaseViewModel<HomeFragment,HomeFragmentModel> {


    @Override
    protected HomeFragmentModel createModel() {
        return new HomeFragmentModel();
    }

    @Override
    public void fetch() {

    }

}
