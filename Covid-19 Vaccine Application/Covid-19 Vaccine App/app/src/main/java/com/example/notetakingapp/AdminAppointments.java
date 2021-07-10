package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdminAppointments extends AppCompatActivity {

    ListView listView;

    private static final String admin_schedule_file = "AdminScheduleFile7.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appointments);

        listView = findViewById(R.id.admin_list_appointments);
        listView.setVisibility(View.VISIBLE);

        ArrayList<String> adminAppointments = new ArrayList<>();





        FileInputStream fis1 = null;
        try {
            fis1 = openFileInput(admin_schedule_file);
            InputStreamReader isr = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            //return_token = sb.toString();
            //Log.d("Token: ", "token is " + return_token);
            //textView.setText(sb.toString());
            adminAppointments.add(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis1 != null) {
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d("admin file", "admin file contents " + adminAppointments);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.admin_list_view_item_layout, adminAppointments.toArray(new String[adminAppointments.size()]));


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminAppointments.this, AdminSendConfirmation.class);
                startActivity(intent);
            }
        });

    }
}