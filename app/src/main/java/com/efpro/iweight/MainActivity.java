package com.efpro.iweight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Inisialisasi View
    Button btnBMI, btnKalori, btnTips, btnAbout;
    TextView txtNama, txtUmur,  txtGender, txtBeratBadan, txtTinggiBadan, btnEdit, txtStatusMenu;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBMI = (Button) findViewById(R.id.btnBMI);
        btnKalori = (Button) findViewById(R.id.btnKalori);
        btnTips = (Button) findViewById(R.id.btnTips);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnEdit = (TextView) findViewById(R.id.btnEdit);


        txtBeratBadan = (TextView) findViewById(R.id.txtBeratBadan);
        txtTinggiBadan = (TextView) findViewById(R.id.txtTinggiBadan);
        txtNama = (TextView) findViewById(R.id.txtNama);
        txtUmur = (TextView) findViewById(R.id.txtUmur);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtStatusMenu = (TextView) findViewById(R.id.txtStatusMenu);

        Intent intent   = getIntent();
        String v_nama   = intent.getStringExtra("txtN  ama");
        String v_umur   = intent.getStringExtra("txtUmur");
        String v_gender = intent.getStringExtra("txtGender");
        String v_bb     = intent.getStringExtra("txtBeratBadan");
        String v_tb     = intent.getStringExtra("txtTinggiBadan");
        String v_status = intent.getStringExtra("txtStatusMenu");
        String v_ideal  = intent.getStringExtra("txtBeratBadanIdealMenu");

//              if(txtStatusMenu != null){
//            txtStatusMenu.setText(v_status);
//        }

        txtNama.setText(v_nama);
        txtUmur.setText(v_umur);
        txtGender.setText(v_gender);
        txtBeratBadan.setText("Berat Badan :" + v_bb);
        txtTinggiBadan.setText("Tinggi Badan :" + v_tb);
        txtStatusMenu.setText(v_status);

        btnBMI.setOnClickListener(this);
        btnKalori.setOnClickListener(this);
        btnTips.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
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
        } else if (v.getId() == R.id.btnAbout) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnEdit) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
    }

}