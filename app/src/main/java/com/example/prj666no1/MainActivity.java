package com.example.prj666no1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NetworkingClass.APIDataListner {
    JsonManager jsonManager = new JsonManager();
    NetworkingClass networkingClass;
    Button login;
    TextView register;
    TextView errorMessage;
    EditText email;
    EditText password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        errorMessage = findViewById(R.id.errorMessage);
        login = findViewById(R.id.loginButton);
        networkingClass = new NetworkingClass(this,getApplicationContext());
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        register = findViewById(R.id.signUp);


        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                networkingClass.loginComponent(email.getText().toString(),password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void returnAPIData(String data) {
        Log.d("data","0"+data);
        //https://obscure-gorge-80600.herokuapp.com
        //"login failTypeError: Cannot read property 'password' of null"
        if(!Objects.equals(data,"\"login fail, wrong password\"")&&!Objects.equals(data,"\"login failTypeError: Cannot read property 'password' of null\"")){
            errorMessage.setText("");
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            Log.v("login return data is :", data);
            intent.putExtra("userData",data);
            startActivity(intent);

        }else{
            errorMessage.setText("wrong password");
        }
    }
}