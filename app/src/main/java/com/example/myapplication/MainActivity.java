package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText _heigthEditText;
    private EditText _weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadElements();
    }

    private void loadElements()
    {
        _weightEditText = findViewById(R.id.input_weight);
        _heigthEditText = findViewById(R.id.input_height);

        findViewById(R.id.btn_calcular).setOnClickListener(button ->
        {
            Toast.makeText(this, "IMC: " + getIMCValue(), Toast.LENGTH_LONG).show();
        });
    }

    private double getIMCValue()
    {
        String heightStr = _heigthEditText.getText().toString();
        String weightStr = _weightEditText.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty())
            return 0;

        double height = Double.parseDouble(heightStr);
        double weight = Double.parseDouble(weightStr);

        return weight / (height * height);
    }
}