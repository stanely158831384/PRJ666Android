package com.example.prj666no1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReceiptListAdapter extends RecyclerView.Adapter<ReceiptListAdapter.ViewHolder>{
    private List<Receipt> mData ;
    private Context mContext;
    private ReceiptListAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(ReceiptListAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    ReceiptListAdapter(List<Receipt> data, Context context){
        mContext = context;
        this.mData = data;
        //Log.v("ReceiptionListAdpater data test: ",data.get(0).getUser());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivReceipt;
        TextView receiptDate;
        TextView user;
        TextView receiptDetail;
        TextView paymentByCredit;
        TextView subscriptionEndDate;
        ImageView receiptPic;

        ViewHolder(View itemView) {
            super(itemView);
            receiptDate=itemView.findViewById(R.id.receiptDate);
            user=itemView.findViewById(R.id.receiptUserName);
            receiptDetail=itemView.findViewById(R.id.receiptDetail);
            paymentByCredit=itemView.findViewById(R.id.receiptPayment);
            subscriptionEndDate=itemView.findViewById(R.id.receiptEndDate);
            ivReceipt=itemView.findViewById(R.id.receiptPic);
        }
    }

    @NonNull
    @Override
    public ReceiptListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_row, parent, false);
        return new ReceiptListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptListAdapter.ViewHolder holder, final int position) {
        String receiptDateString;
        String userString;
        String receiptDetailString;
        String paymentByCreditString;
        String subscriptionEndDateString;



      receiptDateString=mData.get(position).getDate().toString();
      userString=mData.get(position).getUser();
      receiptDetailString=mData.get(position).getReceiptDetail();
      paymentByCreditString=mData.get(position).getPaymentByCredit();

      if(mData.get(position).getEndDate()!=null){
          subscriptionEndDateString=mData.get(position).getEndDate().toString();
      }else{
          subscriptionEndDateString="null";
      }



//        Glide.with(mContext)
//                .load("https://image.tmdb.org/t/p/w185"+ivMovieTemp)
//                .into(holder.ivMovie);
        holder.receiptDate.setText(receiptDateString);
        holder.user.setText(userString);
        holder.receiptDetail.setText(receiptDetailString);
        holder.paymentByCredit.setText(paymentByCreditString);
        holder.subscriptionEndDate.setText(subscriptionEndDateString);

        //step4 这里除了listner.onclick都与interface无关，这个步骤的意义是使用“listener.onClick(position);”就是当目的地的object启用这个listner时，将position传入
        //当地的详细initialization。

//        holder.receiptPic.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                listener.onClick(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        int data = mData.size();
        return data;
    }
}
