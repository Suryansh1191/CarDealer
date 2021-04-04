package com.example.car_dealer_admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdpaterClass extends FirebaseRecyclerAdapter<Chat , AdpaterClass.viewHolder> {

    public AdpaterClass(@NonNull FirebaseRecyclerOptions<Chat> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Chat chat) {
        holder.carname_card.setText(chat.getName());
        holder.year_card.setText(chat.getYear());
        holder.petrol_card.setText(chat.getFuel());
        Glide.with(holder.Img_card.getContext()).load(chat.getImg_url()).into(holder.Img_card);

        holder.Img_card.setOnClickListener(v -> {
            AppCompatActivity activity =(AppCompatActivity)v.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.wraper, new Last_Fragment(chat.getName(),chat.getFuel(),chat.getBrand(),chat.getDescribtion(),chat.getImg_url(),chat.getKm(),chat.getPhone(),chat.getTransmission(),chat.getYear())).addToBackStack(null).commit();
        });
    }

    @NonNull
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_view,parent,false);
        return new viewHolder(view);
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        ImageView Img_card;
        TextView year_card,petrol_card,carname_card;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            Img_card=itemView.findViewById(R.id.img_card);
            year_card=itemView.findViewById(R.id.year_card);
            petrol_card=itemView.findViewById(R.id.petrol_card);
            carname_card=itemView.findViewById(R.id.carname_card);

        }
    }

}

