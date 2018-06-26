package com.pic.hub.pichub;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.pic.hub.pichub.api.ApiInterface;
import com.pic.hub.pichub.model.Photo;
import com.pic.hub.pichub.model.PhotoList;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class PageKeyedPhotoDataSource extends PageKeyedDataSource<Integer, Photo> {
    ApiInterface apiInterface;

    public PageKeyedPhotoDataSource(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Photo> callback) {
        Call<PhotoList> photoList = apiInterface.getPhotos(1, params.requestedLoadSize);
        try {
            Response<PhotoList> response = photoList.execute();
            if (response.isSuccessful()) {
                callback.onResult(response.body().getHits(), 1, 2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {
        Call<PhotoList> photoList = apiInterface.getPhotos(params.key, params.requestedLoadSize);
        try {
            Response<PhotoList> response = photoList.execute();
            if (response.isSuccessful()) {
                int adjacentKey = 0;
                if (params.key > 1)
                    adjacentKey = params.key - 1;

                callback.onResult(response.body().getHits(), adjacentKey);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {
        Call<PhotoList> photoList = apiInterface.getPhotos(params.key, params.requestedLoadSize);
        try {
            Response<PhotoList> response = photoList.execute();
            if (response.isSuccessful()) {
                callback.onResult(response.body().getHits(), params.key + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
