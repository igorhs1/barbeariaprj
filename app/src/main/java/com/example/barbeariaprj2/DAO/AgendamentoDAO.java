package com.example.barbeariaprj2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.barbeariaprj2.bancoSQLite.ConexaoSQLite;
import com.example.barbeariaprj2.model.Agendamento;
import com.example.barbeariaprj2.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {


    private ConexaoSQLite conexao;
    private SQLiteDatabase bancoWrite;
    private SQLiteDatabase bancoRead;

    public AgendamentoDAO(Context context) {
        conexao = new ConexaoSQLite(context);
        bancoWrite = conexao.getWritableDatabase();
        bancoRead = conexao.getReadableDatabase();
    }

    public long inserirAgendamento(Agendamento agendamento) {


        try {
            ContentValues values = new ContentValues();
            values.put("dataAgendamento", agendamento.getData());
            values.put("horario", agendamento.getHorario());
            values.put("status", agendamento.getStatus());
            values.put("idUsuario", agendamento.getIdUsuario());

            return bancoWrite.insert("agendamento", null, values);

        } catch (Exception e) {
            e.printStackTrace();
            return Long.parseLong(null);
        } finally {
            if (bancoWrite != null) {
                bancoWrite.close();
            }
        }


    }

    public List<Agendamento> listAllAgendamento() {
        bancoRead = null;
        bancoRead = conexao.getReadableDatabase();

        List<Agendamento> listaAgendamentos = new ArrayList<>();
        Cursor cursor;

        String query = "SELECT * FROM agendamento;";


        try {

            cursor = bancoRead.rawQuery(query, null);

            if (cursor.moveToFirst()) {

                do {
                    Agendamento agendamento = new Agendamento();
                    agendamento.setId(cursor.getInt(0));
                    agendamento.setData(cursor.getString(1));
                    agendamento.setHorario(cursor.getString(2));
                    agendamento.setStatus(cursor.getString(3));
                    agendamento.setIdUsuario(cursor.getString(4));

                    listaAgendamentos.add(agendamento);

                } while (cursor.moveToNext());

            }

        } catch (Exception e) {
            Log.d("Erro lista agendamentos", "Erro ao retornar agendamentos");
            return null;
        } finally {
            if (bancoRead != null) {
                bancoRead.close();
            }
        }

        return listaAgendamentos;

    }


    public List<Agendamento> listAllAgendamentoID(Integer idUsuario) {

        List<Agendamento> agendamentos = new ArrayList<>();
        String id = idUsuario.toString();

        Cursor cursor;


        String query = "SELECT * FROM agendamento WHERE idUsuario = "+ id +";";


        try {

            cursor = bancoRead.rawQuery(query, null);

            if (cursor.moveToFirst()) {

                do {
                    Agendamento agendamento = new Agendamento();
                    agendamento.setId(cursor.getInt(0));
                    agendamento.setData(cursor.getString(1));
                    agendamento.setHorario(cursor.getString(2));
                    agendamento.setStatus(cursor.getString(3));
                    agendamento.setIdUsuario(cursor.getString(4));

                    agendamentos.add(agendamento);

                } while (cursor.moveToNext());

            }

        } catch (Exception e) {
            Log.d("Erro lista agendamentos", "Erro ao retornar agendamentos");
            return null;
        } finally {
            if (bancoRead != null) {
                bancoRead.close();
            }
        }

        return agendamentos;

    }


    public List<Agendamento> listAllAgendamento2() {
        final SQLiteDatabase db = conexao.getReadableDatabase();


        /**
         Aqui você deve colocar todas as colunas que deseja recuperar
         */
        final String[] properties = {"id", "dataAgendamento", "horario", "status", "idUsuario"};


        /**
         * Vamos criar uma query
         * Informamos:
         * 1. Nome da tabela
         * 2. propriedades que vamos recuperar
         * 3. Condições ( WHERE )
         * 4. Parametros das condições (refere se ao ?) Para cada ? deve haver um parametro neste array
         */
        final Cursor cursor = db.query("agendamento", properties, null, null, null, null, null, null);

        List<Agendamento> listaAgendamentos = new ArrayList<>();

        /**
         * Este método retornará false se o cursor estiver vazio.
         */
        try {
            if (cursor.moveToFirst()) {

                do {
                    Agendamento agendamento = new Agendamento();
                    agendamento.setId(cursor.getInt(0));
                    agendamento.setData(cursor.getString(1));
                    agendamento.setHorario(cursor.getString(2));
                    agendamento.setStatus(cursor.getString(3));
                    agendamento.setIdUsuario(cursor.getString(4));

                    listaAgendamentos.add(agendamento);

                } while (cursor.moveToNext());

            } else {
                return null;
            }

        } catch (Exception e) {
            Log.d("Erro lista agendamentos", "Erro ao retornar agendamentos");
            return null;
        }finally {
            if(db != null){
                db.close();
            }

        }

        return listaAgendamentos;

    }

    public boolean excluirAgendamento(Integer idAgendamento){
        bancoWrite = null;


        try{

            bancoWrite = conexao.getWritableDatabase();
            bancoWrite.delete("agendamento","id = ?", new String[]{String.valueOf(idAgendamento)});


        }catch (Exception e){
            Log.d("AGENDAMENTODAO", "não foi possível deletar agendamento");
            return false;
        }finally {
            if(bancoWrite != null){
                bancoWrite.close();
            }
        }

        return true;
    }


    public boolean atualizarAgendamento(Agendamento agendamento){
        bancoWrite = null;

        try{

            bancoWrite = conexao.getWritableDatabase();

            ContentValues agendamentoAtributos = new ContentValues();

            agendamentoAtributos.put("dataAgendamento", agendamento.getData());
            agendamentoAtributos.put("horario", agendamento.getHorario());
            agendamentoAtributos.put("status", agendamento.getStatus());
            agendamentoAtributos.put("idUsuario", agendamento.getIdUsuario());


            int atualizou = bancoWrite.update("agendamento", agendamentoAtributos, "id = ?", new String[]{String.valueOf(agendamento.getId())});

            if(atualizou > 0){
                return true;
            }


        }catch (Exception e){
            Log.d("AGENDAMENTODAO", "não foi possível atualizar agendamento");
            return false;
        }finally {
            if(bancoWrite != null){
                bancoWrite.close();
            }
        }

        return false;
    }


}