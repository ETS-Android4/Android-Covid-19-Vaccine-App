package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserTakeSurvey extends AppCompatActivity {

    TextView question;

    Button poor;
    Button good;
    Button veryGood;
    Button excellent;

    boolean Poor = false;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_take_survey);

        question = findViewById(R.id.textQuestion);

        poor = findViewById(R.id.buttonPoor);
        poor.setBackgroundColor(Color.GRAY);
        poor.setTextColor(Color.BLACK);

        good = findViewById(R.id.buttonGood);
        good.setBackgroundColor(Color.GRAY);
        good.setTextColor(Color.BLACK);

        veryGood = findViewById(R.id.buttonVeryGood);
        veryGood.setBackgroundColor(Color.GRAY);
        veryGood.setTextColor(Color.BLACK);

        excellent = findViewById(R.id.buttonExcellent);
        excellent.setBackgroundColor(Color.GRAY);
        excellent.setTextColor(Color.BLACK);

        ArrayList<String> surveyQuestions = new ArrayList<>();
        surveyQuestions.add("How would you rate your overall experience?");
        surveyQuestions.add("How would you rate the quality of the medical place you got vaccinated in?");
        surveyQuestions.add("How would you rate the medical stuff?");
        surveyQuestions.add("Overall, how would you rate your experience in using this application?");

        question.setText(surveyQuestions.get(index));

        poor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                index++;
                if(index <= surveyQuestions.size() - 1){
                    question.setText(surveyQuestions.get(index));
                    Log.d("index value", "index is " + index);
                    }
                else{
                        Toast.makeText(UserTakeSurvey.this, "Thanks for finishing the survey", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserTakeSurvey.this, UserPage.class);
                        startActivity(intent);
                    }
                }

        });

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index <= surveyQuestions.size() - 1){

                    question.setText(surveyQuestions.get(index));
                    Log.d("index value", "index is " + index);
                }
                else{
                    Toast.makeText(UserTakeSurvey.this, "Thanks for finishing the survey", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserTakeSurvey.this, UserPage.class);
                    startActivity(intent);
                }
            }
        });

        veryGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index <= surveyQuestions.size() - 1){

                    question.setText(surveyQuestions.get(index));
                    Log.d("index value", "index is " + index);
                }
                else{
                    Toast.makeText(UserTakeSurvey.this, "Thanks for finishing the survey", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UserTakeSurvey.this, UserPage.class);
                    startActivity(intent);
                }
            }
        });

        excellent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index <= surveyQuestions.size() - 1){

                    question.setText(surveyQuestions.get(index));
                    Log.d("index value", "index is " + index);
                }
                else{
                    Toast.makeText(UserTakeSurvey.this, "Thanks for finishing the survey", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UserTakeSurvey.this, UserPage.class);
                    startActivity(intent);
                }
            }
        });






    }

}