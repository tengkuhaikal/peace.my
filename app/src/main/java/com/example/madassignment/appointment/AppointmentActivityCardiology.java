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

public class AppointmentActivityCardiology extends AppCompatActivity {

    TextView tvDoctor1C, tvDetails1C, tvDoctor2C, tvDetails2C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.appointment_activity_cardiology);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDoctor1C = findViewById(R.id.tvDoctor1C);
        tvDetails1C = findViewById(R.id.tvDetails1C);
        tvDoctor2C = findViewById(R.id.tvDoctor2C);
        tvDetails2C = findViewById(R.id.tvDetails2C);

        CardView cardiology1 = findViewById(R.id.cardiology1);
        cardiology1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityCardiology.this, AppointmentActivityDateAndTime.class);
                it.putExtra("DoctorNo", 1);
                it.putExtra("text1.1", tvDoctor1C.getText().toString());
                it.putExtra("text1.2", tvDetails1C.getText().toString());
                startActivity(it);
            }
        });

        CardView cardiology2 = findViewById(R.id.cardiology2);
        cardiology2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityCardiology.this, AppointmentActivityDateAndTime.class);
                it.putExtra("DoctorNo", 2);
                it.putExtra("text2.1", tvDoctor2C.getText().toString());
                it.putExtra("text2.2", tvDetails2C.getText().toString());
                startActivity(it);
            }
        });
    }
}
