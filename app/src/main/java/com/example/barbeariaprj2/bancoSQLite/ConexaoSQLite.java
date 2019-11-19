package com.example.barbeariaprj2.bancoSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static final String name = "banco3.db";
    private static final int version = 1;

    public ConexaoSQLite(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table usuario(id integer primary key autoincrement, " +
                "nome varchar(70), email varchar(70), senha varchar(70), dataNasc varchar(70))");

        db.execSQL("create table agendamento(id integer primary key autoincrement, " +
                "dataAgendamento varchar(70), horario varchar(70), status varchar(70), idUsuario varchar(70))");

        db.execSQL("create table feed(id integer primary key autoincrement, " +
                "mensagem varchar(70), data varchar(70), hora varchar(70))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
