package com.example.myapplication;

import android.content.Intent;
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
    private EditText _nameEditText;

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
        _nameEditText = findViewById(R.id.input_name);

        findViewById(R.id.btn_calcular).setOnClickListener(button ->
        {
            gotoResultsActivity();
        });
    }

    private double getIMCValue()
    {
        double height = getValueOf(_heigthEditText);
        double weight = getValueOf(_weightEditText);

        if (height <= 0 || weight <= 0)
            return 0;

        return weight / (height * height);
    }

    private double getValueOf(EditText input)
    {
        String str = input.getText().toString();

        if (str.isEmpty())
            return 0;

        return Double.parseDouble(str);
    }

    private void gotoResultsActivity()
    {
        Bundle bundle = new Bundle();
        bundle.putString("user-name", _nameEditText.getText().toString());
        bundle.putDouble("height", getValueOf(_heigthEditText));
        bundle.putDouble("weight", getValueOf(_weightEditText));
        bundle.putDouble("imc", getIMCValue());

        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}