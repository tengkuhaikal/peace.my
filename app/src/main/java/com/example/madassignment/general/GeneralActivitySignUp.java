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

        EditText firstNameEditText = findViewById(R.id.PTSignUpFirstName);
        EditText lastNameEditText = findViewById(R.id.PTSignUpLastName);
        EditText usernameEditText = findViewById(R.id.PTSignUpUsername);
        EditText passwordEditText = findViewById(R.id.PTSignUpPassword);
        EditText confirmPasswordEditText = findViewById(R.id.PTSignUpConfirmPassword);
        Button nextButton = findViewById(R.id.BtnSignUpNext);
        Button cancelButton = findViewById(R.id.BtnSignUpCancel);

        cancelButton.setOnClickListener(v -> {
            startActivity(new Intent(GeneralActivitySignUp.this, GeneralActivityLogin.class));
            finish();
        });

        nextButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                    TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(GeneralActivitySignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(GeneralActivitySignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                showStepTwo(firstName, lastName, username, password);
            }
        });
    }

    private void showStepTwo(String firstName, String lastName, String username, String password) {
        setContentView(R.layout.general_activity_sign_up_2);

        EditText addressEditText = findViewById(R.id.PASignUp2Address);
        EditText address2EditText = findViewById(R.id.PASignUp2Address2);
        EditText postcodeEditText = findViewById(R.id.TNSignUp2Postcode);
        EditText cityEditText = findViewById(R.id.PTSignUp2City);
        Spinner stateSpinner = findViewById(R.id.SSignUp2States);
        TextView dobTextView = findViewById(R.id.TDSignUp2DOB);
        EditText phoneEditText = findViewById(R.id.TPSignUp2PhoneNumber);
        Button nextButton = findViewById(R.id.BtnSignUp2Next);
        Button cancelButton = findViewById(R.id.BtnSignUp2Cancel);

        String[] states = getResources().getStringArray(R.array.states_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);

        dobTextView.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(GeneralActivitySignUp.this, (view, selectedYear, selectedMonth, selectedDay) -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(new java.util.Date(selectedYear - 1900, selectedMonth, selectedDay));
                dobTextView.setText(formattedDate);
            }, year, month, day).show();
        });

        cancelButton.setOnClickListener(v -> {
            startActivity(new Intent(GeneralActivitySignUp.this, GeneralActivityLogin.class));
            finish();
        });

        nextButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString();
            String address2 = address2EditText.getText().toString();
            String postcode = postcodeEditText.getText().toString();
            String city = cityEditText.getText().toString();
            String state = stateSpinner.getSelectedItem().toString();
            String dob = dobTextView.getText().toString();
            String phone = phoneEditText.getText().toString();

            if (TextUtils.isEmpty(address) || TextUtils.isEmpty(postcode) || TextUtils.isEmpty(city) ||
                    state.equals("Select State") || TextUtils.isEmpty(dob) || TextUtils.isEmpty(phone)) {
                Toast.makeText(GeneralActivitySignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!isValidPhoneNumber(phone)) {
                Toast.makeText(GeneralActivitySignUp.this, "Invalid phone number format", Toast.LENGTH_SHORT).show();
            } else {
                executorService.execute(() -> {
                    try {
                        GeneralUser user = new GeneralUser(username, password, firstName, lastName, address, address2, postcode, city, state, dob, phone);
                        GeneralAppDatabase.getDatabase(getApplicationContext()).generalUserDao().insertUser(user);
                        Log.d("SignUpActivity", "User inserted successfully: " + username);  // Log the inserted username
                    } catch (Exception e) {
                        Log.e("SignUpActivity", "Error inserting user: ", e);  // Log any error
                    }
                });

                runOnUiThread(() -> {
                    Toast.makeText(GeneralActivitySignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GeneralActivitySignUp.this, GeneralActivityLogin.class));
                    finish();
                });
            }
        });
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}"); // Basic check for a 10-digit phone number
    }
}
