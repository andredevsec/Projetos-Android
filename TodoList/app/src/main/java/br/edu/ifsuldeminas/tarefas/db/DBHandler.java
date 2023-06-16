package br.edu.ifsuldeminas.tarefas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME= "tarefas.db";
    private static final Integer DB_VERSION = 1;
    private static final String CREATE_TABLE = "CRATE TABLE IF NOT EXISTS tasks" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENTE," +
            "description text," +
            "active varchar(1))";

    // e necessario criar um construtor pois a classe SQLiteOpenHelper não tem um construtor padrão
    public DBHandler(@Nullable Context context) { // como nome versao estao definido na classe pode apagar do padrão, e factory esta como null
        super(context, DB_NAME, null, DB_VERSION); //colocar nome e versão, passar factory como null
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // aqui não vamos tratar
    }
}
