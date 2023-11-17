package com.devin.c19_alerts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView cases, recoverde, active, totalDeaths, todayDeaths, affcountry, critical, todayCase,globalstates,indiastats;
    ScrollView scrollView;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    Button gloable,india;
    RelativeLayout affact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cases = findViewById(R.id.casesTv);
        recoverde = findViewById(R.id.recovered);
        active = findViewById(R.id.activeTv);
        totalDeaths = findViewById(R.id.totaldeathTv);
        todayDeaths = findViewById(R.id.todaydeathTv);
        todayCase = findViewById(R.id.todaycaseTv);
        affcountry = findViewById(R.id.affectcntryTv);
        critical = findViewById(R.id.criticaltv);

        gloable=findViewById(R.id.gloablbtn);
        india=findViewById(R.id.indiabtn);

        affact=findViewById(R.id.relafft);
        globalstates=findViewById(R.id.globalstates);
        indiastats=findViewById(R.id.indiastats);
        scrollView = findViewById(R.id.scroolstta);
        simpleArcLoader = findViewById(R.id.loader);
        pieChart = findViewById(R.id.piechart);
        fetchData();
       gloable.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               globalstates.setVisibility(View.VISIBLE);
               indiastats.setVisibility(View.GONE);
               pieChart.clearChart();
               fetchCountry();
               gloable.setVisibility(View.GONE);
               india.setVisibility(View.VISIBLE);
           }
       });
       india.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               globalstates.setVisibility(View.GONE);
               indiastats.setVisibility(View.VISIBLE);
               pieChart.clearChart();
               fetchData();
               gloable.setVisibility(View.VISIBLE);
               india.setVisibility(View.GONE);

           }
       });

    }

    private void fetchCountry() {
        affact.setVisibility(View.VISIBLE);
        String url = "https://corona.lmao.ninja/v2/all";
        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    affcountry.setText(jsonObject.getString("affectedCountries"));
                    cases.setText(jsonObject.getString("cases"));
                    recoverde.setText(jsonObject.getString("recovered"));
                    active.setText(jsonObject.getString("active"));
                    totalDeaths.setText(jsonObject.getString("deaths"));
                    todayDeaths.setText(jsonObject.getString("todayDeaths"));
                    todayCase.setText(jsonObject.getString("todayCases"));
                    critical.setText(jsonObject.getString("critical"));

                    pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(cases.getText().toString()), Color.parseColor("#FF7700")));
                    pieChart.addPieSlice(new PieModel("Death", Integer.parseInt(totalDeaths.getText().toString()), Color.parseColor("#B60400")));
                    pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(recoverde.getText().toString()), Color.parseColor("#009A07")));
                    pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(active.getText().toString()), Color.parseColor("#0082BD")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void fetchData() {
        affact.setVisibility(View.GONE);
        String url = "https://corona.lmao.ninja/v2/countries/india";
        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    cases.setText(jsonObject.getString("cases"));
                    recoverde.setText(jsonObject.getString("recovered"));
                    active.setText(jsonObject.getString("active"));
                    totalDeaths.setText(jsonObject.getString("deaths"));
                    todayDeaths.setText(jsonObject.getString("todayDeaths"));
                    todayCase.setText(jsonObject.getString("todayCases"));
                    critical.setText(jsonObject.getString("critical"));
                    pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(cases.getText().toString()), Color.parseColor("#FF7700")));
                    pieChart.addPieSlice(new PieModel("Death", Integer.parseInt(totalDeaths.getText().toString()), Color.parseColor("#B60400")));
                    pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(recoverde.getText().toString()), Color.parseColor("#009A07")));
                    pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(active.getText().toString()), Color.parseColor("#0082BD")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void track(View view) {
        startActivity(new Intent(getApplicationContext(),CountryActivity.class));
    }
}