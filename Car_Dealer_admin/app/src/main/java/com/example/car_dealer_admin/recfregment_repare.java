package com.example.car_dealer_admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class recfregment_repare extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView regView;
    AdpterClass_repare Adpater;

    public recfregment_repare() {
        // Required empty public constructor
    }


    public static recfregment_repare newInstance(String param1, String param2) {
        recfregment_repare fragment = new recfregment_repare();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recfregment_repare, container, false);
        regView=view.findViewById(R.id.recyclerview_repare);
        regView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Chat> optionss =
                new FirebaseRecyclerOptions.Builder<Chat>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("listed_client"), Chat.class)
                        .build();
        Adpater = new AdpterClass_repare(optionss);
        regView = setAdapater(Adpater);


        return  view;
    }
    public void onStart() {
        super.onStart();
        Adpater.startListening();
    }
    public void onStop() {
        super.onStop();
        Adpater.stopListening();
    }
}