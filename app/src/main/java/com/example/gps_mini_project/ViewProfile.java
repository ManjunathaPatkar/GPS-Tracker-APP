package com.example.gps_mini_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {
    TextView name,email,code;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    String userid;
    String name1,email1,code1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.email);
        code=(TextView)findViewById(R.id.code);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name1=dataSnapshot.child("name").getValue(String.class);
                email1=dataSnapshot.child("email").getValue(String.class);
                code1=dataSnapshot.child("code").getValue(String.class);
                name.setText(name1);
                email.setText(email1);
                 code.setText(code1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
