package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.PopularModel;
import com.example.foodapp.view.OrderActivity;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularFoodViewHolder> {
    Context context;
    List<PopularModel> popularFoodList;


    public PopularAdapter(Context context, List<PopularModel> popularFoodList) {
        this.context = context;
        this.popularFoodList = popularFoodList;
    }

    @NonNull
    @Override
    public PopularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_row_item, parent, false);
        return new PopularFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(popularFoodList.get(position).getImageUrl());
        holder.name.setText(popularFoodList.get(position).getName());
        holder.price.setText(popularFoodList.get(position).getPrice());

        holder.userAnalayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent=new Intent(context, OrderActivity.class);
                ıntent.putExtra("Kadi",holder.name.getText());
                ıntent.putExtra("fiyat",holder.price.getText());

                Bundle bundle=new Bundle();

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

        RelativeLayout userAnalayout;
        ImageView foodImage;
        TextView price, name;

        public PopularFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);

            userAnalayout=itemView.findViewById(R.id.lin);

        }
    }

}
