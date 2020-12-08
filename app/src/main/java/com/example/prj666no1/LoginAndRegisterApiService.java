package com.example.prj666no1;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginAndRegisterApiService {
    @POST("newposts")
    Call<String> registerAccount(@Body RequestBody body);

    @GET("login")
    Call<String> getSpecificMenu(@Query("email") String newsId, @Query("password") String type);
}
