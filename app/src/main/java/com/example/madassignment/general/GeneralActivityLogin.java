package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;

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
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                visibilityButton.setImageResource(R.drawable.visibility_on);
            } else {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                visibilityButton.setImageResource(R.drawable.visibility_off);
            }
            passwordEditText.setSelection(passwordEditText.getText().length());
        });

        // Login button
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            authenticateUser(username, password);
        });

        // Cancel button
        cancelButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Exit Application")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", (dialog, which) -> finishAffinity())
                    .setNegativeButton("No", null)
                    .show();
        });

        // Sign-up button
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GeneralActivitySignUp.class);
            startActivity(intent);
        });

        // Forgot password button
        forgotPasswordButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GeneralActivityForgotPassword.class);
            startActivity(intent);
        });
    }

    private void authenticateUser(String username, String password) {
        executorService.execute(() -> {
            GeneralUser user = userDao.authenticateUser(username, password);

            runOnUiThread(() -> {
                if (user != null) {
                    // User authenticated successfully, navigate to home page
                    Intent intent = new Intent(this, GeneralActivityHome.class);
                    startActivity(intent);
                    finish(); // Close the login activity
                } else {
                    // Invalid credentials, show an error toast
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    usernameEditText.getText().clear();
                    passwordEditText.getText().clear();
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
