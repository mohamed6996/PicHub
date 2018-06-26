package com.pic.hub.pichub.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.pic.hub.pichub.viewmodels.EditorsChoiceViewModel;
import com.pic.hub.pichub.IViewModelListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditorChoiceFragment extends BaseFragment  {

    public EditorChoiceFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditorsChoiceViewModel viewModel = ViewModelProviders.of(this).get(EditorsChoiceViewModel.class);
        IViewModelListener viewModelListener = (IViewModelListener) this;
        viewModelListener.getPhotos("editors_choice", viewModel);
    }


}
