package com.luann.somativa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastrar extends AppCompatActivity {
    Button btCadastrar;
    EditText etChave,etCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        btCadastrar = findViewById(R.id.btCadastrar);
        etChave = findViewById(R.id.ptChave);
        etCodigo =findViewById(R.id.ptCodigo);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BDD bdd = new BDD(getApplicationContext(),1);
                    Toast.makeText(getApplicationContext(),
                            bdd.cadastrar(etChave.getText().toString(),etCodigo.getText().toString()),
                            Toast.LENGTH_SHORT).show();
            }
        });
    }
}
