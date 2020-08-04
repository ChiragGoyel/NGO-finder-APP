package com.example.maps;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText txtEmail,txtPassword;
    Button btnlogin;
    String email,password;

    //    private MaterialButton btnLogin, btnLinkToRegister, btnForgotPass;
//    private TextInputLayout inputEmail, inputPassword;
//    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().setTitle("Login Form");

//        mAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        btnlogin = (Button) findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();


    }



    public void btn_loginform(View view) {
        Intent i = new Intent(Login.this, Signup_Form.class);
        startActivity(i);
    }


//
//        btnlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//        }

    public void login(View view) {
            email = txtEmail.getText().toString().trim();
            password = txtPassword.getText().toString().trim();
            System.out.println("Chirag"+email+""+password);

            if (TextUtils.isEmpty(email)){
                Toast.makeText(Login.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(password)){
                Toast.makeText(Login.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                return;
            }

            if (password.length()<5){
                Toast.makeText(Login.this,"Password too short",Toast.LENGTH_LONG).show();
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if(email.compareToIgnoreCase("goyelchiragkr@gmail.com") == 0){
                                    startActivity(new Intent(getApplicationContext(),ImageActivity.class));

                                }else {
                                    startActivity(new Intent(Login.this, MapsActivity.class));
                                }
                                Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                            }

                            // ...
                        }
                    });
        }



}
