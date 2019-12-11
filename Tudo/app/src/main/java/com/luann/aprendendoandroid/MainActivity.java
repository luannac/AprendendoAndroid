package com.luann.aprendendoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.luann.aprendendoandroid.WebServiceComSessao.WebServiceComSessao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler_principal);

        recycler.setAdapter(new AdapterCardMenu(getApplicationContext(),projetos()));
        recycler.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
    }
    private List<ModeloProjeto> projetos(){
        List<ModeloProjeto> projetos = new ArrayList<>();
        projetos.add(new ModeloProjeto("WebService Com Sessão", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WebServiceComSessao.class);
                startActivity(i);
            }
        }));
        return projetos;
    }
}
