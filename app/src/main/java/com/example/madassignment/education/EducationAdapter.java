package com.example.madassignment.education;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madassignment.R;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder> {
    Context context;
    List<EducationItem> educationItem;
    LayoutInflater layoutInflater;

    public EducationAdapter(Context context, List<EducationItem> educationItem) {
        this.context = context;
        this.educationItem = educationItem;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.education_item, parent, false);
        EducationViewHolder educationViewHolder = new EducationViewHolder(view);
        return educationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        holder.image.setImageResource(educationItem.get(position).getImage());
        holder.icon.setImageResource(educationItem.get(position).getIcon());
        holder.name.setText(educationItem.get(position).getName());
        holder.description.setText(educationItem.get(position).getDescription());
        holder.symptoms.setText(educationItem.get(position).getSymptoms());
        holder.treatment.setText(educationItem.get(position).getTreatment());
    }

    @Override
    public int getItemCount() {
        return educationItem.size();
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, symptoms, treatment;
        ImageView image, icon;

        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.detailedImage);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.listName);
            description = itemView.findViewById(R.id.detailedDescription);
            symptoms = itemView.findViewById(R.id.detailedSymptoms);
            treatment = itemView.findViewById(R.id.detailedTreatment);
        }
    }
}