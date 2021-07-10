package com.example.notetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleAppointmentActivity extends AppCompatActivity {

    public static class VaccineTypes{
        private String vaccineName;

        public VaccineTypes(String vaccineName){
            this.vaccineName = vaccineName;
        }

        @Override
        public String toString() {
            return vaccineName;
        }

    }

    public static class ShotTypes{
        private String shotType;

        public ShotTypes(String shotType){
            this.shotType = shotType;
        }

        @Override
        public String toString() {
            return shotType;
        }

    }

    public static class Time{
        private String time;

        public Time(String time){this.time = time; }

        @Override
        public String toString() {
            return time;
        }
    }

    TextView chooseDate;
    CalendarView calendarView;
    Button finishSchedule;
    TextView chooseTime;
    Spinner timeSelection;

    TextView selectVaccineType;
    TextView selectVaccineShot;
    Spinner vaccineType;
    Spinner vaccineShot;
    Button toCalendar;

    int calDay;
    int calMonth;
    int calYear;
    String date;
    private static final String schedule_file = "ScheduleFile7.txt";
    private static final String admin_schedule_file = "AdminScheduleFile7.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);


        chooseDate = findViewById(R.id.textChooseDate);
        calendarView = findViewById(R.id.calendarView);
        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        calendarView.setMinDate(now);

        finishSchedule = findViewById(R.id.buttonFinishSchedule);
        finishSchedule.setTextColor(Color.BLACK);
        finishSchedule.setBackgroundColor(Color.GRAY);
        chooseTime = findViewById(R.id.textChooseTime);
        timeSelection = findViewById(R.id.timeSelection);

        chooseDate.setVisibility(View.INVISIBLE);
        calendarView.setVisibility(View.INVISIBLE);
        finishSchedule.setVisibility(View.INVISIBLE);
        chooseTime.setVisibility(View.INVISIBLE);
        timeSelection.setVisibility(View.INVISIBLE);

        selectVaccineType = findViewById(R.id.textSelectType);
        selectVaccineShot = findViewById(R.id.textSelectShot);
        vaccineType = findViewById(R.id.vaccineTypeSelection);
        vaccineShot = findViewById(R.id.ShotSelection);
        toCalendar = findViewById(R.id.buttonToCalendar);
        toCalendar.setTextColor(Color.BLACK);
        toCalendar.setBackgroundColor(Color.GRAY);

        VaccineTypes[] vaccines = {
                new VaccineTypes("Jonson and Jonson"),
                new VaccineTypes("Moderna"),
                new VaccineTypes("Pfizer-BioNTect")
        };

        final ArrayAdapter<VaccineTypes> adapter = new ArrayAdapter<VaccineTypes>(this, android.R.layout.simple_spinner_dropdown_item, vaccines);
        vaccineType.setAdapter(adapter);

        ShotTypes[] shots = {
                new ShotTypes("First Shot"),
                new ShotTypes("Second Shot")
        };

        final ArrayAdapter<ShotTypes> adapter1 = new ArrayAdapter<ShotTypes>(this, android.R.layout.simple_spinner_dropdown_item, shots);
        vaccineShot.setAdapter(adapter1);

        Time[] times = {
                new Time("9:00 am"),
                new Time("9:30 am"),
                new Time("10:00 am"),
                new Time("10:30 am"),
                new Time("11:00 am"),
                new Time("11:30 am"),
                new Time("12:00 pm"),
                new Time("12:30 pm"),
                new Time("1:00 pm"),
                new Time("1:30 pm"),
                new Time("2:00 pm"),
                new Time("2:30 pm"),
                new Time("3:00 pm"),
                new Time("3:30 pm"),
                new Time("4:00 pm"),
                new Time("4:30 pm"),
                new Time("5:00 pm")
        };

        final ArrayAdapter<Time> adapter2 = new ArrayAdapter<Time>(this, android.R.layout.simple_spinner_dropdown_item, times);
        timeSelection.setAdapter(adapter2);

        toCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVaccineType.setVisibility(View.INVISIBLE);
                selectVaccineShot.setVisibility(View.INVISIBLE);
                vaccineType.setVisibility(View.INVISIBLE);
                vaccineShot.setVisibility(View.INVISIBLE);
                toCalendar.setVisibility(View.INVISIBLE);

                chooseDate.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.VISIBLE);
                finishSchedule.setVisibility(View.VISIBLE);
                chooseTime.setVisibility(View.VISIBLE);
                timeSelection.setVisibility(View.VISIBLE);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calDay = dayOfMonth;
                calMonth = month + 1;
                calYear = year;
                date = calDay + "/" + calMonth + "/" + calYear;
            }
        });

        finishSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vacType = vaccineType.getSelectedItem().toString();
                String vacShot = vaccineShot.getSelectedItem().toString();
                String vacTime = timeSelection.getSelectedItem().toString();
                String textToFile = "You have an upcoming appointment on " + date + " at " + vacTime + " to take " + vacShot +
                        " of " + vacType + " vaccine";

                String textToAdminFile = "The user of an email [userNumberOne@exm.com] has an upcoming appointment on " + date + " at " + vacTime + " to take " + vacShot +
                        " of " + vacType + " vaccine";

                Toast.makeText(ScheduleAppointmentActivity.this, "Appointment Scheduled Successfully", Toast.LENGTH_SHORT).show();

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(schedule_file, MODE_PRIVATE);
                    fos.write(textToFile.getBytes());
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

                FileOutputStream fos1 = null;
                try {
                    fos1 = openFileOutput(admin_schedule_file, MODE_PRIVATE);
                    fos1.write(textToAdminFile.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos1 != null) {
                        try {
                            fos1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Intent intent = new Intent(ScheduleAppointmentActivity.this, UserPage.class);
                startActivity(intent);


            }
        });


    }
}