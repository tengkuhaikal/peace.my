package com.example.madassignment.appointment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.madassignment.R;

public class AppointmentActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.appointment_activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView radiologyCard = findViewById(R.id.radiologyCard);
        radiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityMain.this, AppointmentActivityRadiology.class);
                it.putExtra("title", "Radiology");
                startActivity(it);
            }
        });

        CardView neurologyCard = findViewById(R.id.neurologyCard);
        neurologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityMain.this, AppointmentActivityNeurology.class);
                it.putExtra("title", "Neurology");
                startActivity(it);
            }
        });

        CardView cardiologyCard = findViewById(R.id.cardiologyCard);
        cardiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityMain.this, AppointmentActivityCardiology.class);
                it.putExtra("title", "Cardiology");
                startActivity(it);
            }
        });

        CardView dermatologyCard = findViewById(R.id.dermatologyCard);
        dermatologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AppointmentActivityMain.this, AppointmentActivityDermatology.class);
                it.putExtra("title", "Dermatology");
                startActivity(it);
            }
        });
    }
}
