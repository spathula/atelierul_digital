package com.example.androidfundamentals.week7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.androidfundamentals.R;

public class BatteryActivity extends AppCompatActivity {
    private ImageView battery;
    private Button minus_button, plus_button;
    private int currentLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        this.battery = findViewById(R.id.battery_image);
        this.minus_button = findViewById(R.id.minus_button);
        this.minus_button.setEnabled(false);
        this.plus_button = findViewById(R.id.plus_button);
    }

    public void onBatteryChangeMinus(View view) {
        updateBatteryLevel(--currentLevel);
        updateButtonState();
    }

    public void onBatteryChangePlus(View view) {
        updateBatteryLevel(++currentLevel);
        updateButtonState();
    }

    private void updateBatteryLevel(int level) {
        battery.setImageLevel(level);
    }

    private void updateButtonState() {
        this.minus_button.setEnabled(currentLevel > 0);
        this.plus_button.setEnabled(currentLevel < 6);
    }
}