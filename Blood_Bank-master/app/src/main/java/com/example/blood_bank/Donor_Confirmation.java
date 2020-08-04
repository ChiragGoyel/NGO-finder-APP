package com.example.blood_bank;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Donor_Confirmation extends AppCompatActivity {


    EditText dn,dg,de,dp,da,dc,db;
    dbhelper mydb;
    String donor_name, user_name;
    int user_id, donor_id;
    Cursor c;
    Button bck1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor__confirmation);



        mydb = new dbhelper(this);

//        dn = (EditText) findViewById(R.id.etDName);
        dg = (EditText) findViewById(R.id.editText2);
        de = (EditText) findViewById(R.id.editText3);
        dp = (EditText) findViewById(R.id.editText5);
        da = (EditText) findViewById(R.id.editText);
        dc = (EditText) findViewById(R.id.editText4);
        db = (EditText) findViewById(R.id.editText6);

        try {
            Intent i = getIntent();
            donor_name = i.getStringExtra("ss");
            user_name = i.getStringExtra("user_id");

            c = mydb.getDon_details(donor_name);

            Toast.makeText(getApplicationContext(),donor_name,Toast.LENGTH_LONG).show();
            c.moveToFirst();



            if(!c.isAfterLast())
            {

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

    public void confirmClick(View v)
    {

        boolean fin = mydb.insertTran(user_name,donor_name);
        if(fin){
            Toast.makeText(getApplicationContext(),"CONFIRMED",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"SHIT",Toast.LENGTH_LONG).show();
        }
    }


    }

