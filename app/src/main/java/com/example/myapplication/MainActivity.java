package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText _inputAltura;
    private EditText _inputPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        _inputAltura = findViewById(R.id.editTextAltura);
        _inputPeso = findViewById(R.id.editTextPeso);

        setButtonListener();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setButtonListener() {
        Button submit = findViewById(R.id.buttonCalcular);
        submit.setOnClickListener(c -> {

            Bundle bundle = new Bundle();
            bundle.putDouble("imc", calcularIMC());

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtras(bundle);

            startActivity(intent);
        });
    }

    private Double calcularIMC() {
        Double altura = Double.parseDouble(_inputAltura.getText().toString());
        Double peso = Double.parseDouble(_inputPeso.getText().toString());

        return peso / (altura * altura);
    }
}