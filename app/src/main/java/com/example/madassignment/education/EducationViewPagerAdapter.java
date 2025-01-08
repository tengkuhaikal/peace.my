package com.example.madassignment.education;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class EducationViewPagerAdapter extends FragmentStateAdapter {
    public EducationViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new EducationFragmentAll();
            case 1:
                return new EducationFragmentBookmark();
            default:
                return new EducationFragmentAll();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}