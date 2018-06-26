package com.pic.hub.pichub;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import com.pic.hub.pichub.api.ApiInterface;
import com.pic.hub.pichub.api.ApiSearchResult;
import com.pic.hub.pichub.model.Photo;

public class Repository {

    private PhotoLocalCache cache;
    private ApiInterface apiInterface;

    public Repository(PhotoLocalCache cache, ApiInterface apiInterface) {
        this.cache = cache;
        this.apiInterface = apiInterface;
    }

    public ApiSearchResult getLivePhotos(String order) {
        DataSource.Factory<Integer, Photo> dataSourceFactory = cache.getSavedPhotos(order);
        PhotoBoundaryCallback boundaryCallback = new PhotoBoundaryCallback(cache, apiInterface,order);
        LiveData<String> networkError = boundaryCallback.getNetworkError();

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(10)
                .build();

        LiveData<PagedList<Photo>> photos = new LivePagedListBuilder(dataSourceFactory, config)
                .setBoundaryCallback(boundaryCallback)
                .build();


        return new ApiSearchResult(photos, networkError);

    }
}
