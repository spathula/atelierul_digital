package com.example.androidfundamentals.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidfundamentals.R;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }
}