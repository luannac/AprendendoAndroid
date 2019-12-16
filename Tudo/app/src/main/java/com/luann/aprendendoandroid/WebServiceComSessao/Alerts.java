package com.luann.aprendendoandroid.WebServiceComSessao;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.luann.aprendendoandroid.R;

public class Alerts {
    public static AlertDialog alerta=null;

    public static ProgressDialog carregaLoading(Context context){
        ProgressDialog loading = new ProgressDialog(context);
        loading.setMessage("Loading...");
        loading.setTitle("Logando");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
        loading.setCancelable(false);
        return loading;
    }
    public static void carregaAlerta(final Context context,LayoutInflater li,String mensagem){
        View view = li.inflate(R.layout.alert_message, null);

        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
                alerta=null;
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(mensagem);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }
    public static void carregaAlertaAcao(final Context context,LayoutInflater li,String mensagem,View.OnClickListener acao){
        View view = li.inflate(R.layout.alert_message, null);
        view.findViewById(R.id.bt).setOnClickListener(acao);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(mensagem);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }
}