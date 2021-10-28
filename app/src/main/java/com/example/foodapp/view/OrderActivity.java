package com.example.foodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.OrderMenuAdapter;
import com.example.foodapp.model.OrderModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ArrayList<OrderModel> courseModalArrayList=new ArrayList<>();
    private RecyclerView courseRV;
    private OrderMenuAdapter adapter;
 public String name,fiyat,total;


   TextView artı1;
   public TextView textView;
   int sayı=0;
   ImageView art,azal;
    int imagevalue;
   OrderMenu s;
    int myNum ;
    ImageView ekle,back;
    ImageView ımage;
    TextView textView2,toplam;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
       setTitle("acıktım");

        art=findViewById(R.id.artma);
        toplam=findViewById(R.id.toplam);
         textView=findViewById(R.id.text1);
       back=findViewById(R.id.imageView5);
        artı1=findViewById(R.id.text3);
         textView2=findViewById(R.id.text2);
      ımage =findViewById(R.id.imagee);
        azal=findViewById(R.id.azalma);
        ekle=findViewById(R.id.add);
       myNum = Integer.parseInt(toplam.getText().toString());

        loadData();



        al();
        göster();
        buildRecyclerView();


       try {

       } catch(NumberFormatException nfe) {
           System.out.println("Could not parse " + nfe);
       }
   back.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           onBackPressed();
       }
   });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sayı>0){
                    Intent intent=new Intent(OrderActivity.this, MainActivity.class);

                    intent.putExtra("toplam",myNum);

                    courseModalArrayList.add(new OrderModel(textView.getText().toString(),artı1.getText().toString(),Integer.parseInt(String.valueOf(toplam.getText()))));

                    adapter.notifyItemInserted(courseModalArrayList.size());

                    saveData();
                    Toast.makeText(getBaseContext(),"Siparişiniz sepete eklenmiştir",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else {
                    Toast.makeText(OrderActivity.this,"Lütfen Geçerli Bir Değer Giriniz",Toast.LENGTH_SHORT).show();
                }

            }
        });
        azal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sayı>=1){
                    sayı--;
                    artı1.setText(Integer.toString(sayı));
                    total();
                }

            }
        });
        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayı++;
                artı1.setText(Integer.toString(sayı));
                total();
            }
        });
    }
    private void al(){

        name=getIntent().getStringExtra("Kadi");
        fiyat=getIntent().getStringExtra("fiyat");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imagevalue = bundle.getInt("IMAGE");
        }

    }
    private void göster(){



        ımage.setImageResource(imagevalue);
        textView.setText(name);


        textView2.setText(fiyat);

    }
    private void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences9", MODE_PRIVATE);


        Gson gson = new Gson();


        String json = sharedPreferences.getString("courses", null);


        Type type = new TypeToken<ArrayList<OrderModel>>() {}.getType();


        courseModalArrayList = gson.fromJson(json, type);


        if (courseModalArrayList == null) {

            courseModalArrayList = new ArrayList<>();
        }

    }
    private void buildRecyclerView() {

        courseRV=findViewById(R.id.orderrecy);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        courseRV.setLayoutManager(layoutManager);
        adapter=new OrderMenuAdapter(this,courseModalArrayList);
        courseRV.setAdapter(adapter);
    }
    private void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences9", MODE_PRIVATE);


        SharedPreferences.Editor editor = sharedPreferences.edit();


        Gson gson = new Gson();


        String json = gson.toJson(courseModalArrayList);


        editor.putString("courses", json);


        editor.apply();



    }


    private void total(){
        Integer.parseInt(artı1.getText().toString());
        Integer.parseInt(textView2.getText().toString());
        int myNum,mynum2  ;

        try {
            myNum = Integer.parseInt(artı1.getText().toString());
            mynum2 = Integer.parseInt(textView2.getText().toString());
           toplam.setText(String.valueOf(myNum*mynum2));

        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }


    }


}

