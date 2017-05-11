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

        txtKalori       = (TextView) findViewById(R.id.txtKalori);
        txtKalori2      = (TextView) findViewById(R.id.txtKalori2);
        spinner         = (Spinner) findViewById(R.id.spinner);
        listview       = (ListView) findViewById(R.id.listview);
        btnResetKalori  = (Button) findViewById(R.id.btnResetKalori);
        spinner.setOnItemSelectedListener(this);
        btnResetKalori.setOnClickListener(this);

        //listview.setLayoutParams(new ListView.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, 500));


        DBHandler db = new DBHandler(this);
        db.dropFood();
        // Inserting Foods/Rows
        db.addFood(new Food(0    ,   "Pilih menu makanan anda " ,   0   ));
        db.addFood(new Food(1    ,   "Ayam Goreng Kecap"        ,   358 ));
        db.addFood(new Food(2    ,   "Ayam Kentucky Paha Atas"  ,   194 ));
        db.addFood(new Food(3    ,   "Ayam Panggang"            ,   385 ));
        db.addFood(new Food(4    ,   "Bakso Sapi dan Mie"       ,   325 ));
        db.addFood(new Food(5    ,   "Bubur Ayam"               ,   165 ));
        db.addFood(new Food(6    ,   "Ikan Teri Goreng"         ,   66  ));
        db.addFood(new Food(7    ,   "Mie Instant"              ,   168 ));
        db.addFood(new Food(8    ,   "Nasi Goreng"              ,   267 ));
        db.addFood(new Food(9    ,   "Sayap Ayam"               ,   63  ));
        db.addFood(new Food(10   ,   "Sayur Asem"               ,   88  ));
        db.addFood(new Food(11   ,   "Sayur Lodeh"              ,   61  ));
        db.addFood(new Food(12   ,   "Tahu Bacem"               ,   147 ));
        db.addFood(new Food(13   ,   "Tahu Isi"                 ,   124 ));
        db.addFood(new Food(14   ,   "Telur Ayam Rebus"         ,   97  ));
        db.addFood(new Food(15   ,   "Telur Dadar "             ,   188 ));
        db.addFood(new Food(16   ,   "Telur Mata Sapi"          ,   40  ));
        db.addFood(new Food(17   ,   "Tempe Bacem"              ,   157 ));
        db.addFood(new Food(18   ,   "Tempe Goreng"             ,   118 ));

        Intent intent   = getIntent();
        String hasilbmr = intent.getStringExtra("hasilmbr");

        txtKalori.setText(hasilbmr);
        loadSpinnerData();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnResetKalori){
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

    private void loadSpinnerData(){
        DBHandler db = new DBHandler(getApplicationContext());
        List<Food> foods = db.getAllFoods();

        String[][] nameList = new String[foods.size()][2];

        for(int i=0;i<foods.size();i++){
            nameList[i][0]=foods.get(i).getName();
            nameList[i][1]= String.valueOf(foods.get(i).getCalorie());
        }
        String [] nameList2 =  new String[foods.size()];
        for(int i=0;i<foods.size();i++){
            if(i==0){
                nameList2[i]= nameList[i][0];
            } else {
                nameList2[i]= nameList[i][0] +" - "+ nameList[i][1] + " ccal";
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
        int[] calorieList=new int[foods.size()];
        for(int i=0;i<foods.size();i++){
            calorieList[i]=foods.get(i).getCalorie();
        }
        int calorie = calorieList[position];

        if(position > 0) {
            Add(category, String.valueOf(calorie));
            sumcalorie = sumcalorie + calorie;
            txtKalori2.setText(String.valueOf(sumcalorie) + "ccal");
            spinner.setSelection(0);
        }
    }

    public void Add(String cat, String cal){
        foodList.add(cat);

        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, foodList);
        listview.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
