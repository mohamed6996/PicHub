package com.pic.hub.pichub.fragments;


import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pic.hub.pichub.viewmodels.BaseViewModel;
import com.pic.hub.pichub.IViewModelListener;
import com.pic.hub.pichub.R;
import com.pic.hub.pichub.adapter.RecyclerViewAdapter;
import com.pic.hub.pichub.model.Photo;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements IViewModelListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_base);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new RecyclerViewAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void getPhotos(String order, boolean isCategory,BaseViewModel viewModel) {
        viewModel.getPhotos(order,isCategory).observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> photos) {
                adapter.submitList(photos);
            }
        });
    }

    @Override
    public void getSearch(String searchQuery, boolean isCategory,BaseViewModel viewModel) {
        viewModel.getPhotos(searchQuery,isCategory).observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> photos) {
                adapter.submitList(photos);
            }
        });
    }


}
