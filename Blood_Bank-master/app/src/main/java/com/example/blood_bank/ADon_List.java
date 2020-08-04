package com.example.blood_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ADon_List extends AppCompatActivity {

    ArrayList al;
    dbhelper db;
    ListView Lv;
    String sm;
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adon__list);

        try {
            db = new dbhelper(this);
            Lv = (ListView) findViewById(R.id.lvADonors);
            al = db.getAllDonors();


            ad = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,al);
            Lv.setAdapter(ad);
            Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    sm = ad.getItem(position);
                    Intent i = new Intent(getApplicationContext(),Donor_details.class);
                    i.putExtra("sm",sm);
                    startActivity(i);
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
        }
    }
}

