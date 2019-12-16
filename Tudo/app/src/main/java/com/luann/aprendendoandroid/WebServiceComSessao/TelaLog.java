package com.luann.aprendendoandroid.WebServiceComSessao;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.luann.aprendendoandroid.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TelaLog extends Fragment {
    private Map<String,Object> itensDaTela;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.web_service_sessao_fragment_menu, container, false);
        itensDaTela = new HashMap<>();
        itensDaTela.put("bt_logar",v.findViewById(R.id.web_service_sessao_bt_login));
        itensDaTela.put("campo_user",v.findViewById(R.id.web_service_sessao_campo_usuario));
        itensDaTela.put("campo_key",v.findViewById(R.id.web_service_sessao_campo_senha));

        ((Button) itensDaTela.get("bt_logar")).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> parametros = new HashMap<>();

                parametros.put("cpf",((EditText)itensDaTela.get("campo_user")).getText());
                parametros.put("senha",((EditText)itensDaTela.get("campo_key")).getText());

                new WebServices(getContext()).chamarWebService("loginSys", parametros, new RetornoWS() {
                    @Override
                    public void onSucess(String response) {
                        try{
                            JSONObject resposta = new JSONObject(response);
                            if(resposta.get("Resposta")=="Sucesso"){
                                        Alerts.carregaAlerta(getContext(),getLayoutInflater(),"Deu Certo");
                            }else{
                                Alerts.carregaAlerta(getContext(),getLayoutInflater(),"Deu Errado");
                            }
                        }catch (Exception e){
                            Alerts.carregaAlerta(getContext(),getLayoutInflater(),"Deu Errado");
                        }
                    }

                    @Override
                    public void onError(String erro) {
                        Alerts.carregaAlerta(getContext(),getLayoutInflater(),"Deu Errado");
                    }
                });
            }
        });

        return v;
    }
}
