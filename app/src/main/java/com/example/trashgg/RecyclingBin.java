package com.example.trashgg;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

public class RecyclingBin extends androidx.appcompat.widget.AppCompatImageButton {
    private String color;
    private Garbage recycle;

    public RecyclingBin(View view, String color, Garbage recycle)
    {
        super(view.getContext());
        this.color = color;
        this.recycle = recycle;
    }



    public boolean ifRecycler(Garbage g)
    {
        return (g.getKind()).equals((this.recycle).getKind());
    }


}
