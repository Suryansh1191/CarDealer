package com.example.cardealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Calendar;
import java.util.HashMap;




public class repare_car_activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText car_model , person_name, contect_per, describtion_rep , address_car ;
    String model , name , contect, describtion , address, EnterDate;
    Button date, conti_rep , home , contect_but;
    private ProgressDialog progressBars;
    LinearLayout linearLayout;
    ScrollView scrollView;
    String qrImgLink="linksoon";
    private static final int REQUEST_CALL = 1;
    private ImageView QrView;
    private DatabaseReference rootRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repare_car_activity);

        car_model =findViewById(R.id.Car_model_rep);
        person_name = findViewById(R.id.your_name_rep);
        describtion_rep = findViewById(R.id.car_problem_rep);
        contect_per = findViewById(R.id.contect_rep_f);
        address_car = findViewById(R.id.Address_rep);
        linearLayout = findViewById(R.id.liner_layout_Qr);
        scrollView = findViewById(R.id.scrollview_form);
        home = findViewById(R.id.home_rep_aft);
        contect_but = findViewById(R.id.contect_rep_aftttt);

        conti_rep = findViewById(R.id.contiu_rep);
        QrView = findViewById(R.id.qr_view);

        rootRef = FirebaseDatabase.getInstance().getReference();
        date = findViewById(R.id.date_rep);
        progressBars = new ProgressDialog(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(repare_car_activity.this,MainActivity.class));
            }
        });

        contect_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(repare_car_activity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(repare_car_activity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }
                else {
                    String call = "9179322789".toString();
                    String no = "tel:" + call;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Toast.makeText(repare_car_activity.this, "Calling our Executive", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(no));
                    startActivity(intent);
                }
            }
        });



        conti_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                model = car_model.getText().toString();
                name = person_name.getText().toString();
                contect = contect_per.getText().toString();
                describtion = describtion_rep.getText().toString();
                address = address_car.getText().toString();

                progressBars.setTitle("Wait");
                progressBars.setMessage("Sending Your Information To Our Executive");
                progressBars.setCanceledOnTouchOutside(false);
                progressBars.show();

                if (model.equals("") || name.equals("") || contect.equals("") || describtion.equals("") || address.equals("")){
                    Toast.makeText(repare_car_activity.this, "please enter all details first", Toast.LENGTH_SHORT).show();
                     }
                else {
                    Tofirebase();
                }

            }
        });


    }

    private void Tofirebase() {
        FirebaseFirestore bd =  FirebaseFirestore.getInstance();
        final String userId= FirebaseAuth.getInstance().getUid();
        final HashMap<String, Object> profileMap = new HashMap<>();
        profileMap.put("model", model);
        profileMap.put("name", name);
        profileMap.put("contect", contect);
        profileMap.put("describtion" , describtion);
        profileMap.put("address" , address);
        profileMap.put("date" ,  EnterDate);

        rootRef.child("car_for_repare").child(userId).setValue(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    scrollView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);

                    genrateQr(userId);
                    profileMap.put("qrLink",qrImgLink);
                    rootRef.child("car_for_repare").child(userId).updateChildren(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(repare_car_activity.this, "Added", Toast.LENGTH_SHORT).show();
                            progressBars.dismiss();
                        }
                    });
                }
                else {
                    Toast.makeText(repare_car_activity.this, "failed", Toast.LENGTH_SHORT).show();
                    progressBars.dismiss();
                }
            }
        });

    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int i, int i1, int i2) {
        String dat = i + "/" + (i1+1) + "/" + i2;
        date.setText(dat);
        EnterDate = i + " " + (i1+1) + " " + i2;
    }

    private void genrateQr(String userId) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(userId, BarcodeFormat.QR_CODE, 200, 200);
            Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x<200; x++){
                for (int y=0; y<200; y++){
                    bitmap.setPixel(x,y, ((BitMatrix) bitMatrix).get(x,y)? Color.BLACK : Color.WHITE);
                }
            }
            QrView.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}