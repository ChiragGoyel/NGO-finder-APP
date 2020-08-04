package com.example.blood_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Donors_List extends AppCompatActivity {

    ArrayList al;
    ListView lv;
    dbhelper db;
    String s,ss;
    Spinner bd;
    ArrayAdapter<String> ad1,ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors__list);

        db = new dbhelper(this);
        lv = (ListView) findViewById(R.id.lvDonors);
        bd = (Spinner) findViewById(R.id.spBld_grp);

        try {
            String ar[] = {"Select","A+", "B+", "O+", "O-", "AB+"};
            ad1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, ar);
            bd.setAdapter(ad1);

            bd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    s = ad1.getItem(position);
                    //Toast.makeText(getApplicationContext(), "" + s, Toast.LENGTH_LONG).show();
                    al = db.getAllDonors(s);
                    ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
                    lv.setAdapter(ad);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            // al = db.getAllDonors(s);

        }
        catch (Exception e)
        {
            //Toast.makeText(getApplicationContext(), "Please select "+e, Toast.LENGTH_LONG).show();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ss = ad.getItem(position);
                Intent i = new Intent(getApplicationContext(),don_details.class);
                i.putExtra("ss",ss);
                startActivity(i);
            }
        });
    }


}
