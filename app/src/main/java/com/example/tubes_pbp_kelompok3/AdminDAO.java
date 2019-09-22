package com.example.tubes_pbp_kelompok3;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AdminDAO {

    public String username;
    public String email;

    public AdminDAO() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public AdminDAO(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
