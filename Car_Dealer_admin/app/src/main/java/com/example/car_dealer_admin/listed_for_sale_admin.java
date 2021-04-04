package com.example.car_dealer_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class listed_for_sale_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listed_for_sale_admin);

        getSupportFragmentManager().beginTransaction().replace(R.id.wraper,new recfregment()).commit();


    }
}