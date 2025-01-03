package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
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

    private GeneralUserDao generalUserDao;
    private GeneralAppDatabase generalAppDatabase;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_activity_login);

        generalAppDatabase = GeneralAppDatabase.getDatabase(getApplicationContext());
        generalUserDao = generalAppDatabase.generalUserDao();

        usernameEditText = findViewById(R.id.PTLoginUsername);
        passwordEditText = findViewById(R.id.PTLoginPassword);
        loginButton = findViewById(R.id.BtnLoginLogin);
        cancelButton = findViewById(R.id.BtnLoginCancel);
        visibilityButton = findViewById(R.id.IBVisibilityOff);
        signUpButton = findViewById(R.id.BtnLoginSignUp);
        forgotPasswordButton = findViewById(R.id.BtnLoginForgotPassword);

        // Toggle password visibility
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
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            authenticateUser(username, password);
        });

        // Cancel button
        cancelButton.setOnClickListener(v -> finishAffinity());

        // Sign up button
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

    // Authenticate the user
    private void authenticateUser(String username, String password) {
        executorService.execute(() -> {
            GeneralUser generalUser = generalUserDao.authenticateUser(username, password);

            runOnUiThread(() -> {
                if (generalUser != null) {
                    // User authenticated successfully, navigate to home page
                    Intent intent = new Intent(GeneralActivityLogin.this, GeneralActivityHome.class);
                    startActivity(intent);
                    finish(); // Close the login activity
                } else {
                    // Invalid credentials, show an error toast
                    Toast.makeText(GeneralActivityLogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    usernameEditText.getText().clear();
                    passwordEditText.getText().clear();
                }
            });
        });
    }
}