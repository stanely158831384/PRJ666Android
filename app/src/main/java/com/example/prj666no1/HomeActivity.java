package com.example.prj666no1;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    TextView username;
    TextView email;
    TextView language;
    TextView location;
    TextView membership;
    TextView home_payment;
    TextView menus;
    TextView notification;
    TextView subscription;
    JsonManager jsonManager = new JsonManager();
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        username=findViewById(R.id.home_username);
        home_payment = findViewById(R.id.home_payment);
        membership = findViewById(R.id.membership);
        email=findViewById(R.id.home_email);
        language=findViewById(R.id.language);
        location=findViewById(R.id.location);
        menus = findViewById(R.id.Menus);
        notification = findViewById(R.id.notification);
        subscription = findViewById(R.id.Subscription);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent= getIntent();
        data = intent.getStringExtra("userData");
        Log.d("here is the data passed from the parent activity: ",data);
        String username1 = jsonManager.getUsername(data);
        username.setText(username1);
        email.setText(jsonManager.getEmailAddress(data));
        language.setText(jsonManager.getLanguage(data));
        location.setText(jsonManager.getAddress(data));

        String membership1 = jsonManager.getMembership(data);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date t = sdf.parse(membership1);
            String dateFinal = sdf.format(t);
            membership.setText(dateFinal);
        }catch (ParseException e){
            Log.e("parse exception: ",e.getMessage());
        }



        home_payment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,PaymentActivity.class);
                intent.putExtra("home_payment",data);
                startActivity(intent);
            }
        });

        menus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this,MenuListActivity.class);
                intent.putExtra("home_menu",data);
                Log.v("homeActivity_pass_data",data);
                startActivity(intent);
            }
        });

        notification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ReceiptListActivity.class);
                intent.putExtra("home_receipt",data);
                Log.v("homeActivity_pass_data",data);
                startActivity(intent);
            }
        });

        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SubscriptionActivity.class);
                intent.putExtra("home_subscription",data);
                Log.v("homeActivity_pass_data",data);
                startActivity(intent);
            }
        });







    }
}
