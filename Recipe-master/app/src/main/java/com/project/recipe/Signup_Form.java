package com.project.recipe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Form extends AppCompatActivity {

    EditText txtName,txtUsername,txtEmail,txtPassword,txtCnfPassword;
    Button btnRegister;
    String gender = " ";
    RadioButton radiogendermale, radiogenderfemale;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");


        txtName = (EditText)findViewById(R.id.txt_name);
        txtUsername = (EditText)findViewById(R.id.txt_username);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);
        txtCnfPassword = (EditText)findViewById(R.id.txt_cnfpassword);
        btnRegister = (Button) findViewById(R.id.btn_register);
        radiogendermale = (RadioButton)findViewById(R.id.radiomale);
        radiogenderfemale = (RadioButton)findViewById(R.id.radiofemale);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String cnfpassword = txtCnfPassword.getText().toString().trim();
                final String fullName = txtName.getText().toString().trim();
                final String username = txtUsername.getText().toString().trim();

                if (radiogendermale.isChecked()){
                    gender = "Male";
                }
                if (radiogenderfemale.isChecked()){
                    gender = "Female";
                }


                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(cnfpassword)){
                    Toast.makeText(Signup_Form.this,"Please Enter Confirm Password",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(fullName)){
                    Toast.makeText(Signup_Form.this,"Please Enter Name",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(Signup_Form.this,"Please Enter Username",Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.length()<6){
                    Toast.makeText(Signup_Form.this,"Password too short",Toast.LENGTH_LONG).show();
                }

                if (password.equals(cnfpassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        customer information = new customer(
                                                fullName,
                                                username,
                                                email,
                                                gender
                                        );

                                        FirebaseDatabase.getInstance().getReference("Customer")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information);

                                        Toast.makeText(Signup_Form.this,"Registration Complete",Toast.LENGTH_SHORT).show();
                                        Intent h = new Intent(Signup_Form.this,Login_Form.class);
                                        startActivity(h);

                                    }
                                    else {
                                        Toast.makeText(Signup_Form.this,"Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
}
