package com.example.restoanchik.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.restoanchik.data.model.RestaurantModel;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    List<RestaurantModel> getRestaurantAll();

    @Query("Select * FROM restaurants WHERE id LIKE :restaurantId")
    RestaurantModel getRestaurantById(int restaurantId);

    @Insert
    void insertAll(RestaurantModel... restaurants);
    @Insert
    void insertAllAsync(RestaurantModel... restaurants);
    @Delete
    void delete(RestaurantModel restaurants);
    @Query("DELETE FROM restaurants")
    void deleteAll();
}
