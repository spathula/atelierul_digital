package com.example.traveljournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.traveljournal.fragments.HomeFragment;
import com.kwabenaberko.openweathermaplib.constant.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callback.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.model.currentweather.CurrentWeather;

public class TripDetailsActivity extends AppCompatActivity {
    private static final String API_KEY = "95e1f888a9441c8e901337955c58a6e9";
    private TextView tripName, destination, tripType, startDate, endDate, price, rating, weather;
    private Button deleteTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        this.tripName = findViewById(R.id.tv_tripName);
        this.destination = findViewById(R.id.tv_destination);
        this.tripType = findViewById(R.id.tv_tripType);
        this.price = findViewById(R.id.tv_price);
        this.rating = findViewById(R.id.tv_rating);
        this.startDate = findViewById(R.id.tv_startDate);
        this.endDate = findViewById(R.id.tv_endDate);
        this.deleteTrip = findViewById(R.id.button_deleteTrip);
        this.weather = findViewById(R.id.tv_weather);

        Trip trip = getIntent().getParcelableExtra(HomeFragment.TRIP_STRING);
        this.tripName.setText(trip.getTripName());
        this.destination.setText(trip.getDestination());
        this.price.setText(String.format("%s €", trip.getPrice()));
        this.tripType.setText(trip.getTripType());
        this.rating.setText(String.format("%s stars", trip.getRating()));
        this.startDate.setText(trip.getStartDate());
        this.endDate.setText(trip.getEndDate());

        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(API_KEY);
        helper.setUnits(Units.METRIC);
        helper.getCurrentWeatherByCityName(trip.getDestination(), new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weather.setText(String.format("Temperature: %s ºC | Information: %s", currentWeather.getMain().getTempMax(), currentWeather.getWeather().get(0).getDescription()));
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("ERROR - " + throwable.getMessage());
            }
        });

        deleteTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);

                intent.putExtra(HomeFragment.POSITION_STRING, getIntent().getIntExtra(HomeFragment.POSITION_STRING, 1));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}