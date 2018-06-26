package com.pic.hub.pichub;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.pic.hub.pichub.api.ApiInterface;
import com.pic.hub.pichub.model.Photo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoBoundaryCallback extends PagedList.BoundaryCallback<Photo> {
    private PhotoLocalCache cache;
    private ApiInterface apiInterface;
    private String order;

    public PhotoBoundaryCallback(PhotoLocalCache cache, ApiInterface apiInterface, String order) {
        this.cache = cache;
        this.apiInterface = apiInterface;
        this.order = order;
    }

    private int lastRequestPage = 1;
    private static final int NETWORK_PAGE_SIZE = 20;
    private boolean isRequestInProgress = false;

    private MutableLiveData<String> networkError = new MutableLiveData<>();

    public MutableLiveData<String> getNetworkError() {
        return networkError;
    }

    @Override
    public void onZeroItemsLoaded() {
        requestAndSaveData();
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Photo itemAtEnd) {
        requestAndSaveData();
    }

    private void requestAndSaveData() {
        if (isRequestInProgress) return;
        isRequestInProgress = true;

//        apiInterface.getPhotos(lastRequestPage, NETWORK_PAGE_SIZE).enqueue(new Callback<PhotoList>() {
//            @Override
//            public void onResponse(Call<PhotoList> call, Response<PhotoList> response) {
//                if (response.isSuccessful()) {
//                    cache.insertPhotos(response.body().getHits()); //todo only save 20 - 40 items
//                    lastRequestPage++;
//                    isRequestInProgress = false;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PhotoList> call, Throwable t) {
//                networkError.postValue(t.getMessage());
//                isRequestInProgress = false;
//            }
//        });


        apiInterface.getPopularPhotos(lastRequestPage, NETWORK_PAGE_SIZE, order).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {

                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray hits = jsonObject.getJSONArray("hits");
                        List<Photo> photos = new ArrayList<>();
                        for (int i = 0; i < hits.length(); i++) {
                            JSONObject photoJSONObject = hits.getJSONObject(i);

                            photoJSONObject.put("order", order); // add new property

                            String json = photoJSONObject.toString();

                            Gson gson = new Gson();

                            Photo photo = gson.fromJson(json, Photo.class);

                            photos.add(photo);


                        }

                        cache.insertPhotos(photos);
                        lastRequestPage++;
                        isRequestInProgress = false;

                        Log.i("response_body", "size of hits " + photos.size());


                    } catch (Exception e) {
                        Log.i("response_body", e.getMessage());

                    }

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                networkError.postValue(t.getMessage());
                isRequestInProgress = false;
            }
        });

    }


}
