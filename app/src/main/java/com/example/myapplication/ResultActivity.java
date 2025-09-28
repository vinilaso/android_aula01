package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView _resultText;
    private TextView _messageText;
    private Button _btnVoltar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_result);

            iniciarComponentes();

            Double imc = getIntent().getExtras().getDouble("imc");
            _resultText.setText(String.valueOf(imc));

            _messageText.setText(getMessage(imc));

            _btnVoltar.setOnClickListener(button -> {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            });
        }
        catch (Exception e) {
            Utils.handleException(this, e);
        }
    }

    private void iniciarComponentes() {
        _resultText = findViewById(R.id.imc_result);
        _messageText = findViewById(R.id.imc_message);
        _btnVoltar = findViewById(R.id.btn_calcular_novamente);
    }

    private String getMessage(double imc) {
        if (imc < 18.5) {
            return "ABAIXO DO PESO";
        }

        if (imc >= 18.5 && imc <= 24.9) {
            return "PESO NORMAL";
        }

        if (imc >= 25 && imc <= 29.9) {
            return "SOBREPESO";
        }

        if (imc >= 30 && imc <= 34.9) {
            return "OBESIDADE GRAU 1";
        }

        if (imc >= 35 && imc <= 39.9) {
            return "OBESIDADE GRAU 2";
        }

        return "OBESIDADE GRAU 3";
    }
}
