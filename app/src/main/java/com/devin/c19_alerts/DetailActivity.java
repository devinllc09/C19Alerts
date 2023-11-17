package com.devin.c19_alerts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
     private  int positionCountry;
    TextView cases, recoverde, active, totalDeaths, todayDeaths, countrys, critical, todayCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=new Intent();
        positionCountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+ CountryActivity.counterModelList.get(positionCountry).getCountery());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cases = findViewById(R.id.casetvd);
        recoverde = findViewById(R.id.crecoverdetvd);
        active = findViewById(R.id.activeTvD);
        totalDeaths = findViewById(R.id.deathsTvD);
        todayDeaths = findViewById(R.id.todaydeathstvd);
        todayCase = findViewById(R.id.todayCaseTvD);
        critical = findViewById(R.id.criticaltvd);
        countrys = findViewById(R.id.countrytvd);

        countrys.setText(CountryActivity.counterModelList.get(positionCountry).getCountery());
        cases.setText(CountryActivity.counterModelList.get(positionCountry).getCases());
        active.setText(CountryActivity.counterModelList.get(positionCountry).getActive());
        recoverde.setText(CountryActivity.counterModelList.get(positionCountry).getRecovered());
        totalDeaths.setText(CountryActivity.counterModelList.get(positionCountry).getDeaths());
        todayCase.setText(CountryActivity.counterModelList.get(positionCountry).getTodayCases());
        todayDeaths.setText(CountryActivity.counterModelList.get(positionCountry).getTodayDeaths());
        critical.setText(CountryActivity.counterModelList.get(positionCountry).getCritical());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}