package com.example.car_dealer_admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class Last_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String name ,fuel ,Brand , Describtion, IMG_ulr ,KM,Phone ,Transmition ,year;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Last_Fragment() {
        // Required empty public constructor
    }

    public Last_Fragment(String name , String fuel , String Brand , String Describtion, String IMG_ulr ,String KM, String Phone , String Transmition , String year) {

        this.name=name;
        this.fuel = fuel;
        this.Brand = Brand;
        this.Describtion = Describtion;
        this.IMG_ulr = IMG_ulr;
        this.KM =KM;
        this.Phone = Phone;
        this.Transmition = Transmition;
        this.year = year;

    }

    public static Last_Fragment newInstance(String param1, String param2) {
        Last_Fragment fragment = new Last_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_last_, container, false);

        ImageView imageHolder = view.findViewById(R.id.image_holder_last);
        TextView name_holder = view.findViewById(R.id.model_last);
        TextView componey = view.findViewById(R.id.Brand_last);
        TextView Year = view.findViewById(R.id.year_last);
        TextView KM2 = view.findViewById(R.id.KM_last);
        TextView description = view.findViewById(R.id.Description_last);
        TextView fuel2 = view.findViewById(R.id.Fuel_last);
        TextView Transmition2 = view.findViewById(R.id.Transmition_last);
        Button call_seller = view.findViewById(R.id.call_customer);

        name_holder.setText(name);
        componey.setText(Brand);
        Year.setText(year);
        KM2.setText(KM);
        description.setText(Describtion);
        fuel2.setText(fuel);
        Transmition2.setText(Transmition);
        Glide.with(getContext()).load(IMG_ulr).into(imageHolder);

        call_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String call = Phone.toString();
                String no = "tel:" + call;
                Intent intent = new Intent(Intent.ACTION_CALL);
                Toast.makeText(getActivity(), "Calling our Executive", Toast.LENGTH_SHORT).show();
                intent.setData(Uri.parse(no));
                startActivity(intent);

            }
        });

        return view;

    }

    public void onBackPressed(){
        AppCompatActivity activity =(AppCompatActivity)getContext();
        assert activity != null;
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wraper, new recfregment()).addToBackStack(null).commit();

    }

}