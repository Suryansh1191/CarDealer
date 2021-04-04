package com.example.car_dealer_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button add_car;
    Button listed_car;
    Button listed_for_repare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_car = findViewById(R.id.Add_car);
        listed_car = findViewById(R.id.listed_car);
        listed_for_repare = findViewById(R.id.repare_car);

        listed_for_repare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Listed_for_repare_admin.class));
            }
        });

        listed_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , listed_for_sale_admin.class));
            }
        });

        add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , add_car_Activity.class));
            }
        });
    }
}