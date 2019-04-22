package com.luann.somativa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Validar extends AppCompatActivity {
    Button btValidar;
    EditText etChave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar);

        btValidar = findViewById(R.id.btValida);
        etChave = findViewById(R.id.ptChave);

        btValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BDD bdd = new BDD(getApplicationContext(),1);
                Toast.makeText(getApplicationContext(),
                        bdd.valida(etChave.getText().toString()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
