package com.example.madassignment.education;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.madassignment.R;

import java.util.ArrayList;

public class EducationListAdapter extends ArrayAdapter<EducationListData>{

    public EducationListAdapter(@NonNull Context context, ArrayList<EducationListData> dataArrayList) {
        super(context, R.layout.education_list_item, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        EducationListData educationListData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.education_list_item, parent, false);
        }

        ImageView listImage = view.findViewById(R.id.listIconImage);
        TextView listName = view.findViewById(R.id.listName);

        listImage.setImageResource(educationListData.icon);
        listName.setText(educationListData.name);

        return view;
    }
}