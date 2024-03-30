package com.example.trashgg;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderBoardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Players> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

            recyclerView = findViewById(R.id.userList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


            list = new ArrayList<>();
            myAdapter = new  MyAdapter(LeaderBoardActivity.this,list);
            recyclerView.setAdapter(myAdapter);

            myFirebase mf = new myFirebase();
            mf.cList(list,myAdapter);





    }
}