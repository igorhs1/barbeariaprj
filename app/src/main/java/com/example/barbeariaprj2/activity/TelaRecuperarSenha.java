package com.example.barbeariaprj2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.barbeariaprj2.R;

public class TelaRecuperarSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_recuperar_senha);
    }

    public void OnClickBtnCancelar(View view) {
        onBackPressed();
    }
}
