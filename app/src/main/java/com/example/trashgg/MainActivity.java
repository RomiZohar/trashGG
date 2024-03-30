package com.example.trashgg;// MainActivity.java
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

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Button startGameButton = findViewById(R.id.startGameButton);
            Button instructionsButton = findViewById(R.id.instructionsButton);
            Button leaderboardButton = findViewById(R.id.leaderboardButton);



            // Set onClickListener for Start Game Button
            startGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent to start game activity
                    Intent startGameIntent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(startGameIntent);
                }
            });

            // Set onClickListener for Instructions Button
            instructionsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent to show instructions activity
                    Intent instructionsIntent = new Intent(MainActivity.this, instractionsActivity.class);
                    startActivity(instructionsIntent);
                }
            });

            leaderboardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,LeaderBoardActivity.class));
                }
            });

            // Set onClickListener for Chart Button



    }
}
