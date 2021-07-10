package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Messages extends AppCompatActivity {

    ListView userMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        ArrayList<String> messages = new ArrayList<>();

        userMessages = findViewById(R.id.user_messages);

        messages.add("Confirmation Message from Admin");
        messages.add("Survey Message from Admin");

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.messages_layout, messages.toArray(new String[messages.size()]));


        userMessages.setAdapter(adapter);

        userMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(Messages.this, UserRecieveConfirmation.class);
                startActivity(intent);*/
                Intent intent = new Intent(Messages.this, UserTakeSurvey.class);
                startActivity(intent);
            }
        });
    }
}