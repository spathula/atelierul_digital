package com.example.androidfundamentals.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidfundamentals.R;

public class ChatTwoActivity extends AppCompatActivity {
    private TextView receivedMessage;
    private Button sendButton;
    private EditText inputText;
    protected static final String INPUT_MESSAGE = "input_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_two);

        this.receivedMessage = findViewById(R.id.textView_second_chatTwo);
        this.sendButton = findViewById(R.id.button_chatTwo);
        this.inputText = findViewById(R.id.editText_chatTwo);

        handleReceivedMessage(getIntent());

        this.sendButton.setOnClickListener(this::sendMessage);
    }

    private void handleReceivedMessage(Intent intent) {
        if (intent != null) {
            String message = intent.getStringExtra(INPUT_MESSAGE);
            if (message != null) {
                receivedMessage.setText(message);
            }
        }
    }

    private void sendMessage(View view) {
        String input = inputText.getText().toString();
        Intent intent = new Intent(this, ChatOneActivity.class);

        intent.putExtra(ChatTwoActivity.INPUT_MESSAGE, input);
        setResult(RESULT_OK, intent);
        finish();
    }
}