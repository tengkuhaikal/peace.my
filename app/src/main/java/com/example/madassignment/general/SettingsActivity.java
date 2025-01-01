package com.example.madassignment.general;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout btnSettingsFavourites;
    private LinearLayout btnSettingsMedicalHistory;
    private LinearLayout btnSettingsAccount;
    private LinearLayout btnSettingsPrivacy;
    private LinearLayout btnSettingsNotifications;
    private LinearLayout btnSettingsStorage;
    private LinearLayout btnSettingsHelp;
    private LinearLayout btnSettingsLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnSettingsFavourites = findViewById(R.id.BtnSettingsFavourites);
        btnSettingsMedicalHistory = findViewById(R.id.BtnSettingsMedicalHistory);
        btnSettingsAccount = findViewById(R.id.BtnSettingsAccount);
        btnSettingsPrivacy = findViewById(R.id.BtnSettingsPrivacy);
        btnSettingsNotifications = findViewById(R.id.BtnSettingsNotifications);
        btnSettingsStorage = findViewById(R.id.BtnSettingsStorage);
        btnSettingsHelp = findViewById(R.id.BtnSettingsHelp);
        btnSettingsLogOut = findViewById(R.id.BtnSettingsLogOut);

        btnSettingsFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoritesClicked();
            }
        });

        btnSettingsMedicalHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMedicalHistoryClicked();
            }
        });

        btnSettingsAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAccountClicked();
            }
        });

        btnSettingsPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPrivacyClicked();
            }
        });

        btnSettingsNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNotificationsClicked();
            }
        });

        btnSettingsStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStorageClicked();
            }
        });

        btnSettingsHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHelpClicked();
            }
        });

        btnSettingsLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogOutClicked();
            }
        });
    }

    // Favourites button
    public void onFavoritesClicked() {
        // Implement your functionality here
        Toast.makeText(this, "Favorites clicked", Toast.LENGTH_SHORT).show();
    }

    // Medical history button
    public void onMedicalHistoryClicked() {
        // Implement your functionality here
        Toast.makeText(this, "Medical History clicked", Toast.LENGTH_SHORT).show();
    }

    // Account button
    public void onAccountClicked() {
        // Implement your functionality here
        Toast.makeText(this, "Account clicked", Toast.LENGTH_SHORT).show();
    }

    // Privacy button
    public void onPrivacyClicked() {
        // Implement your functionality here
        Toast.makeText(this, "Privacy clicked", Toast.LENGTH_SHORT).show();
    }

    // Notifications button
    public void onNotificationsClicked() {
        // Implement your functionality here
        Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show();
    }

    // Storage button
    public void onStorageClicked() {
        // Implement your functionality here
        Toast.makeText(this, "Storage clicked", Toast.LENGTH_SHORT).show();
    }

    // Help button
    public void onHelpClicked() {
        // Implement your functionality here
        Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show();
    }

    // Log out button
    public void onLogOutClicked() {
        // Implement your functionality here
        Toast.makeText(this, "Log Out clicked", Toast.LENGTH_SHORT).show();
    }
}