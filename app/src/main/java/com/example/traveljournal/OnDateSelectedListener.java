package com.example.traveljournal;

import androidx.annotation.NonNull;
import java.util.Calendar;

public interface OnDateSelectedListener {
    void onStartDateSelected(@NonNull Calendar calendar);
    void onEndDateSelected(@NonNull Calendar calendar);
}