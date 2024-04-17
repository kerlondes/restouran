package com.example.restoanchik.presentation.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Pair;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoanchik.R;
import com.example.restoanchik.domain.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFragment extends Fragment {
    private final RestaurantViewModel viewModel = new RestaurantViewModel();

    private int restaurantId = 0;
    private int lastSelectedSlot = 0;

    private RecyclerView recyclerView;
    private ImageView image;
    private TextView title;
    private int position;
    private  TextView address;
    private TextView description;
    private TextView workTime;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null){
            restaurantId = arguments.getInt(ARG_ID, 0);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_restaurant, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.slots_recycler_view);
        image = view.findViewById(R.id.restaurant_image_view);
        title = view.findViewById(R.id.restaurant_title);
        address = view.findViewById(R.id.restaurant_address);
        description = view.findViewById(R.id.restaurant_description);
        workTime = view.findViewById(R.id.work_time);
        observeViewModel();
        viewModel.start(restaurantId);
    }
    private void observeViewModel(){
        viewModel.getRestaurantData().observe(
                getViewLifecycleOwner(),
                this::applyData
        );
    }
    private Object applyData(Restaurant restaurant){
        if (getContext() !=null){
            int resourceId = getResources().getIdentifier(
                    restaurant.imageUrl, "drawable",
                    getContext().getPackageName()
            );
        }
        title.setText(restaurant.name);
        address.setText(restaurant.address);
        description.setText(restaurant.description);
        workTime.setText(restaurant.workingTime);
        recyclerView = getView().findViewById(R.id.recyclerView);
        return null;
    }
    private List<Pair<String,Boolean>> gemerateSlotList(){
        List<Pair<String,Boolean>> slotList = new ArrayList<>();
        slotList.add(new Pair<>("09:00", true));
        slotList.add(new Pair<>("11:00", false));
        slotList.add(new Pair<>("13:00", false));
        slotList.add(new Pair<>("14:00", false));
        slotList.add(new Pair<>("15:00", false));
        slotList.add(new Pair<>("16:00", false));
        slotList.add(new Pair<>("18:00", false));
        slotList.add(new Pair<>("20:00", false));
        slotList.add(new Pair<>("22:00", false));
        return slotList;
    }
    public static final String ARG_ID = "restaurant_id";

    public static RestaurantFragment newInstance(int param1){
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }
}
