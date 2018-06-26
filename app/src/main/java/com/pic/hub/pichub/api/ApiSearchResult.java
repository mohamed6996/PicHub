package com.pic.hub.pichub.api;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.pic.hub.pichub.model.Photo;

public class ApiSearchResult {
    LiveData<PagedList<Photo>> photos;
    LiveData<String> networkError;

    public ApiSearchResult(LiveData<PagedList<Photo>> photos, LiveData<String> networkError) {
        this.photos = photos;
        this.networkError = networkError;
    }

    public LiveData<PagedList<Photo>> getPhotos() {
        return photos;
    }

    public LiveData<String> getNetworkError() {
        return networkError;
    }
}
