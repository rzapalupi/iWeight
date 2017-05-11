package com.efpro.iweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TipsActivity extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout Berita1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        Berita1 = (RelativeLayout) findViewById(R.id.rlBerita);

        Berita1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rlBerita) {
            Intent intent = new Intent(this, TipsViewActivity.class);
            startActivity(intent);
        }
    }
}
