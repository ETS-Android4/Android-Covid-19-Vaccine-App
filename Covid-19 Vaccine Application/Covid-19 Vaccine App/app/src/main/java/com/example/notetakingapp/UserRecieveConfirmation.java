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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserRecieveConfirmation extends AppCompatActivity {

    TextView askUserConfirm;
    Button userConfirmApp;

    private static final String admin_send_confirm_file = "fileConfirm4.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recieve_confirmation);

        askUserConfirm = findViewById(R.id.textAskUserConfirm);
        userConfirmApp = findViewById(R.id.buttonUserConfirmApp);
        userConfirmApp.setTextColor(Color.BLACK);
        userConfirmApp.setBackgroundColor(Color.GRAY);

        FileInputStream fis = null;
        try {
            fis = openFileInput(admin_send_confirm_file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            //return_token = sb.toString();
            askUserConfirm.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        userConfirmApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confirmedOrNo = "sss";

                Toast.makeText(UserRecieveConfirmation.this, "Your appointment has been successfully confirmed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserRecieveConfirmation.this, UserPage.class);
                intent.putExtra("confirmed", confirmedOrNo);
                startActivity(intent);
            }
        });



    }
}