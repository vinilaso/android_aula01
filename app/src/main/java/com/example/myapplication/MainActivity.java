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

    private EditText _inputPeso;
    private EditText _inputAltura;
    private Button _buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iniciarComponentes();

        _buttonCalcular.setOnClickListener(button -> {
            try {
                Bundle bundle = new Bundle();
                bundle.putDouble("imc", calcularIMC());

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            } catch (Exception e) {
                Utils.handleException(this, e);
            }
        });
    }

    private void iniciarComponentes() {
        _inputPeso = findViewById(R.id.inpt_peso);
        _inputAltura = findViewById(R.id.inpt_altura);

        _buttonCalcular = findViewById(R.id.btn_calcular);
    }

    private double calcularIMC() {
        double altura = getValue(_inputAltura);
        double peso = getValue(_inputPeso);

        if (altura == 0)
            return 0;

        return peso / (altura * 2);
    }

    private double getValue(EditText input) {
        String strVal = input.getText().toString();
        return strVal.isEmpty() ? 0 : Double.parseDouble(strVal);
    }
}