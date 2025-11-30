package com.example.myapplication.paint;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class PaintLayer {
    private final Paint paint;
    private final Path path;

    public Paint getPaint() {
        return paint;
    }

    public Path getPath() {
        return path;
    }

    public PaintLayer() {
        this.paint = new Paint();
        this.path = new Path();
    }

    public PaintLayer(Paint paint) {
        this.paint = paint;
        this.path = new Path();
    }

    public void setup() {
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(6f);
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
    }

    public void clear() {
        this.path.reset();
    }
}
