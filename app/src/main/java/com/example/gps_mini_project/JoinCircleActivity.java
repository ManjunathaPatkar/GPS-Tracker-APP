package com.example.gps_mini_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gps_mini_project.ui.CircleJoin;
import com.goodiebag.pinview.Pinview;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class JoinCircleActivity extends AppCompatActivity {

    Button b;
    Pinview pinview;
    DatabaseReference databaseReference,currentReference,CircleReference;
    FirebaseUser user;
    FirebaseAuth auth;
    String currentUid,joinCircleuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_circle);
        pinview=(Pinview)findViewById(R.id.pinview);
        b=(Button)findViewById(R.id.button4);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        currentReference= FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
        currentUid=user.getUid();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query=databaseReference.orderByChild("circlecode").equalTo(pinview.getValue());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            CreateUser createUser=null;
                            for(DataSnapshot childDss:dataSnapshot.getChildren())
                            {
                                createUser=childDss.getValue(CreateUser.class);
                                joinCircleuid=createUser.userID;
                                CircleReference=FirebaseDatabase.getInstance().getReference().child("Users")
                                        .child(joinCircleuid).child("CircleMembers");

                                CircleJoin circleJoin=new CircleJoin(currentUid);
                                CircleJoin circleJoin1=new CircleJoin(joinCircleuid);

                                CircleReference.child(user.getUid()).setValue(circleJoin)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(JoinCircleActivity.this, "User Joined Circle Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                            }
                        }
                        else
                        {
                            Toast.makeText(JoinCircleActivity.this, "Joining circle failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
