package com.example.prj666no1;

import android.util.Log;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class newNetworkingClass {
    static final String BASE_URL = "https://obscure-gorge-80600.herokuapp.com/api/";
    static Retrofit retrofit = null;

    public newNetworkingClass() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    void register(String data){
        String output;
        LoginAndRegisterApiService loginAndRegisterApiService = retrofit.create(LoginAndRegisterApiService.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),data);
        Call<String> registerReturn = loginAndRegisterApiService.registerAccount(body);
        registerReturn.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String reply = response.body();
                Log.v("register return value from newNetworking:", reply);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
