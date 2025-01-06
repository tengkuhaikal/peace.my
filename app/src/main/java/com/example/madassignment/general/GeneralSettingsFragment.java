package com.example.madassignment.general;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.madassignment.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeneralSettingsFragment extends Fragment {

    private TextView tvSettingsName;
    private LinearLayout btnSettingsFavourites;
    private LinearLayout btnSettingsMedicalHistory;
    private LinearLayout btnSettingsAccount;
    private LinearLayout btnSettingsPrivacy;
    private LinearLayout btnSettingsNotifications;
    private LinearLayout btnSettingsStorage;
    private LinearLayout btnSettingsHelp;
    private LinearLayout btnSettingsLogOut;

    private GeneralAppDatabase database;
    private GeneralUserDao userDao;
    private ExecutorService executorService;

    public GeneralSettingsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = GeneralAppDatabase.getDatabase(requireContext());
        userDao = database.generalUserDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.general_activity_settings, container, false);

        tvSettingsName = rootView.findViewById(R.id.TVSettingsName);

        btnSettingsFavourites = rootView.findViewById(R.id.BtnSettingsFavourites);
        btnSettingsMedicalHistory = rootView.findViewById(R.id.BtnSettingsMedicalHistory);
        btnSettingsAccount = rootView.findViewById(R.id.BtnSettingsAccount);
        btnSettingsPrivacy = rootView.findViewById(R.id.BtnSettingsPrivacy);
        btnSettingsNotifications = rootView.findViewById(R.id.BtnSettingsNotifications);
        btnSettingsStorage = rootView.findViewById(R.id.BtnSettingsStorage);
        btnSettingsHelp = rootView.findViewById(R.id.BtnSettingsHelp);
        btnSettingsLogOut = rootView.findViewById(R.id.BtnSettingsLogOut);

        btnSettingsFavourites.setOnClickListener(v -> onFavoritesClicked());
        btnSettingsMedicalHistory.setOnClickListener(v -> onMedicalHistoryClicked());
        btnSettingsAccount.setOnClickListener(v -> onAccountClicked());
        btnSettingsPrivacy.setOnClickListener(v -> onPrivacyClicked());
        btnSettingsNotifications.setOnClickListener(v -> onNotificationsClicked());
        btnSettingsStorage.setOnClickListener(v -> onStorageClicked());
        btnSettingsHelp.setOnClickListener(v -> onHelpClicked());
        btnSettingsLogOut.setOnClickListener(v -> onLogOutClicked());

        fetchAndDisplayUserName();

        return rootView;
    }

    private void fetchAndDisplayUserName() {

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String loggedInUsername = sharedPreferences.getString("username", null);

        if (loggedInUsername != null) {
            executorService.execute(() -> {
                GeneralUser user = userDao.getUserByUsername(loggedInUsername);
                if (user != null) {
                    String fullName = user.getFirstName() + " " + user.getLastName();
                    requireActivity().runOnUiThread(() -> tvSettingsName.setText(fullName));
                } else {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show());
                }
            });
        } else {
            Toast.makeText(getContext(), "No logged-in user", Toast.LENGTH_SHORT).show();
        }
    }

    // Favourites button
    public void onFavoritesClicked() {
        Toast.makeText(getContext(), "Favorites clicked", Toast.LENGTH_SHORT).show();
    }

    // Medical history button
    public void onMedicalHistoryClicked() {
        Toast.makeText(getContext(), "Medical History clicked", Toast.LENGTH_SHORT).show();
    }

    // Account button
    public void onAccountClicked() {
        Toast.makeText(getContext(), "Account clicked", Toast.LENGTH_SHORT).show();
    }

    // Privacy button
    public void onPrivacyClicked() {
        Toast.makeText(getContext(), "Privacy clicked", Toast.LENGTH_SHORT).show();
    }

    // Notifications button
    public void onNotificationsClicked() {
        Toast.makeText(getContext(), "Notifications clicked", Toast.LENGTH_SHORT).show();
    }

    // Storage button
    public void onStorageClicked() {
        Toast.makeText(getContext(), "Storage clicked", Toast.LENGTH_SHORT).show();
    }

    // Help button
    public void onHelpClicked() {
        Toast.makeText(getContext(), "Help clicked", Toast.LENGTH_SHORT).show();
    }

    // Log out button
    public void onLogOutClicked() {
        // Show confirmation dialog
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirm Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Logout", (dialog, which) -> {
                    // Clear shared preferences
                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();

                    Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();

                    // Redirect to LoginActivity
                    Intent intent = new Intent(getActivity(), GeneralActivityLogin.class);
                    startActivity(intent);
                    requireActivity().finish();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Dismiss the dialog
                    dialog.dismiss();
                })
                .show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
