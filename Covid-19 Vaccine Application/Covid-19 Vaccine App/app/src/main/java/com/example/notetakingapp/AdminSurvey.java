package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminSurvey extends AppCompatActivity {

    Button sendSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_survey);

        sendSurvey = findViewById(R.id.buttonSendSurvey);
        sendSurvey.setBackgroundColor(Color.GRAY);
        sendSurvey.setTextColor(Color.BLACK);
    }

    public void sendSurvey(View view){
        Toast.makeText(AdminSurvey.this, "Survey has been sent to the user.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminSurvey.this, AdminPage.class);
        startActivity(intent);
    }
}