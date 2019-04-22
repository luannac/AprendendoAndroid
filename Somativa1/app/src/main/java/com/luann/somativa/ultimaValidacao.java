package com.luann.somativa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class UltimaValidacao extends AppCompatActivity {
    TextView tvChave,tvCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultima_validacao);

        tvChave = findViewById(R.id.tvChave);
        tvCodigo = findViewById(R.id.tvCodigo);

        BDD bdd = new BDD(getApplicationContext(),1);
        Chave chave = bdd.ultimaValidaçao();

        tvChave.setText("Chave: "+chave.chave);
        tvCodigo.setText("Codigo: "+chave.codigo);
        bdd.close();
    }
}
