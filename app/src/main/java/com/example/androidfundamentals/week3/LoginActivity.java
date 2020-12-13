package com.example.androidfundamentals.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.androidfundamentals.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout emailInputLayout, phoneInputLayout;
    private EditText emailEditText, phoneEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.emailInputLayout = findViewById(R.id.til_email);
        this.phoneInputLayout = findViewById(R.id.til_phone);
        this.emailEditText = findViewById(R.id.et_email);
        this.phoneEditText = findViewById(R.id.et_phone);

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        if(this.emailEditText.getText().toString().isEmpty()) {
            emailInputLayout.setError("Email address cannot be empty!");
        }
        else {
            emailInputLayout.setError(null);
        }

        if(this.phoneEditText.getText().toString().isEmpty()) {
            phoneInputLayout.setError("Phone number cannot be empty!");
        }
        else {
            phoneInputLayout.setError(null);
        }
    }
}