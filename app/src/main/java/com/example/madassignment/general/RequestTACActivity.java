package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;

public class RequestTACActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tac);

        EditText tacCodeEditText = findViewById(R.id.TNRequestTACCode);
        Button verifyButton = findViewById(R.id.BtnRequestTACVerify);
        Button backButton = findViewById(R.id.BtnRequestTACBack);
        Button sendTACAgainButton = findViewById(R.id.BtnRequestTACAgain);

        verifyButton.setOnClickListener(v -> {
            String tacCode = tacCodeEditText.getText().toString();

            if (!tacCode.isEmpty()) {
                verifyTAC(tacCode);
            } else {
                Toast.makeText(RequestTACActivity.this, "Please enter the TAC", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(RequestTACActivity.this, ForgotPasswordActivity.class));
            finish();
        });

        sendTACAgainButton.setOnClickListener(v -> {
            // Simulate sending the TAC again
            Toast.makeText(RequestTACActivity.this, "TAC sent again", Toast.LENGTH_SHORT).show();
        });
    }

    private void verifyTAC(String tacCode) {
        // Simulate TAC verification
        if ("1234".equals(tacCode)) {  // Placeholder for actual TAC validation
            // Navigate to reset password page
            startActivity(new Intent(RequestTACActivity.this, ResetPasswordActivity.class));
            finish();
        } else {
            Toast.makeText(RequestTACActivity.this, "Invalid TAC", Toast.LENGTH_SHORT).show();
        }
    }
}