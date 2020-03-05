package com.example.gps_mini_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class NameActivity extends AppCompatActivity {

    String email,password;
    String code="";
    String date="";
    EditText e5_name;
    CircleImageView circleImageView;
    Uri resultUri;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        e5_name = findViewById(R.id.editText3);
        circleImageView = (CircleImageView) findViewById(R.id.profile);

        Intent intent = getIntent();
        if(intent!=null)
        {
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("password");
        }
        next = findViewById(R.id.button4next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date myDate = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
                date = simpleDateFormat.format(myDate);
                Random r = new Random();

                int n = 100000 + r.nextInt(900000);
                code = String.valueOf(n);

                Intent intent = new Intent(NameActivity.this,InviteCodeActivity.class);
                intent.putExtra("name",e5_name.getText().toString());
                intent.putExtra("email",email);
                intent.putExtra("date",date);
                intent.putExtra("password",password);
                intent.putExtra("isSharing","false");
                intent.putExtra("code",code);
                //intent.putExtra("imageUri",resultUri);
                startActivity(intent);
                finish();

            }
        });
    }


}
