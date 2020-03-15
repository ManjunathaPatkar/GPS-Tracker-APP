package com.example.gps_mini_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    EditText e4_email;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        e4_email = findViewById(R.id.registerEmail);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }

    public  void goToPasswordActivity(View v)
    {
        dialog.setMessage("Checking Email Address");
        auth.fetchSignInMethodsForEmail(e4_email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if(Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getSignInMethods()).size() == 0)
                        {
                            //Email does not exist
                            dialog.dismiss();
                            Intent myIntent = new Intent(RegisterActivity.this,PasswordActivity.class);
                            myIntent.putExtra("email",e4_email.getText().toString());
                            startActivity(myIntent);

                        }
                        else
                        {
                            //Email exists
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"This Email is already registered",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
