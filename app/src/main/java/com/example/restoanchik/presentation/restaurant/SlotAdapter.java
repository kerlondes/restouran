package com.example.restoanchik.presentation.restaurant;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.restoanchik.R;

import java.util.ArrayList;

import kotlin.Pair;

public class SlotAdapter extends RecyclerView.Adapter<SlotViewHolder> {
    private final ArrayList<Pair<String,Boolean>> slots;
    private final View.OnClickListener onSlotClickListener;
    public SlotAdapter(ArrayList<Pair<String,Boolean>> slots, View.OnClickListener onSlotClickListener){
        this.slots = slots;
        this.onSlotClickListener = onSlotClickListener;
    }
    @Override
    public SlotViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_list_item, parent, false);
        return new SlotViewHolder(view);
    }
    @Override
    public void onBindViewHolder(SlotViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSlotClickListener.onClick(v);
            }
        });
        holder.bind(slots.get(position));
    }
    @Override
    public int getItemCount(){ return slots.size();}
    public static class  OnClickListener{
        private final java.util.function.Consumer<Integer> clickListener;

        public OnClickListener(java.util.function.Consumer<Integer> clickListener){
            this.clickListener = clickListener;
        }
        public void onClick(int position) { clickListener.accept(position);}
    }
}
