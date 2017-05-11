package com.efpro.iweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rzapalupi on 5/9/2017.
 */

public class KaloriActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtKalori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori);

        txtKalori   = (TextView) findViewById(R.id.txtKalori);

        Intent intent   = getIntent();
        String hasilbmr = intent.getStringExtra("hasilmbr");

        txtKalori.setText(hasilbmr);
    }

    @Override
    public void onClick(View v) {


    }
}
