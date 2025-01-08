package com.example.madassignment.symptom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madassignment.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SymptomHistoryAdapter extends RecyclerView.Adapter<SymptomHistoryAdapter.ViewHolder> {
    private List<SymptomEntity> symptoms;

    public SymptomHistoryAdapter(List<SymptomEntity> symptoms) {
        this.symptoms = symptoms;
    }

    // Method to get the list of symptoms
    public List<SymptomEntity> getSymptoms() {
        return symptoms;
    }

    public void updateData(List<SymptomEntity> newSymptoms) {
        this.symptoms = newSymptoms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.symptom_item_symptom_history_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SymptomEntity symptom = symptoms.get(position);

        // Set the symptom name
        holder.symptomNameTextView.setText(symptom.getName());

        // Format the date and set it
        if (symptom.getDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            holder.symptomDateTextView.setText(dateFormat.format(symptom.getDate()));
        } else {
            holder.symptomDateTextView.setText("Date not available");
        }
    }

    @Override
    public int getItemCount() {
        return symptoms != null ? symptoms.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView symptomNameTextView; // For displaying symptom name
        TextView symptomDateTextView; // For displaying symptom date

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomNameTextView = itemView.findViewById(R.id.symptomNameTextView);
            symptomDateTextView = itemView.findViewById(R.id.symptomDateTextView);
        }
    }
}
