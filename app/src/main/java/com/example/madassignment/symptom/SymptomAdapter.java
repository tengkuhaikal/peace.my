package com.example.madassignment.symptom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madassignment.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.ViewHolder> {

    private List<SymptomEntity> symptoms;
    private List<String> selectedSymptoms = new ArrayList<>();

    public SymptomAdapter(List<SymptomEntity> symptoms) {
        this.symptoms = symptoms;
    }

    public void updateSymptoms(List<SymptomEntity> newSymptoms) {
        this.symptoms.clear(); // Clear the current list
        this.symptoms.addAll(newSymptoms); // Add the new symptoms
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    public List<String> getSelectedSymptoms() {
        return selectedSymptoms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptom_item_symptom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SymptomEntity symptom = symptoms.get(position);
        holder.symptomName.setText(symptom.getName());

        // Manage selection state
        holder.itemView.setOnClickListener(v -> {
            if (selectedSymptoms.contains(symptom.getName())) {
                selectedSymptoms.remove(symptom.getName());
                holder.itemView.setBackgroundResource(R.color.secondary_btn_color); // Unselected state
            } else {
                selectedSymptoms.add(symptom.getName());
                holder.itemView.setBackgroundResource(R.color.pressed_secondary_btn_color); // Selected state
            }
        });

        // Update UI for selected/unselected state
        if (selectedSymptoms.contains(symptom.getName())) {
            holder.itemView.setBackgroundResource(R.color.pressed_secondary_btn_color);
        } else {
            holder.itemView.setBackgroundResource(R.color.secondary_btn_color);
        }
    }

    @Override
    public int getItemCount() {
        return symptoms != null ? symptoms.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView symptomName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomName = itemView.findViewById(R.id.tvSymptomName);
        }
    }
}
