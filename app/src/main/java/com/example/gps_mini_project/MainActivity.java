package com.example.gps_mini_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    PermissionManager manager;

    Button signUP,signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if(user==null)
        {
            setContentView(R.layout.activity_main);
            manager=new PermissionManager() {};
            manager.checkAndRequestPermissions(this);

        }
        else
        {
           // Intent i=new Intent(MainActivity.this,UserLocationMainActivity.class);
           // startActivity(i);
            //finish();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        manager.checkResult(requestCode,permissions,grantResults);
        ArrayList<String> denied_permission=manager.getStatus().get(0).denied;
        if(denied_permission.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Permisssion Enabled",Toast.LENGTH_LONG).show();
    }
}


}
