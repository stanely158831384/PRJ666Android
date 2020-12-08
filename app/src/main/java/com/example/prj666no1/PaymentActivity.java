package com.example.prj666no1;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class PaymentActivity extends AppCompatActivity implements NetworkingClass.APIDataListner{
    EditText userName;
    EditText cardNumber;
    EditText expiredDate;
    EditText cvv;
    Button save;
    String data;
    NetworkingClass networkingClass;
    JsonManager jsonManager = new JsonManager();
    ArrayList<String> jsonManagerOutput;
    String userData;
    String userID;

    @Override
    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.payment_page);

        networkingClass = new NetworkingClass(this,getApplicationContext());

        userName = findViewById(R.id.payment_cardholder);
        cardNumber = findViewById(R.id.payment_cardnumber);
        expiredDate = findViewById(R.id.payment_expired);
        cvv = findViewById(R.id.payment_code);
        save = findViewById(R.id.payment_button);

        Intent intent = getIntent();
        data = intent.getStringExtra("home_payment");

        jsonManagerOutput = jsonManager.getPaymentMethod(data);

        userName.setText(jsonManagerOutput.get(0).toString());
        cardNumber.setText(jsonManagerOutput.get(1).toString());
        expiredDate.setText(jsonManagerOutput.get(3).toString());
        cvv.setText(jsonManagerOutput.get(2).toString());

        userID = jsonManager.getSystemID(data);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userData ="{" +
                        "  \"paymentMethod\": {" +
                        "    \"username\": \""+userName.getText().toString()+"\"," +
                        "    \"cardNumber\": \""+cardNumber.getText().toString()+"\"," +
                        "    \"securityCode\":\""+cvv.getText().toString()+"\" ," +
                        "    \"expiredDate\": \""+expiredDate.getText().toString()+"\"" +
                        "}"+
                        "  }";
                Log.d("test :",userData);
                networkingClass.updateComponent(userData,userID);
            }
        });



    }

    @Override
    public void returnAPIData(String data) {
        if(data.equals("register successfully")){
            finish();
        }else{
            save.setText("submit failed");
        }
    }
}
