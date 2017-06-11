package com.efpro.iweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Zhiezz on 5/25/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRegister, btnCancel;
    EditText edtUserReg, edtPassReg, edtPass2Reg, edtNamaReg, edtUmurReg, edtBeratBadanReg, edtTinggiBadanReg;
    RadioButton rdbPriaReg, rdbWanitaReg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUserReg          = (EditText) findViewById(R.id.edtUserReg);
        edtPassReg          = (EditText) findViewById(R.id.edtPassReg);
        edtPass2Reg         = (EditText) findViewById(R.id.edtPass2Reg);
        edtNamaReg          = (EditText) findViewById(R.id.edtNamaReg);
        edtUmurReg          = (EditText) findViewById(R.id.edtUmurReg);
        edtBeratBadanReg    = (EditText) findViewById(R.id.edtBeratBadanReg);
        edtTinggiBadanReg   = (EditText) findViewById(R.id.edtTinggiBadanReg);
        rdbPriaReg          = (RadioButton) findViewById(R.id.rdbPriaReg);
        rdbWanitaReg        = (RadioButton) findViewById(R.id.rdbWanitaReg);


        btnRegister    = (Button) findViewById(R.id.btnRegister);
        btnCancel   = (Button) findViewById(R.id.btnCancel);

        btnRegister.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rdbPriaReg:
                if (checked)
                    break;
            case R.id.rdbWanitaReg:
                if (checked)
                    break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            Registrasi();

        } else if (v.getId() == R.id.btnCancel) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void Registrasi() {
        DBHandler db = new DBHandler(this);
        boolean isChecked = (rdbPriaReg.isChecked() || rdbWanitaReg.isChecked());
        if (!isChecked ||
                db.checkUser(edtUserReg.getText().toString().trim()) ||
                edtUserReg.getText().toString().isEmpty() ||
                edtPassReg.getText().toString().isEmpty() ||
                edtPass2Reg.getText().toString().isEmpty() ||
                edtNamaReg.getText().toString().isEmpty() ||
                edtUmurReg.toString().isEmpty() ||
                edtBeratBadanReg.getText().toString().isEmpty() ||
                edtTinggiBadanReg.getText().toString().isEmpty()) {
            if(db.checkUser(edtUserReg.getText().toString().trim())){
                edtUserReg.setError("Username not Available");
            }
            if (!isChecked) {
                Toast.makeText(this, "Check Gender", Toast.LENGTH_SHORT).show();
            }
            if (edtUserReg.getText().toString().isEmpty()) {
                edtUserReg.setError("Empty");
            }
            if (edtPassReg.getText().toString().isEmpty()) {
                edtPassReg.setError("Empty");
            } else if (!(edtPass2Reg.getText().toString()).equals(edtPassReg.getText().toString())) {
                edtPass2Reg.setError("Password didnt match");
            }
            if (edtNamaReg.getText().toString().isEmpty()) {
                edtNamaReg.setError("Empty");
            }
            if (edtUmurReg.getText().toString().isEmpty()) {
                edtUmurReg.setError("Empty");
            }
            if (edtBeratBadanReg.getText().toString().isEmpty()) {
                edtBeratBadanReg.setError("Empty");
            }
            if (edtTinggiBadanReg.getText().toString().isEmpty()) {
                edtTinggiBadanReg.setError("Empty");
            }
        } else if (rdbPriaReg.isChecked() || rdbWanitaReg.isChecked()){
            String username = edtUserReg.getText().toString();
            String password = edtPass2Reg.getText().toString();
            String nama = edtNamaReg.getText().toString();
            int umur = Integer.parseInt(edtUmurReg.getText().toString());
            String gender = null;
            if (rdbPriaReg.isChecked()) {
                gender = rdbPriaReg.getText().toString();
            } else if (rdbWanitaReg.isChecked()) {
                gender = rdbWanitaReg.getText().toString();
            }
            double bb = Double.parseDouble(edtBeratBadanReg.getText().toString());
            double tb = Double.parseDouble(edtTinggiBadanReg.getText().toString());
            db.addUser(new User(username, password, nama, umur, gender, bb, tb ));
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            }
        }
}
