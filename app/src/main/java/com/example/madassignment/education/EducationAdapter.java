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
        holder.iconIV.setImageResource(currentItem.getIcon());
        holder.nameTV.setText(currentItem.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EducationActivityDetailed.class);
            intent.putExtra("name", educationItemList.get(position).getName());
            intent.putExtra("description", currentItem.getDescription() != null ? currentItem.getDescription() : "");
            intent.putExtra("symptoms", currentItem.getSymptoms() != null ? currentItem.getSymptoms() : "");
            intent.putExtra("treatment", currentItem.getTreatment() != null ? currentItem.getTreatment() : "");
            intent.putExtra("image", currentItem.getImage());
            intent.putExtra("icon", currentItem.getIcon());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return educationItemList.size();
    }

    public void updateList(ArrayList<EducationItem> filteredList) {
        this.educationItemList = filteredList;
        notifyDataSetChanged();
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        ImageView iconIV;

        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            iconIV = itemView.findViewById(R.id.icon);
            nameTV = itemView.findViewById(R.id.listName);
        }
    }
}