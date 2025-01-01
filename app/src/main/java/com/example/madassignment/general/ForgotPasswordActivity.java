package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText usernameEditText = findViewById(R.id.PTForgotPasswordUsername);
        EditText phoneEditText = findViewById(R.id.TPForgotPasswordPhoneNumber);
        Button sendOTPButton = findViewById(R.id.BtnForgotPasswordSendOTP);
        Button cancelButton = findViewById(R.id.BtnForgotPasswordCancel);

        sendOTPButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();

            if (!username.isEmpty() && !phone.isEmpty()) {
                // Simulate sending OTP
                sendOTP(phone);

                // Pass to ResetPasswordActivity
                Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "Please enter both username and phone number", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> {
            // Back to the login page
            startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void sendOTP(String phone) {
        // Simulate OTP sending
        Toast.makeText(this, "OTP sent to " + phone, Toast.LENGTH_SHORT).show();

        // Navigate to RequestTACActivity
        startActivity(new Intent(ForgotPasswordActivity.this, RequestTACActivity.class));
        finish();
    }
}