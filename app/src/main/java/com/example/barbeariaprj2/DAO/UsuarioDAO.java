package com.example.barbeariaprj2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.bancoSQLite.ConexaoSQLite;
import com.example.barbeariaprj2.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private ConexaoSQLite conexao;
    private SQLiteDatabase banco;

    public UsuarioDAO(Context context){
        conexao = new ConexaoSQLite(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserirUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        values.put("dataNasc", usuario.getDataNascimento());

        return banco.insert("usuario", null, values);

    }

    public void atualizarUsuario(Usuario usuario){
        ContentValues values = new ContentValues();

        values.put("nome", usuario.getNome());
        values.put("dataNasc", usuario.getDataNascimento());

        banco.update("usuario", values, "id = ?", new String[]{usuario.getId().toString()});

    }

    public List<Usuario> listAllUsuarios(){

        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = banco.query("usuario", new String[]{"id", "nome", "email", "senha", "dataNasc"}, null, null, null, null, null);

        while(cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
            usuario.setDataNascimento(cursor.getString(4));


            usuarios.add(usuario);
        }

        return usuarios;

    }


    public Usuario usuarioAutenticar (String email, String senha){
        final SQLiteDatabase db = conexao.getReadableDatabase();


        /**
         Aqui você deve colocar todas as colunas que deseja recuperar
         */
        final String[] properties = {"id", "nome", "email", "senha", "dataNasc"};


        /**
         * Vamos criar uma query
         * Informamos:
         * 1. Nome da tabela
         * 2. propriedades que vamos recuperar
         * 3. Condições ( WHERE )
         * 4. Parametros das condições (refere se ao ?) Para cada ? deve haver um parametro neste array
         */
        final Cursor cursor = db.query("usuario", properties, "email = ? and senha = ?", new String[]{email, senha}, null, null, null, null);

        /**
         * Este método retornará false se o cursor estiver vazio.
         */
        if(cursor.moveToFirst()){
            final Usuario usuario = new Usuario ();
            usuario.setId(Integer.parseInt(cursor.getString(0)));
            usuario.setNome(cursor.getString(1).toString());
            usuario.setEmail(cursor.getString(2).toString());
            usuario.setSenha(cursor.getString(3).toString());
            usuario.setDataNascimento(cursor.getString(4).toString());

            return usuario;
        }else{
            return null;
        }
    }


}
