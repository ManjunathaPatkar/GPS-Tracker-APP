package com.example.gps_mini_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder> {
    ArrayList<CreateUser> namelist;
    Context c;
    MembersAdapter(ArrayList<CreateUser> namelist, Context c)
    {
        this.namelist=namelist;
        this.c=c;
    }

    @Override
    public int getItemCount() {


        return namelist.size();
    }

    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
       MembersViewHolder membersViewHolder=new MembersViewHolder(v,c,namelist);
       

        return membersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {

        CreateUser currentuser=namelist.get(position);
        holder.t1.setText(currentuser.name);

    }

    public static class MembersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView t1;
        DatabaseReference reference;
        String lati,longi;
        Context c;
        ArrayList<CreateUser> namearrayList;
        FirebaseAuth auth;
        FirebaseUser user;
        CreateUser s;


        public MembersViewHolder(@NonNull View itemView, Context c,ArrayList<CreateUser> namearrayList) {
            super(itemView);
            this.c=c;
            this.namearrayList=namearrayList;
            itemView.setOnClickListener(this);
            auth=FirebaseAuth.getInstance();
            user=auth.getCurrentUser();
            t1=itemView.findViewById(R.id.item_title);

        }



        @Override
        public void onClick(View v) {

            int a = getPosition();
            s=namearrayList.get(a);
            String userid;
            userid=user.getUid();
            reference= FirebaseDatabase.getInstance().getReference().child("users").child(userid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  lati =dataSnapshot.child("lat").getValue(String.class);
                  longi =dataSnapshot.child("lng").getValue(String.class);
                    Toast.makeText(c, "    "+lati+"    "+longi, Toast.LENGTH_SHORT).show();
                    String uri = "http://maps.google.com/maps?saddr=" + lati + "," + longi + "&daddr=" + s.lat + "," + s.lng;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    intent.setPackage("com.google.android.apps.maps");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    c.startActivity(intent);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }
}
