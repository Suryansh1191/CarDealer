package com.example.car_dealer_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class recfregment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView regView;
    AdpaterClass Adpater;

    public recfregment() {
        // Required empty public constructor
    }

    public static recfregment newInstance(String param1, String param2) {
        recfregment fragment = new recfregment();
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
        View view = inflater.inflate(R.layout.fragment_recfregment, container, false);
        regView = view.findViewById(R.id.freg_recyclerView);
        regView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Chat> options =
                new FirebaseRecyclerOptions.Builder<Chat>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("listed_client"), Chat.class)
                        .build();
        Adpater=new AdpaterClass(options);
        regView.setAdapter(Adpater);
        return view;
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
