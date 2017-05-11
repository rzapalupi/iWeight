package com.efpro.iweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rzapalupi on 5/8/2017.
 */

public class BMIActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    //inisialisasi
    TextView txtResultBMI;
    EditText edtUmur, edtBeratBadan, edtTinggiBadan;
    RadioButton rdbPria, rdbWanita;
    RadioGroup rdgGender;
    Button btnCount, btnReset;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        edtUmur         = (EditText) findViewById(R.id.edtUmur);
        edtBeratBadan   = (EditText) findViewById(R.id.edtBeratBadan);
        edtTinggiBadan  = (EditText) findViewById(R.id.edtTinggiBadan);

        rdbPria         = (RadioButton) findViewById(R.id.rdbPria);
        rdbWanita       = (RadioButton) findViewById(R.id.rdbWanita);

        rdgGender       = (RadioGroup) findViewById(R.id.rdgGender);

        txtResultBMI    = (TextView) findViewById(R.id.txtResultBMI);

        btnCount        = (Button) findViewById(R.id.btnCount);
        btnReset        = (Button) findViewById(R.id.btnReset);

        spinner         = (Spinner) findViewById(R.id.spinner);


        //Event
        btnCount.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.aktifitas_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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
        String status       = null;
        String deskripsi    = null;
        boolean isChecked = (rdbPria.isChecked() || rdbWanita.isChecked());
        if (v.getId() == R.id.btnCount) {
            if (!isChecked || edtBeratBadan.getText().toString().isEmpty() || edtTinggiBadan.getText().toString().isEmpty() ||
                    edtUmur.toString().isEmpty()) {
                if(!isChecked) {
                    Toast.makeText(this, "Check Gender", Toast.LENGTH_SHORT).show();
                }
                if (edtUmur.getText().toString().isEmpty()) {
                    edtUmur.setError("Empty");
                }
                if (edtBeratBadan.getText().toString().isEmpty()) {
                    edtBeratBadan.setError("Empty");
                }
                if (edtTinggiBadan.getText().toString().isEmpty()) {
                    edtTinggiBadan.setError("Empty");
                }
            } else if (rdbPria.isChecked() || rdbWanita.isChecked()) {
                if (CountBMI() < 18.5) {
                    status  = "Underweight";
                    deskripsi = "Kurus lu! makan sana yang banyak";
                } else if (CountBMI() >= 18.5 && CountBMI() <= 24.9) {
                    status = "Normal";
                    deskripsi = "Mantap, ideal banget makan sehat rajin olahraga";
                } else if (CountBMI() >= 25 && CountBMI() <= 29.9) {
                    status = "Overweight";
                    deskripsi = "Gembrot, olahraga sana, jangan ngemil terus";
                } else if (CountBMI() >= 30) {
                    status = "Obesity";
                    deskripsi = "Mending ke dokter coy, ngapain pake aplikasi ini";
                }
                CountBMR();
                String resultBMI = String.format("%.2f",CountBMI());
                String resultBMR = String.format("%.2f",CountBMR());
                Intent intent = new Intent(this, BMIResultActivity.class);
                intent.putExtra("resultBMI",resultBMI);
                intent.putExtra("resultBMR",resultBMR);
                intent.putExtra("status",status);
                intent.putExtra("deskripsi",deskripsi);
                startActivity(intent);
            }
        }
        else if (v.getId() == R.id.btnReset) {
            edtBeratBadan.setText("");
            edtTinggiBadan.setText("");
            edtUmur.setText("");
            rdbPria.setChecked(false);
            rdbWanita.setChecked(false);
        }
    }

    public double CountBMI() {
        double bb = Double.parseDouble(edtBeratBadan.getText().toString());
        double tb = Double.parseDouble(edtTinggiBadan.getText().toString());
        double bmi = 0;

        bmi = bb / ((tb/100) * (tb/100));

        return bmi;
    }

    public double CountBMR() {
        double bb = Double.parseDouble(edtBeratBadan.getText().toString());
        double tb = Double.parseDouble(edtTinggiBadan.getText().toString());
        double u = Double.parseDouble(edtUmur.getText().toString());
        double bmr = 0;
        double indexbmr = 0;
        if (spinner.getSelectedItem().toString().equals("Tidak Aktif")){
            indexbmr = 1.2;
        } else if (spinner.getSelectedItem().toString().equals("Kurang Aktif")){
            indexbmr = 1.375;
        } else if (spinner.getSelectedItem().toString().equals("Aktif")){
            indexbmr = 1.55;
        } else if (spinner.getSelectedItem().toString().equals("Sangat Aktif")){
            indexbmr = 1.725;
        }

        if (rdbPria.isChecked()){
            //BMR_Pria = 66 + (13,7 BB) + (5 TB) – (6,8 U)
            bmr = (66 + (13.7 * bb) + (5 * tb) - (6.8 * u)) * indexbmr;
        } else if (rdbWanita.isChecked()){
            //BMR_Wanita = 65,5 + (9,6 BB) + (1,8 TB) – (4,7 U)
            bmr = (65.5 + (9.6 * bb) + (1.8 * tb) - (4.7 * u)) * indexbmr;
        }
        return bmr;
    }
}
