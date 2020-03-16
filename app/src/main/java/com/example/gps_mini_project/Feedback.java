package com.example.gps_mini_project;

public class Feedback {
    Feedback()
    {

    }
    public Feedback(String email,String name,String message) {
        this.email = email;

        this.name = name;
        this.message = message;

    }

    public String email,message,name;
}
