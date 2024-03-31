package com.example.trashgg;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class myFirebase {
    private static FirebaseUser currentUser;
    private static FirebaseAuth fAuth;
    private static FirebaseDatabase db;
    private static DatabaseReference ref;


    public myFirebase()
    {
        fAuth = FirebaseAuth.getInstance();
        currentUser= fAuth.getCurrentUser();
        db = FirebaseDatabase.getInstance("https://trashgg-22709-default-rtdb.europe-west1.firebasedatabase.app/");
    }

    public static FirebaseDatabase getDb() {
        return db;
    }

    public static FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public static FirebaseAuth getfAuth() {
        return fAuth;
    }

    public void setRef( String s) {
        ref = db.getReference(s);
    }
    public void addPlayer(Players p)
    {
        //ref.setValue("player/"+currentUser.getUid());
        //ref.push();
        //setRef("player/"+currentUser.getUid());
        //ref= db.getReference();
        //ref.setValue(p);
        currentUser= fAuth.getCurrentUser();
        ref = db.getReference("player/"+currentUser.getUid());
        ref.setValue(p);


        //Players p = new Players(nikName.getText().toString(),emailEditText.getText().toString(),passwordEditText.getText().toString());
        //myFirebase f = new myFirebase();
       // f.setRef("player/"+myFirebase.getCurrentUser().getUid());
       // DatabaseReference d = myFirebase.getDb().getReference();
        //d.setValue(p);romi
    }
    public void get_userNode(int r) {
        ref = db.getReference("player/" + currentUser.getUid());

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Players p = snapshot.getValue(Players.class);
                p.newRecord(r);
                ref.setValue(p);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void cList(ArrayList<Players> list,MyAdapter myAdapter)
    {
        ref = db.getReference("player/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Players players = dataSnapshot.getValue(Players.class);
                    list.add(players);
                }
                Collections.sort(list);
                Collections.reverse(list);
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}
