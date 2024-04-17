package com.example.restoanchik.data.database;

import androidx.room.RoomDatabase;
import android.content.Context;
import android.util.Log;
import androidx.room.Database;
import androidx.room.Room;

import com.example.restoanchik.data.model.RestaurantModel;

@Database(entities = {RestaurantModel.class}, version = 1)
public abstract class GetTableDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();
    private static volatile GetTableDatabase instance;

    public static GetTableDatabase getInstance(Context context){
        synchronized (GetTableDatabase.class){
            if (instance == null){
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        GetTableDatabase.class, "get-table-database"
                ).build();
                Log.d("GetTableDatabase", "Database instance created successfully");
            }
            return instance;
        }
    }
}
