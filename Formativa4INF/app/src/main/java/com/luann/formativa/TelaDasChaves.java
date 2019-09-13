package com.luann.formativa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

public class TelaDasChaves extends AppCompatActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_das_chaves);

        List<Chave> chaves = new BancoDeDados(TelaDasChaves.this,1).listaChave();
        recycler = findViewById(R.id.lista_view);

        recycler.setAdapter(new AdapterChaves(chaves,getApplicationContext()));

        RecyclerView.LayoutManager layout = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

    }
}
