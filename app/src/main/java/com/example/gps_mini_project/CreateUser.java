package com.example.gps_mini_project;

public class CreateUser {

    CreateUser()
    {

    }

    public CreateUser(String email, String password, String code, String issharing, String lat, String lng, String name) {
        this.email = email;
        this.password = password;
        this.code = code;
        this.issharing = issharing;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    public String email,password,code,issharing,lat,lng,name;
}
