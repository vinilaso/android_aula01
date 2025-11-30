package com.example.myapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.paint.PaintCoordinates;
import com.example.myapplication.paint.PaintLayer;
import com.example.myapplication.paint.Shapes;

import java.util.ArrayList;

public class SimplePaint extends View {
    private ArrayList<PaintLayer> layers;
    private PaintCoordinates coordinates;
    private Shapes selectedShape;

    public SimplePaint(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initLayers();
    }

    public void selectShape(Shapes selectedShape) {
        this.selectedShape = selectedShape;
    }

    public void resetPaint() {
        initLayers();
        invalidate();
    }

    public void undo() {
        if (layers.size() > 1) {
            layers.remove(getCurrentLayer());
        } else {
            clear();
        }
    }

    public void clear() {
        getCurrentPath().reset();
        invalidate();
    }

    private void initLayers() {
        layers = new ArrayList<>();
        layers.add(new PaintLayer());
        selectShape(Shapes.None);

        getCurrentLayer().setup();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        for (PaintLayer layer : layers) {
            canvas.drawPath(layer.getPath(), layer.getPaint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: return actionDown(x, y);
            case MotionEvent.ACTION_MOVE: return actionMove(x, y);
            case MotionEvent.ACTION_UP: return onDrawDefaultBehaviour();
            default: return false;
        }
    }

    private boolean actionDown(float x, float y) {
        addLayer();
        getCurrentPath().moveTo(x, y);

        coordinates = new PaintCoordinates();
        coordinates.setX(x);
        coordinates.setY(y);

        return true;
    }

    private boolean actionMove(float x, float y) {
        switch (selectedShape) {
            case None: return draw(x, y);
            case Square: return drawSquare(x, y);
            case Circle: return drawCircle(x, y);
            default: return onDrawDefaultBehaviour();
        }
    }

    private boolean draw(float x, float y) {
        getCurrentPath().lineTo(x, y);
        return onDrawDefaultBehaviour();
    }

    private boolean drawSquare(float x, float y) {
        getCurrentPath().addRect(
                coordinates.getX(),
                coordinates.getY(),
                x,
                y,
                Path.Direction.CW
        );

        return onDrawDefaultBehaviour();
    }

    private boolean drawCircle(float x, float y) {
        getCurrentPath().reset();
        getCurrentPath().moveTo(coordinates.getX(), coordinates.getY());

        double xPow = Math.pow(coordinates.getX() -x, 2);
        double yPow = Math.pow(coordinates.getY() -y, 2);

        float radius = (float) Math.sqrt(xPow + yPow);

        getCurrentPath().addCircle(x, y, radius, Path.Direction.CW);
        return onDrawDefaultBehaviour();
    }

    private boolean onDrawDefaultBehaviour() {
        invalidate();
        return true;
    }

    private void addLayer() {
        layers.add(new PaintLayer(getCurrentPaint()));
        invalidate();
    }

    private Paint getCurrentPaint() {
        return getCurrentLayer().getPaint();
    }

    private Path getCurrentPath() {
        return getCurrentLayer().getPath();
    }

    private PaintLayer getCurrentLayer() {
        return layers.get(layers.size() - 1);
    }
}
