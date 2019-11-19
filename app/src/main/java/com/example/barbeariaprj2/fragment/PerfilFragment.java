package com.example.barbeariaprj2.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barbeariaprj2.DAO.UsuarioDAO;
import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.activity.TelaCadastro;
import com.example.barbeariaprj2.model.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private Context context;

    private Usuario usuario = MainActivity.usuarioLogado;
    private UsuarioDAO usuarioDAO;

    private EditText etNomePerfil, etEmailPerfil, etDataPerfil;
    private Button btnSalvarPerfil, btnEditarPerfil;



    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);

        etNomePerfil = rootView.findViewById(R.id.etNomePerfil);
        etEmailPerfil = rootView.findViewById(R.id.etEmailPerfil);
        etDataPerfil = rootView.findViewById(R.id.etDataPerfil);

        btnEditarPerfil = rootView.findViewById(R.id.btnEditarPerfil);
        btnSalvarPerfil = rootView.findViewById(R.id.btnSalvarPerfil);

        etNomePerfil.setText(MainActivity.usuarioLogado.getNome());
        etEmailPerfil.setText(MainActivity.usuarioLogado.getEmail());
        etDataPerfil.setText(MainActivity.usuarioLogado.getDataNascimento());

        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etNomePerfil.setEnabled(true);
                etDataPerfil.setEnabled(true);

                btnSalvarPerfil.setEnabled(true);
                btnSalvarPerfil.setClickable(true);
                btnSalvarPerfil.setBackgroundColor(Color.parseColor("#ED9301"));


                btnEditarPerfil.setEnabled(false);
                btnEditarPerfil.setClickable(false);
                btnEditarPerfil.setBackgroundColor(Color.parseColor("#808080"));


            }
        });

        btnSalvarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarAtualizarUsuario();

                etNomePerfil.setEnabled(false);
                etDataPerfil.setEnabled(false);

                btnSalvarPerfil.setEnabled(false);
                btnSalvarPerfil.setClickable(false);
                btnSalvarPerfil.setBackgroundColor(Color.parseColor("#808080"));


                btnEditarPerfil.setEnabled(false);
                btnEditarPerfil.setClickable(false);
                btnEditarPerfil.setBackgroundColor(Color.parseColor("#808080"));


            }
        });


        // Inflate the layout for this fragment
        return  rootView;
    }

    public void validarAtualizarUsuario(){

        String textoNome = etNomePerfil.getText().toString();
        String textoDataNascimento = etDataPerfil.getText().toString();

        if(!textoNome.isEmpty()){
            if(!textoDataNascimento.isEmpty()){

                usuario.setNome(textoNome);
                usuario.setDataNascimento(textoDataNascimento);

                try{
                    usuarioDAO.atualizarUsuario(usuario);
                    Toast.makeText(context, "Usuário atualizado com sucesso!", Toast.LENGTH_SHORT).show();

                    MainActivity.usuarioLogado.setNome(usuario.getNome());
                    MainActivity.usuarioLogado.setEmail(usuario.getEmail());

                }catch (Exception e){

                }


            }else{
                Toast.makeText(context, "Preencha a data de nascimento!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "Preencha o nome!", Toast.LENGTH_SHORT).show();
        }

    }


    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }




}
