package com.example.restoanchik.presentation.restaurant;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoanchik.R;

import kotlin.Pair;

public class SlotViewHolder extends RecyclerView.ViewHolder {
    private final TextView slotTime;
    public SlotViewHolder(View itemView) {
        super(itemView);
        slotTime = itemView.findViewById(R.id.slot_time);
    }
    public void bind(Pair<String,Boolean> slot){
        slotTime.setText(slot.getClass().getModifiers());
    }
}
