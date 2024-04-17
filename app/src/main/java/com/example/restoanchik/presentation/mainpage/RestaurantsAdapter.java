package com.example.restoanchik.presentation.mainpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.restoanchik.domain.Restaurant;

import androidx.recyclerview.widget.RecyclerView;

import com.example.restoanchik.R;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsViewHolder> {
    private final List<Restaurant> restaurantsList;
    private final OnClickListener onRestaurantClickListener;

    public RestaurantsAdapter(List<Restaurant> restaurantsList, OnClickListener onRestaurantClickListener){
        this.restaurantsList = restaurantsList;
        this.onRestaurantClickListener = onRestaurantClickListener;
    }
    @Override
    public RestaurantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
        return new RestaurantsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RestaurantsViewHolder holder, int position){
        holder.itemView.setOnClickListener(view -> onRestaurantClickListener.onClick(restaurantsList.get(position)));
        holder.bind(restaurantsList.get(position));
    }
    @Override
    public int getItemCount() { return restaurantsList.size();}
    public  interface OnClickListener{
        void onClick(Restaurant restaurant);
    }
}
