package com.example.madassignment.general;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.madassignment.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeneralActivitySignUp extends AppCompatActivity {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showStepOne();
    }

    private void showStepOne() {
        setContentView(R.layout.general_activity_sign_up_1);

        ImageButton backButton = findViewById(R.id.IBSignUpBackArrow);
        EditText firstNameEditText = findViewById(R.id.PTSignUpFirstName);
        EditText lastNameEditText = findViewById(R.id.PTSignUpLastName);
        EditText usernameEditText = findViewById(R.id.PTSignUpUsername);
        EditText passwordEditText = findViewById(R.id.PTSignUpPassword);
        EditText confirmPasswordEditText = findViewById(R.id.PTSignUpConfirmPassword);
        Button nextButton = findViewById(R.id.BtnSignUpNext);
        Button cancelButton = findViewById(R.id.BtnSignUpCancel);

        Intent intent = getIntent();
        if (intent != null) {
            populateStepOneFields(intent, firstNameEditText, lastNameEditText, usernameEditText, passwordEditText, confirmPasswordEditText);
        }

        backButton.setOnClickListener(v -> navigateToLogin());

        cancelButton.setOnClickListener(v -> navigateToLogin());

        nextButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (validateStepOneFields(firstName, lastName, username, password, confirmPassword)) {
                showStepTwo(firstName, lastName, username, password);
            }
        });
    }

    private void populateStepOneFields(Intent intent, EditText firstNameEditText, EditText lastNameEditText, EditText usernameEditText, EditText passwordEditText, EditText confirmPasswordEditText) {
        firstNameEditText.setText(intent.getStringExtra("firstName"));
        lastNameEditText.setText(intent.getStringExtra("lastName"));
        usernameEditText.setText(intent.getStringExtra("username"));
        String password = intent.getStringExtra("password");
        if (password != null) {
            passwordEditText.setText(password);
            confirmPasswordEditText.setText(password);
        }
    }

    private boolean validateStepOneFields(String firstName, String lastName, String username, String password, String confirmPassword) {
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showStepTwo(String firstName, String lastName, String username, String password) {
        setContentView(R.layout.general_activity_sign_up_2);

        ImageButton backButton = findViewById(R.id.IBSignUp2BackArrow);
        EditText addressEditText = findViewById(R.id.PASignUp2Address);
        EditText address2EditText = findViewById(R.id.PASignUp2Address2);
        EditText postcodeEditText = findViewById(R.id.TNSignUp2Postcode);
        EditText cityEditText = findViewById(R.id.PTSignUp2City);
        Spinner stateSpinner = findViewById(R.id.SSignUp2States);
        TextView dobTextView = findViewById(R.id.TDSignUp2DOB);
        EditText phoneEditText = findViewById(R.id.TPSignUp2PhoneNumber);
        Button nextButton = findViewById(R.id.BtnSignUp2Next);
        Button cancelButton = findViewById(R.id.BtnSignUp2Cancel);

        setupStateSpinner(stateSpinner);
        setupDOBPicker(dobTextView);

        backButton.setOnClickListener(v -> navigateBackToStepOne(firstName, lastName, username, password));
        cancelButton.setOnClickListener(v -> navigateToLogin());

        nextButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString();
            String address2 = address2EditText.getText().toString();
            String postcode = postcodeEditText.getText().toString();
            String city = cityEditText.getText().toString();
            String state = stateSpinner.getSelectedItem().toString();
            String dob = dobTextView.getText().toString();
            String phone = phoneEditText.getText().toString();

            if (validateStepTwoFields(address, postcode, city, state, dob, phone)) {
                registerUser(firstName, lastName, username, password, address, address2, postcode, city, state, dob, phone);
            }
        });
    }

    private void setupStateSpinner(Spinner stateSpinner) {
        String[] states = getResources().getStringArray(R.array.states_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
    }

    private void setupDOBPicker(TextView dobTextView) {
        dobTextView.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                        new java.util.Date(year - 1900, month, dayOfMonth)
                );
                dobTextView.setText(formattedDate);
            },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private boolean validateStepTwoFields(String address, String postcode, String city, String state, String dob, String phone) {
        if (TextUtils.isEmpty(address) || TextUtils.isEmpty(postcode) || TextUtils.isEmpty(city) ||
                state.equals("Select State") || TextUtils.isEmpty(dob) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isValidPhoneNumber(phone)) {
            Toast.makeText(this, "Invalid phone number format", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}"); // Basic check for a 10-digit phone number
    }

    private void registerUser(String firstName, String lastName, String username, String password, String address, String address2, String postcode, String city, String state, String dob, String phone) {
        executorService.execute(() -> {
            try {
                GeneralUser user = new GeneralUser(username, password, firstName, lastName, address, address2, postcode, city, state, dob, phone);
                GeneralAppDatabase.getDatabase(getApplicationContext()).generalUserDao().insertUser(user);
                Log.d("SignUpActivity", "User inserted successfully: " + username);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    navigateToLogin();
                });
            } catch (Exception e) {
                Log.e("SignUpActivity", "Error inserting user: ", e);
            }
        });
    }

    private void navigateToLogin() {
        startActivity(new Intent(this, GeneralActivityLogin.class));
        finish();
    }

    private void navigateBackToStepOne(String firstName, String lastName, String username, String password) {
        Intent intent = new Intent(this, GeneralActivitySignUp.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }
}
