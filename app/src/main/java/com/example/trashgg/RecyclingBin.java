package com.example.trashgg;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

public class RecyclingBin extends androidx.appcompat.widget.AppCompatImageView {
    private String color;
    private Garbage recycle;

    public RecyclingBin(Context context, String color, Garbage recycle)
    {
        super(context);
        this.color = color;
        this.recycle = recycle;
    }

    public boolean ifRecycler(Garbage g)
    {
        return (g.getKind()).equals((this.recycle).getKind());
    }


}
