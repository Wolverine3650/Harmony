package com.example.sandjmusic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class Login_page extends AppCompatActivity {
     EditText Enemail,EnPass;
     Button Login,Register;
     FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Enemail=(EditText)findViewById(R.id.txtMail);
        EnPass=(EditText)findViewById(R.id.txtPassword);
        Login=(Button)findViewById(R.id.btn_Login);
        Register=(Button)findViewById(R.id.btn_Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=Enemail.getText().toString().trim();
                String password=EnPass.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login_page.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login_page.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6)
                {
                    Toast.makeText(Login_page.this, "too short password", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_page.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                   startActivity(new Intent(getApplicationContext(),Home.class));
finish();

                                } else {

                                    Toast.makeText(Login_page.this, "LOGIN FAIED or USER NOT AVAILABLE", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });







            }
        });




    }
}
