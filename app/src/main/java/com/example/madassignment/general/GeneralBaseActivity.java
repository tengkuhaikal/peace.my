package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.madassignment.R;

public class GeneralBaseActivity extends AppCompatActivity {

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_menu);

        // Initialize navigation icons
        ImageView homeIcon = findViewById(R.id.nav_home);
        ImageView searchIcon = findViewById(R.id.nav_search);
        ImageView notificationIcon = findViewById(R.id.nav_notification);
        ImageView settingsIcon = findViewById(R.id.nav_settings);

        // Check which page is active
        String activePage = getIntent().getStringExtra("active_page");
        if ("home".equals(activePage)) {
            setSelectedButton(homeIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            loadFragment(new GeneralHomeFragment());
        } else if ("search".equals(activePage)) {
            setSelectedButton(searchIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            loadFragment(new GeneralSearchFragment());
        } else if ("notification".equals(activePage)) {
            setSelectedButton(notificationIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            loadFragment(new GeneralNotificationFragment());
        } else if ("settings".equals(activePage)) {
            setSelectedButton(settingsIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            loadFragment(new GeneralSettingsFragment());
        } else {
            // Default to home
            setSelectedButton(homeIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            loadFragment(new GeneralHomeFragment());
        }

        // Set up listeners for each icon
        homeIcon.setOnClickListener(v -> {
            setSelectedButton(homeIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            navigateTo(new GeneralHomeFragment());
        });

        searchIcon.setOnClickListener(v -> {
            setSelectedButton(searchIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            navigateTo(new GeneralSearchFragment());
        });

        notificationIcon.setOnClickListener(v -> {
            setSelectedButton(notificationIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            navigateTo(new GeneralNotificationFragment());
        });

        settingsIcon.setOnClickListener(v -> {
            setSelectedButton(settingsIcon, homeIcon, searchIcon, notificationIcon, settingsIcon);
            navigateTo(new GeneralSettingsFragment());
        });
    }

    private void navigateTo(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
    }

    private void setSelectedButton(ImageView selectedButton, ImageView... buttons) {
        for (ImageView button : buttons) {
            button.setSelected(false); // Reset all buttons to default state
        }
        selectedButton.setSelected(true); // Highlight the selected button
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment); // Replace the existing fragment
            transaction.commit();
        }
    }
}
