package com.example.barbeariaprj2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.barbeariaprj2.bancoSQLite.ConexaoSQLite;
import com.example.barbeariaprj2.model.Feed;

import java.util.ArrayList;
import java.util.List;

public class FeedDAO {

    private ConexaoSQLite conexao;
    private SQLiteDatabase bancoWrite;
    private SQLiteDatabase bancoRead;

    public FeedDAO(Context context){
        conexao = new ConexaoSQLite(context);
        bancoWrite = conexao.getWritableDatabase();
        bancoRead = conexao.getReadableDatabase();
    }

    public long inserirFeed(Feed feed) {
        bancoWrite = null;
        bancoWrite = conexao.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put("mensagem", feed.getMensagem());
            values.put("data", feed.getData());
            values.put("hora", feed.getHora());

            return bancoWrite.insert("feed", null, values);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (bancoWrite != null) {
                bancoWrite.close();
            }
        }
    }

    public List<Feed> listAllFeed() {
        bancoRead = null;
        bancoRead = conexao.getReadableDatabase();

        List<Feed> listaFeeds = new ArrayList<>();
        Cursor cursor;

        String query = "SELECT * FROM feed;";


        try {

            cursor = bancoRead.rawQuery(query, null);

            if (cursor.moveToFirst()) {

                do {
                    Feed feed = new Feed();
                    feed.setId(cursor.getInt(0));
                    feed.setMensagem(cursor.getString(1));
                    feed.setData(cursor.getString(2));
                    feed.setHora(cursor.getString(3));

                    listaFeeds.add(feed);

                } while (cursor.moveToNext());

            }

        } catch (Exception e) {
            Log.d("Erro lista feeds", "Erro ao retornar feeds");
            return null;
        } finally {
            if (bancoRead != null) {
                bancoRead.close();
            }
        }

        return listaFeeds;

    }

    public boolean excluirFeed(Integer idFeed){
        bancoWrite = null;

        try{

            bancoWrite = conexao.getWritableDatabase();
            bancoWrite.delete("feed","id = ?", new String[]{String.valueOf(idFeed)});


        }catch (Exception e){
            Log.d("FEEDDAO", "não foi possível deletar feed");
            return false;
        }finally {
            if(bancoWrite != null){
                bancoWrite.close();
            }
        }

        return true;
    }


}
