package com.example.madassignment.education;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madassignment.R;

import java.util.ArrayList;
import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder> {
    Context context;
    List<EducationItem> educationItemList;
    LayoutInflater layoutInflater;

    public EducationAdapter(Context context, List<EducationItem> educationItemList) {
        this.context = context;
        this.educationItemList = educationItemList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.education_item, parent, false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        EducationItem currentItem = educationItemList.get(position);
        holder.imageIV.setImageResource(currentItem.getImage());
        holder.iconIV.setImageResource(currentItem.getIcon());
        holder.nameTV.setText(currentItem.getName());
        holder.descriptionTV.setText(currentItem.getDescription());
        holder.symptomsTV.setText(currentItem.getSymptoms());
        holder.treatmentTV.setText(currentItem.getTreatment());

        holder.itemView.setOnClickListener(v-> {
            Intent intent = new Intent(context, EducationActivityDetailed.class);
            intent.putExtra("name", educationItemList.get(position).getName());
            intent.putExtra("description", educationItemList.get(position).getDescription());
            intent.putExtra("symptoms", educationItemList.get(position).getSymptoms());
            intent.putExtra("treatmentTV", educationItemList.get(position).getTreatment());
            intent.putExtra("image", educationItemList.get(position).getImage());
            intent.putExtra("icon", educationItemList.get(position).getIcon());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return educationItemList.size();
    }

    public void updateList(ArrayList<EducationItem> filteredList) {
        educationItemList = filteredList;
        notifyDataSetChanged();
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, descriptionTV, symptomsTV, treatmentTV;
        ImageView imageIV, iconIV;

        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIV = itemView.findViewById(R.id.detailedImage);
            iconIV = itemView.findViewById(R.id.icon);
            nameTV = itemView.findViewById(R.id.listName);
            descriptionTV = itemView.findViewById(R.id.detailedDescription);
            symptomsTV = itemView.findViewById(R.id.detailedSymptoms);
            treatmentTV = itemView.findViewById(R.id.detailedTreatment);
        }
    }
}