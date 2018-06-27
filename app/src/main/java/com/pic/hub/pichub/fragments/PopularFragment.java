package com.pic.hub.pichub.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.pic.hub.pichub.IViewModelListener;
import com.pic.hub.pichub.viewmodels.PopularViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends BaseFragment {

    public PopularFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PopularViewModel viewModel = ViewModelProviders.of(this).get(PopularViewModel.class);

        IViewModelListener viewModelListener = (IViewModelListener) this;
        viewModelListener.getPhotos("popular",false, viewModel);

    }

}
