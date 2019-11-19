package com.example.barbeariaprj2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.fragment.AgendarFragment;
import com.example.barbeariaprj2.fragment.FeedFragment;
import com.example.barbeariaprj2.fragment.HorariosFragment;
import com.example.barbeariaprj2.fragment.PerfilFragment;

import android.view.View;
import android.widget.Button;


public class TelaHome extends AppCompatActivity {

    private Button btnHorarios, btnAgendar, btnPerfil, btnFeed;

    private HorariosFragment horariosFragment;
    private AgendarFragment agendarFragment;
    private PerfilFragment perfilFragment;
    private FeedFragment feedFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);

        btnHorarios = findViewById(R.id.btnHorarios);
        btnAgendar = findViewById(R.id.btnAgendar);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnFeed = findViewById(R.id.btnFeed);



        if(MainActivity.usuarioLogado.getId() == 1){

            btnAgendar.setVisibility(View.GONE);

            horariosFragment = new HorariosFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameConteudo, horariosFragment);
            transaction.commit();

        }else {
            agendarFragment = new AgendarFragment();

            //configurar objeto para fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameConteudo, agendarFragment);
            transaction.commit();
        }


        //chama HorariosFragment
        btnHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                horariosFragment = new HorariosFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, horariosFragment);
                transaction.commit();

                btnHorarios.setEnabled(false);
                btnAgendar.setEnabled(true);
                btnPerfil.setEnabled(true);
                btnFeed.setEnabled(true);

            }
        });


        //chama AgendarFragment
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agendarFragment = new AgendarFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, agendarFragment);
                transaction.commit();


                btnHorarios.setEnabled(true);
                btnAgendar.setEnabled(false);
                btnPerfil.setEnabled(true);
                btnFeed.setEnabled(true);

            }
        });


        //chama PerfilFragment
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                perfilFragment = new PerfilFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, perfilFragment);
                transaction.commit();

                btnAgendar.setEnabled(true);
                btnHorarios.setEnabled(true);
                btnPerfil.setEnabled(false);
                btnFeed.setEnabled(true);

            }
        });


        //chama FeedFragment
        btnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                feedFragment = new FeedFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, feedFragment);
                transaction.commit();

                btnAgendar.setEnabled(true);
                btnHorarios.setEnabled(true);
                btnPerfil.setEnabled(true);
                btnFeed.setEnabled(false);
            }
        });







    }






}
