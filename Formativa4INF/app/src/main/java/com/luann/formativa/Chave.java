package com.luann.formativa;

public class Chave {
    private int _id;
    private String chave,autenticacao;
    private double dataHora;
    private int status;

    public Chave(int _id,String chave,String autenticacao,double data,int status){
        this._id = _id;
        this.chave =chave;
        this.autenticacao = autenticacao;
        this.dataHora = data;
        this.status = status;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) { this._id = _id; }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(String autenticacao) {
        this.autenticacao = autenticacao;
    }

    public double getDatahora() {
        return dataHora;
    }

    public void setDatahora(double datahora) {
        this.dataHora = datahora;
    }

    public int getStatus(){return status;}

    public void setStatus(int status){this.status=status;}
}
