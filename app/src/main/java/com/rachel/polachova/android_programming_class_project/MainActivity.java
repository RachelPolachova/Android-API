package com.rachel.polachova.android_programming_class_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button searchButton = (Button) findViewById(R.id.searchButton);

		searchButton.setOnClickListener(
				new Button.OnClickListener() {
					public void onClick (View v) {
						final TextView nameTextView = (TextView) findViewById(R.id.name);
						getCharacters(nameTextView.getText().toString());
					}
				}
		);

	}

	void getCharacters(String name) {
		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(this);
		String url ="https://swapi.co/api/people/?search="+name;

// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							JSONObject jsonResponse = new JSONObject(response);
							System.out.println(jsonResponse);
						} catch (JSONException e) {
							System.out.println("Error in response, while convertin into JSON: " + e.getLocalizedMessage());
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				System.out.println("That didn't work!");
			}
		});

// Add the request to the RequestQueue.
		queue.add(stringRequest);

	}





}
