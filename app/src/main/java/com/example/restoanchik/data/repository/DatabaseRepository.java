package com.example.restoanchik.data.repository;

import android.os.AsyncTask;

import com.example.restoanchik.data.database.GetTableDatabase;
import com.example.restoanchik.data.model.RestaurantModel;
import com.example.restoanchik.domain.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {
    private final GetTableDatabase database;
    public DatabaseRepository(GetTableDatabase database) {this.database = database;}
    public void getRestaurantByIdAsync(int id, Consumer<Restaurant> callback){
        new GetRestaurantAsyncTask(database, id, callback).execute();
    }
    public void getAllRestaurantsAsync(Consumer<List<Restaurant>>callback){
        new GetAllRestaurentsAsyncTask(database,callback).execute();
    }
    public void deleteAll(){
        database.restaurantDao().deleteAll();
    }
    public void insertRestaurantsAsync(RestaurantModel... restaurantModels){
        new InsertRestaurantAsyncTask(database).execute(restaurantModels);
    }
    private static class GetRestaurantAsyncTask extends AsyncTask<Void,Void,Restaurant>{
        private final GetTableDatabase database;
        private final int id;
        private final Consumer<Restaurant> callback;
        public GetRestaurantAsyncTask(GetTableDatabase database, int id, Consumer<Restaurant> callback){
            this.database =database;
            this.id = id;
            this.callback = callback;
        }
        @Override
        protected Restaurant doInBackground(Void... voids){
            return database.restaurantDao().getRestaurantById(id).toDomainModel();
        }
        @Override
        protected void onPostExecute(Restaurant restaurant) { callback.accept(restaurant);}
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private final GetTableDatabase db;

        DeleteAllAsyncTask(GetTableDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db.restaurantDao().deleteAll();
            return null;
        }
    }
    public void deleteAllAsync() {
        new DeleteAllAsyncTask(database).execute();
    }
    private static class GetAllRestaurentsAsyncTask extends AsyncTask<Void, Void, List<Restaurant>>{
        private final GetTableDatabase database;
        private final Consumer<List<Restaurant>> callback;
        public GetAllRestaurentsAsyncTask(GetTableDatabase database, Consumer<List<Restaurant>> callback){
            this.database =database;
            this.callback = callback;
        }
        @Override
        protected List<Restaurant> doInBackground(Void... voids){
            List<RestaurantModel> restaurantModels = database.restaurantDao().getRestaurantAll();
            List<Restaurant> restaurants = new ArrayList<>();

            for (RestaurantModel model : restaurantModels){
                restaurants.add(model.toDomainModel());
            }
            return restaurants;
        }
        @Override
        protected void onPostExecute(List<Restaurant> restaurants) {callback.accept(restaurants);}
    }
    private static class InsertRestaurantAsyncTask extends AsyncTask<RestaurantModel, Void, Void>{
        private final GetTableDatabase database;
        public InsertRestaurantAsyncTask(GetTableDatabase database){ this.database = database;}
        @Override
        protected Void doInBackground(RestaurantModel... restaurantModels){
            database.restaurantDao().insertAll(restaurantModels);
            return null;
        }
    }
    public interface Consumer<T>{
        void accept(T t);
    }
}
