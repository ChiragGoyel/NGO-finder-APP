package com.example.blood_bank;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Donor_details extends AppCompatActivity {

    EditText dn,dg,de,dp,da,dc,db;
    dbhelper mydb;
    String id2;
    Cursor c;
    Button bck1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);
        mydb = new dbhelper(this);

        dn = (EditText) findViewById(R.id.etDName);
        dg = (EditText) findViewById(R.id.etDGen);
        de = (EditText) findViewById(R.id.etDEm);
        dp = (EditText) findViewById(R.id.etDPh);
        da = (EditText) findViewById(R.id.etDAdd);
        dc = (EditText) findViewById(R.id.etDCity);
        db = (EditText) findViewById(R.id.etDBldgrp);
        bck1 = (Button) findViewById(R.id.btnDBack);
        try {
            Intent i = getIntent();
            id2 = i.getStringExtra("sm");

            //Toast.makeText(getApplicationContext(), id2, Toast.LENGTH_LONG).show();
            c = mydb.getDon_details(id2);
            c.moveToFirst();

            if(!c.isAfterLast())
            {
                dn.setText(c.getString(1));
                dg.setText(c.getString(2));
                de.setText(c.getString(3));
                dp.setText(c.getString(4));
                da.setText(c.getString(5));
                dc.setText(c.getString(6));
                db.setText(c.getString(7));
          }
            c.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }
    }
    public void clickback(View v)
    {
        Intent i = new Intent(getApplicationContext(),ADon_List.class);
        startActivity(i);
    }
}
