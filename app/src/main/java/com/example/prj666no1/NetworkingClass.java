package com.example.prj666no1;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.renderscript.ScriptGroup;
import android.util.Log;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingClass {
    APIDataListner activity;
    Context mainActivityContext;

    public interface APIDataListner{
        public void returnAPIData(String data);
        //public void helpToSetData(String data);
    }

    NetworkingClass(APIDataListner apiDataListner,Context context){
        this.activity = apiDataListner;
        mainActivityContext = context;
    }


    void loginComponent(final String email, final String password){
        final String url ="https://obscure-gorge-80600.herokuapp.com/api/login?email="+email+"&password="+password;
        connectAnAPI_GET(url);
    }

    void registerComponent(final String data){
        final String url ="https://obscure-gorge-80600.herokuapp.com/api/newposts";
        connectAnAPI_POST_PUT(url,data,"POST");
    }

    void updateComponent(final String data,final String id){
        final String url ="https://obscure-gorge-80600.herokuapp.com/api/posts/"+id;
        connectAnAPI_POST_PUT(url, data,"PUT");
    }

    void findCustomerByID(final String id){
        final String url ="https://obscure-gorge-80600.herokuapp.com/api/posts/"+id;
        connectAnAPI_GET(url);
    }

    void findMenuByMenuCode(final int code){
        final String url ="https://obscure-gorge-80600.herokuapp.com/api/findMenuByMenuCode/"+code;
        connectAnAPI_GET(url);
    }

    void findAllMenu(){
        final String url ="https://obscure-gorge-80600.herokuapp.com/api/allMenus";
        connectAnAPI_GET(url);
    }

    void connectAnAPI_GET(final String url){
        try{
            Thread thread = new Thread(){
                public void run(){
                    Looper.prepare();
                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            String data = "";
                            HttpURLConnection httpURLConnection = null;
                            try{
                                httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                                httpURLConnection.setRequestMethod("GET");
                                int status = httpURLConnection.getResponseCode();
                                Log.d("GET RX", " status=> " + status);

                                try{

                                    InputStream in = httpURLConnection.getInputStream();
                                    InputStreamReader inputStreamReader = new InputStreamReader(in);
                                    int inputStreamData = inputStreamReader.read();

                                    while (inputStreamData!=-1){
                                        char current = (char)inputStreamData;
                                        inputStreamData = inputStreamReader.read();
                                        data += current;
                                    }

                                    final String finalData = data;

                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {activity.returnAPIData(finalData);}
                                    });
                                }catch (Exception exx){
                                    Log.d("error", exx.toString());
                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                if(httpURLConnection != null){
                                    httpURLConnection.disconnect();
                                }
                            }
                            handler.removeCallbacks(this);
                            Looper.myLooper().quit();
                        }
                    },1000);
                    Looper.loop();
                }
            };
            thread.start();
        }catch (Exception ex){
            Log.e("ERROR =>", "" + ex.getMessage());
            ex.printStackTrace();
        }
    }




    void connectAnAPI_POST_PUT(final String url, final String data1, final String request){
        try{
            Thread thread = new Thread(){
                public void run(){
                    Looper.prepare();
                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            String data = data1;
                            HttpURLConnection httpURLConnection = null;

                            try{
                                byte[] input = data.getBytes("utf-8");
                                URL urlInit = new URL(url);
                                httpURLConnection = (HttpURLConnection) urlInit.openConnection();
                                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                                httpURLConnection.setRequestProperty("Accept", "application/json");
                                httpURLConnection.setConnectTimeout(5000);
                                httpURLConnection.setRequestMethod(request);
                                httpURLConnection.setDoOutput(true);
                                httpURLConnection.setDoInput(true);
                                httpURLConnection.setUseCaches(false);
                                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length()));
                                httpURLConnection.connect();
                                //int status = httpURLConnection.getResponseCode();
                                //Log.d("POST RX", " status=> " + status);
                                try(OutputStream os = httpURLConnection.getOutputStream()) {
                                    Log.d("the string is :",input.toString()+" "+input.length);
                                    os.write(input);
                                    os.flush();
                                    os.close();


                                    int status = httpURLConnection.getResponseCode();
                                    Log.d("POST RX", " status=> " + status);
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {activity.returnAPIData("register successfully");}
                                    });
                                }catch (Exception e){
                                    e.printStackTrace();
                                    Log.d("error", e.toString());
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {activity.returnAPIData("register failed");}
                                    });

                                }


                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                if(httpURLConnection != null){
                                    httpURLConnection.disconnect();
                                }
                            }
                            handler.removeCallbacks(this);
                            Looper.myLooper().quit();
                        }
                    },1000);
                    Looper.loop();
                }
            };
            thread.start();
        }catch (Exception ex){
            Log.e("ERROR =>", "" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
