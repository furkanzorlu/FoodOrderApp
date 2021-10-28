package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderMenuAdapter extends RecyclerView.Adapter<OrderMenuAdapter.OrderMenuViewHolder> {
    Context context;

    private ArrayList<OrderModel> orderModelList;
   public List<String > list = new ArrayList<>();
    public OrderMenuAdapter(Context context, ArrayList<OrderModel> orderModelList) {
        this.context = context;
        this.orderModelList = orderModelList;
    }

    @NonNull
    @Override
    public OrderMenuAdapter.OrderMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_row_item, parent, false);
        return new OrderMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderMenuAdapter.OrderMenuViewHolder holder, int position) {

        holder.name.setText(orderModelList.get(position).getName());

        holder.sayı.setText(orderModelList.get(position).getSayı());

        holder.toplam.setText(String.valueOf(Integer.valueOf(orderModelList.get(position).getToplam())));
    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }
    public static final class OrderMenuViewHolder extends RecyclerView.ViewHolder{


        TextView  name,sayı,toplam;
        public OrderMenuViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.isimm);
            sayı = itemView.findViewById(R.id.say);
            toplam=itemView.findViewById(R.id.toplamf);



        }

    }

}
