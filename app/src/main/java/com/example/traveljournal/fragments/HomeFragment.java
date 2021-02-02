package com.example.traveljournal.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.AddTripActivity;
import com.example.traveljournal.EditTripActivity;
import com.example.traveljournal.MainActivity;
import com.example.traveljournal.R;
import com.example.traveljournal.Trip;
import com.example.traveljournal.TripDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {
    List<Trip> tripList;
    private RecyclerView recyclerView;
    private TripAdapter adapter;
    protected static final int ADD_REQUEST_CODE = 1;
    protected static final int EDIT_REQUEST_CODE = 2;
    protected static final int DELETE_REQUEST_CODE = 3;
    public static final String TRIP_STRING = "trip";
    public static final String POSITION_STRING = "position";
    public static final String TRIP_LIST_STRING = "trip list";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.recyclerView = view.findViewById(R.id.recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        loadData();
        this.adapter = new TripAdapter(tripList);
        this.recyclerView.setAdapter(this.adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), AddTripActivity.class), ADD_REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tripList);
        editor.putString(TRIP_LIST_STRING, json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(TRIP_LIST_STRING, null);
        Type type = new TypeToken<ArrayList<Trip>>() {
        }.getType();
        tripList = gson.fromJson(json, type);

        if (tripList == null) {
            tripList = new ArrayList<>();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Trip trip = data.getParcelableExtra(TRIP_STRING);
            if (trip != null) {
                System.out.println(trip);
                addTrip(trip);
            }
        }
        if (requestCode == EDIT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Trip trip = data.getParcelableExtra(TRIP_STRING);
            int position = data.getIntExtra(POSITION_STRING, 1);
            if (trip != null) {
                System.out.println(trip);
                updateTrip(trip, position);
            }
        }
        if (requestCode == DELETE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra(POSITION_STRING, 1);
            deleteTrip(position);
        }
    }

    public void addTrip(Trip trip) {
        this.tripList.add(trip);
        this.adapter.notifyItemInserted(tripList.size() - 1);
        saveData();
    }

    public void updateTrip(Trip trip, int position) {
        this.tripList.remove(position);
        this.tripList.add(position, trip);
        this.adapter.notifyItemChanged(position);
        saveData();
    }

    public void deleteTrip(int position) {
        this.tripList.remove(position);
        this.adapter.notifyItemRemoved(position);
        saveData();
    }

    private static class TripViewHolder extends RecyclerView.ViewHolder {
        private final TextView tripName;
        private final TextView destination;
        private final TextView price;
        private final TextView rating;


        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tripName = itemView.findViewById(R.id.tv_tripName);
            this.destination = itemView.findViewById(R.id.tv_destination);
            this.price = itemView.findViewById(R.id.tv_price);
            this.rating = itemView.findViewById(R.id.tv_rating);
        }

        public void bind(@NonNull Trip trip) {
            this.tripName.setText(trip.getTripName());
            this.destination.setText(trip.getDestination());
            this.price.setText(String.format("%s €", trip.getPrice()));
            this.rating.setText(String.format("%s stars", trip.getRating()));
        }
    }

    private class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {
        @NonNull
        private final List<Trip> tripList;

        public TripAdapter(@NonNull List<Trip> tripList) {
            this.tripList = tripList;
        }

        @NonNull
        @Override
        public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.layout_trip, parent, false);
            return new TripViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
            holder.bind(this.tripList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Trip trip = tripList.get(position);
                    Intent intent = new Intent(v.getContext(), TripDetailsActivity.class);
                    intent.putExtra(TRIP_STRING, trip);
                    intent.putExtra(POSITION_STRING, position);
                    startActivityForResult(intent, DELETE_REQUEST_CODE);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Trip trip = tripList.get(position);
                    Intent intent = new Intent(v.getContext(), EditTripActivity.class);
                    intent.putExtra(TRIP_STRING, trip);
                    intent.putExtra(POSITION_STRING, position);
                    startActivityForResult(intent, EDIT_REQUEST_CODE);
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return tripList.size();
        }
    }

}