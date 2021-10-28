package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.MenuListModel;
import com.example.foodapp.view.OrderActivity;


import java.util.ArrayList;
import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.PopularFoodViewHolder> {
    Context context;
    List<MenuListModel> popularFoodList;


    public MenuListAdapter(Context context, List<MenuListModel> popularFoodList) {
        this.context = context;
        this.popularFoodList = popularFoodList;
    }

    @NonNull
    @Override
    public PopularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.food_row_item, parent, false);
        return new PopularFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularFoodViewHolder holder, int position) {
        MenuListModel currentItem = popularFoodList.get(position);
        holder.ımage.setImageResource(popularFoodList.get(position).getImageUrl());
        holder.firstname.setText(popularFoodList.get(position).getName());
        holder.lastname.setText(popularFoodList.get(position).getPrice());

        holder.userAnalayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent=new Intent(context, OrderActivity.class);
                ıntent.putExtra("Kadi",holder.firstname.getText());
                ıntent.putExtra("fiyat",holder.lastname.getText());

                Bundle bundle=new Bundle();
                //bundle.putInt("IMAGE", popularFoodList.get(holder.getAdapterPosition()).getImageUrl());
                bundle.putInt("IMAGE", popularFoodList.get(holder.getAdapterPosition()).getImageUrl());
                ıntent.putExtras(bundle);
                context.startActivity(ıntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }


    public static final class PopularFoodViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout userAnalayout;
        TextView firstname, lastname;
        ImageView ımage;

        public PopularFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            firstname
                    = itemView.findViewById(R.id.restorant_name);
            lastname = itemView.findViewById(R.id.rating);
            ımage=itemView.findViewById(R.id.food_image);

            userAnalayout=itemView.findViewById(R.id.consfood);

        }

    }
    public void filterList(ArrayList<MenuListModel> filteredList) {
        popularFoodList = filteredList;
        notifyDataSetChanged();
    }

}
