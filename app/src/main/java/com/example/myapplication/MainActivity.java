package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.paint.Shapes;
import com.example.myapplication.views.SimplePaint;

public class MainActivity extends AppCompatActivity {

    private SimplePaint simplePaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        simplePaint = findViewById(R.id.simplePaint);

        final ImageButton btnCircle = findViewById(R.id.circle_button);
        final ImageButton btnSquare = findViewById(R.id.square_button);
        final ImageButton btnPencil = findViewById(R.id.finger_button);
        final ImageButton btnColors = findViewById(R.id.change_color_button);
        final ImageButton btnReset = findViewById(R.id.clear_button);
        final ImageButton btnUndo = findViewById(R.id.undo_button);

        btnCircle.setOnClickListener(button -> simplePaint.selectShape(Shapes.Circle));
        btnSquare.setOnClickListener(button -> simplePaint.selectShape(Shapes.Square));
        btnPencil.setOnClickListener(button -> simplePaint.selectShape(Shapes.None));
        btnReset.setOnClickListener(button -> simplePaint.resetPaint());
        btnUndo.setOnClickListener(button -> simplePaint.undo());
    }
}