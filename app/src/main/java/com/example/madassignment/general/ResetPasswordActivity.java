package com.example.madassignment.general;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        String username = getIntent().getStringExtra("username");

        EditText newPasswordEditText = findViewById(R.id.PTResetPasswordNewPassword);
        EditText confirmPasswordEditText = findViewById(R.id.PTResetPasswordConfirmPassword);
        Button cancelButton = findViewById(R.id.BtnResetPasswordCancel);
        Button resetButton = findViewById(R.id.BtnResetPasswordOkay);

        cancelButton.setOnClickListener(v -> {
            // Navigate back to the login page
            startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
            finish();
        });

        resetButton.setOnClickListener(v -> {
            String newPassword = newPasswordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (newPassword.equals(confirmPassword)) {
                if (username != null) {
                    new ResetPasswordTask().execute(username, newPassword);
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ResetPasswordTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String newPassword = params[1];

            if (newPassword.isEmpty()) {
                return false;
            }

            User user = AppDatabase.getDatabase(ResetPasswordActivity.this).userDao().getUserByUsername(username);
            if (user != null) {
                // Update the user's password
                AppDatabase.getDatabase(ResetPasswordActivity.this).userDao().updateUserPassword(username, newPassword);
                return true;
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);

            if (success) {
                Toast.makeText(ResetPasswordActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ResetPasswordActivity.this.startActivity(intent);
            } else {
                Toast.makeText(ResetPasswordActivity.this, "User not found or password is empty", Toast.LENGTH_SHORT).show();
            }
        }
    }
}