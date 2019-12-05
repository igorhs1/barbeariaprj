package com.example.barbeariaprj2.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.barbeariaprj2.DAO.AgendamentoDAO;
import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.RecyclerItemClickListener;
import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.adapter.AdapterHorarios;
import com.example.barbeariaprj2.adapter.AdapterListaHorarios;
import com.example.barbeariaprj2.model.Agendamento;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HorariosFragment extends Fragment {


    private Context contextAdapter;
    private AgendamentoDAO agendamentoDAO;

    private RecyclerView recyclerHorarios;
    private ListView listViewHorarios;
    private List<Agendamento> listaAgendamentos;
    private AdapterListaHorarios adapterListaHorarios;

    public HorariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_horarios, container, false);


        agendamentoDAO = new AgendamentoDAO(contextAdapter);

        //Se o usuário logado for ID 1 ele é o administrador
        if(MainActivity.usuarioLogado.getId() == 1){
            listaAgendamentos = agendamentoDAO.listAllAgendamento();
        }else{
            listaAgendamentos = agendamentoDAO.listAllAgendamentoID(MainActivity.usuarioLogado.getId());
        }

        this.listViewHorarios = rootView.findViewById(R.id.listViewHorarios);

        //configurando adapter
        this.adapterListaHorarios = new AdapterListaHorarios(contextAdapter, this.listaAgendamentos);
        this.listViewHorarios.setAdapter(this.adapterListaHorarios);


        listViewHorarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long l) {

                final Agendamento agendamentoSelecionado = (Agendamento) adapterListaHorarios.getItem(posicao);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(contextAdapter, R.style.DialogTheme);

                if(MainActivity.usuarioLogado.getId() == 1){

                    if(((Agendamento) adapterListaHorarios.getItem(posicao)).getStatus().equals("Pendente")){

                        janelaDeEscolha.setTitle("Agendamento: ");
                        janelaDeEscolha.setMessage("Deseja aceitar ou recusar o horário marcado?");

                        janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        janelaDeEscolha.setNegativeButton("Aceitar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                agendamentoSelecionado.setStatus("Aprovado");
                                boolean atualizou = agendamentoDAO.atualizarAgendamento(agendamentoSelecionado);
                                dialogInterface.cancel();


                                if (atualizou) {
                                    adapterListaHorarios.atualizarListViewFeeds(agendamentoDAO.listAllAgendamento());
                                    Toast.makeText(contextAdapter, "Agendamento aprovado!", Toast.LENGTH_SHORT);

                                } else {
                                    Toast.makeText(contextAdapter, "Erro ao aceitar agendamento!", Toast.LENGTH_SHORT);
                                }

                            }
                        });

                        janelaDeEscolha.setPositiveButton("Recusar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                boolean excluiu = agendamentoDAO.excluirAgendamento(agendamentoSelecionado.getId());
                                dialogInterface.cancel();

                                if (excluiu == true) {
                                    adapterListaHorarios.removerHorario(posicao);
                                    Toast.makeText(contextAdapter, "Agendamento recusado!", Toast.LENGTH_SHORT);

                                } else {
                                    Toast.makeText(contextAdapter, "Erro ao recusar agendamento!", Toast.LENGTH_SHORT);
                                }

                            }
                        });

                    }else{ //caso seja status Aprovado

                        janelaDeEscolha.setTitle("Cancelar Horário: ");
                        janelaDeEscolha.setMessage("Deseja cancelar o horário marcado?");

                        janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        janelaDeEscolha.setNegativeButton("Cancelar Horário", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                boolean excluiu = agendamentoDAO.excluirAgendamento(agendamentoSelecionado.getId());
                                dialogInterface.cancel();

                                if(excluiu == true){
                                    adapterListaHorarios.removerHorario(posicao);
                                    Toast.makeText(contextAdapter, "Agendamento cancelado!", Toast.LENGTH_SHORT);

                                }else{
                                    Toast.makeText(contextAdapter, "Erro ao cancelar agendamento!", Toast.LENGTH_SHORT);
                                }


                            }
                        });

                    }

                }else{

                    janelaDeEscolha.setTitle("Cancelar Horário: ");
                    janelaDeEscolha.setMessage("Deseja cancelar o horário marcado?");

                    janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    janelaDeEscolha.setNegativeButton("Cancelar Horário", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            boolean excluiu = agendamentoDAO.excluirAgendamento(agendamentoSelecionado.getId());
                            dialogInterface.cancel();

                            if(excluiu == true){
                                adapterListaHorarios.removerHorario(posicao);
                                Toast.makeText(contextAdapter, "Agendamento cancelado!", Toast.LENGTH_SHORT);

                            }else{
                                Toast.makeText(contextAdapter, "Erro ao cancelar agendamento!", Toast.LENGTH_SHORT);
                            }

                        }
                    });

                }

                janelaDeEscolha.create().show();

            }
        });




        //recyclerHorarios = rootView.findViewById(R.id.recyclerHorarios);

        /*
        //Configurar adapter
        final AdapterHorarios adapterHorarios = new AdapterHorarios(contextAdapter, listaAgendamentos);

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contextAdapter);
        recyclerHorarios.setLayoutManager(layoutManager);
        recyclerHorarios.setHasFixedSize(true);
        recyclerHorarios.setAdapter(adapterHorarios);
        */








        /*
        //evento de click
        recyclerHorarios.addOnItemTouchListener(
            new RecyclerItemClickListener(contextAdapter, recyclerHorarios, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, final int position) {

                    final Agendamento agendamentoSelecionado = listaAgendamentos.get(position);

                    AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(contextAdapter, R.style.DialogTheme);

                    //Se o usuário logado for ID 1 ele é o administrador
                    if(MainActivity.usuarioLogado.getId() == 1) {

                        if(agendamentoSelecionado.getStatus().equals("Pendente")) {

                            janelaDeEscolha.setTitle("Agendamento: ");
                            janelaDeEscolha.setMessage("Deseja aceitar ou recusar o horário marcado?");

                            janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                            janelaDeEscolha.setNegativeButton("Aceitar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {


                                    agendamentoSelecionado.setStatus("Aprovado");
                                    boolean atualizou = agendamentoDAO.atualizarAgendamento(agendamentoSelecionado);
                                    dialogInterface.cancel();


                                    if (atualizou) {
                                        adapterHorarios.atualizarRecycler(agendamentoDAO.listAllAgendamento());
                                        Toast.makeText(contextAdapter, "Agendamento aprovado!", Toast.LENGTH_SHORT);

                                    } else {
                                        Toast.makeText(contextAdapter, "Erro ao aceitar agendamento!", Toast.LENGTH_SHORT);
                                    }

                                }
                            });

                            janelaDeEscolha.setPositiveButton("Recusar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    boolean excluiu = agendamentoDAO.excluirAgendamento(agendamentoSelecionado.getId());
                                    dialogInterface.cancel();

                                    if (excluiu) {
                                        adapterHorarios.removerHorario(position);
                                        Toast.makeText(contextAdapter, "Agendamento recusado!", Toast.LENGTH_SHORT);

                                    } else {
                                        Toast.makeText(contextAdapter, "Erro ao recusar agendamento!", Toast.LENGTH_SHORT);
                                    }

                                }
                            });

                        }else{

                            janelaDeEscolha.setTitle("Cancelar Horário: ");
                            janelaDeEscolha.setMessage("Deseja cancelar o horário marcado?");

                            janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                            janelaDeEscolha.setNegativeButton("Cancelar Horário", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    boolean excluiu = agendamentoDAO.excluirAgendamento(agendamentoSelecionado.getId());
                                    dialogInterface.cancel();

                                    if(excluiu == true){
                                        adapterHorarios.removerHorario(position);
                                        Toast.makeText(contextAdapter, "Agendamento cancelado!", Toast.LENGTH_SHORT);

                                    }else{
                                        Toast.makeText(contextAdapter, "Erro ao cancelar agendamento!", Toast.LENGTH_SHORT);
                                    }


                                }
                            });

                        }

                    }else{
                        janelaDeEscolha.setTitle("Cancelar Horário: ");
                        janelaDeEscolha.setMessage("Deseja cancelar o horário marcado?");

                        janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        janelaDeEscolha.setNegativeButton("Cancelar Horário", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                boolean excluiu = agendamentoDAO.excluirAgendamento(agendamentoSelecionado.getId());
                                dialogInterface.cancel();

                                if(excluiu == true){
                                    adapterHorarios.removerHorario(position);
                                    Toast.makeText(contextAdapter, "Agendamento cancelado!", Toast.LENGTH_SHORT);

                                }else{
                                    Toast.makeText(contextAdapter, "Erro ao cancelar agendamento!", Toast.LENGTH_SHORT);
                                }


                            }
                        });

                    }

                    janelaDeEscolha.create().show();


                }

                @Override
                public void onLongItemClick(View view, int position) {

                }

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            })
        );


        */

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onAttach(Context context) {
        this.contextAdapter = context;
        super.onAttach(context);
    }


}
