package com.example.madassignment.appointment;

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

public class AppointmentActivityNeurology extends AppCompatActivity {

    TextView tvDoctor1N, tvDetails1N, tvDoctor2N, tvDetails2N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.appointment_activity_neurology);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDoctor1N = findViewById(R.id.tvDoctor1N);
        tvDetails1N = findViewById(R.id.tvDetails1N);
        tvDoctor2N = findViewById(R.id.tvDoctor2N);
        tvDetails2N = findViewById(R.id.tvDetails2N);

        CardView neurology1 = findViewById(R.id.neurology1);
        neurology1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityNeurology.this, AppointmentActivityDateAndTime.class);
                it.putExtra("DoctorNo", 1);
                it.putExtra("text1.1", tvDoctor1N.getText().toString());
                it.putExtra("text1.2", tvDetails1N.getText().toString());
                startActivity(it);
            }
        });

        CardView neurology2 = findViewById(R.id.neurology2);
        neurology2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityNeurology.this, AppointmentActivityDateAndTime.class);
                it.putExtra("DoctorNo",  2);
                it.putExtra("text2.1", tvDoctor2N.getText().toString());
                it.putExtra("text2.2", tvDetails2N.getText().toString());
                startActivity(it);
            }
        });
    }
}