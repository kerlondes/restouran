package com.example.restoanchik.presentation.mainpage;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.restoanchik.data.repository.DatabaseRepository;

import com.example.restoanchik.App;
import com.example.restoanchik.domain.Restaurant;

import java.util.Date;
import java.util.List;

public class MainPageViewModel extends ViewModel {
    private final DatabaseRepository databaseRepository;
    private final MutableLiveData<List<Restaurant>> restaurantsData = new MutableLiveData<>();

    public MainPageViewModel(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }
    public class MainPageViewModelFactory implements ViewModelProvider.Factory {
        private final DatabaseRepository databaseRepository;

        public MainPageViewModelFactory(DatabaseRepository databaseRepository) {
            this.databaseRepository = databaseRepository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(MainPageViewModel.class)) {
                return (T) new MainPageViewModel(databaseRepository);
            }
            throw new RuntimeException("Unknown view model class " + modelClass);
        }
    }
    public  MutableLiveData<List<Restaurant>> getRestaurantsData() { return restaurantsData;}
    public void start() { getRestaurantsListAsync();}
    private void getRestaurantsListAsync(){
        databaseRepository.getAllRestaurantsAsync(restaurants -> {
           restaurantsData.postValue(restaurants);
        });
    }

}
