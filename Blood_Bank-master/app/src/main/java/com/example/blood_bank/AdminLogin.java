package com.example.blood_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText us, pass;
    Button log,bck6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        us = (EditText) findViewById(R.id.etUserName2);
        pass = (EditText) findViewById(R.id.etPassword);
        log = (Button) findViewById(R.id.btnAlBck);
        bck6 = (Button) findViewById(R.id.btnAlBck);
    }
    public void adLogin(View v)
    {
        if(us.getText().toString().equals(""))
        {
            us.setError("UserName is must");
        }
        if(pass.getText().toString().equals(""))
        {
            pass.setError("Password is must");
        }
        else
        {
            if(us.getText().toString().equals("Admin") && pass.getText().toString().equals("123"))
            {
                Toast.makeText(getApplicationContext(),"Logged in Successfully",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),Admin.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void Alback(View v)
    {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
