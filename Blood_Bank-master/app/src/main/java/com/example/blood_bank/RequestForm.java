package com.example.blood_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RequestForm extends AppCompatActivity {

    EditText t,r,nm,cn;
    RadioGroup con;
    RadioButton con_option;
    Button s,bck8;
    Spinner b;
    String strcon;
    dbhelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);

        mydb = new dbhelper(this);

        nm = (EditText) findViewById(R.id.etName2);
        cn = (EditText) findViewById(R.id.etContact);
        con = (RadioGroup) findViewById(R.id.rgCondition);
        s = (Button) findViewById(R.id.btnSubmit);
        bck8 = (Button) findViewById(R.id.btnRqBck);
        b = (Spinner) findViewById(R.id.spBGroup2);

        String ar[] = {"A+", "B+", "O+", "O-", "AB+"};
        ArrayAdapter<String> ad = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,ar);
        b.setAdapter(ad);

        con.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                con_option = con.findViewById(id);

                switch (id)
                {
                    case R.id.rbRegular:
                        strcon = con_option.getText().toString();
                        break;

                    case R.id.rbEmergency:
                        strcon = con_option.getText().toString();
                        break;

                    default:
                }
            }
        });
    }

    public void submit(View v)
    {
        if(nm.getText().toString().equals(""))
        {
            nm.setError("Type is must");
        }
        if(cn.getText().toString().equals(""))
        {
            cn.setError("Contact is must");
        }

        else
        {
            String ins = mydb.insertPatient("","",nm.getText().toString(),b.getSelectedItem().toString(),cn.getText().toString(),strcon);
            if(ins.equals("yes"))
            {
                Toast.makeText(getApplicationContext(),"Request submitted successfully",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Something went wrong"+ins,Toast.LENGTH_LONG).show();
            }
        }
    }

    public void RqBack(View v)
    {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
