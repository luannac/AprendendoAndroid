package com.luann.formativa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {
    private final String modeloBanco =
            "CREATE TABLE chaves (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "chave TEXT NOT NULL," +
                    "autenticacao TEXT NOT NULL," +
                    "dataHora REAL NOT NULL," +
                    "status INTEGER NOT NULL);";
    private final String valoresIniciais =
        "INSERT INTO chaves VALUES " +
                "(1,'JH91HC','812j97f4298jnc2',1568026065,1)," +
                "(2,'PA83B7','m9ads78cj208jf4',1568026123,0)," +
                "(3,'HJ272A','981m98cmc8a12',1568026778,1)," +
                "(4,'99CY2P','98mcaks326174',1568027060,0)," +
                "(5,'278JMA','7812nca6123kas',1568027165,0);";

    public BancoDeDados(Context context,int version){
        super(context,"bd_latLong",null,version);
    }

    public boolean chaveExiste(String chave){
        Cursor cursor = null;
        try{
            SQLiteDatabase db = getWritableDatabase();
            cursor = db.rawQuery("SELECT chave FROM chaves WHERE chave = ?;",new String[]{chave});
        }catch (Exception e){ Log.d("Banco",e.toString());}

        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public boolean chaveUtilizada(String chave){
        Cursor cursor = null;
        try{
            SQLiteDatabase db = getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM chaves WHERE chave = ? AND status=?;",new String[]{chave,"1"});
        }catch (Exception e){ Log.d("Banco",e.toString());}

        if(cursor.moveToFirst())
            return true;
        else
            return false;
    }

    public  boolean atualizachave(String chave,boolean status){
        int intStatus = 0;
        if(status)
            intStatus =1;
        try{
            SQLiteDatabase db = getWritableDatabase();
            db.rawQuery("UPDATE chaves " +
                    "SET status = ? " +
                    "WHERE chave = ?;",new String[]{String.valueOf(intStatus),chave});
        }catch (Exception e){ Log.d("Banco",e.toString());return false;}
        return true;
    }

    public Chave getChave(String chave){
        Cursor cursor = null;
        Chave chaveResposta  = null;
        try{
            SQLiteDatabase db = getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM chaves WHERE chave = ?;",new String[]{chave});
        }catch (Exception e){ Log.d("Banco",e.toString());}

        if(cursor.moveToFirst())
            chaveResposta = new Chave(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getLong(3),cursor.getInt(4));

        return chaveResposta;
    }

    public List<Chave> listaChave(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM chaves",new String[]{});
        List<Chave> chaves = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Chave chave = new Chave(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getLong(3),cursor.getInt(4));
                chaves.add(chave);
            }while (cursor.moveToNext());
        }
        return chaves;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(modeloBanco);
        sqLiteDatabase.execSQL(valoresIniciais);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
