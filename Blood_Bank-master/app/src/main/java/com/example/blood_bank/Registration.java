package com.example.blood_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText n, em, ph, ad, c, un, pw, cp;
    Button reg,bck7;
    RadioGroup gen;
    RadioButton gen_option;
    Spinner bg;
    dbhelper db;
    String strGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new dbhelper(this);

        n = (EditText) findViewById(R.id.etName);
        em = (EditText) findViewById(R.id.etEmail);
        ph = (EditText) findViewById(R.id.etPhone);
        ad = (EditText) findViewById(R.id.etAddress);
        c = (EditText) findViewById(R.id.etCity);
        un = (EditText) findViewById(R.id.etUserName2);
        gen = (RadioGroup) findViewById(R.id.rbGender);
        reg = (Button) findViewById(R.id.btnRegister);
        bck7 = (Button) findViewById(R.id.btnRBck);
        bg = (Spinner) findViewById(R.id.spBGroup);

        String ar[] = {"A+", "B+", "O+", "O-", "AB+"};
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, ar);
        bg.setAdapter(ad);

        gen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                gen_option = gen.findViewById(i);

                switch (i) {
                    case R.id.rbMale:
                        strGen = gen_option.getText().toString();
                        break;

                    case R.id.rbFemale:
                        strGen = gen_option.getText().toString();
                        break;

                    default:
                }
            }
        });
    }

    public void register(View v) {
        if (n.getText().toString().equals("")) {
            n.setError("Name is must");
        }
        if (em.getText().toString().equals("")) {
            em.setError("Email is must");
        }
        if (ph.getText().toString().equals("")) {
            ph.setError("Phone is must");
        }
        if (ad.getText().toString().equals("")) {
            ad.setError("Address is must");
        }
        if (c.getText().toString().equals("")) {
            c.setError("City is must");
        }
         else {
             Boolean ins = db.insertDon(n.getText().toString(), strGen, em.getText().toString(), ph.getText().toString(), ad.getText().toString(), c.getText().toString(), bg.getSelectedItem().toString(), "", "");
             if (ins) {
                 Toast.makeText(getApplicationContext(), "You are Registered successfully", Toast.LENGTH_LONG).show();
                 Intent i = new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(i);
             }

        }

    }

    public void ReBack(View v)
    {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
