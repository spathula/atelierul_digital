package com.example.androidfundamentals.week5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidfundamentals.R;

public class ChatOneActivity extends AppCompatActivity {
    private static final int MESSAGE_RESULT_CODE = 1001;
    private TextView receivedMessage;
    private Button sendButton;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_one);

        this.receivedMessage = findViewById(R.id.textView_second_chatOne);
        this.sendButton = findViewById(R.id.button_chatOne);
        this.inputText = findViewById(R.id.editText_chatOne);

        this.sendButton.setOnClickListener(this::sendMessage);
    }

    private void sendMessage(View view) {
        String input = this.inputText.getText().toString();
        Intent intent = new Intent(this, ChatTwoActivity.class);

        intent.putExtra(ChatTwoActivity.INPUT_MESSAGE, input);
        startActivityForResult(intent, MESSAGE_RESULT_CODE);

        this.inputText.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MESSAGE_RESULT_CODE && resultCode == RESULT_OK && data != null) {
            String receivedMessage = data.getStringExtra(ChatTwoActivity.INPUT_MESSAGE);
            if (receivedMessage != null) {
                this.receivedMessage.setText(receivedMessage);
            }
        }
    }
}