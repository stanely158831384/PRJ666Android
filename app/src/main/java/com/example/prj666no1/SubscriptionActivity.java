package com.example.prj666no1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubscriptionActivity extends AppCompatActivity {
    String userData;
    Retrofit retrofit;
    JsonManager jsonManager = new JsonManager();
    static final String BASE_URL = "https://obscure-gorge-80600.herokuapp.com/api/";
    TextView receiptDate;
    TextView user;
    TextView receiptDetail;
    TextView endDate;
    TextView deccriptionTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        Intent intent = getIntent();
        userData= intent.getStringExtra("home_subscription");
        //Log.v("in home_receipt: ", ""+intent.getStringExtra("home_receipt"));
        receiptDate = findViewById(R.id.receiptDate);
        user = findViewById(R.id.user);
        receiptDetail = findViewById(R.id.receiptDetail);
        endDate = findViewById(R.id.subscriptionEndDate);
        deccriptionTopic = findViewById(R.id.descriptionTopic);

        connect(userData);
    }

    private void connect(String userData) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        //this is for the GET function
        ReceiptApiService receiptApiService = retrofit.create(ReceiptApiService.class);
        //this is for the POST function
        Log.v("test userData: ",userData);
        String input = jsonManager.getUsername(userData);



        Call<List<Receipt>> callAllMenu = receiptApiService.getReceipt(input);

        callAllMenu.enqueue(new Callback<List<Receipt>>() {

            @Override
            public void onResponse(Call<List<Receipt>> call, Response<List<Receipt>> response) {
                List<Receipt> dataSet = response.body();
                if(dataSet!=null&&dataSet.size()>0){
                    Receipt latestReceipt = dataSet.get(dataSet.size()-1);

                    if(latestReceipt.getEndDate().before(new Date())){
                        deccriptionTopic.setText("Subscription Expired");
                    }

                    receiptDate.setText(latestReceipt.getDate().toString());
                    user.setText(latestReceipt.getUser().toString());
                    receiptDetail.setText(latestReceipt.getReceiptDetail().toString());
                    endDate.setText(latestReceipt.getEndDate().toString());
                }else{
                    deccriptionTopic.setText("No any subscription");
                }



            }

            @Override
            public void onFailure(Call<List<Receipt>> call, Throwable t) {

            }
        });
    }

}
