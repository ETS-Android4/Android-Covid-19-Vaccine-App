package com.example.notetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AdminPage extends AppCompatActivity {

    Menu optionsMenu;

    String url = "http://10.0.0.7:12345";
    String MY_TOKEN;

    private static final String token_file = "fileTokenAdmin.txt";
    String return_token = "";

    ImageView viewAppointments;
    ImageView viewSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        MY_TOKEN = getIntent().getStringExtra(MainActivity.EXTRA_AUTHO_TOKEN);
        Log.d("Token: ", "token is " + MainActivity.EXTRA_AUTHO_TOKEN);

        if(MY_TOKEN != null){
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(token_file, MODE_PRIVATE);
                fos.write(MY_TOKEN.getBytes());
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
        }

        FileInputStream fis = null;
        try {
            fis = openFileInput(token_file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            return_token = sb.toString();
            Log.d("Token: ", "token is " + return_token);
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



        viewAppointments = findViewById(R.id.viewAppointmentsAdmin);
        viewSurvey = findViewById(R.id.adminSurvey);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        optionsMenu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        final RequestQueue queue = Volley.newRequestQueue(this);
        switch (item.getItemId()){
            case R.id.menu_save:

                return true;

            case R.id.menu_share:

                return true;

            case R.id.menu_description:

                return true;

            case R.id.sign_out:


                JSONObject jsonObj = new JSONObject();
                try {

                    jsonObj.put("method", "signOut");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent intent = new Intent(AdminPage.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error: ", error.getMessage());
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("autho_token", return_token);

                        return headers;
                    }
                };

                queue.add(request);

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void toAdminAppointments(View view){
        Intent intent = new Intent(AdminPage.this, AdminAppointments.class);
        startActivity(intent);
    }

    public void toAdminSurvey(View view){
        Intent intent = new Intent(AdminPage.this, AdminSurvey.class);
        startActivity(intent);
    }

}