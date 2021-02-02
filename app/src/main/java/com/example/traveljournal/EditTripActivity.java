package com.example.traveljournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.traveljournal.fragments.EndDatePickerFragment;
import com.example.traveljournal.fragments.HomeFragment;
import com.example.traveljournal.fragments.StartDatePickerFragment;
import com.google.android.material.slider.Slider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditTripActivity extends AppCompatActivity implements OnDateSelectedListener {
    private EditText tripName, destination;
    private RadioGroup radioGroup;
    private RadioButton tripType;
    private Slider price;
    private RatingBar rating;
    private Button editButton, startDateButton, endDateButton;
    private TextView startDate, endDate;
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String TAG_DATE_PICKER = "datePicker";
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);

        this.tripName = findViewById(R.id.et_tripName);
        this.destination = findViewById(R.id.et_destination);
        this.radioGroup = findViewById(R.id.radioGroup);
        this.price = findViewById(R.id.slider);
        this.rating = findViewById(R.id.rating);
        this.editButton = findViewById(R.id.button_editTrip);
        this.startDateButton = findViewById(R.id.button_startDate);
        this.endDateButton = findViewById(R.id.button_endDate);
        this.startDate = findViewById(R.id.tv_startDate);
        this.endDate = findViewById(R.id.tv_endDate);

        this.startDateButton.setOnClickListener(this::showStartDatePickerDialog);
        this.endDateButton.setOnClickListener(this::showEndDatePickerDialog);

        Trip trip = getIntent().getParcelableExtra(HomeFragment.TRIP_STRING);
        position = getIntent().getIntExtra(HomeFragment.POSITION_STRING, 1);

        this.tripName.setText(trip.getTripName());
        this.destination.setText(trip.getDestination());
        this.price.setValue(Integer.parseInt(trip.getPrice()));
        this.rating.setRating(Integer.parseInt(trip.getRating()));
        this.startDate.setText(trip.getStartDate());
        this.endDate.setText(trip.getEndDate());

        this.editButton.setOnClickListener(this::editTrip);
    }

    private Trip createTrip() {
        Trip trip = new Trip();
        if (this.tripName != null) {
            trip.setTripName(this.tripName.getText().toString());
        }
        if (this.destination != null) {
            trip.setDestination(this.destination.getText().toString());
        }
        if (this.price != null) {
            String price = String.valueOf(this.price.getValue());
            trip.setPrice(price.substring(0, price.length() - 2));
        }
        if (this.rating != null) {
            String rating = String.valueOf(this.rating.getRating());
            trip.setRating(rating.substring(0, rating.length() - 2));
        }
        if (this.tripType != null) {
            trip.setTripType(this.tripType.getText().toString());
        }
        if (this.startDate != null) {
            trip.setStartDate(this.startDate.getText().toString());
        }
        if (this.endDate != null) {
            trip.setEndDate(this.endDate.getText().toString());
        }
        return trip;
    }

    private void editTrip(View view) {
        this.tripType = findViewById(radioGroup.getCheckedRadioButtonId());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(HomeFragment.TRIP_STRING, createTrip());
        intent.putExtra(HomeFragment.POSITION_STRING, position);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void showStartDatePickerDialog(View view) {
        DialogFragment newFragment = new StartDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), TAG_DATE_PICKER);
    }

    @Override
    public void onStartDateSelected(@NonNull Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        String dateFormat = format.format(calendar.getTime());
        startDate.setText(getString(R.string.date_selected_n, dateFormat));
    }

    public void showEndDatePickerDialog(View view) {
        DialogFragment newFragment = new EndDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), TAG_DATE_PICKER);
    }

    @Override
    public void onEndDateSelected(@NonNull Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        String dateFormat = format.format(calendar.getTime());
        endDate.setText(getString(R.string.date_selected_n, dateFormat));
    }
}