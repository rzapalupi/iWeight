package com.efpro.iweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.efpro.iweight.R.id.txtStatusMenu;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    String v_nama, v_umur, v_bb, v_tb, v_gender;
    Button btnSave, btnCancel;
    Double d_bb, d_tb;
    EditText edtNama, edtUmur, edtBeratBadan, edtTinggiBadan;
    RadioButton rdbPria, rdbWanita;
    RadioGroup rdgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtNama = (EditText)findViewById(R.id.edtNama);
        edtUmur = (EditText)findViewById(R.id.edtUmur);
        edtBeratBadan = (EditText)findViewById(R.id.edtBeratBadan);
        edtTinggiBadan = (EditText)findViewById(R.id.edtTinggiBadan);
        rdgGender = (RadioGroup)findViewById(R.id.rdgGender);
        rdbPria = (RadioButton)findViewById(R.id.rdbPria);
        rdbWanita = (RadioButton)findViewById(R.id.rdbWanita);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rdbPria:
                if (checked)
                    break;
            case R.id.rdbWanita:
                if (checked)
                    break;
        }
    }

    @Override
    public void onClick(View v) {
        String txtStatus = null;
        if (v.getId() == R.id.btnSave) {
            v_nama = edtNama.getText().toString();
            v_umur = edtUmur.getText().toString();
            v_bb = edtBeratBadan.getText().toString();
            v_tb = edtTinggiBadan.getText().toString();

            if (rdbPria.isChecked()){
                v_gender = rdbPria.getText().toString();
            } else if (rdbWanita.isChecked()){
                v_gender = rdbWanita.getText().toString();
            }

            double tes =  CountBMI();
            if (tes < 18.5) {
                txtStatus = "Underweight";
            } else if (tes >= 18.5 && tes <= 24.9) {
                txtStatus = "Normal";
            } else if (tes >= 25 && tes <= 29.9) {
                txtStatus = "Overweight";
            } else if (tes >= 30) {
                txtStatus = "Obesity";
            }

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("txtNama", v_nama);
            intent.putExtra("txtUmur", v_umur);
            intent.putExtra("txtBeratBadan", v_bb);
            intent.putExtra("txtTinggiBadan", v_tb);
            intent.putExtra("txtGender", v_gender);
            intent.putExtra("txtStatusMenu", txtStatus);

            Toast toast = Toast.makeText(getApplicationContext(),"Profile Diperbarui",Toast.LENGTH_SHORT);
            toast.show();
            startActivity(intent);
        } else if (v.getId() == R.id.btnCancel) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public double CountBMI() {
        double bb = Double.parseDouble(edtBeratBadan.getText().toString());
        double tb = Double.parseDouble(edtTinggiBadan.getText().toString());
        double bmi = 0;

        bmi = bb / ((tb/100) * (tb/100));

        return bmi;
    }
}
