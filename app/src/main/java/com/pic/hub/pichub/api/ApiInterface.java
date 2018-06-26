package com.pic.hub.pichub.api;

import com.pic.hub.pichub.Constants;
import com.pic.hub.pichub.model.PhotoList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

  //  @GET("?key=3759940-2ad2e64eaca323a5916a18590")
  //  public Call<PhotoList> getPhotos(@Query("page") int pageNumber, @Query("per_page") int pageSize);


    @GET(Constants.API_KEY) // order: accepted values: popular(default) or latest
    public Call<ResponseBody> getPopularPhotos(@Query("page") int pageNumber, @Query("per_page") int pageSize, @Query("order") String order);

}
