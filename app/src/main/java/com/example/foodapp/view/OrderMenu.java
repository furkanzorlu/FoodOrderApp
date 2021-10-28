package com.example.foodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.OrderMenuAdapter;
import com.example.foodapp.model.OrderModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderMenu extends AppCompatActivity {

    TextView textView,empty;
   public Button but;


    private RecyclerView courseRV;
    private OrderMenuAdapter adapter;
    List<String > list = new ArrayList<>();
   int toplam;
    String json;
    ArrayList<OrderModel> courseModalArrayList=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_ordermenu);
       textView=findViewById(R.id.totalprice);
        empty=findViewById(R.id.emptytext);
     but=findViewById(R.id.orderbut);
        setTitle("sepetim");

     but.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Toast.makeText(getBaseContext(),"Siparişiniz Alınmıştır",Toast.LENGTH_LONG).show();
             SharedPreferences preferences = getSharedPreferences("shared preferences9", MODE_PRIVATE);
             preferences.edit().remove("courses").commit();

             Intent intent=new Intent(OrderMenu.this, MainActivity.class);
             startActivity(intent);


         }
     });

        loadData();

        toplam=getIntent().getIntExtra("toplam",0);


        buildRecyclerView();


        adapter.notifyItemInserted(courseModalArrayList.size());
        getSumofAllitemss();
        if (adapter.getItemCount()==0){
 empty.setVisibility(View.VISIBLE);
        }






    }

    private void buildRecyclerView() {

        courseRV=findViewById(R.id.orderrecy);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        courseRV.setLayoutManager(layoutManager);
        adapter=new OrderMenuAdapter(this,courseModalArrayList);
        courseRV.setAdapter(adapter);
    }

    private void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences9", MODE_PRIVATE);


        Gson gson = new Gson();


         json = sharedPreferences.getString("courses", null);


        Type type = new TypeToken<ArrayList<OrderModel>>() {}.getType();


        courseModalArrayList = gson.fromJson(json, type);


        if (courseModalArrayList == null) {

            courseModalArrayList = new ArrayList<>();
        }
    }
    public void getSumofAllitemss(){
        int totalPrice =0;
        for (int i = 0; i<courseModalArrayList.size(); i++)
        {
            totalPrice += courseModalArrayList.get(i).getToplam();
        }
        textView.setText(totalPrice+ "₺");

    }
    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.RIGHT);
            slide.setDuration(400);
            slide.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }

}