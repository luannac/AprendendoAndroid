package com.luann.aprendendoandroid.WebServiceComSessao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import com.luann.aprendendoandroid.R;

import java.util.HashMap;
import java.util.Map;

public class Menu extends Fragment {
    private Map<String,Button> itensDaTela;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.web_service_sessao_fragment_menu, container, false);
        itensDaTela = new HashMap<>();
        itensDaTela.put("tela_login",container.findViewById(R.id.web_service_sessao_bt_tela_login));
        itensDaTela.put("tela_listar",container.findViewById(R.id.web_service_sessao_bt_tela_listar));

        itensDaTela.get("tela_login").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        itensDaTela.get("tela_listar").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;
    }

}
