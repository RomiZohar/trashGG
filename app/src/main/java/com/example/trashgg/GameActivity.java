package com.example.trashgg;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    ImageView orangeBinImage = findViewById(R.id.orangeBinImage);
    Garbage packaging = new Garbage("packaging");
    RecyclingBin orangeBin = new  RecyclingBin(orangeBinImage.getContext(),"orange", packaging);

    ImageView purpleBinImage = findViewById(R.id.purpleBinImage);
    Garbage glass = new Garbage("glass");
    RecyclingBin purpleBin = new  RecyclingBin(purpleBinImage.getContext(),"purple", glass);

    ImageView blueBinImage = findViewById(R.id.blueBinImage);
    Garbage paper = new Garbage("paper");
    RecyclingBin blueBin = new  RecyclingBin(blueBinImage.getContext(),"blue", paper);

    ImageView brownBinImage = findViewById(R.id.brownBinImage);
    Garbage organic = new Garbage("organic");
    RecyclingBin brownBin = new  RecyclingBin(brownBinImage.getContext(),"brown", organic);













}