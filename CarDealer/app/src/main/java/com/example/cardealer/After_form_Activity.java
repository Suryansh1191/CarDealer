package com.example.cardealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class After_form_Activity extends AppCompatActivity {

    private Button done;
    private Button contect;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_form_);

        done = findViewById(R.id.done_but);
        contect = findViewById(R.id.contect_but);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(After_form_Activity.this , MainActivity.class));
            }
        });

        contect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(After_form_Activity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(After_form_Activity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }
                else {
                    String call = "9179322789".toString();
                    String no = "tel:" + call;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Toast.makeText(After_form_Activity.this, "Calling our Executive", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(no));
                    startActivity(intent);
                }
            }
        });
    }
}