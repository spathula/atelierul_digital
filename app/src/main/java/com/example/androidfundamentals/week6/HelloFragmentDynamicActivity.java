package com.example.androidfundamentals.week6;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidfundamentals.R;

public class HelloFragmentDynamicActivity extends AppCompatActivity implements TheListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_fragment_dynamic);

        HelloFragment helloFragment = new HelloFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(HelloFragment.EXTRA_KEY_INT, 1001);
        bundle.putString(HelloFragment.EXTRA_KEY_STRING, "HelloFragment with Bundle");

        helloFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, helloFragment, "HelloFragment");
        fragmentTransaction.commit();

        findViewById(R.id.button_fragmentToast).setOnClickListener(v -> helloFragment.fragmentToast());
    }

    @Override
    public void doSomeWork(String work) {
        Toast.makeText(this, work, Toast.LENGTH_LONG).show();
    }
}
