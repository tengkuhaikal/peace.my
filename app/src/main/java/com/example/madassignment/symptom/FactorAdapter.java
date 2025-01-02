package com.example.madassignment.symptom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madassignment.R;

import java.util.List;

public class FactorAdapter extends RecyclerView.Adapter<FactorAdapter.FactorViewHolder> {
    private final List<String> factorsList; // Data source
    private int selectedPosition = -1; // To track selected RadioButton

    public FactorAdapter(List<String> factorsList) {
        this.factorsList = factorsList;
    }

    @NonNull
    @Override
    public FactorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.symptom_item_factor, parent, false);
        return new FactorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactorViewHolder holder, int position) {
        String factor = factorsList.get(position);
        holder.radioButton.setText(factor);

        // Manage selected state
        holder.radioButton.setChecked(position == selectedPosition);

        holder.radioButton.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged(); // Refresh to update selected state
        });
    }

    @Override
    public int getItemCount() {
        return factorsList.size();
    }

    public String getSelectedFactor() {
        return selectedPosition != -1 ? factorsList.get(selectedPosition) : null;
    }

    static class FactorViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;

        FactorViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.factorRadioButton);
        }
    }
}

