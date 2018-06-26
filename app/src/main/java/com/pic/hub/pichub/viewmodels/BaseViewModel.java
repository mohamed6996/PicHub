package com.pic.hub.pichub.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.pic.hub.pichub.PhotoLocalCache;
import com.pic.hub.pichub.Repository;
import com.pic.hub.pichub.api.ApiClient;
import com.pic.hub.pichub.api.ApiInterface;
import com.pic.hub.pichub.api.ApiSearchResult;
import com.pic.hub.pichub.model.Photo;
import com.pic.hub.pichub.database.AppDatabase;
import com.pic.hub.pichub.database.PhotoDao;

import java.util.concurrent.Executors;

public abstract class BaseViewModel extends AndroidViewModel {

    // todo use switch map when implement search feature

    private Repository repository;
    private PhotoLocalCache cache;
    private ApiInterface apiInterface;
    private ApiSearchResult apiSearchResult;
    private LiveData<PagedList<Photo>> photos;
    private LiveData<String> networkState;


    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<PagedList<Photo>> getPhotos(String order) {
        if (repository == null) {
            init();
            apiSearchResult = repository.getLivePhotos(order);
            photos = apiSearchResult.getPhotos();
            networkState = apiSearchResult.getNetworkError();

        }
        return photos;
    }

    private void init() {
        AppDatabase database = AppDatabase.getInstance(getApplication());
        PhotoDao photoDao = database.photoDao();
        cache = new PhotoLocalCache(photoDao, Executors.newSingleThreadExecutor());

        apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        repository = new Repository(cache, apiInterface);
    }


}
