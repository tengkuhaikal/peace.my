package com.example.madassignment.general;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.madassignment.R;

public class GeneralSettingsFragment extends Fragment {

    private LinearLayout btnSettingsFavourites;
    private LinearLayout btnSettingsMedicalHistory;
    private LinearLayout btnSettingsAccount;
    private LinearLayout btnSettingsPrivacy;
    private LinearLayout btnSettingsNotifications;
    private LinearLayout btnSettingsStorage;
    private LinearLayout btnSettingsHelp;
    private LinearLayout btnSettingsLogOut;

    public GeneralSettingsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.general_activity_settings, container, false);

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

        return rootView;
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
        Toast.makeText(getContext(), "Log Out clicked", Toast.LENGTH_SHORT).show();
    }
}
