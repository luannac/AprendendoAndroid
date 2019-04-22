package com.luann.somativa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BDD extends SQLiteOpenHelper {

    //comando CREATE para montar tabela
    private final  String tabelaCadastro = "CREATE TABLE cad_chaves(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "chave VARCHAR(8) NOT NULL, " +
            "cod_autenticacao VARCHAR(20) NOT NULL);";
    private final String tabelaAutentica =" CREATE TABLE val_chaves(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "chave VARCHAR(8) NOT NULL," +
            "situacao VARCHAR(3)NOT NULL);";

    public BDD( Context context, int version) {
        super(context, "BD_Somativa1", null, version);
    }

    public String cadastrar(String chave, String codAutenticacao) {
        if(!chaveValida(chave)) {
            //Criar um objeto  da classe SqLiteDatabase e abre a conexão
            SQLiteDatabase banco = getWritableDatabase();

            //Utilização da classe ContentValues para acessar o valor de cada coluna
            ContentValues valores = new ContentValues();
            valores.put("chave", chave);//Coluna do banco de dados, valor do campo
            valores.put("cod_autenticacao", codAutenticacao);

            //Executando o comando INSERT no banco de dados
            if (banco.insert("cad_chaves", null, valores) != -1) {
                banco.close();
                return "Dados Cadastrados";
            } else {
                banco.close();
                return "Erro ao inserir";
            }
        }
        return "Erro - Chave já existe";
    }

    public String valida(String chave) {
        if(chaveValida(chave)){
            //Criar um objeto  da classe SqLiteDatabase e abre a conexão
            SQLiteDatabase banco = getWritableDatabase();

            //Utilização da classe ContentValues para acessar o valor de cada coluna
            ContentValues valores = new ContentValues();
            valores.put("chave", chave);//Coluna do banco de dados, valor do campo
            valores.put("situacao", "Sim");

            //Executando o comando INSERT no banco de dados
            if (banco.insert("val_chaves", null, valores) != -1) {
                banco.close();
                return "Chave Validada";
            } else {
                banco.close();
                return "Erro - BD ao validar!";
            }
        }
        return "Erro - Chave não cadastrada!";
    }

    public boolean chaveValida(String chave){
        SQLiteDatabase bd = getWritableDatabase(); // Abre conexão com banco

        //Classe cursor para armazenar os dados que o SELECT irá biscar
        Cursor c = bd.rawQuery("SELECT chave FROM cad_chaves WHERE chave = ?",
                new String[]{chave} );

        //Verifica se encontrou alguma linha, ou seja, se tem algo
        if(c.moveToFirst()){
            bd.close();
            return true;
        }
        //Se caso não encontrou o usuário ou a senha correspondente
        bd.close();
        return false;
    }

    public Chave ultimaValidaçao(){
        Chave chave = new Chave();

        SQLiteDatabase bd = getWritableDatabase(); // Abre conexão com banco

        //Classe cursor para armazenar os dados que o SELECT irá biscar
        Cursor c = bd.rawQuery("select * from cad_chaves where chave = (select chave from val_chaves where id = (select max(id) from val_chaves));",null);

        //Verifica se encontrou alguma linha, ou seja, se tem algo
        if(c.moveToFirst()){
            chave.chave = c.getString(1);
            chave.codigo = c.getString(2);
            bd.close();
        }else{
            chave.chave = "";
            chave.codigo = "";
        }
        return chave;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tabelaAutentica);//Executa o comando SQL
        db.execSQL(tabelaCadastro);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //Habilitar chave estrangeira
        db.execSQL("PRAGMA foreing_keys = ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
