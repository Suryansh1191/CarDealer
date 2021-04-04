package com.example.car_dealer_admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdpterClass_repare extends FirebaseRecyclerAdapter<Chat , AdpterClass_repare.viewHolder> {
    public AdpterClass_repare(@NonNull FirebaseRecyclerOptions<Chat> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull AdpterClass_repare.viewHolder holder, int position, @NonNull Chat chat){
        holder.car_model.setText(chat.getName());
        holder.p_name.setText(chat.getTransmission());

    }
    public AdpterClass_repare.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_repare,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder {

        TextView p_name, car_model,_car_discribtion,car_address,date_repare;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            p_name = itemView.findViewById(R.id.car_discribtion_repare2);
            car_model = itemView.findViewById(R.id.car_name_repare);

        }
    }
}
