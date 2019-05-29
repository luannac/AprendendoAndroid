package luann.app.aula6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {
    //comando CREATE para montar tabela
    private final  String criaTabela = "CREATE TABLE usuario(" +
            "usuario VARCHAR(20) PRIMARY KEY, " +
            "nome VARCHAR(40) NOT NULL, " +
            "senha VARCHAR(30) NOT NULL);";
    private final String criaFavoritos =" CREATE TABLE favoritos(" +
            "idfavoritos INTEGER PRIMARY KEY AUTOINCREMENT," +
            "num int(10) NOT NULL);";

    public BancoDeDados( Context context, int version) {
        super(context, "BD_Aula6", null, version);
    }

    public boolean cadastraUsuario(String usuario, String nome,String senha) {
        if (verificaUsuario(usuario)) {

            //Criar um objeto  da classe SqLiteDatabase e abre a conexão
            SQLiteDatabase banco = getWritableDatabase();

            //Utilização da classe ContentValues para acessar o valor de cada coluna
            ContentValues valores = new ContentValues();
            valores.put("usuario", usuario);//Coluna do banco de dados, valor do campo
            valores.put("nome", nome);
            valores.put("senha", senha);

            //Executando o comando INSERT no banco de dados
            if (banco.insert("Usuario", null, valores) != -1) {
                banco.close();
                return true;
            } else {
                banco.close();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(criaTabela);//Executa o comando SQL
        db.execSQL(criaFavoritos);
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

    public boolean verificaAcesso(String usuario, String senha){
        SQLiteDatabase bd = getWritableDatabase(); // Abre conexão com banco

        //Classe cursor para armazenar os dados que o SELECT irá biscar
        Cursor c = bd.rawQuery("SELECT senha FROM usuario WHERE usuario = ?",
                new String[]{usuario} );


        //Verifica se encontrou alguma linha, ou seja, se tem algo
        if(c.moveToFirst()){
            do{
                //Recupera o valor da coluna 0 do SELECT (que é a senha)
                String senhaBanco = c.getString(0);

                if(senhaBanco.equals(senha)){
                    bd.close();
                    return true;

                }
            }while (c.moveToFirst());
        }
        //Se caso não encontrou o usuário ou a senha correspondente
        bd.close();
        return false;
    }
    public boolean verificaUsuario(String usuario){
        SQLiteDatabase bd = getWritableDatabase(); // Abre conexão com banco

        //Classe cursor para armazenar os dados que o SELECT irá biscar
        Cursor c = bd.rawQuery("SELECT usuario FROM usuario WHERE usuario = ?",
                new String[]{usuario} );


        //Verifica se encontrou alguma linha, ou seja, se tem algo
        if(c.moveToFirst()){
            bd.close();
            return false;
        }
        //Se caso não encontrou o usuário ou a senha correspondente
        bd.close();
        return true;
    }
    public String getNome(String usuario){
        SQLiteDatabase bd = getWritableDatabase(); // Abre conexão com banco

        //Classe cursor para armazenar os dados que o SELECT irá biscar
        Cursor c = bd.rawQuery("SELECT nome FROM usuario WHERE usuario = ?",
                new String[]{usuario} );


        //Verifica se encontrou alguma linha, ou seja, se tem algo
        c.moveToFirst();
        String nome =c.getString(0);
        bd.close();
        return nome;
    }
    public boolean isFavorito(int numero){
        SQLiteDatabase bd = getWritableDatabase(); // Abre conexão com banco

        //Classe cursor para armazenar os dados que o SELECT irá buscar
        Cursor c = bd.rawQuery("SELECT num FROM favoritos WHERE num = ?",
                new String[]{String.valueOf(numero)} );


        //Verifica se encontrou alguma linha, ou seja, se tem algo
        if(c.moveToFirst()){
            bd.close();
            return true;
        }
        //Se caso não encontrou correspondente
        bd.close();
        return false;
    }
    public void setFavorito(int numero){

            //Criar um objeto  da classe SqLiteDatabase e abre a conexão
            SQLiteDatabase banco = getWritableDatabase();

            //Utilização da classe ContentValues para acessar o valor de cada coluna
            ContentValues valores = new ContentValues();
            valores.put("num", numero);

            //Executando o comando INSERT no banco de dados
            if (banco.insert("favoritos", null, valores) != -1) {
                banco.close();
            } else {
                banco.close();
            }
    }
    public void deleteFavorito(int numero){
        SQLiteDatabase bd = getWritableDatabase(); // Abre conexão com banco

        try {

            bd.rawQuery("DELETE FROM favoritos WHERE num = " + numero,
                    new String[]{String.valueOf(numero)});
        }catch (Exception e) {
            e.printStackTrace();
    }
    }
}