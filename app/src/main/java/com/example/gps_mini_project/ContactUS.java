package com.example.gps_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import static java.security.AccessController.getContext;

public class ContactUS extends AppCompatActivity {

    ImageButton g,l,i,g1,l1,i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_u_s);
        g=(ImageButton)findViewById(R.id.imageButton);
        l=(ImageButton)findViewById(R.id.imageButton3);
        i=(ImageButton)findViewById(R.id.imageButton2);
        g1=(ImageButton)findViewById(R.id.imageButton6);
        i1=(ImageButton)findViewById(R.id.imageButton4);
        l1=(ImageButton)findViewById(R.id.imageButton5);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setType("plain/text")
                        .setData(Uri.parse("manjunathapatkar150@gmail.com"))
                        .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"manjunathapatkar150@gmail.com"})
                        .putExtra(Intent.EXTRA_SUBJECT, "t")
                        .putExtra(Intent.EXTRA_TEXT, "hello. Enter your feedback here :-)");
                startActivity(intent);
            }
        });
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setType("plain/text")
                        .setData(Uri.parse("manukashyap.u.v0@gmail.com"))
                        .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"manukashyap.u.v0@gmail.com"})
                        .putExtra(Intent.EXTRA_SUBJECT, "t")
                        .putExtra(Intent.EXTRA_TEXT, "hello. Enter your feedback here :-)");
                startActivity(intent);
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/in/manjunatha-patkar-36582a180"));

                startActivity(intent);


            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/manukashyap-u-v-a61387191/"));

                startActivity(intent);
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/manjunathapatkar");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/manjunathapatkar")));
                }
            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/kashyap_manu_");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/kashyap_manu_")));
                }
            }
        });

    }
}
