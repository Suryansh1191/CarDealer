package com.example.cardealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Car_for_sale_Activity extends AppCompatActivity {

  //  private RecyclerView mRecyclerView;
//    private ImageAdapter mAdapter;
//    private DatabaseReference mDatabaseRef;
//    private List<Chat> mChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_for_sale_);

        getSupportFragmentManager().beginTransaction().replace(R.id.wraper,new recfregment()).commit();

//        mRecyclerView = findViewById(R.id.recycler_sale);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mUploads = new ArrayList<>();
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    upload upload = postSnapshot.getValue(upload.class);
//                    mUploads.add(upload);
//                }
//                mAdapter = new ImageAdapter(Car_for_sale_Activity.this, mUploads);
//                mRecyclerView.setAdapter(mAdapter);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(Car_for_sale_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}