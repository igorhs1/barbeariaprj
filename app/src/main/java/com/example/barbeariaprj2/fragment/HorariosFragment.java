package com.example.barbeariaprj2.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.adapter.AdapterHorarios;

/**
 * A simple {@link Fragment} subclass.
 */
public class HorariosFragment extends Fragment {


    private Context context;
    private RecyclerView recyclerHorarios;


    public HorariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_horarios, container, false);

        recyclerHorarios = rootView.findViewById(R.id.recyclerHorarios);

        //Configurar adapter
        AdapterHorarios adapterHorarios = new AdapterHorarios();

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerHorarios.setLayoutManager(layoutManager);
        recyclerHorarios.setHasFixedSize(true);
        recyclerHorarios.setAdapter(adapterHorarios);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

}
