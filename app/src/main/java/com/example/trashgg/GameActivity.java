package com.example.trashgg;

import android.app.Dialog;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
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
    //ImageButton[] b1;
    //RecyclingBin[] b2;
    ImageButton stopButton;
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
    TextView points;
    TextView countdownTimer;
    TextView countdown3;
    CountDownTimer timer;
    CountDownTimer timer3;
    int count;
    int counter=0;
    int pointsCount=0;
    private long timeLeft=60000;
    private long seconds2;
    public static boolean timeRunning = false;

    public static final String EXTRA_SCORE ="score";

    Dialog myDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            myDialog= new Dialog(this);


            //b1 = new ImageButton[4];
            //b2 = new RecyclingBin[4];

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
            countdown3 = findViewById(R.id.countdown3);

            stopButton = (ImageButton) findViewById(R.id.stopButton);

            points = findViewById(R.id.points);
            start3count();





            stopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openStopWindow();

                }
            });


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
    public void start3count()
    {
        timer3 = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {
                long sec = (l / 1000);
                String s = String.format(Locale.getDefault(), "%2d", sec);
                countdown3.setText(s);

            }

            @Override
            public void onFinish() {
                countdown3.setText("");
                startTime();

            }
        }.start();
    }
    public void binPrassed(RecyclingBin b) {//פעולה המפעילה על כל פח שלחצו עליו את הפעולה ifRecycler עם הזבל שמוצג על המסך
        counter=counter+1;
        if (b.ifRecycler(glass)) {
            if(counter==1)
            {
               count = count+3;
               pointsCount = pointsCount+10;
            }
            else {
                pointsCount= pointsCount+5;
            }
            Toast.makeText(GameActivity.this, "Good job", Toast.LENGTH_SHORT).show();
            counter=0;
            String scoreValue = String.valueOf(pointsCount);
            points.setText(scoreValue);
        } else {
            Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();
            count = count -5;
            count += 1;
        }
    }

    public void startTime() {
            timer = new CountDownTimer(timeLeft, 1000) {
                @Override
                public void onTick(long l) {
                    if(!myDialog.isShowing()) {
                        long seconds = ((l / 1000) + count);
                        if ((seconds) < 0) {
                            seconds = 0;
                        }
                        seconds2 = seconds;
                        String timeFormatted = String.format(Locale.getDefault(), "%02d", seconds);
                        if (seconds == 0) {
                            cancel();
                            onFinish();
                        }
                        countdownTimer.setText(timeFormatted);
                    }
                    else {
                        cancel();
                    }
                }


                @Override
                public void onFinish() {

                    if (seconds2 > 0) {
                        timeLeft = seconds2 * 1000;
                        count = 0;
                        startTime();
                    } else {
                        countdownTimer.setText("00");
                        String score = points.getText().toString();
                        Intent gameIntent = new Intent(GameActivity.this, GameOverActivity.class);
                        gameIntent.putExtra(EXTRA_SCORE, score);
                        startActivity(gameIntent);
                    }
                }


            }.start();
            timeRunning = true;
        }





    private void openStopWindow() {
        Button resumeButton;
        Button restartButton;
        Button exitButton;

        myDialog.setContentView(R.layout.activity_stop_window);
        resumeButton = myDialog.findViewById(R.id.resumeGameButton);
        restartButton = myDialog.findViewById(R.id.restartGameButton);
        exitButton = myDialog.findViewById(R.id.exitGameButton);

        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                timeLeft = seconds2 * 1000;
                count = 0;
                start3count();
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restartIntant = getIntent();
                startActivity(restartIntant);

            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exitIntant = new Intent(GameActivity.this, MainActivity.class);
                startActivity(exitIntant);

            }
        });

        myDialog.show();
    }


        //Intent stopwindowIntent = new Intent(GameActivity.this, StopWindow.class );

       // startActivity(stopwindowIntent);

    public void stopTimer()
    {
        timer.cancel();
        timeRunning=false;

    }

    public void startStop()
    {
        if(timeRunning)
        {
            stopTimer();
        }
        else
        {

            startTime();
        }
    }




















}