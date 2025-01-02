package com.example.madassignment.general;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;

import java.util.Calendar;
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

            if (!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() &&
                    !password.isEmpty() && password.equals(confirmPassword)) {
                showStepTwo(firstName, lastName, username, password);
            } else {
                Toast.makeText(GeneralActivitySignUp.this, "Please fill all fields and ensure passwords match", Toast.LENGTH_SHORT).show();
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
                String formattedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
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

            if (!address.isEmpty() && !postcode.isEmpty() && !city.isEmpty() &&
                    !state.equals("Select State") && !dob.isEmpty() && !phone.isEmpty()) {
                executorService.execute(() -> {
                    GeneralUser generalUser = new GeneralUser(username, password, firstName, lastName, address, address2, postcode, city, state, dob, phone);
                    GeneralAppDatabase.getDatabase(getApplicationContext()).userDao().insertUser(generalUser);
                });

                runOnUiThread(() -> {
                    Toast.makeText(GeneralActivitySignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GeneralActivitySignUp.this, GeneralActivityLogin.class));
                    finish();
                });
            } else {
                Toast.makeText(GeneralActivitySignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}