package com.example.user.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.buttonID);
        button.setOnClickListener(openCam);

        Button Signup = (Button) findViewById(R.id.signup);
        Signup.setOnClickListener(openSignUp);

        Button check = (Button) findViewById(R.id.check);
        check.setOnClickListener(opencheck);

        Button notify = (Button) findViewById(R.id.notify);
        notify.setOnClickListener(notifyFunction);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.bg);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi, This is Android Notification Detail!");

        Intent resultIntent = new Intent(this, ApiCall.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ApiCall.class);


    }

    private View.OnClickListener openSignUp = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SignUp.class);


            startActivity(intent);


        }
    };
    private View.OnClickListener opencheck = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CheckBoxScreen.class);


            startActivity(intent);
        }
    };
    private View.OnClickListener openCam = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ApiCall.class);

            Boolean loignReply = Login();

            startActivity(intent);


        }
    };

    private boolean Login() {
        final Context context = getApplicationContext();
        final CharSequence text = "Hello toast!";
        final int duration = Toast.LENGTH_SHORT;

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.14.126:3004/posts/4";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String contacts = jsonObj.getString("email");
                            Toast toast = Toast.makeText(context, contacts, duration);
                            toast.show();
                            Log.e("title", "text here");
                        } catch (JSONException e) {

                            Toast toast = Toast.makeText(context, response, duration);
                            toast.show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, "tetzx", duration);
                toast.show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

        return true;
    }

    private View.OnClickListener notifyFunction = new View.OnClickListener() {
        public void onClick(View v) {


            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);

            mBuilder.setSmallIcon(R.drawable.bg);
            mBuilder.setContentTitle("Notification Alert, Click Me!");
            mBuilder.setContentText("Hi, This is Android Notification Detail!");

            Intent resultIntent = new Intent(MainActivity.this, CheckBoxScreen.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
            stackBuilder.addParentStack(MainActivity.class);

            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);

// Adds the Intent that starts the Activity to the top of the stack
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(23134, mBuilder.build());


        }

        ;
    };
}