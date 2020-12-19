package com.example.androidfundamentals.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidfundamentals.R;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        this.spinner = findViewById(R.id.spinner);

        this.spinner.setAdapter(getAdapter());

        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected: " + parent.getAdapter().getItem(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private ArrayAdapter<String> getAdapter() {
        List<String> data = new ArrayList<>();
        data.add("Cupcake");
        data.add("Donut");
        data.add("Eclair");
        data.add("KitKat");
        data.add("Pie");

        return new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
    }
}