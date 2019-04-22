package com.luann.somativa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btCadastrar,btValidar,btUltimaValidacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastrar = findViewById(R.id.btTelaCadastrar);
        btValidar = findViewById(R.id.btTelaValidar);
        btUltimaValidacao = findViewById(R.id.btTelaUltimaVal);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Cadastrar.class);
                startActivity(i);
            }
        });

        btValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Validar.class);
                startActivity(intent);
            }
        });
        btUltimaValidacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UltimaValidacao.class);
                startActivity(i);
            }
        });
    }
}
