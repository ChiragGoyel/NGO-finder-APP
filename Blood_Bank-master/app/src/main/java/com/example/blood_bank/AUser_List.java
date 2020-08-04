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

public class AUser_List extends AppCompatActivity {

    ArrayList ual;
    dbhelper db;
    ListView uLv;
    String nm;
    ArrayAdapter<String> ad;
    Button bck4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auser__list);

        try {
            db = new dbhelper(this);
            uLv = (ListView) findViewById(R.id.lvUsers);
            bck4 = (Button) findViewById(R.id.btnAuBck);
            ual = db.getAllUsers();

            ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ual);
            uLv.setAdapter(ad);

            uLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    nm = ad.getItem(position);
                    Intent i = new Intent(getApplicationContext(),User_details.class);
                    i.putExtra("nm",nm);
                    startActivity(i);
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
        }
    }
    public void AuList(View v)
    {
        Intent i = new Intent(getApplicationContext(),Admin.class);
        startActivity(i);
    }
}
