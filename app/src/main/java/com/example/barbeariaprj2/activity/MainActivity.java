package com.example.barbeariaprj2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbeariaprj2.DAO.UsuarioDAO;
import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.model.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Usuario usuarioLogado = null;

    private UsuarioDAO usuarioDAO;

    private EditText email, senha;


    private TextView txtCadastro;
    private TextView txtRecuperarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioDAO = new UsuarioDAO(this);

        //inicializando componentes
        email = findViewById(R.id.etEmailLogin);
        senha = findViewById(R.id.etSenhaLogin);


    }


    //CHAMAR TELA HOME
    public void onClickBtnEntrar(View view){

        //recuperando texto dos campos
        String textoEmail = email.getText().toString();
        String textoSenha = senha.getText().toString();

        //List<Usuario> usuarios = usuarioDAO.listAllUsuarios();


        if(!textoEmail.isEmpty()){
            if(!textoSenha.isEmpty()){

                Usuario usuario = usuarioDAO.usuarioAutenticar(textoEmail, textoSenha);

                if(usuario!=null){

                    usuarioLogado = usuario;
                    Intent intent = new Intent(getApplicationContext(), TelaHome.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(this, "Usuário ou senha inválido!", Toast.LENGTH_SHORT).show();
                }


            }else{
                Toast.makeText(this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
        }


    }



    //CHAMAR TELA CADASTRO
    public void onClickTxtCadastro(View view){
        Intent intent = new Intent(getApplicationContext(), TelaCadastro.class);
        startActivity(intent);

    }

    //CHAMAR TELA RECUPERAR SENHA
    public void onClickTxtRecuperarSenha(View view){
        Intent intent = new Intent(getApplicationContext(), TelaRecuperarSenha.class);
        startActivity(intent);
    }





}
