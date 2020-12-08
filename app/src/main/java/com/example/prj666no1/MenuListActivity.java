package com.example.prj666no1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuListActivity extends AppCompatActivity {
    static final String TAG = MenuListActivity.class.getSimpleName();
    static final String BASE_URL = "https://obscure-gorge-80600.herokuapp.com/api/";
    static Retrofit retrofit = null;
    private RecyclerView recyclerView;
    List<Menu> allMenu;

    String userData = new String();
    String userName = new String();
    String cardNumber = new String();
    JsonManager jsonManager = new JsonManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        Intent intent = getIntent();
        userData= intent.getStringExtra("home_menu");
        Log.v("in menulistactivity: ", ""+intent.getStringExtra("home_menu"));

        connect();
    }

    private void connect(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        //this is for the GET function
        MenuApiService menuApiService = retrofit.create(MenuApiService.class);
        //this is for the POST function




        Call<List<Menu>> callAllMenu = menuApiService.getAllMenu();

        callAllMenu.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                allMenu = response.body();
                recyclerView = findViewById(R.id.rvMenuList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                MenuListAdapter menuListAdapter = new MenuListAdapter(allMenu,getApplicationContext());
                recyclerView.setAdapter(menuListAdapter);

                //这一段是定义里面的onitemCLickListener.onclick的。
                //setonintemclicklistner是listner的setter,然后接下来的的部分就是建造一个listner去取代原有的listener，注意这个listner是onitemclicklsitner
                //type的，这个onitemclicklistner里面的东西就是onclick。
                menuListAdapter.setOnItemClickListener(new MenuListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        //String text = "this is the text no " + position;
//                        MyDialog newDialog = MyDialog.newInstant(position);
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        newDialog.show(transaction,"fragment");
                        showNormalDialog(retrofit,allMenu,position);

                    }
                });





            }
            @Override
            public void onFailure(Call<List<Menu>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });



    }

    private void showNormalDialog(Retrofit retrofit, List<Menu> allMenu,int position){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        ReceiptApiService receiptApiService = retrofit.create(ReceiptApiService.class);
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MenuListActivity.this);
        normalDialog.setIcon(R.drawable.icon_test);
        normalDialog.setTitle("Order Comformation");
        normalDialog.setMessage("Are you sure want to order this?");
        normalDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userName = jsonManager.getUsername(userData);
                        cardNumber = jsonManager.getPaymentMethod(userData).get(1);
                        if(cardNumber.length()>4){
                            cardNumber = cardNumber.substring(cardNumber.length()-4);
                        }
                        Log.v("number of the whcih", String.valueOf(position));
                        Log.v("number of the whcih2", allMenu.get(0).getMenu_id());

                        Date newDate = new Date();
                        newDate.setDate(newDate.getDate()+7);
                        String uploadData = "{\n"+
                        "\"receiptDate\":"+ "\""+new Date()+"\","+
                                "\"user\":"+"\""+userName+"\","+
                                "\"receiptDetail\":"+"\""+allMenu.get(position).getMenu_menuName()+"\","+
                                "\"paymentByCredit\":"+"\""+"******"+cardNumber+"\","+
                                "\"subscriptionEndDate\":"+ "\""+newDate+"\""+
                                "\n}";
                        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),uploadData);
                        Call<String> repos=receiptApiService.psotReceipt(body);

                        repos.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String reply = response.body();
                                Log.v("the string return is :", reply);

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
//                        try {
//                            String productResult=repos.execute().body();
//                            Log.v("receiptApiService return: ",productResult);
//                        } catch (IOException e)receiptApiService {
//                            e.printStackTrace();
//                        }
                    }
                });
        normalDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }
}
