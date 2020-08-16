package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Instantiate the RequestQueue.
        final RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        // Request array response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos", null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {

                    for(int i =0;i<response.length();i++){

                        JSONObject obj = response.getJSONObject(i);

                        Log.d("myApp", "" + "onResponse: " +
                                "   \nUser id is:    " +obj.getInt("userId")+
                                "   \nTitle is:      " +obj.getString("title")+
                                "   \nUser Id is:    " + obj.getInt("id")+
                                "   \ncompleted is:  " + obj.getBoolean("completed")
                        );
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(MainActivity.this,"somthing went wrong",Toast.LENGTH_SHORT).show();
            }
        });


        // Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }
}
