package com.efpro.iweight;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnShare, btnFeedback;
    Intent shareIntent, feedbackIntent, feedback;
    String sharebody = "Coba aplikasi ini iWeight v1.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnShare = (Button) findViewById(R.id.btnShare);
        btnFeedback = (Button) findViewById(R.id.btnFeedback);

        btnShare.setOnClickListener(this);
        btnFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnShare) {
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/pain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "iWeight");
            shareIntent.putExtra(Intent.EXTRA_TEXT, sharebody);
            startActivity(Intent.createChooser(shareIntent, "Share Via"));
        } else if (v.getId() == R.id.btnFeedback) {
            feedbackIntent = new Intent(Intent.ACTION_SEND);
            feedbackIntent.putExtra(Intent.EXTRA_EMAIL, "husain7723@students.amikom.ac.id");
            feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, "Email Feedback");
            feedbackIntent.putExtra(Intent.EXTRA_TEXT, "Aplikasi nya keren mas, wkwkwk");
            feedbackIntent.setType("message/rfc822");
            feedback = Intent.createChooser(feedbackIntent, "Kirim Feedback");
            startActivity(feedback);
        }
    }
}
