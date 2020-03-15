package com.example.gps_mini_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyCircleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseAuth auth;
    FirebaseUser user;
    CreateUser createUser;
    ArrayList<CreateUser> namelist;
    DatabaseReference reference,userreference;
    String Circlememeberid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle);
        Toast.makeText(getApplicationContext(),"Inside on create",Toast.LENGTH_SHORT).show();
        recyclerView=(RecyclerView)findViewById(R.id.irecyleid);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        namelist=new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        userreference= FirebaseDatabase.getInstance().getReference().child("users");
        Toast.makeText(getApplicationContext(),"after user reference",Toast.LENGTH_SHORT).show();
        reference=FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("CircleMembers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namelist.clear();
                //Toast.makeText(getApplicationContext(),"before checking data is there",Toast.LENGTH_SHORT).show();
                if(dataSnapshot.exists())
                {
                    Toast.makeText(getApplicationContext(),"data is there",Toast.LENGTH_SHORT).show();
                    for (DataSnapshot dss: dataSnapshot.getChildren())
                    {
                        Circlememeberid=dss.child("circlemember").getValue(String.class);
                        userreference.child(Circlememeberid)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        createUser=dataSnapshot.getValue(CreateUser.class);
                                        namelist.add(createUser);
                                        adapter.notifyDataSetChanged();
                                        
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(MyCircleActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
                else
                {
                    Toast.makeText(MyCircleActivity.this, "no data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyCircleActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter=new MembersAdapter(namelist,getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
