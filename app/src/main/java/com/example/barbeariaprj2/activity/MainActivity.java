package com.example.barbeariaprj2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import com.example.barbeariaprj2.R;

public class MainActivity extends AppCompatActivity {

    private TextView txtCadastro;
    private TextView txtRecuperarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    //CHAMAR TELA HOME
    public void onClickBtnEntrar(View view){
        Intent intent = new Intent(getApplicationContext(), TelaHome.class);
        startActivity(intent);


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
