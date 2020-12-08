package com.example.prj666no1;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReceiptApiService {
    @POST("newReceipt")
    Call<String> psotReceipt(@Body RequestBody body);

    @GET("getReceiptByUserName/{userName}")
    Call<List<Receipt>> getReceipt(@Path("userName") String userName);


}
