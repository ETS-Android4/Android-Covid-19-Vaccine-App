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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewUserActivity extends AppCompatActivity {

    String url = "http://10.0.0.7:12345";
    public String email = "";
    public String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        final TextView mainText = findViewById(R.id.textView);
        final Button newUser = findViewById(R.id.newUser);
        newUser.setBackgroundColor(Color.GRAY);
        newUser.setTextColor(Color.BLACK);
        final Button register = findViewById(R.id.register);
        register.setBackgroundColor(Color.GRAY);
        register.setTextColor(Color.BLACK);
        final Button finishRegistration = findViewById(R.id.finishRegistration);
        finishRegistration.setBackgroundColor(Color.GRAY);
        finishRegistration.setTextColor(Color.BLACK);
        finishRegistration.setVisibility(View.INVISIBLE);
        final EditText newEmail = findViewById(R.id.newEmail);
        final EditText newFirstName = findViewById(R.id.newFirstName);
        final EditText newLastName = findViewById(R.id.newLastName);
        final EditText newPass = findViewById(R.id.newPass);
        final TextView emailText = findViewById(R.id.emailText);
        final TextView firstNameText = findViewById(R.id.firstName);
        final TextView lastNameText = findViewById(R.id.lastName);
        final TextView passText = findViewById(R.id.passText);
        final EditText temporaryPass = findViewById(R.id.temporaryPass);
        temporaryPass.setVisibility(View.INVISIBLE);

        final RequestQueue queue = Volley.newRequestQueue(this);


        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = newEmail.getText().toString();
                password = newPass.getText().toString();

                Map<String, String> postParam= new HashMap<String, String>();
                postParam.put("method", "createAccount");
                postParam.put("last_name", newLastName.getText().toString());
                postParam.put("first_name", newFirstName.getText().toString());
                postParam.put("email", email);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("response", response.toString());
                                newUser.setVisibility(View.INVISIBLE);
                                register.setVisibility(View.INVISIBLE);
                                newEmail.setVisibility(View.INVISIBLE);
                                newFirstName.setVisibility(View.INVISIBLE);
                                newLastName.setVisibility(View.INVISIBLE);
                                newPass.setVisibility(View.INVISIBLE);
                                emailText.setVisibility(View.INVISIBLE);
                                firstNameText.setVisibility(View.INVISIBLE);
                                lastNameText.setVisibility(View.INVISIBLE);
                                passText.setVisibility(View.INVISIBLE);
                                mainText.setText("Please enter your temporary password that was sent to your email");
                                temporaryPass.setVisibility(View.VISIBLE);
                                finishRegistration.setVisibility(View.VISIBLE);

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

        Log.d("email1: ", "hghghg" + email);
        Log.d("password1: ", password);

        final RequestQueue queue1 = Volley.newRequestQueue(this);


            finishRegistration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, String> postParam= new HashMap<String, String>();
                    postParam.put("method", "registerAccount");
                    postParam.put("password", password);
                    postParam.put("temp_password", temporaryPass.getText().toString());
                    postParam.put("email", email);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam),
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

                    Intent intent = new Intent(NewUserActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            });


    }
}