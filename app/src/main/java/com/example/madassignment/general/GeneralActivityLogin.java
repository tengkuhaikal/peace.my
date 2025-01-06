package com.example.madassignment.general;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeneralActivityLogin extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button cancelButton;
    private ImageButton visibilityButton;
    private Button signUpButton;
    private Button forgotPasswordButton;
    private boolean isPasswordVisible = false;

    private GeneralUserDao userDao;
    private GeneralAppDatabase appDatabase;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_activity_login);

        appDatabase = GeneralAppDatabase.getDatabase(getApplicationContext());
        userDao = appDatabase.generalUserDao();

        usernameEditText = findViewById(R.id.PTLoginUsername);
        passwordEditText = findViewById(R.id.PTLoginPassword);
        loginButton = findViewById(R.id.BtnLoginLogin);
        cancelButton = findViewById(R.id.BtnLoginCancel);
        visibilityButton = findViewById(R.id.IBVisibilityOff);
        signUpButton = findViewById(R.id.BtnLoginSignUp);
        forgotPasswordButton = findViewById(R.id.BtnLoginForgotPassword);

        // Show/Hide Password
        visibilityButton.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                passwordEditText.setTransformationMethod(null); // Show password
                visibilityButton.setImageResource(R.drawable.visibility_on);
            } else {
                passwordEditText.setTransformationMethod(new PasswordTransformationMethod()); // Hide password
                visibilityButton.setImageResource(R.drawable.visibility_off);
            }
            passwordEditText.setSelection(passwordEditText.getText().length());
        });

        // Login button
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Snackbar.make(loginButton, "Please enter username and password", Snackbar.LENGTH_SHORT).show();
                return;
            }

            authenticateUser(username, password);
        });

        // Cancel button
        cancelButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Exit")
                    .setMessage("Are you sure you want to close the application?")
                    .setPositiveButton("Exit", (dialog, which) -> finishAffinity())
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        // Sign-up button
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(GeneralActivityLogin.this, GeneralActivitySignUp.class);
            startActivity(intent);
        });

        // Forgot password button
        forgotPasswordButton.setOnClickListener(v -> {
            Intent intent = new Intent(GeneralActivityLogin.this, GeneralActivityForgotPassword.class);
            startActivity(intent);
        });
    }

    private void authenticateUser(String username, String password) {
        executorService.execute(() -> {
            GeneralUser user = userDao.authenticateUser(username, password);

            Log.d("Login", "Checking user: " + username);
            Log.d("Login", "Authenticated user: " + (user != null ? user.getUsername() : "No user found"));

            runOnUiThread(() -> {
                if (user != null) {
                    // Save username
                    SharedPreferences sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putLong("userID", user.getId());
                    editor.apply();

                    // User authenticated successfully, navigate to GeneralBaseActivity
                    Intent intent = new Intent(GeneralActivityLogin.this, GeneralBaseActivity.class);
                    intent.putExtra("active_page", "home"); // Set the initial page as "home"
                    startActivity(intent);
                    finish();
                } else {
                    // Invalid credentials
                    Snackbar.make(loginButton, "Invalid username or password", Snackbar.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
