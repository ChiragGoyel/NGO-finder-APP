package com.example.blood_bank;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class don_details extends AppCompatActivity {

    EditText g1,e1,p1,a1,c1,b1;
    dbhelper db;
    Button bck10;
    String id;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_details);

        db = new dbhelper(this);

        g1 = (EditText) findViewById(R.id.editText2);
        e1 = (EditText) findViewById(R.id.editText3);
        p1 = (EditText) findViewById(R.id.editText5);
        a1 = (EditText) findViewById(R.id.editText);
        c1 = (EditText) findViewById(R.id.editText4);
        b1 = (EditText) findViewById(R.id.editText6);
        bck10 = (Button) findViewById(R.id.button);

        try {
            Intent i = getIntent();
            id = i.getStringExtra("ss");
            //Toast.makeText(getApplicationContext(), id2, Toast.LENGTH_LONG).show();
            c = db.getDon_details1(id);
            c.moveToFirst();

            if(!c.isAfterLast())
            {
                g1.setText(c.getString(2));
                e1.setText(c.getString(3));
                p1.setText(c.getString(4));
                a1.setText(c.getString(5));
                c1.setText(c.getString(6));
                b1.setText(c.getString(7));

            }
            c.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }
    }

    public void donBack(View v)
    {
        Intent i = new Intent(getApplicationContext(),Donors_List.class);
        startActivity(i);
    }
}
