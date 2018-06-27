package com.pic.hub.pichub;


import com.pic.hub.pichub.viewmodels.BaseViewModel;

public interface IViewModelListener {
    void getPhotos(String order, boolean isCategory,BaseViewModel viewModel);

    void getSearch(String searchQuery, boolean isCategory,BaseViewModel viewModel);


}
