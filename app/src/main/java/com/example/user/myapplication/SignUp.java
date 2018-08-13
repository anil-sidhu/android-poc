package com.example.user.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        Button btnSubmit = (Button) findViewById(R.id.submit);
        btnSubmit.setOnClickListener(SubmitSignUp);



    }

    private View.OnClickListener SubmitSignUp = new View.OnClickListener() {
        public void onClick(View v) {


            final Context context = getApplicationContext();
            final CharSequence text = "Hello toast!";
            final int duration = Toast.LENGTH_SHORT;
            RequestQueue queue = Volley.newRequestQueue(SignUp.this);
            String url="http://192.168.14.126:3004/posts";

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Toast toast = Toast.makeText(context, response, duration);
                            toast.show();
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error

                            Toast toast = Toast.makeText(context, "error", duration);
                            toast.show();
                        }
                    }
            ) {
                @Override
                protected Map getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    EditText name= (EditText) findViewById(R.id.name);
                    EditText email= (EditText) findViewById(R.id.email);
                    String Swap=  name.getText().toString();
                    //email.setText("Swap ..");


                    params.put("mobile", Swap);
                    params.put("email", "http@info.com");
                    params.put("id", "9116");

                    return params;
                }
            };
            queue.add(postRequest);

        }
    };

}
