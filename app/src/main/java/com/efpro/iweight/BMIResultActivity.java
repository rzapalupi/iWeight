package com.efpro.iweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BMIResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtResultBMI, txtResultBMR, txtStatus, txtDeskripsi;
    Button btnKalori_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        txtResultBMI        = (TextView) findViewById(R.id.txtResultBMI);
        txtResultBMR        = (TextView) findViewById(R.id.txtResultBMR);
        txtStatus           = (TextView) findViewById(R.id.txtStatus);
        txtDeskripsi        = (TextView) findViewById(R.id.txtDeskripsi) ;

        btnKalori_2         = (Button) findViewById(R.id.btnKalori_2);

        Intent intent       = getIntent();
        String hasilbmi     = intent.getStringExtra("resultBMI");
        String hasilbmr     = intent.getStringExtra("resultBMR");
        String statusbmi    = intent.getStringExtra("status");
        String deskripsi    = intent.getStringExtra("deskripsi");

        txtResultBMI.setText(hasilbmi);
        txtResultBMR.setText(hasilbmr + " CCAL");
        txtStatus.setText(statusbmi);
        txtDeskripsi.setText(deskripsi);



        btnKalori_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.btnKalori_2){
            String hasilbmr = txtResultBMR.getText().toString();
            Intent intent = new Intent (this, KaloriActivity.class);
            intent.putExtra("hasilmbr",hasilbmr);
            startActivity(intent);
        }

    }
}
