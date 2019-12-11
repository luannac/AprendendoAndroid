package com.luann.aprendendoandroid;

import android.view.View;

public class ModeloProjeto {
    public String nome;
    private View.OnClickListener iniciar;

    public ModeloProjeto(String nome,View.OnClickListener acao) {
        this.nome = nome;
        this.iniciar=acao;
    }

    public View.OnClickListener getIniciar() {
        return iniciar;
    }

    public void setIniciar(View.OnClickListener iniciar) {
        this.iniciar = iniciar;
    }
}
