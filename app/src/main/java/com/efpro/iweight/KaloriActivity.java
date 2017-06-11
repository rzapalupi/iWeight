package com.efpro.iweight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class KaloriActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    TextView txtKalori, txtKalori2;
    Spinner spinner;
    ListView listview;
    Button btnResetKalori;
    ArrayList<String> foodList = new ArrayList<String>();
    private int sumcalorie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori);

        txtKalori = (TextView) findViewById(R.id.txtKalori);
        txtKalori2 = (TextView) findViewById(R.id.txtKalori2);
        spinner = (Spinner) findViewById(R.id.spinner);
        listview = (ListView) findViewById(R.id.listview);
        btnResetKalori = (Button) findViewById(R.id.btnResetKalori);
        spinner.setOnItemSelectedListener(this);
        btnResetKalori.setOnClickListener(this);


        //db.dropFood();
        // Inserting Foods/Rows


        Intent intent = getIntent();
        String hasilbmr = intent.getStringExtra("hasilmbr");

        txtKalori.setText(hasilbmr);
        loadSpinnerData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnResetKalori) {
            txtKalori2.setText("0 ccal");
            foodList.clear();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void loadSpinnerData() {
        DBHandler db = new DBHandler(getApplicationContext());
        List<Food> foods = db.getAllFoods();

        String[][] nameList = new String[foods.size()][2];

        for (int i = 0; i < foods.size(); i++) {
            nameList[i][0] = foods.get(i).getName();
            nameList[i][1] = String.valueOf(foods.get(i).getCalorie());
        }
        String[] nameList2 = new String[foods.size()];
        for (int i = 0; i < foods.size(); i++) {
            if (i == 0) {
                nameList2[i] = nameList[i][0];
            } else {
                nameList2[i] = nameList[i][0] + " - " + nameList[i][1] + " ccal";
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameList2);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String category = parent.getItemAtPosition(position).toString();

        DBHandler db = new DBHandler(getApplicationContext());
        List<Food> foods = db.getAllFoods();
        int[] calorieList = new int[foods.size()];
        for (int i = 0; i < foods.size(); i++) {
            calorieList[i] = foods.get(i).getCalorie();
        }
        int calorie = calorieList[position];

        if (position > 0) {
            Add(category, String.valueOf(calorie));
            sumcalorie = sumcalorie + calorie;
            txtKalori2.setText(String.valueOf(sumcalorie) + "ccal");
            spinner.setSelection(0);
        }
    }

    public void Add(String cat, String cal) {
        foodList.add(cat);

        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, foodList);
        listview.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
