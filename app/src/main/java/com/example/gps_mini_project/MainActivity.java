package com.example.gps_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    Button signUP,signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if(user==null)
        {
            setContentView(R.layout.activity_main);
        }
        else
        {
            Intent i=new Intent(MainActivity.this,MyNavigationActivity.class);
            startActivity(i);
            finish();
        }
        signUP = findViewById(R.id.button2);
        signIn = findViewById(R.id.button3);
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(myIntent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent =  new Intent(MainActivity.this,LoginActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
