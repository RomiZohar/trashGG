package com.example.trashgg;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Players implements Comparable<Players> {

    private int record;
    private String name;
    private String email;
    private String pass;

    public Players() {
    }

    public Players(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.record = 0;
    }


    public int getRecord() {
        return record;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public void newRecord(int r) {
        if (r > this.record) {
            this.record = r;
        }

    }




    @Override
    public int compareTo(Players o) {
            return this.record>o.record ? 1 :(this.record<o.record ? -1: 0);

    }
}

