package com.example.androidfundamentals.week5;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfundamentals.R;

public class ThreeButtonsActivity extends AppCompatActivity {

    private static final String TAG = "ThreeButtonsActivity";
    protected static final String EXTRA_STRING_RES_LONG = "extra_string_res_long";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_three_buttons);

        findViewById(R.id.first_button).setOnClickListener(view -> {
            openSecondActivity(R.id.first_button);
        });

        findViewById(R.id.second_button).setOnClickListener(view -> {
            openSecondActivity(R.id.second_button);
        });

        findViewById(R.id.third_button).setOnClickListener(view -> {
            openSecondActivity(R.id.third_button);
        });

    }

    private void openSecondActivity(int buttonResource) {
        int stringResource;

        if (buttonResource == R.id.first_button) {
            stringResource = R.string.first_passage;
        } else if (buttonResource == R.id.second_button) {
            stringResource = R.string.second_passage;
        } else if (buttonResource == R.id.third_button) {
            stringResource = R.string.third_passage;
        } else {
            stringResource = 0;
        }

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_STRING_RES_LONG, stringResource);

        startActivity(intent);
    }

}
