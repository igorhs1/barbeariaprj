package com.example.barbeariaprj2.fragment;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.barbeariaprj2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


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


        // Inflate the layout for this fragment
        return  rootView;
    }



}
