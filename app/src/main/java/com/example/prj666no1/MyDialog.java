package com.example.prj666no1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyDialog extends DialogFragment implements View.OnClickListener {


    @Override
    public void onClick(View v) {
        dismiss();
    }

    public static MyDialog newInstant(int textIdToPassToFragment){

        MyDialog m = new MyDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("help_testA",textIdToPassToFragment);
        m.setArguments(bundle);
        return m;
    }

    public static MyDialog newInstant2(String[] textIdToPassToFragment){

        MyDialog m = new MyDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArray("help_testB",textIdToPassToFragment);
        m.setArguments(bundle);
        return m;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.dialog_fragment,container,false);
        TextView textInDialog = (TextView) view.findViewById(R.id.helptextid);
        textInDialog.setText(""+getArguments().getInt("help_testA"));
        Button button = (Button) view.findViewById(R.id.close_but_id);
        button.setOnClickListener(this);
        return view;
    }
//    @Override
//    public void onClick(View v) {
//        dismiss();
//    }
//
//    public static MyDialog newInstant(int textIdToPassToFragment){
//        MyDialog m = new MyDialog();
//        Bundle bundle = new Bundle();
//        bundle.putInt("help_testA",textIdToPassToFragment);
//        m.setArguments(bundle);
//        return m;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
//
//        View view = inflater.inflate(R.layout.dialog_fragment,container,false);
//        TextView textInDialog = (TextView) view.findViewById(R.id.helptextid);
//        textInDialog.setText(""+getArguments().getInt("help_testA"));
//        Button button = (Button) view.findViewById(R.id.close_but_id);
//        button.setOnClickListener(this);
//        return view;
//    }

}
