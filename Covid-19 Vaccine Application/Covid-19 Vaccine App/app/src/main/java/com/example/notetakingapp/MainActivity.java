package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView welcomeText;
    Button buttonUser;
    Button buttonAdmin;

    TextView userOrAdmin;

    TextView textEmail;
    TextView textPassword;

    Button signIn;
    Button new_user;
    Button forgotPassword;
    EditText email;
    EditText password;
    public String Email = "";
    public String pass = "";
    String url = "http://10.0.0.7:12345";
    String AUTHO_TOKEN = "";
    public final static String EXTRA_AUTHO_TOKEN = "com.example";

    boolean userSelect = false;
    boolean adminSelect = false;
    //public final static String EXTRA_EMAIL = "com.example";

    NotificationCompat.Builder builder;
    NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        welcomeText = findViewById(R.id.textWelcome);
        welcomeText.setTextColor(Color.GRAY);

        buttonUser = findViewById(R.id.buttonUser);
        buttonUser.setBackgroundColor(Color.GRAY);
        buttonUser.setTextColor(Color.BLACK);

        buttonAdmin = findViewById(R.id.buttonAdmin);
        buttonAdmin.setBackgroundColor(Color.GRAY);
        buttonAdmin.setTextColor(Color.BLACK);

        userOrAdmin = findViewById(R.id.uesrOrAdmin);
        userOrAdmin.setVisibility(View.INVISIBLE);

        textEmail = findViewById(R.id.textEmail);
        textEmail.setVisibility(View.INVISIBLE);
        textPassword = findViewById(R.id.textPassword);
        textPassword.setVisibility(View.INVISIBLE);

        signIn = findViewById(R.id.buttonSignIn);
        signIn.setBackgroundColor(Color.GRAY);
        signIn.setTextColor(Color.BLACK);
        signIn.setVisibility(View.INVISIBLE);

        new_user = findViewById(R.id.buttonNewUser);
        new_user.setBackgroundColor(Color.GRAY);
        new_user.setTextColor(Color.BLACK);
        new_user.setVisibility(View.INVISIBLE);

        forgotPassword = findViewById(R.id.buttonForgotPass);
        forgotPassword.setBackgroundColor(Color.GRAY);
        forgotPassword.setTextColor(Color.BLACK);
        forgotPassword.setVisibility(View.INVISIBLE);

        email = findViewById(R.id.email);
        email.setVisibility(View.INVISIBLE);
        password = findViewById(R.id.password);
        password.setVisibility(View.INVISIBLE);

        /*if(!haveNetworkConnection()) //returns true if internet available
        {

            Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"There is an Internet Connection",Toast.LENGTH_LONG).show();
        }*/

        //ConnectivityManager mgr = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        /*if(mgr.getActiveNetworkInfo() != null && mgr.getActiveNetworkInfo().isConnected()) //returns true if internet available
        {

            Toast.makeText(MainActivity.this,"There is an Internet Connection",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }*/

        /*if (netInfo != null) {
            if (netInfo.isConnected()) {
                Toast.makeText(MainActivity.this,"There is an Internet Connection",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }*/




        builder = new NotificationCompat.Builder(MainActivity.this, "ahmed1")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Internet Connection")
                .setContentText("No Internet Connection")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);



        notificationManager = NotificationManagerCompat.from(MainActivity.this);



        final RequestQueue queue = Volley.newRequestQueue(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = email.getText().toString();
                pass = password.getText().toString();

                JSONObject jsonObj = new JSONObject();
                try {
                    jsonObj.put("password", pass);
                    jsonObj.put("time_span", 30);
                    jsonObj.put("method", "authenticate");
                    jsonObj.put("time_unit", "MINUTES");
                    jsonObj.put("email", Email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    AUTHO_TOKEN = response.get("token").toString();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.d("token is:", AUTHO_TOKEN);

                                if(userSelect == true){
                                    Intent intent = new Intent(MainActivity.this, UserPage.class);
                                    intent.putExtra(EXTRA_AUTHO_TOKEN, AUTHO_TOKEN);
                                    Log.d("Token: ", "token is " + AUTHO_TOKEN);
                                    startActivity(intent);
                                }
                                if(adminSelect == true){
                                    Intent intent = new Intent(MainActivity.this, AdminPage.class);
                                    intent.putExtra(EXTRA_AUTHO_TOKEN, AUTHO_TOKEN);
                                    startActivity(intent);
                                }


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


    }


    public void newUser(View view){
        Intent intent = new Intent(MainActivity.this, NewUserActivity.class);

        startActivity(intent);

    }

    public void toUserSign(View view){

        buttonAdmin.setVisibility(View.INVISIBLE);
        buttonUser.setVisibility(View.INVISIBLE);
        welcomeText.setVisibility(View.INVISIBLE);

        textEmail.setVisibility(View.VISIBLE);
        textPassword.setVisibility(View.VISIBLE);
        signIn.setVisibility(View.VISIBLE);
        new_user.setVisibility(View.VISIBLE);
        forgotPassword.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);

        userOrAdmin.setText("User Sign in");
        userOrAdmin.setVisibility(View.VISIBLE);

        userSelect = true;

        notificationManager.notify(1, builder.build());

    }

    public void toAdminSign(View view){

        buttonAdmin.setVisibility(View.INVISIBLE);
        buttonUser.setVisibility(View.INVISIBLE);
        welcomeText.setVisibility(View.INVISIBLE);

        textEmail.setVisibility(View.VISIBLE);
        textPassword.setVisibility(View.VISIBLE);
        signIn.setVisibility(View.VISIBLE);
        new_user.setVisibility(View.VISIBLE);
        forgotPassword.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);

        userOrAdmin.setText("Admin Sign in");
        userOrAdmin.setVisibility(View.VISIBLE);

        adminSelect = true;

    }

    public void resetPass(View view){
        Intent intent = new Intent(MainActivity.this, ResetPassword.class);
        startActivity(intent);
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "student_channel";
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("ahmed1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }





}