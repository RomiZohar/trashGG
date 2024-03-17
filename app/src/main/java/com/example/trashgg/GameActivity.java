package com.example.trashgg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    ImageButton[] b1;
    RecyclingBin[] b2;
    ImageButton orangeBinImage;
    ImageButton purpleBinImage;
    ImageButton blueBinImage;
    ImageButton brownBinImage;
    RecyclingBin orangeBin;
    RecyclingBin purpleBin;
    RecyclingBin blueBin;
    RecyclingBin brownBin;
    Garbage packaging;
    Garbage glass;
    Garbage paper;
    Garbage organic;
    TextView countdownTimer;
    CountDownTimer timer;
    int i;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            b1 = new ImageButton[4];
            b2 = new RecyclingBin[4];

            orangeBinImage = (ImageButton) findViewById(R.id.orangeBinImage);
            packaging = new Garbage("packaging");
            orangeBin = new  RecyclingBin(orangeBinImage,"orange", packaging);



            purpleBinImage = (ImageButton) findViewById(R.id.purpleBinImage);
            glass = new Garbage("glass");
            purpleBin = new  RecyclingBin(purpleBinImage,"purple", glass);


            blueBinImage = (ImageButton) findViewById(R.id.blueBinImage);
            paper = new Garbage("paper");
            blueBin = new  RecyclingBin(blueBinImage,"blue", paper);


            brownBinImage = (ImageButton) findViewById(R.id.brownBinImage);
            organic = new Garbage("organic");
            brownBin = new  RecyclingBin(brownBinImage,"brown", organic);

            countdownTimer =findViewById(R.id.countdown_timer);
            startTime();






            brownBinImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binPrassed(brownBin);
                }
            });
            blueBinImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binPrassed(blueBin);
                }
            });
            purpleBinImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binPrassed(purpleBin);
                }
            });
            orangeBinImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binPrassed(orangeBin);

                }
            });

            return insets;
        });
    }
    public void binPrassed(RecyclingBin b) {//פעולה המפעילה על כל פח שלחצו עליו את הפעולה ifRecycler עם הזבל שמוצג על המסך
        if (b.ifRecycler(glass)) {
            Toast.makeText(GameActivity.this, "Good job", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();
            count = count+5;
        }
    }

    private void startTime()
    {
        timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                long minutes = ((l/1000)%3600)/60;
                long seconds = ((l/1000)%60);
                    String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, (seconds-count));
                    countdownTimer.setText(timeFormatted);


            }

            @Override
            public void onFinish() {
                countdownTimer.setText("00:00");
                Intent gameIntent = new Intent(GameActivity.this, GameOverActivity.class);
                startActivity(gameIntent);

            }
        }.start();
    }



















}