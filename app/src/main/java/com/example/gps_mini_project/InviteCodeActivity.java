package com.example.gps_mini_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InviteCodeActivity extends AppCompatActivity {

    String name,email,password,date,issharing,code;
    TextView t1;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);
        auth = FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference();
        t1=(TextView) findViewById(R.id.textViewi);
        Intent intent = getIntent();
        if(intent!=null)
        {
            name=intent.getStringExtra("name");
            email=intent.getStringExtra("email");
            password=intent.getStringExtra("password");
            date=intent.getStringExtra("date");
            issharing=intent.getStringExtra("isSharing");
            code=intent.getStringExtra("code");
            t1.setText(code);
        }

    }

    public void registerUser(View view)
    {
        Toast.makeText(getApplicationContext(),"Creating a new account",Toast.LENGTH_SHORT).show();
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete())
                        {
                            CreateUser createUser = new CreateUser(email,password,code,"false","na","na", name);
                            user = auth.getCurrentUser();
                            userId = user.getUid();
                            reference.child("users").child(userId).setValue(createUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {

                                                sendVerificationEmail();
                                              //  auth.signOut();
                                                Intent i=new Intent(InviteCodeActivity.this,MainActivity.class);
                                                startActivity(i);
                                                //finish();

                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"Registration unsuccessful",Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });
                        }
                    }
                });
    }
    public void sendVerificationEmail()
    {
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Email sent for verification",Toast.LENGTH_SHORT).show();
                    finish();
                    auth.signOut();
                    //finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Could not send Email",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
