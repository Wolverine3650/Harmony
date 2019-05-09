package com.example.sandjmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {
    static int v=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread spl=new Thread(){
            public void run(){
                try {
                     sleep(2*1000);
                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }catch (Exception e){

                }
            }
        };
        spl.start();
    }

}
