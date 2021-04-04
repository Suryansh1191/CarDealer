package com.example.car_dealer_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import android.net.Uri;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class add_car_Activity extends AppCompatActivity {

    private EditText car_brand , car_name,car_year,car_km,car_describtion,asking_price,phone_num;
    private Button continue_but , upload_img;
    private Spinner fuel,transmitions;
    private String brand , name , year, KM,describtion,price ,phone,  downloadImag ;
    private String car_fule, car_transmition ;
    private DatabaseReference rootRef;
    private FirebaseDatabase rootNood;
    private ProgressDialog progressBar;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private ImageView mImageView;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_);

        car_brand = findViewById(R.id.Car_brand);
        car_name = findViewById(R.id.car_name);
        car_km = findViewById(R.id.km_driven);
        car_year = findViewById(R.id.year_car);
        car_describtion = findViewById(R.id.discribtion_car);
        asking_price = findViewById(R.id.price_car);
        phone_num = findViewById(R.id.phone_no_);
        upload_img = findViewById(R.id.upload_img_sell);
        mImageView = findViewById(R.id.image_view);

        continue_but = findViewById(R.id.contiu);
        rootRef = FirebaseDatabase.getInstance().getReference();

        fuel = findViewById(R.id.fuel_Spinner);
        transmitions = findViewById(R.id.Transmition_Spinner);
        progressBar = new ProgressDialog(this);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseIMG();
            }
        });


        /*transmition*/
        List<String> categoriess = new ArrayList<>();
        categoriess.add(0, "Seclect Transmission");
        categoriess.add("Manual");
        categoriess.add("Automatic");
        /*----------------------list end----------------------*/

        ArrayAdapter<String> dataAdapterr;
        dataAdapterr = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categoriess);

        dataAdapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        transmitions.setAdapter(dataAdapterr);

        transmitions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("choose Doctor")) {

                } else {
                    String item = adapterView.getItemAtPosition(i).toString();
                    car_transmition = item;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*transmition*/

        /*fule*/
        List<String> categories = new ArrayList<>();
        categories.add(0, "Seclect Fuel");
        categories.add("Diesel");
        categories.add("Petrol");
        /*----------------------list end----------------------*/


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fuel.setAdapter(dataAdapter);

        fuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("choose Doctor")) {

                } else {
                    String item = adapterView.getItemAtPosition(i).toString();
                    car_fule = item;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*fule*/


        continue_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brand = car_brand.getText().toString();
                name = car_name.getText().toString();
                describtion = car_describtion.getText().toString();
                price = asking_price.getText().toString();
                year = car_year.getText().toString();
                KM = car_km.getText().toString();
                phone = phone_num.getText().toString();

                progressBar.setTitle("Wait");
                progressBar.setMessage("Sending Your Information To Our Executive");
                progressBar.setCanceledOnTouchOutside(false);
                progressBar.show();

                if (brand.equals("") || name.equals("") || describtion.equals("") || price.equals("") || year.equals("") || KM.equals("") ) {
                    Toast.makeText(add_car_Activity.this, "please enter all details first", Toast.LENGTH_SHORT).show();
                }
                else{
                    uploadFile();
                }
            }
        });

    }

    private void choseIMG() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void uploadFile() {
        StorageReference filePath = FirebaseStorage.getInstance().getReference().child("uploads").child(name);
        final UploadTask uploadTask = filePath.putFile(mImageUri);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful())
                    throw task.getException();
                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        downloadImag = uri.toString();
                    }
                });
//                downloadImag = filePath.getDownloadUrl().toString();
                return filePath.getDownloadUrl();

            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()){
//                    downloadImag = task.getResult().toString();
                    addData();
                }
            }
        });
    }


        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                    && data != null && data.getData() != null) {
                mImageUri = data.getData();
                Picasso.with(this).load(mImageUri).into(mImageView);
            }
        }

        private String getFileExtension(Uri uri){
            ContentResolver cR = getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(cR.getType(uri));
        }




    private void addData() {
        final HashMap<String, Object> profileMap = new HashMap<>();
        profileMap.put("brand", brand);
        profileMap.put("name", name);
        profileMap.put("describtion", describtion);
        profileMap.put("year", year);
        profileMap.put("km", KM);
        profileMap.put("fuel", car_fule);
        profileMap.put("Transmission", car_transmition);
        profileMap.put("img_url", downloadImag);
        profileMap.put("Phone", phone);

        rootRef.child("car_for_sale_admin").child(phone).setValue(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(add_car_Activity.this, "Added firebase", Toast.LENGTH_SHORT).show();
                    progressBar.dismiss();
                    // startActivity(new Intent(add_car_Activity.this, After_form_Activity.class));
                    return;
                } else {
                    Toast.makeText(add_car_Activity.this, "failed", Toast.LENGTH_SHORT).show();
                    progressBar.dismiss();
                }
            }
        });
    }
}