package com.example.sandjmusic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
   EditText userEmail,userPassword,ConfirmPass;
   Button btn_register,tvInfo;
   private FirebaseAuth firebaseAuth;
  // TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEmail=(EditText)findViewById(R.id.etUserEmail);
        userPassword=(EditText)findViewById(R.id.etUserPassword);
        ConfirmPass=(EditText)findViewById(R.id.etConfirmPassword);
        btn_register=(Button)findViewById(R.id.btnRegister);
        tvInfo=(Button) findViewById(R.id.btn_Login) ;
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(MainActivity.this,Home.class));
            finish();
        }

        /*tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login_page.class));
            }
        });*/



       btn_register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           String email=userEmail.getText().toString().trim();
           String password=userPassword.getText().toString().trim();
           String confirmpass=ConfirmPass.getText().toString().trim();


           if(TextUtils.isEmpty(email))
           {
               Toast.makeText(MainActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
               return;
           }
               if(TextUtils.isEmpty(password))
               {
                   Toast.makeText(MainActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                   return;
               }


               if(TextUtils.isEmpty(confirmpass))
               {
                   Toast.makeText(MainActivity.this, "please enter confirm Password", Toast.LENGTH_SHORT).show();
                   return;
               }
               if(password.length()<6)
               {
                   Toast.makeText(MainActivity.this, "too short password", Toast.LENGTH_SHORT).show();
               }



               if(password.equals(confirmpass))
               {
                   firebaseAuth.createUserWithEmailAndPassword(email, password)
                           .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()) {
                                       // Sign in success, update UI with the signed-in user's information
                                       startActivity(new Intent(getApplicationContext(),Login_page.class));
                                       Toast.makeText(MainActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
finish();
                                   } else {
                                       // If sign in fails, display a message to the user.
                                       Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                   }

                                   // ...
                               }
                           });



               }




           }
       });




    }

    public void login(View view)
    {
        startActivity(new Intent(getApplicationContext(),Login_page.class));
        finish();
    }
}
