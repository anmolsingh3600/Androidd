package com.sherry.campus.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sherry.campus.R;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = auth.getCurrentUser();
        //firebaseUser.getUid();

        if(firebaseUser != null){
            //Toast.makeText(this,"User Exists "+firebaseUser.getUid(),Toast.LENGTH_LONG).show();
            handler.sendEmptyMessageDelayed(101,2500);
        }else{
            //Toast.makeText(this,"User Does Not Exists ",Toast.LENGTH_LONG).show();
            handler.sendEmptyMessageDelayed(101,2500);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Intent intent = null;

            if(msg.what == 101){
                intent = new Intent(SplashActivity.this,MainActivity.class);
            }else{
                intent = new Intent(SplashActivity.this,MainActivity.class);
            }

            startActivity(intent);
            finish();
        }
    };
}

