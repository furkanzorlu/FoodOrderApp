package com.example.foodapp.model;

import java.util.List;

public class OrderModel {
    String name;

    String sayı;
    //String toplam;
    int toplam;

    public OrderModel(String name, String sayı, int toplam) {
        this.name = name;
        this.sayı = sayı;
        this.toplam = toplam;
    }

    public int getToplam() {
        return toplam;
    }

    public void setToplam(int toplam) {
        this.toplam = toplam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getSayı() {
        return sayı;
    }

    public void setSayı(String sayı) {
        this.sayı = sayı;
    }
}
