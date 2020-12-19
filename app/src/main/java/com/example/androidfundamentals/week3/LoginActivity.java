package com.example.androidfundamentals.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfundamentals.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout emailInputLayout, phoneInputLayout;
    private EditText emailEditText, phoneEditText;
    private CheckBox tacCheckBox;
    private Button submitButton;
    private TextView inputTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        this.tacCheckBox.setChecked(false);

        this.submitButton.setEnabled(false);

        this.tacCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> this.submitButton.setEnabled(isChecked));

        this.submitButton.setOnClickListener(v -> {
            if(validateInput()) {
                successfulInput();
            }
            else {
                Log.d("LoginActivity","Input data is not valid.");
            }
        });
    }

    private void initViews() {
        this.emailInputLayout = findViewById(R.id.til_email);
        this.phoneInputLayout = findViewById(R.id.til_phone);
        this.emailEditText = findViewById(R.id.et_email);
        this.phoneEditText = findViewById(R.id.et_phone);
        this.tacCheckBox = findViewById(R.id.cb_tac);
        this.submitButton = findViewById(R.id.btn_submit);
        this.inputTextView = findViewById(R.id.tv_input);
    }

    private boolean validateInput() {
        boolean validationState = true;
        if(this.emailEditText.getText().toString().isEmpty()) {
            this.emailInputLayout.setError("Email address cannot be empty!");
            validationState = false;
        }
        else {
            this.emailInputLayout.setError(null);
        }

        if(this.phoneEditText.getText().toString().isEmpty()) {
            this.phoneInputLayout.setError("Phone number cannot be empty!");
            validationState = false;
        }
        else {
            this.phoneInputLayout.setError(null);
        }

        return validationState;
    }

    private void successfulInput() {
        Toast.makeText(this, "Data submitted", Toast.LENGTH_LONG).show();
        setInputTextView();
        clearInputs();
    }

    private void clearInputs() {
        this.emailEditText.setText("");
        this.phoneEditText.setText("");
        this.tacCheckBox.setChecked(false);
    }

    private void setInputTextView() {
        String emailText = "Email: " + this.emailEditText.getText().toString();
        String phoneText = "\nPhone: " + this.phoneEditText.getText().toString();
        String checkBoxState = "\nTerms and conditions accepted: ";
        checkBoxState += (this.tacCheckBox.isChecked()) ? "True" : "False";

        this.inputTextView.setText(String.format("%s%s%s", emailText, phoneText, checkBoxState));
    }
}