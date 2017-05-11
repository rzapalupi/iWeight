package com.efpro.iweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    ArrayList<String> foodList = new ArrayList<String>();
    private int sumcalorie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori);

        txtKalori   = (TextView) findViewById(R.id.txtKalori);
        txtKalori2   = (TextView) findViewById(R.id.txtKalori2);
        spinner     = (Spinner) findViewById(R.id.spinner);
        listview    = (ListView) findViewById(R.id.listview);
        spinner.setOnItemSelectedListener(this);

        //listview.setLayoutParams(new ListView.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, 500));


        DBHandler db = new DBHandler(this);
        db.dropFood();
        // Inserting Foods/Rows
        db.addFood(new Food(0," ", 0));
        db.addFood(new Food(1,"Ayam", 100));
        db.addFood(new Food(2,"Ikan", 150));
        db.addFood(new Food(3,"Daging", 200));
        db.addFood(new Food(4,"Kuda", 1000));

        Intent intent   = getIntent();
        String hasilbmr = intent.getStringExtra("hasilmbr");

        txtKalori.setText(hasilbmr);
        loadSpinnerData();
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void loadSpinnerData(){
        DBHandler db = new DBHandler(getApplicationContext());
        List<Food> foods = db.getAllFoods();

        String[] nameList=new String[foods.size()];

        for(int i=0;i<foods.size();i++){
            nameList[i]=foods.get(i).getName(); //create array of name
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String category = parent.getItemAtPosition(position).toString();

        DBHandler db = new DBHandler(getApplicationContext());
        List<Food> foods = db.getAllFoods();
        int[] calorieList=new int[foods.size()];
        for(int i=0;i<foods.size();i++){
            calorieList[i]=foods.get(i).getCalorie();
        }
        int calorie = calorieList[position];

            Add(category, String.valueOf(calorie));
            sumcalorie = sumcalorie + calorie;
            txtKalori2.setText(String.valueOf(sumcalorie) + "ccal");

    }

    public void Add(String cat, String cal){
        foodList.add(cat);
        foodList.add(String.valueOf(cal));

        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, foodList);
        listview.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
