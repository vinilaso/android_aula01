package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textViewResultado;
    EditText editTextMin,editTextMax;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editTextMin = findViewById(R.id.edMin);
        editTextMax = findViewById(R.id.edMax);
        textViewResultado = findViewById(R.id.tvResultado);
        button.setOnClickListener(v -> {
            int min = Integer.parseInt(editTextMin.getText().toString());
            int max = Integer.parseInt(editTextMax.getText().toString());
            int sorteado = 0;
            sorteado = (int) (Math.random() * (max-min)+min);
            textViewResultado.setText(Integer.toString(sorteado));
        });

    }
}