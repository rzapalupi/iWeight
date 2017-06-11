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
    Button btnLogin, btnRegister;
    DBHandler db = new DBHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inisialisasi ID
        edtPass     = (EditText) findViewById(R.id.edtPass);
        edtUser     = (EditText) findViewById(R.id.edtUser);
        btnLogin    = (Button) findViewById(R.id.btnLogin);
        btnRegister    = (Button) findViewById(R.id.btnRegister);

        //Event
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
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

        AddData();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
//            //Logika Button Login
//            Toast.makeText(this, "Button Login Disentuh",
//                    Toast.LENGTH_SHORT).show();
            Validasi();
        } else if (v.getId() == R.id.btnRegister){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }


    public void Validasi (){
        if (edtUser.getText().toString().isEmpty() &&
                edtPass.getText().toString().isEmpty()) {
            edtUser.setError("User is empty");
            edtPass.setError("Password is empty");
        } else if (db.checkUser(edtUser.getText().toString().trim())) {
            if (db.checkUser(edtUser.getText().toString().trim(), edtPass.getText().toString().trim())) {
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

    public void AddData(){
        db.ClearData();

        db.addUser(new User("admin", "admin", "admin", 21, "Pria", 46.0, 164.0));

        db.addFood(new Food(0    ,   "Pilih menu makanan anda " ,   0   ));
        db.addFood(new Food(1    ,   "Ayam Goreng Kecap"        ,   358 ));
        db.addFood(new Food(2    ,   "Ayam Kentucky Paha Atas"  ,   194 ));
        db.addFood(new Food(3    ,   "Ayam Panggang"            ,   385 ));
        db.addFood(new Food(4    ,   "Bakso Sapi dan Mie"       ,   325 ));
        db.addFood(new Food(5    ,   "Bubur Ayam"               ,   165 ));
        db.addFood(new Food(6    ,   "Ikan Teri Goreng"         ,   66  ));
        db.addFood(new Food(7    ,   "Mie Instant"              ,   168 ));
        db.addFood(new Food(8    ,   "Nasi Goreng"              ,   267 ));
        db.addFood(new Food(9    ,   "Sayap Ayam"               ,   63  ));
        db.addFood(new Food(10   ,   "Sayur Asem"               ,   88  ));
        db.addFood(new Food(11   ,   "Sayur Lodeh"              ,   61  ));
        db.addFood(new Food(12   ,   "Tahu Bacem"               ,   147 ));
        db.addFood(new Food(13   ,   "Tahu Isi"                 ,   124 ));
        db.addFood(new Food(14   ,   "Telur Ayam Rebus"         ,   97  ));
        db.addFood(new Food(15   ,   "Telur Dadar "             ,   188 ));
        db.addFood(new Food(16   ,   "Telur Mata Sapi"          ,   40  ));
        db.addFood(new Food(17   ,   "Tempe Bacem"              ,   157 ));
        db.addFood(new Food(18   ,   "Tempe Goreng"             ,   118 ));
    }
}
