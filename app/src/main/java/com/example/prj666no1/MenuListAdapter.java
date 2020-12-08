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

import com.bumptech.glide.Glide;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder>{
    private List<Menu> mData ;
    private Context mContext;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    MenuListAdapter(List<Menu> data, Context context){
        mContext = context;
        this.mData = data;
        Log.v("data124",data.get(0).getMenu_menuName());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView ivMenu;
        TextView menuCode;
        TextView menuName;
        TextView menuDetail;
        ImageView menuPic;

        ViewHolder(View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.menuName);
            menuCode = itemView.findViewById(R.id.menuCode);
            menuDetail = itemView.findViewById(R.id.menuDetail);
            menuPic = itemView.findViewById(R.id.menuPic);

        }
    }

    @NonNull
    @Override
    public MenuListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_row, parent, false);
        return new MenuListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListAdapter.ViewHolder holder, final int position) {
        String menuNameOnBind = null;
        String menuCodeOnBind = null;
        String menuDetailOnBind="";
        String menuPic="";



        menuNameOnBind = mData.get(position).getMenu_menuName();
        //ivMovieTemp= mData.getJSONObject(position).getString("poster_path");
        menuCodeOnBind = mData.get(position).getMenu_menuCode();
        //tvTitleTemp= mData.getJSONObject(position).getString("title");

        menuPic = mData.get(position).getPictureLink();

            //mdata is the set of all list<singleDayMenu>
            for(int j = 0; j<mData.get(position).getMenu_allMenu().size();j++){
                //getAllMenu will return list<SingleDayMenu>
                menuDetailOnBind+="Day: "+mData.get(position).getMenu_allMenu().get(j).getMenu_day()+"\n";
                menuDetailOnBind+="Breakfast: "+mData.get(position).getMenu_allMenu().get(j).getMenu_breakfast()+"\n";
                menuDetailOnBind+="Lunch: "+mData.get(position).getMenu_allMenu().get(j).getMenu_lunch()+"\n";
                menuDetailOnBind+="Dinner: "+mData.get(position).getMenu_allMenu().get(j).getMenu_dinner()+"\n\n";
            }






        Glide.with(mContext)
                .load(menuPic)
                .into(holder.menuPic);
        holder.menuName.setText(menuNameOnBind);
        holder.menuCode.setText(menuCodeOnBind);
        holder.menuDetail.setText(menuDetailOnBind);

        //step4 这里除了listner.onclick都与interface无关，这个步骤的意义是使用“listener.onClick(position);”就是当目的地的object启用这个listner时，将position传入
        //当地的详细initialization。

        holder.menuPic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        int data = mData.size();
        return data;
    }
}
