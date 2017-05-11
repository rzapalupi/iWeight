package com.efpro.iweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Inisialisasi View
    EditText edtUser, edtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inisialisasi ID
        edtPass     = (EditText) findViewById(R.id.edtPass);
        edtUser     = (EditText) findViewById(R.id.edtUser);
        btnLogin    = (Button) findViewById(R.id.btnLogin);

        //Event
        btnLogin.setOnClickListener(this);
        edtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUser.setHint("");
            }
        });
        edtUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                edtUser.setHint("Username");
            }
        });

        edtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPass.setHint("");
            }
        });
        edtPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                edtPass.setHint("Password");
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
//            //Logika Button Login
//            Toast.makeText(this, "Button Login Disentuh",
//                    Toast.LENGTH_SHORT).show();
            Validasi();
        }
    }

    public void Validasi (){
        if (edtUser.getText().toString().isEmpty() &&
                edtPass.getText().toString().isEmpty()) {
            edtUser.setError("User is empty");
            edtPass.setError("Password is empty");
        } else if (edtUser.getText().toString().equalsIgnoreCase("user")) {
            if (edtPass.getText().toString().equalsIgnoreCase("user")) {
//                Toast.makeText(this, "Login Sukses",
//                        Toast.LENGTH_SHORT).show();

                //Pindah Halaman
                Intent intent = new Intent(LoginActivity.this ,MainActivity.class);
                startActivity(intent);
            } else {
                edtPass.setError("Invalid Password");
            }
        } else{
            edtUser.setError("Invalid User");

        }
    }
}
