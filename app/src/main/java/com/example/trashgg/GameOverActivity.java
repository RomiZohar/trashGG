package com.example.trashgg;



import static com.example.trashgg.myFirebase.getCurrentUser;
import static com.example.trashgg.myFirebase.getDb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class GameOverActivity extends AppCompatActivity {
    TextView score;
    Button BackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


            myFirebase f1 = new myFirebase() ;
            score = findViewById(R.id.yourScore);
            Intent gameIntent = getIntent();
            String yourScore = gameIntent.getStringExtra(GameActivity.EXTRA_SCORE);
            score.setText("your score:"+yourScore);
            int n = Integer.parseInt(yourScore);
            f1.get_userNode(n);


            BackHome = findViewById(R.id.backHome);
            BackHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent backToHome = new Intent(GameOverActivity.this, MainActivity.class);
                    startActivity(backToHome);
                }
            });





    }
}