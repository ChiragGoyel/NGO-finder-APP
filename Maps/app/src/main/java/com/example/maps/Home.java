package com.example.maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

    }
    public void MapsClick (View view){
        Intent intent = new Intent(Home.this, MapsActivity.class);
        startActivity(intent);
    }
    public void LoginClick (View view){
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
    }
}
