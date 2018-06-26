package com.pic.hub.pichub;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.pic.hub.pichub.api.ApiClient;
import com.pic.hub.pichub.api.ApiInterface;
import com.pic.hub.pichub.model.Photo;

public class PhotoDataSourceFactory extends DataSource.Factory<Integer, Photo> {
    MutableLiveData<PageKeyedPhotoDataSource> mutableDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Photo> create() {
        PageKeyedPhotoDataSource dataSource = new PageKeyedPhotoDataSource(ApiClient.getRetrofitInstance().create(ApiInterface.class));
        mutableDataSource.postValue(dataSource);

        return dataSource;
    }
}
