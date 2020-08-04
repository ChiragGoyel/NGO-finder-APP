package com.example.blood_bank;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_details extends AppCompatActivity {

    EditText ut,ur,un,ub,uc,ucon;
    Button bck2, matchBtn;
    dbhelper db;
    String id;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        db = new dbhelper(this);
        un = (EditText) findViewById(R.id.etUName);
        ub = (EditText) findViewById(R.id.etUBld_grp);
        uc = (EditText) findViewById(R.id.etUContact);
        ucon = (EditText) findViewById(R.id.etUCond);
        bck2 = (Button) findViewById(R.id.btnUBack);
        matchBtn = (Button) findViewById(R.id.matchbtn);

        try
        {
            Intent i = getIntent();
            id = i.getStringExtra("nm");
//            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
            c = db.getUser_details(id);
            c.moveToFirst();

            if (!c.isAfterLast())
            {
                un.setText(c.getString(2));
                ub.setText(c.getString(4));
                uc.setText(c.getString(5));
                ucon.setText(c.getString(6));
            }
            c.close();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }
    }

    public void cback(View v)
    {
        Intent i = new Intent(getApplicationContext(), AUser_List.class);
        startActivity(i);
    }

    public void MatchClick(View v)
    {
        Intent i = new Intent(getApplicationContext(), Donor_Select_List.class);
        i.putExtra("user_id",id);
        startActivity(i);
    }
}
