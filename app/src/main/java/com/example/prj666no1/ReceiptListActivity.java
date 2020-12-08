package com.example.prj666no1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReceiptListActivity extends AppCompatActivity {
    static final String BASE_URL = "https://obscure-gorge-80600.herokuapp.com/api/";
    static Retrofit retrofit = null;
    private RecyclerView recyclerView;
    List<Receipt> allReceipt;

    String receiptDateString;
    String userString;
    String receiptDetailString;
    String paymentByCreditString;
    String subscriptionEndDateString;
    String userData;
    JsonManager jsonManager = new JsonManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_list);
        Intent intent = getIntent();
        userData= intent.getStringExtra("home_receipt");
        Log.v("in home_receipt: ", ""+intent.getStringExtra("home_receipt"));

        connect(userData);
    }

    private void connect(String userData){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        userData = jsonManager.getUsername(userData);
        //this is for the GET function
        ReceiptApiService receiptApiService = retrofit.create(ReceiptApiService.class);
        //this is for the POST function

        Call<List<Receipt>> callReceipts = receiptApiService.getReceipt(userData);

        callReceipts.enqueue(new Callback<List<Receipt>>() {
            @Override
            public void onResponse(Call<List<Receipt>> call, Response<List<Receipt>> response) {
                allReceipt = response.body();
                Log.v("onResponse",allReceipt.size()+"");
                recyclerView = findViewById(R.id.rvReceiptList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                ReceiptListAdapter receiptListAdapter = new ReceiptListAdapter(allReceipt,getApplicationContext());
                recyclerView.setAdapter(receiptListAdapter);
            }
            @Override
            public void onFailure(Call<List<Receipt>> call, Throwable throwable) {
                Log.v("onFailure","ReceiptListActivity");
            }
        });



    }


}
