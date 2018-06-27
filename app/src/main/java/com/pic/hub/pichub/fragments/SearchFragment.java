package com.pic.hub.pichub.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.pic.hub.pichub.IViewModelListener;
import com.pic.hub.pichub.viewmodels.SearchViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String query = getActivity().getIntent().getStringExtra("search_query");
        boolean isCategory = getActivity().getIntent().getBooleanExtra("is_category",false);

        SearchViewModel viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        IViewModelListener viewModelListener = (IViewModelListener) this;
        viewModelListener.getSearch(query, isCategory, viewModel);
    }

}
