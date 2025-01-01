package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Radiology extends AppCompatActivity {

    TextView tvDoctor1R, tvDetails1R, tvDoctor2R, tvDetails2R;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radiology);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDoctor1R = findViewById(R.id.tvDoctor1R);
        tvDetails1R = findViewById(R.id.tvDetails1R);
        tvDoctor2R = findViewById(R.id.tvDoctor2R);
        tvDetails2R = findViewById(R.id.tvDetails2R);

        CardView radiology1 = findViewById(R.id.radiology1);
        radiology1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Radiology.this, DateAndTime.class);
                it.putExtra("DoctorNo", 1);
                it.putExtra("text1.1", tvDoctor1R.getText().toString());
                it.putExtra("text1.2", tvDetails1R.getText().toString());
                startActivity(it);
            }
        });

        CardView radiology2 = findViewById(R.id.radiology2);
        radiology2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Radiology.this, DateAndTime.class);
                it.putExtra("DoctorNo", 2);
                it.putExtra("text2.1", tvDoctor2R.getText().toString());
                it.putExtra("text2.2", tvDetails2R.getText().toString());
                startActivity(it);
            }
        });
    }
}