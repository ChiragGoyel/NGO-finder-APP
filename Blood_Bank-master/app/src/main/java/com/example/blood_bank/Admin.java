package com.example.blood_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {

    Button dl, ul, bck5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dl = (Button) findViewById(R.id.btnDonors);
        ul = (Button) findViewById(R.id.btnUsers);
        bck5 = (Button) findViewById(R.id.btnABack);
    }

    public void adonlist(View v) {
        Intent i = new Intent(getApplicationContext(), ADon_List.class);
        startActivity(i);
    }

    public void auList(View v) {
        Intent i = new Intent(getApplicationContext(), AUser_List.class);
        startActivity(i);
    }

    public void Abck(View v)
    {
        Intent i = new Intent(getApplicationContext(), AdminLogin.class);
        startActivity(i);
    }
}
