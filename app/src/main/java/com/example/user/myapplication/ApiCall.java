package com.example.user.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiCall extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_call);

         mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)


        final TextView t2 = (TextView) findViewById(R.id.textView2);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.androidhive.info/contacts/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray contacts = jsonObj.getJSONArray("contacts");
                            String resp=response;
                           // mAdapter = new MyAdapter(resp);
                            //mRecyclerView.setAdapter(mAdapter);
                            for (int i = 0; i < 2; i++) {
                                JSONObject c = contacts.getJSONObject(i);
                                String id = c.getString("id");

                                String email = c.getString("email");

                                // tmp hash map for single contact

                                // adding each child node to HashMap key => value
                                t2.append(id+"\n");
                                t2.append(email+"\n");
//                                contact.put("name", name);
//                                contact.put("email", email);
//                                contact.put("mobile", mobile);

                                // adding contact to contact list
//                                t2.setText("Response is: " + contact);
                            }

                        }
                        catch (JSONException e) {
                            Log.e("MYAPP", "unexpected JSON exception", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t2.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}
