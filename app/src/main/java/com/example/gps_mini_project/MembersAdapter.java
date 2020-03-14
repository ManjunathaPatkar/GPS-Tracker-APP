package com.example.gps_mini_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MembersAdapter extends RecyclerView.Adapter <MembersAdapter.MembersViewHolder> {
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

       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layput,parent,false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {

    }

    public static class MembersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView t1;
        View v;
        Context c;
        ArrayList<CreateUser> namearrayList;
        FirebaseAuth auth;
        FirebaseUser user;


        public MembersViewHolder(@NonNull View itemView,View v, Context c,ArrayList<CreateUser> namearrayList) {
            super(itemView);
            this.v=v;
            this.c=c;
            this.namearrayList=namearrayList;
            itemView.setOnClickListener(this);
            auth=FirebaseAuth.getInstance();
            user=auth.getCurrentUser();
            t1=itemView.findViewById(R.id.item_title);

        }


        @Override
        public void onClick(View v) {

            Toast.makeText(c,"You have clicked this user",Toast.LENGTH_SHORT).show();

        }
    }
}
