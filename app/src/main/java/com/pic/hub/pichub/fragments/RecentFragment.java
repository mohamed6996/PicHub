package com.pic.hub.pichub.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.pic.hub.pichub.IViewModelListener;
import com.pic.hub.pichub.viewmodels.RecentViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends BaseFragment {

    public RecentFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecentViewModel viewModel = ViewModelProviders.of(this).get(RecentViewModel.class);
        IViewModelListener viewModelListener = (IViewModelListener) this;
        viewModelListener.getPhotos("recent", viewModel);
    }

}
