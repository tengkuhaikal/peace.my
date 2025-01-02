package com.example.madassignment.appointment;

import android.annotation.SuppressLint;
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

import com.example.madassignment.R;

public class AppointmentActivityDermatology extends AppCompatActivity {

    TextView tvDoctor1D, tvDetails1D, tvDoctor2D, tvDetails2D;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.appointment_activity_dermatology);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDoctor1D = findViewById(R.id.tvDoctor1D);
        tvDetails1D = findViewById(R.id.tvDetails1D);
        tvDoctor2D = findViewById(R.id.tvDoctor2D);
        tvDetails2D = findViewById(R.id.tvDetails2D);

        CardView dermatology1 = findViewById(R.id.dermatology1);
        dermatology1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityDermatology.this, AppointmentActivityDateAndTime.class);
                it.putExtra("DoctorNo", 1);
                it.putExtra("text1.1", tvDoctor1D.getText().toString());
                it.putExtra("text1.2", tvDetails1D.getText().toString());
                startActivity(it);
            }
        });

        CardView dermatology2 = findViewById(R.id.dermatology2);
        dermatology2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityDermatology.this, AppointmentActivityDateAndTime.class);
                it.putExtra("DoctorNo", 2);
                it.putExtra("text2.1", tvDoctor2D.getText().toString());
                it.putExtra("text2.2", tvDetails2D.getText().toString());
                startActivity(it);
            }
        });
    }
}