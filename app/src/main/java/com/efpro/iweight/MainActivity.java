package com.efpro.iweight;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Inisialisasi View
    Button btnBMI, btnKalori, btnTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBMI = (Button) findViewById(R.id.btnBMI);
        btnKalori = (Button) findViewById(R.id.btnKalori);
        btnTips = (Button) findViewById(R.id.btnTips);

        btnBMI.setOnClickListener(this);
        btnKalori.setOnClickListener(this);
        btnTips.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBMI) {
            Intent intent = new Intent(this, BMIActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnKalori) {
            Intent intent = new Intent(this, KaloriActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnTips) {
            Intent intent = new Intent(this, TipsActivity.class);
            startActivity(intent);
        }
    }
}