package com.example.blood_bank;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Donor_Select_List extends AppCompatActivity {

    String user_id, ss;
    dbhelper db;
    Cursor c;
    String bg;
    ArrayList al;
    ListView lv;
    ArrayAdapter<String> ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor__select__list);

        String uid = getIntent().getStringExtra("user_id");
        user_id = uid;
        db = new dbhelper(this);
        c = db.getUser_details(user_id);
        c.moveToFirst();

        if (!c.isAfterLast())
        {
            bg = c.getString(4);
//            Toast.makeText(getApplicationContext(),bg,Toast.LENGTH_LONG).show();
            c.close();

            lv = (ListView) findViewById(R.id.lvDonors);
            al = db.getAllDonors(bg);
            ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
            lv.setAdapter(ad);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ss = ad.getItem(position);
                    Intent i = new Intent(getApplicationContext(), Donor_Confirmation.class);
                    i.putExtra("ss",ss);
                    i.putExtra("user_id",user_id);
                    startActivity(i);
                }
            });
        }

    }
}
