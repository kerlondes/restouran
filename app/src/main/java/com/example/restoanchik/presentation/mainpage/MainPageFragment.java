package com.example.restoanchik.presentation.mainpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoanchik.App;
import com.example.restoanchik.R;
import com.example.restoanchik.data.repository.DatabaseRepository;
import com.example.restoanchik.domain.Restaurant;

import java.util.List;

public class MainPageFragment extends Fragment {
    private static final String ARG_ID = "restaurant_id";
    private RecyclerView recyclerView;
    private MainPageViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize viewModel before the fragment view is created
        DatabaseRepository databaseRepository = App.getDatabaseRepository();
        viewModel = new MainPageViewModel(databaseRepository);

        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        observeViewModel();
        viewModel.start();
    }
    private void observeViewModel(){
        viewModel.getRestaurantsData().observe(getViewLifecycleOwner(), this::applyData);
    }
    private void applyData(List<Restaurant> restaurantsList){
        recyclerView.setAdapter(new RestaurantsAdapter(restaurantsList, restaurant -> navigateToRestaurant(restaurant.id)));
    }
    private void navigateToRestaurant(int restaurantId){
        if(getView() != null){
            Bundle bundle = bundleOf(ARG_ID, restaurantId);
            Navigation.findNavController(getView()).navigate(R.id.navigate_to_restaurant_fragment, bundle);
        }
    }
    private Bundle bundleOf(String argId, int restaurantId){
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_ID, restaurantId);
        return bundle;
    }
}
