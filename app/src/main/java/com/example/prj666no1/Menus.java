package com.example.prj666no1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Menus extends AppCompatActivity implements NetworkingClass.APIDataListner {
    JsonManager jsonManager = new JsonManager();
    JSONObject jsonObject = null;
    NetworkingClass networkingClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkingClass = new NetworkingClass(this,getApplicationContext());
        networkingClass.findAllMenu();
        //now the jsonObject is the set of the menu;



    }
    @Override
    public void returnAPIData(String data) {
        Log.d("here is the menu data returned from the networking class",data);
        try{
            jsonObject = new JSONObject(data);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
