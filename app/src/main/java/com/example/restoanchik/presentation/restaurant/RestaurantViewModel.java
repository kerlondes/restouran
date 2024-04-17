package com.example.restoanchik.presentation.restaurant;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restoanchik.App;
import com.example.restoanchik.data.repository.DatabaseRepository;
import com.example.restoanchik.domain.Restaurant;

public class RestaurantViewModel extends ViewModel {
    private  final MutableLiveData<Restaurant> restaurantData = new MutableLiveData<>();
    private final DatabaseRepository databaseRepository = App.getDatabaseRepository();
    public LiveData<Restaurant> getRestaurantData(){
        return restaurantData;
    }
    public void start(int restaurantId){
        getRestaurantByIdAsync(restaurantId);
    }
    private void getRestaurantByIdAsync(int restaurantId){
        databaseRepository.getRestaurantByIdAsync(restaurantId, new DatabaseRepository.Consumer<Restaurant>(){
            @Override
            public void accept(Restaurant restaurant){
                restaurantData.postValue(restaurant);
            }
        });
    }
}
