package com.example.prj666no1;


import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MenuApiService {
//    @GET("movie/{id}")
//    Call<Movie> getMovie(@Path("id") int id, @Query("api_key") String apiKey);
//
//    @GET("movie/top_rated")
//    Call<TopRatedResponse> getTop10Movie(@Query("api_key") String apiKey);

    @GET("findMenuByMenuCode/")
    Call<Menu> getSpecificMenu(@Query("menu_number") String menuNum);

    @GET("allMenus")
    Call<List<Menu>> getAllMenu();



}
