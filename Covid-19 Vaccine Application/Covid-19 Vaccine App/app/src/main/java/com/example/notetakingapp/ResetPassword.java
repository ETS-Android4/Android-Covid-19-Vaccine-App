package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class ResetPassword extends AppCompatActivity {

    String url = "http://10.0.0.7:12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        TextView askEmail = findViewById(R.id.askEmail);
        EditText inputEmailAsked = findViewById(R.id.inputEmailAsked);
        Button reset = findViewById(R.id.Reset);
        reset.setBackgroundColor(Color.GRAY);
        reset.setTextColor(Color.BLACK);

        TextView e = findViewById(R.id.e);
        TextView tp = findViewById(R.id.tp);
        TextView ns = findViewById(R.id.np);
        EditText savedEmail = findViewById(R.id.savedEmail);
        EditText tempPass = findViewById(R.id.tempPass);
        EditText enterNewPass = findViewById(R.id.enterNewPass);
        Button setNewPass = findViewById(R.id.setNewPass);
        setNewPass.setBackgroundColor(Color.GRAY);
        setNewPass.setTextColor(Color.BLACK);


        e.setVisibility(View.INVISIBLE);
        tp.setVisibility(View.INVISIBLE);
        ns.setVisibility(View.INVISIBLE);
        savedEmail.setVisibility(View.INVISIBLE);
        tempPass.setVisibility(View.INVISIBLE);
        enterNewPass.setVisibility(View.INVISIBLE);
        setNewPass.setVisibility(View.INVISIBLE);

        final RequestQueue queue = Volley.newRequestQueue(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailEntered = inputEmailAsked.getText().toString();

                JSONObject jsonObj = new JSONObject();
                try {
                    jsonObj.put("method", "forgotPassword");
                    jsonObj.put("email", emailEntered);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Log.d("response", response.toString());

                                askEmail.setVisibility(View.INVISIBLE);
                                inputEmailAsked.setVisibility(View.INVISIBLE);
                                reset.setVisibility(View.INVISIBLE);
                                e.setVisibility(View.VISIBLE);
                                tp.setVisibility(View.VISIBLE);
                                ns.setVisibility(View.VISIBLE);
                                savedEmail.setVisibility(View.VISIBLE);
                                savedEmail.setText(emailEntered);
                                tempPass.setVisibility(View.VISIBLE);
                                enterNewPass.setVisibility(View.VISIBLE);
                                setNewPass.setVisibility(View.VISIBLE);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error: ", error.getMessage());
                    }
                });

                queue.add(request);
            }
        });

        final RequestQueue queue1 = Volley.newRequestQueue(this);

        setNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("method", "registerAccount");
                    jsonObject.put("password", enterNewPass.getText().toString());
                    jsonObject.put("temp_password", tempPass.getText().toString());
                    jsonObject.put("email", savedEmail.getText().toString());
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("response", response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error: ", error.getMessage());
                    }
                });

                queue1.add(request);

                Intent intent = new Intent(ResetPassword.this, MainActivity.class);
                startActivity(intent);

            }
        });



    }
}