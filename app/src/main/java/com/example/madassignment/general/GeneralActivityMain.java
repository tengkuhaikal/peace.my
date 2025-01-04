package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;

public class GeneralActivityMain extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.general_start_up);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(GeneralActivityMain.this, GeneralActivityLogin.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}