package com.example.prj666no1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import static android.text.TextUtils.indexOf;
import static android.text.TextUtils.substring;

public class JsonManager {
    public String getEmailAddress(String obj){
        String temp ="";
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(obj);
            temp = jsonObject.getString("email");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }

    public String getUsername(String obj){
        Log.d("here is the username function1",obj);
        String temp ="";
        JSONObject jsonObject = null;
        Log.d("here is the username function2",obj);
        try{
            jsonObject = new JSONObject(obj);

            temp = jsonObject.getString("username");

        }catch (JSONException e){
            e.printStackTrace();
        }
        Log.d("here is the username function3",obj);

        return temp;
    }

    public String getSystemID(String obj){
        String temp ="";
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(obj);
            temp = jsonObject.getString("_id");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }

    public int getPassword(String obj){
        int temp =0;
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(obj);
            temp = jsonObject.getInt("password");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }

    public String getAddress(String obj){
        String temp ="";
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(obj);
            temp = jsonObject.getString("address");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }

    public int getAccountType(String obj){
        int temp =0;
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(obj);
            temp = jsonObject.getInt("accountType");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }

    public String getLanguage(String obj){
        String temp ="";
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(obj);
            temp = jsonObject.getString("language");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }

    public String getMembership(String obj){
        String temp = "";
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(obj);
            temp = jsonObject.getString("Membership");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }

    public ArrayList<ArrayList<String>> getMealPlan(String obj){
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        ArrayList<ArrayList<String>> temp =null;
        try{
            jsonObject = new JSONObject(obj);
            jsonArray = jsonObject.getJSONArray("mealPlan");


            temp = new ArrayList<ArrayList<String>>(jsonArray.length());
            for(int i =0; i<jsonArray.length();i++){
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                ArrayList<String> daysMeal = new ArrayList<String>();
                daysMeal.add(jsonObject2.getString("day"));
                daysMeal.add(jsonObject2.getString("breakFast"));
                daysMeal.add(jsonObject2.getString("lunch"));
                daysMeal.add(jsonObject2.getString("dinner"));
                temp.add(daysMeal);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }

    public ArrayList<String> getPaymentMethod(String obj){
        JSONObject jsonObject = null;
        ArrayList<String> temp =new ArrayList<>();
        try{
            jsonObject = new JSONObject(obj);
            JSONObject jsonObject1 = jsonObject.getJSONObject("paymentMethod");
            temp.add(jsonObject1.getString("username"));
            temp.add(jsonObject1.getString("cardNumber"));
            temp.add(jsonObject1.getString("securityCode"));
            temp.add(jsonObject1.getString("expiredDate"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        return temp;
    }


    public JSONObject getCertainMenu(String obj){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(obj);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }









}
