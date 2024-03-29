package com.example.trashgg;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myFirebase {
    private static FirebaseUser currentUser;
    private static FirebaseAuth fAuth;
    private static FirebaseDatabase db;
    private static DatabaseReference ref;


    public myFirebase()
    {
        fAuth = FirebaseAuth.getInstance();
        currentUser= fAuth .getCurrentUser();
        db = FirebaseDatabase.getInstance("https://trashgg-22709-default-rtdb.europe-west1.firebasedatabase.app/");
        ref = db.getReference();
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
        setRef("player/"+currentUser.getUid());
        ref= db.getReference();
        ref.setValue(p);
        //Players p = new Players(nikName.getText().toString(),emailEditText.getText().toString(),passwordEditText.getText().toString());
        //myFirebase f = new myFirebase();
       // f.setRef("player/"+myFirebase.getCurrentUser().getUid());
       // DatabaseReference d = myFirebase.getDb().getReference();
        //d.setValue(p);romi
    }
    public void get_userNode(int r) {
        setRef("player/" + currentUser.getUid());
        ref = db.getReference();

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
}
