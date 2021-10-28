package com.example.foodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.adapter.MenuListAdapter;
import com.example.foodapp.adapter.PopularAdapter;
import com.example.foodapp.model.MenuListModel;
import com.example.foodapp.model.PopularModel;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   ImageView satın;
    String name,fiyat,sayı;
    RecyclerView Popularrecy,menurecy;
    PopularAdapter popularadapter;
    MenuListAdapter menuListAdapter;
    int toplam;
    List<MenuListModel> popularmodelListt = new ArrayList<>();
 EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("acıktım");
        satın = findViewById(R.id.sat);
        editText=findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        name = getIntent().getStringExtra("isim");
        fiyat = getIntent().getStringExtra("fiyat");
        sayı = getIntent().getStringExtra("sayı");

        toplam = getIntent().getIntExtra("toplam", 0);
        satın.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderMenu.class);

                intent.putExtra("toplam", toplam);

                if(Build.VERSION.SDK_INT>20)
                {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                    startActivity(intent,options.toBundle());
                }
                else {
                    startActivity(intent);
                }
            }
        });

        List<PopularModel> popularModelList = new ArrayList<>();
        popularModelList.add(new PopularModel("Izgara Et", "40", R.drawable.et));
        popularModelList.add(new PopularModel("Hamburger", "25", R.drawable.ham));
        popularModelList.add(new PopularModel("Cola", "8", R.drawable.cola));
        PopularRecycler(popularModelList);

        popularmodelListt.add(new MenuListModel("Izgara Et", "40", R.drawable.et));
        popularmodelListt.add(new MenuListModel("Hamburger", "25", R.drawable.ham));
        popularmodelListt.add(new MenuListModel("Salata", "15", R.drawable.salata));
        popularmodelListt.add(new MenuListModel("Cola", "8", R.drawable.cola));
        popularmodelListt.add(new MenuListModel("Su", "3", R.drawable.su));
        MenuRecyler(popularmodelListt);

    }
    private void PopularRecycler(List<PopularModel> popularModelList){
        Popularrecy=findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        Popularrecy.setLayoutManager(layoutManager);
        popularadapter=new PopularAdapter(MainActivity.this, popularModelList);
        Popularrecy.setAdapter(popularadapter);
    }
    private void MenuRecyler(List<MenuListModel> popularmodelListt){
        menurecy=findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        menurecy.setLayoutManager(layoutManager);
        menuListAdapter=new MenuListAdapter(this,popularmodelListt);
        menurecy.setAdapter(menuListAdapter);
    }
    private void filter(String text) {

        ArrayList<MenuListModel> filteredList = new ArrayList<>();
        for (MenuListModel item : popularmodelListt) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        menuListAdapter.filterList(filteredList);
    }

}