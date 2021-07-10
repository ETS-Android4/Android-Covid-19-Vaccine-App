package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AdminSendConfirmation extends AppCompatActivity {

    TextView textView;
    Button button;

    private static final String admin_send_confirm_file = "fileConfirm5.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_send_confirmation);

        textView = findViewById(R.id.adminTextSendConfirmation);
        button = findViewById(R.id.buttonAdminSendConfirm);
        button.setTextColor(Color.BLACK);
        button.setBackgroundColor(Color.GRAY);

        button.setOnClickListener(new View.OnClickListener() {
            String toFile = "Do you want to confirm your appointment on 18/7/2021 at 11 am?";
            @Override
            public void onClick(View v) {
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(admin_send_confirm_file, MODE_PRIVATE);
                    fos.write(toFile.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Toast.makeText(AdminSendConfirmation.this, "Confirmation message has been successfully sent.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminSendConfirmation.this, AdminPage.class);
                startActivity(intent);
            }
        });

    }
}