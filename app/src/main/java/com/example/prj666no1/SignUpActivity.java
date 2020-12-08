package com.example.prj666no1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//public class SignUpActivity extends AppCompatActivity implements NetworkingClass.APIDataListner
public class SignUpActivity extends AppCompatActivity implements NetworkingClass.APIDataListner{
    TextView username;
    TextView email;
    TextView password;
    TextView location;
    TextView errorMessage;
    Spinner language;
    Button submit;
    List<String> dataList;
    ArrayAdapter<String> adapter;
    String languageChosen;
    JsonManager jsonManager;
    NetworkingClass networkingClass;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        username = findViewById(R.id.signUp_uesrname);
        email = findViewById(R.id.signUp_email);
        password = findViewById(R.id.signUp_password);
        location = findViewById(R.id.signUp_address);
        language = findViewById(R.id.signUp_language);
        errorMessage =findViewById(R.id.signUp_errorMessage);
        submit =findViewById(R.id.button);
        networkingClass = new NetworkingClass(this,getApplicationContext());

        dataList = new ArrayList<String>();
        dataList.add("chinese");
        dataList.add("indian");
        dataList.add("korean");
        dataList.add("vietnamese");



        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        language.setAdapter(adapter);

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                languageChosen = dataList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                String url ="{\n" +
//                        "  \"paymentMethod\": {\n" +
//                        "    \"username\": \"\",\n" +
//                        "    \"cardNumber\": \"\",\n" +
//                        "    \"securityCode\":\"\" ,\n" +
//                        "    \"expiredDate\": \"\"\n" +
//                        "  },\n" +
//                        "  \"email\": \""+email.getText().toString()+"\",\n" +
//                        "  \"username\": \""+username.getText().toString()+"\",\n" +
//                        "  \"accountID\": 1,\n" +
//                        "  \"password\": \""+password.getText().toString()+"\",\n" +
//                        "  \"address\": \""+location.getText().toString()+"\",\n" +
//                        "  \"accountType\": 2,\n" +
//                        "  \"mealPlan\": [\n" +
//                        "    {\n" +
//                        "      \"_id\": \"5f504769dab5da70f25dd680\",\n" +
//                        "      \"day\": \"monday\",\n" +
//                        "      \"breakFast\": \"nonoe\",\n" +
//                        "      \"lunch\": \"none\",\n" +
//                        "      \"dinner\": \"none\"\n" +
//                        "    }\n" +
//                        "  ],\n" +
//                        "  \"language\": \""+languageChosen+"\",\n" +
//                        "  \"Membership\": \""+new Date() +"\"\n" +
//                        "}";

                String data ="{" +
                        "  \"paymentMethod\": {" +
                        "    \"username\": \"\"," +
                        "    \"cardNumber\": \"\"," +
                        "    \"securityCode\":\"\" ," +
                        "    \"expiredDate\": \"\"" +
                        "  }," +
                        "  \"email\": \""+email.getText().toString()+"\"," +
                        "  \"username\": \""+username.getText().toString()+"\"," +
                        "  \"accountID\": 1," +
                        "  \"password\": \""+password.getText().toString()+"\"," +
                        "  \"address\": \""+location.getText().toString()+"\"," +
                        "  \"accountType\": 2," +
                        "  \"mealPlan\": " +"0"+","+
                        "  \"language\": \""+languageChosen+"\"," +
                        "  \"Membership\": \""+new Date() +"\"" +
                        "}";


                Log.d("url example=",data);

                networkingClass.registerComponent(data);

            }
        });
    }

    @Override
    public void returnAPIData(String data) {
        if(data.equals("register successfully")){
            finish();
        }else{
            errorMessage.setText("Failed");
        }
    }
}
