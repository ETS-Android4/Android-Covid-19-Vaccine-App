package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Appointments extends AppCompatActivity {

    private static final String schedule_file = "ScheduleFile7.txt";

    TextView textView;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        String confirmInAppointments = getIntent().getStringExtra("confirmation");

        ArrayList<String> userAppointments = new ArrayList<>();

        textView = findViewById(R.id.userNoAppointmentsText);



        listView = findViewById(R.id.userListAppointments);





        FileInputStream fis = null;
        try {
            fis = openFileInput(schedule_file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            if(sb.toString() == null){

            }
            else if(sb.toString() != null && confirmInAppointments == null){
                userAppointments.add(sb.toString() + "\n" + "not confirmed");
            }else{
                userAppointments.add(sb.toString() + "\n" + "confirmed");
            }

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

        textView.setVisibility(userAppointments == null || userAppointments.isEmpty() ? View.VISIBLE : View.INVISIBLE);
        listView.setVisibility(userAppointments == null || userAppointments.isEmpty() ? View.INVISIBLE : View.VISIBLE);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_view_item_layout, userAppointments.toArray(new String[userAppointments.size()]));


        listView.setAdapter(adapter);




    }
}