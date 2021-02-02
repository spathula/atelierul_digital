package com.example.androidfundamentals.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidfundamentals.R;

public class ImplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
    }
}